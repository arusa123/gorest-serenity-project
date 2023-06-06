package com.gorest.userinfo;

import com.gorest.testbase.TestBase;
import com.gorest.userInfoSteps.UserSteps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

@Concurrent(threads = "4x")
@UseTestDataFrom("src/test/java/resources/testdata/userinfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class UserDataDriven extends TestBase {
    private String name;
    private String email;
    private String gender;
    private String status;
    private int id;
    @Steps
    UserSteps userSteps;
    @Title("Data Driven Test for adding multiple users to the application")
    @Test
    public void createMultipleUsers(){
        userSteps.createUserByuserId(name,email,gender,status,id).statusCode(201);
    }
}

