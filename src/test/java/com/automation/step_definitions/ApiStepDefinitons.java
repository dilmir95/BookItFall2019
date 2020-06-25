package com.automation.step_definitions;

import com.automation.pojos.Room;
import com.automation.utils.APIUtils;
import com.automation.utils.Endpoints;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class ApiStepDefinitons {
    Response response;
    String token;
    JsonPath jsonPath;
    RequestSpecification requestSpecification; // is to access given() , when(), then();
    ContentType contentType;




    @Given("authorization token is provided for {string}")
    public void authorization_token_is_provided_for(String role) {
        token = APIUtils.getToken(role);
    }

    @Given("user accepts content type as {string}")
    public void user_accepts_content_type_as(String type) {
        if(type.toLowerCase().contains("json")){
            contentType = ContentType.JSON;
        }else if(type.toLowerCase().contains("xml")){
            contentType = ContentType.XML;
        }
    }

    @When("user sends GET request to {string}")
    public void user_sends_GET_request_to(String path) {
        response = given().accept(ContentType.JSON).auth().oauth2(token).when().get(path).prettyPeek();

    }

    @Then("user should be able to see {int} rooms")
    public void user_should_be_able_to_see_rooms(int numberOfRooms) {
       List<Room> rooms = response.jsonPath().getList("", Room.class);
        Assert.assertEquals(numberOfRooms,rooms.size());
    }

    @Then("user verifies that response status code is {int}")
    public void user_verifies_that_response_status_code_is(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("user should be able to see all room names")
    public void user_should_be_able_to_see_all_room_names() {
        List<String> roomNames = new ArrayList<>();
        List<Room> rooms = response.jsonPath().getList("", Room.class);

        for(Room each: rooms){
            roomNames.add(each.getName());
        }
        System.out.println(roomNames);
      //  rooms.forEach(room -> roomNames.add(room.getName()));
    }


    @Then("user payload contains following room names:")
    public void user_payload_contains_following_room_names(List<String> data) {
        List<Room> rooms = response.jsonPath().getList("", Room.class);
        List<String> roomNames = new ArrayList<>();
        rooms.forEach(room -> roomNames.add(room.getName()));
        Assert.assertTrue(roomNames.containsAll(data));

    }

    @When("user sends POST request to {string} with following information:")
    public void user_sends_POST_request_to_with_following_information(String path, List<Map<String,?>> data) {

        for(Map<String,?> each: data){
            response = given().contentType(contentType).auth().oauth2(token).queryParams(each).
                    when().post(path).prettyPeek();
        }
    }

    @Then("user deletes previously added students")
    public void user_deletes_previously_added_students(List<Map<String,Object>> dataTable) {
       Response response = given().auth().oauth2(APIUtils.getToken(dataTable.get(0).get("email"),dataTable.get(0).get("password"))).
               contentType(contentType).
                when().get("/api/users/me").prettyPeek();


        int id = response.jsonPath().getInt("id");

        APIUtils.deleteUserById(id).then().statusCode(200);

    }




}
