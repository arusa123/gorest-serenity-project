package com.gorest.userinfo;

import com.gorest.testbase.TestBase;
import com.gorest.userInfoSteps.UserSteps;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)

public class UserCRUDTest extends TestBase {
    @Steps
    UserSteps userSteps;
    static String name = TestUtils.getRandomValue();
    static String email = TestUtils.getRandomValue() + "lily@gmail.com";
    static String status = "active";
    static String gender = "Female";
    static int id;

    @Test
    public void test001() {

        ValidatableResponse response = userSteps.createUserByuserId(name, email, gender, status,id);
        response.log().all().statusCode(201);
        id = response.extract().path("id");
    }

    @Test
    public void test002() {
      int response =  userSteps.getUser(id);
        Assert.assertEquals(response,id);

    }

    @Title("Update the student information and verify the updated information")
    @Test
    public void test003(){
       // email="Milan"+TestUtils.getRandomValue()+"@gmail.com";
        userSteps.updateUser(id,email).statusCode(200);
    }

    @Test
    public void test004() {
        userSteps.deleteUser(id).statusCode(204);
        userSteps.getsingleUser(id).statusCode(404);

    }


}
