package main.java.api;

import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_USER_ROLES;

public class UserRolesApi extends CommonAPI{
    public UserRolesApi(){
        setURL(API_PREFIX, ENDPOINT_USER_ROLES);
    }
}
