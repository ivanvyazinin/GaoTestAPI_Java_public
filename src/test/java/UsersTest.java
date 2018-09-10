package test.java;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import main.java.entities.Role;
import main.java.entities.User;
import main.java.steps.CommonSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class UsersTest extends SuperTest{
    private CommonSteps steps;
    private List<Role> roles;

    @BeforeClass
    public void prepareSteps(){
        steps = new CommonSteps();
        roles = steps.getEntites(Role.class, Role.url);
    }

    @Test
    @Story("Create User")
    @Description("Just create User")
    public void createUser() {
        steps.createEntity(new User(roles.get(1).id));

        steps.checkStatusCode(201);
        steps.checkThatJsonContains(1,"data.status");
    }

    @Test
    @Story("Delete User")
    @Description("Just delete User")
    public void deleteUser() {
        User testUser = steps.createEntity(new User(roles.get(1).id));

        steps.deleteEntity(testUser);
        steps.checkStatusCode(204);
        steps.getEntity(testUser);
        steps.checkStatusCode(200);
        steps.checkThatJsonContains(true,"data.deleted");
    }

    @Test
    @Story("Get Users")
    @Description("Just get all Users")
    public void getUsers() {
        steps.getEntites(User.class, User.url);
        steps.checkStatusCode(200);
    }
}
