package main.java.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

import static main.java.utils.Generator.getRandomEmail;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextRandomLength;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    public String email;
    public String password;
    public String firstName;
    public String lastName;
    public List<String> roles;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String id;

    public User(String role){
        roles = new ArrayList<>();
        email=getRandomEmail();
        password=getRandomText(48);
        lastName=getRandomTextRandomLength(160);
        firstName=getRandomTextRandomLength(160);
        roles.add(role);
    }

}


