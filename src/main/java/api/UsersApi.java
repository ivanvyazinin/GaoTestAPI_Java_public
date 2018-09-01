package main.java.api;

import static main.java.properties.Endpoints.API_PREFIX;
import static main.java.properties.Endpoints.ENDPOINT_USERS;

public class UsersApi extends CommonAPI{
    public UsersApi(){
        setURL(API_PREFIX, ENDPOINT_USERS);
    }
}
