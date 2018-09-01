package test.java;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import main.java.api.UserRolesApi;
import main.java.api.UsersApi;
import main.java.entities.Role;
import main.java.entities.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static main.java.properties.Constants.PATH_ID;

public class UsersTest extends SuperTest{
    private UsersApi usersApi;
    private User user;
    private UserRolesApi userRolesApi;
    private List<Role> roles;

    @BeforeClass
    public void prepareSteps(){
        userRolesApi = new UserRolesApi();
        usersApi = new UsersApi();
        roles = userRolesApi.get().jsonPath().getList("data.items",Role.class);
    }

    @Test
    @Story("Create User")
    @Description("Just create User")
    public void createUser() {
        user  = new User(roles.get(1).id);

        newResponse = usersApi.post(user);
        checkStatusCode(201);
        checkThatJsonContains(1,"data.status");
    }

    @Test
    @Story("Delete User")
    @Description("Just delete User")
    public void deleteUser() {
        user  = new User(roles.get(1).id);
        newResponse = usersApi.post(user);
        user.id = newResponse.jsonPath().getString(PATH_ID);

        newResponse = usersApi.delete(user.id);
        checkStatusCode(204);
        newResponse = usersApi.getById(user.id);
        checkStatusCode(200);
        checkThatJsonContains(0,"data.status");
    }

    @Test
    @Story("Get Users")
    @Description("Just get all User")
    public void getUsers() {
        newResponse = usersApi.get();
        checkStatusCode(200);
    }

}
