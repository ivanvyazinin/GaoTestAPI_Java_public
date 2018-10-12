package test.java.users;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.Role;
import main.java.entities.User;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;

import java.util.List;

import static main.java.utils.Generator.getRandomEmail;
import static main.java.utils.Generator.getRandomTextField;

@Epic("Users")
@Feature("Admin works with users")
@Story("Admin views list of users")
public class UsersSortingTest extends SuperTest {
    private CommonSteps steps;
    private String lastName = getRandomTextField("!aSortTest");
    private String firstName = getRandomTextField("!aSortTest");
    private String role;
    private String email= "!" + getRandomEmail("SortTest");
    private User deletedUser;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
        role = context.getRoleByName("ROLE_EDITOR");

        for (int i=0;i<3;i++){
            steps.createEntity(
                    new User(context.getRoleByName("ROLE_MODERATOR")));
        }

        User testUser = new User(role);
        testUser.firstName = firstName;
        testUser.lastName = lastName;
        testUser.email = email;
        steps.createEntity(testUser);

        for (int i=0;i<3;i++){
            deletedUser = steps.createEntity(
                    new User(context.getRoleByName("ROLE_ADMIN")));
        }
        steps.deleteEntity(deletedUser);
    }

    @Test
    public void checkPaginationUsers() {
        steps.api.setRequestParameters(new String[][] {
                {"page", "1"},
                {"per_page", "3"}
        });

        steps.getEntites(User.class,User.url);
        steps.checkStatusCode(200);
        steps.checkItemsNumberInResponse(3);
    }

    @Test
    public void sortUsersByStatus() {
        steps.api.setRequestParameters(new String[][] {
                {"sorting", "status"},
                {"order", "desc"}
        });

        steps.getEntites(User.class,User.url);
        steps.checkStatusCode(200);
        steps.checkThatJsonContains(2,"data.items[0].status");
    }

    @Test
    public void sortUsersByLastName() {
        steps.api.setRequestParameters(new String[][] {
                {"sorting", "lastName"},
                {"order", "asc"}
        });

        steps.getEntites(User.class,User.url);
        steps.checkStatusCode(200);
        steps.checkThatJsonContains(lastName,"data.items[0].lastName");
    }

    @Test
    public void sortUsersByFirstName() {
        steps.api.setRequestParameters(new String[][] {
                {"sorting", "firstName"},
                {"order", "asc"}
        });

        steps.getEntites(User.class,User.url);
        steps.checkStatusCode(200);
        steps.checkThatJsonContains(firstName,"data.items[0].firstName");
    }

    @Test
    public void sortUsersByEmail() {
        steps.api.setRequestParameters(new String[][] {
                {"sorting", "email"},
                {"order", "asc"}
        });

        steps.getEntites(User.class,User.url);
        steps.checkStatusCode(200);
        steps.checkThatJsonContains(email,"data.items[0].email");
    }

    @Test
    public void sortUsersByRole() {
        steps.api.setRequestParameters(new String[][] {
                {"sorting", "roles.role"},
                {"order", "asc"}
        });

        steps.getEntites(User.class,User.url);
        steps.checkStatusCode(200);
    }

    @Test
    public void sortUsersByCreatedAt() {
        steps.api.setRequestParameters(new String[][] {
                {"sorting", "createdAt"},
                {"order", "asc"}
        });

        steps.getEntites(User.class,User.url);
        steps.checkStatusCode(200);
    }

}
