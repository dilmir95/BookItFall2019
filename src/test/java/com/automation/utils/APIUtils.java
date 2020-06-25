package com.automation.utils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class APIUtils {
        static {
            baseURI = Environment.BASE_URI;
        }
    public static String getToken(String role ){
            String email = null;
            String password = null;

            if(role.contains("teacher")){
                email = Environment.TEACHER_USERNAME;
                password = Environment.TEACHER_PASSWORD;
            }else if(role.contains("leader")){
                email = Environment.LEADER_USERNAME;
                password = Environment.LEADER_PASSWORD;
            }else{
                email = Environment.MEMBER_USERNAME;
                password = Environment.MEMBER_PASSWORD;

            }

        Response response = given().
                queryParam("email",email).
                queryParam("password",password).
                when().get("/sign");

        response.then().log().ifError();

        return  response.jsonPath().getString("accessToken");

    }

    public static String getToken(Object email,Object password){
        Response response = given().
                queryParam("email",email).
                queryParam("password",password).
                when().get("/sign");

        return  response.jsonPath().getString("accessToken");
    }

    public static Response deleteUserById(int id){
        Response response1 = given().auth().oauth2(APIUtils.getToken("teacher")).contentType(ContentType.JSON).
                when().get(Endpoints.DELETE_STUDENT,id).prettyPeek();

        return response1;
    }

    public static Response addBatch(int batchNumber) {
        String token = getToken("teacher");

        Response response = given().
                auth().oauth2(token).
                queryParam("batch-number", batchNumber).
                post(Endpoints.ADD_BATCH);
        response.then().log().ifError();
        return response;
    }

    public static Response addTeam(String teamName, String location, int batchNumber){
        String token = getToken("teacher");
        Response response = given().
                auth().oauth2(token).
                queryParam("team-name", teamName).
                queryParam("campus-location", location).
                queryParam("batch-number", batchNumber).
                when().
                post(Endpoints.ADD_TEAM);
        response.then().log().ifError();
        return response;
    }
//    public static void ensureUserDoesntExist(String email, String password) {
//        int userID = getUserID(email, password);
//        if (userID > 0) {
//            deleteUserByID(userID);
//        }
//    }

}
