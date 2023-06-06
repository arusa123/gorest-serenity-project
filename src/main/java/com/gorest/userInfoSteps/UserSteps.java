package com.gorest.userInfoSteps;

import com.gorest.constants.EndPoints;
import com.gorest.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UserSteps {

    @Step("This will create new user ")
    public ValidatableResponse createUserByuserId(String name, String email, String gender, String status,int id) {
        UserPojo userPojo = UserPojo.getUserPojo(name, email, gender, status,id);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer e319da6e2718c70ab799e2b6645523809b0695e38428f3b7ef95bd2fbc36884f")
                .when()
                .body(userPojo)
                .post(EndPoints.CREATE_USER)
                .then();
    }

    @Step("This will get all user by user id {0}")
    public int getUser(int id) {
        return SerenityRest.given()
                .header("Authorization", "Bearer e319da6e2718c70ab799e2b6645523809b0695e38428f3b7ef95bd2fbc36884f")
                .pathParam("id",id)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then().statusCode(200)
                .extract().path("id");
    }
    @Step("Updating Partial User information with id: {0}, name: {1}, email: {2}, gender: {3}, status: {4}")
    public ValidatableResponse updateUser(int id, String email){
        UserPojo userPojo=UserPojo.getUserPojo(email);
        return SerenityRest.given().log().all()
                .header("Connection", "keep-alive")
               .header("Authorization", "Bearer e319da6e2718c70ab799e2b6645523809b0695e38428f3b7ef95bd2fbc36884f")
                .pathParams("id", id)
                .when()
                .body(userPojo)
                .patch(EndPoints.UPDATE_USER_BY_ID)
                .then().log().all();
    }
//    @Step("This will update the user with id {0} , name: {1}, email: {2}, gender: {3}, status: {4}")
//    public ValidatableResponse updateUserWithUserId(int id,String email){
//        UserPojo userPojo = UserPojo.getUserPojo(email);
//        return SerenityRest.given().log().all()
//                .header("Content-Type", "application/json")
//                .header("Authorization", "Bearer e319da6e2718c70ab799e2b6645523809b0695e38428f3b7ef95bd2fbc36884f")
//                .pathParam("id",id)
//                .body(userPojo)
//                .when()
//                .patch(EndPoints.UPDATE_USER_BY_ID)
//        .then();

//    }
    @Step("This will delete the user with id {0}")
    public ValidatableResponse deleteUser(int id){
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer e319da6e2718c70ab799e2b6645523809b0695e38428f3b7ef95bd2fbc36884f")
                .pathParam("id",id)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }
    @Step("This will get all user by user id {0}")
    public ValidatableResponse getsingleUser(int id) {
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer e319da6e2718c70ab799e2b6645523809b0695e38428f3b7ef95bd2fbc36884f")
                .pathParam("id",id)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then();
    }



}
