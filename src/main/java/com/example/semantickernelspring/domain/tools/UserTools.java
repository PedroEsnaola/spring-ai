package com.example.semantickernelspring.domain.tools;

import com.example.semantickernelspring.domain.model.User;
import com.example.semantickernelspring.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class UserTools {

    private final UserService userService;

    @Bean
    @Description("""
            Returns a user by its username or his name
            used in cases like find the user by the username x
            """)
    public Function<FindUserByName, User> findEmailByUsername() {
        return (findUserByName) -> userService.findByUsername(findUserByName.username()).get();
    }

    @Bean
    @Description("""
             Updates the user data
             If only one field should be updated, u must keep sending the information that shouldn't be updated or else the info will be lost
             Receives the user with all the fields, to be updated by the provided user id in the object
             Returns the updated user
             """)
    public Function<User, User> updateUser(){
        return userService::updateUser;
    }

    @Bean
    @Description("Calculate the area of a rectangle from its base and height")
    public Function<RectangleAreaService.Request, RectangleAreaService.Response> rectangeleAreaFunction() {
        return new RectangleAreaService();
    }

    public record FindUserByName(String username){}


}
