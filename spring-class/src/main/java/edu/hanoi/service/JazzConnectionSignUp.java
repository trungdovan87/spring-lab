package edu.hanoi.service;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;

/**
 * Created by trungdovan on 12/1/16.
 */
public class JazzConnectionSignUp implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        //UserProfile userProfile = connection.fetchUserProfile();
        Facebook facebook = (Facebook) connection.getApi();
        String [] fields = { "id", "email",  "first_name", "last_name" };
        User user = facebook.fetchObject("me", User.class, fields);
        return user.getEmail();
    }
}
