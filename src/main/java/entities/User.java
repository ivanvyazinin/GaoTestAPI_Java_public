package main.java.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import static main.java.properties.Endpoints.ENDPOINT_USERS;
import static main.java.utils.Generator.getRandomEmail;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextRandomLength;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends AbstractEntity{
    public String email;
    public String password;
    public String firstName;
    public String lastName;
    public List<String> roles;
    @JsonIgnore
    public static String url = ENDPOINT_USERS;

    public User(String role){
        roles = new ArrayList<>();
        email=getRandomEmail();
        password=getRandomText(48);
        lastName=getRandomTextRandomLength(160);
        firstName=getRandomTextRandomLength(160);
        roles.add(role);
    }
    @Override
    public String getUrl() {
        return this.url;
    }

    public User(){

    }


}


