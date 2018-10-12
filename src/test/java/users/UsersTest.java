package test.java.users;

import io.qameta.allure.*;
import main.java.entities.Role;
import main.java.entities.User;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;

import java.util.List;

import static main.java.utils.Generator.getRandomEmail;

@Epic("Users")
@Feature("Admin works with users")
public class UsersTest extends SuperTest {
    private CommonSteps steps;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
    }

    @Test
    @Story("Admin adds new user to the system")
    @Description("Just create User")
    public void createUser() {
        steps.createEntity(
                new User(context.getRole()));

        steps.checkStatusCode(201);
        steps.checkThatJsonContains(0,"data.status");
    }

    @Test
    @Story("Admin edits user parameters and roles")
    @Description("Edit User")
    public void editUser() {
        User testUser = steps.createEntity(
                new User(context.getRoleByName("ROLE_ADMIN")));

        testUser.firstName = "changed firstName";
        testUser.lastName = "changed latName";
        testUser.email = getRandomEmail("changedEmail");
        testUser.roles.clear();
        testUser.roles.add(context.getRoleByName("ROLE_EDITOR"));

        steps.editEntity(testUser);
        steps.checkStatusCode(200);
        steps.checkThatBodyHasValue("changed firstName");
        steps.checkThatBodyHasValue("changed latName");
        steps.checkThatBodyHasValue("changedEmail");

        steps.checkThatBodyHasValue(context.getRoleByName("ROLE_EDITOR"));
    }

    @Test
    @Story("Admin deletes user from system")
    @Description("Just delete User")
    public void deleteUser() {
        User testUser = steps.createEntity(
                new User(context.getRole()));

        steps.deleteEntity(testUser);
        steps.checkStatusCode(204);
        steps.getEntity(testUser);
        steps.checkStatusCode(200);
        steps.checkThatJsonContains(true,"data.deleted");
    }

    @Test
    @Story("Admin views list of users")
    @Description("Just get all Users")
    public void getUsers() {
        steps.getEntites(User.class, User.url);
        steps.checkStatusCode(200);
    }
}
