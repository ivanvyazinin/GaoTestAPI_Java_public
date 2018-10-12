package test.java.users;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.entities.User;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.utils.Generator.getRandomEmail;
import static main.java.utils.Generator.getRandomTextField;

@Epic("Users")
@Feature("Admin works with users")
@Story("Admin views list of users")
public class UsersSearchTest extends SuperTest {
    private CommonSteps steps;
    private String userLastName = getRandomTextField("Last name search test");
    private String userFirstName = getRandomTextField("First name search test");
    private String userRole;
    private String userEmail= getRandomEmail("EmailSearchTest");

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
        userRole = context.getRoleByName("ROLE_EDITOR");

        for (int i=0;i<5;i++){
            steps.createEntity(
                    new User(context.getRoleByName("ROLE_ADMIN")));
        }

        User testUser = new User(userRole);
        testUser.firstName = userFirstName;
        testUser.lastName = userLastName;
        testUser.email = userEmail;
        steps.createEntity(testUser);
    }

    @Test
    public void searchUserByLastName() {
        steps.api.setRequestParameters(new String[][] {
                {"search", userLastName}
        });

        steps.getEntites(User.class,User.url);
        steps.checkThatJsonContains(1,"data.count");
        steps.checkThatBodyHasValue(userLastName);

    }

    @Test
    public void searchUserByFirstName() {
        steps.api.setRequestParameters(new String[][] {
                {"search", userFirstName}
        });

        steps.getEntites(User.class,User.url);
        steps.checkThatJsonContains(1,"data.count");
        steps.checkThatBodyHasValue(userFirstName);

    }

    @Test
    public void searchUserByEmail() {
        steps.api.setRequestParameters(new String[][] {
                {"search", userEmail}
        });

        steps.getEntites(User.class,User.url);
        steps.checkThatJsonContains(1,"data.count");
        steps.checkThatBodyHasValue(userEmail);

    }

    @Test
    public void filterUserByRole() {
        steps.api.setRequestParameters(new String[][] {
                {"role", userRole}
        });

        steps.getEntites(User.class,User.url);
        steps.checkThatBodyHasValue(userRole);
        steps.checkThatBodyHasNotValue(context.getRoleByName("ROLE_ADMIN"));
    }

}
