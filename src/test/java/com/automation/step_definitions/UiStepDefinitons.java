package com.automation.step_definitions;

import com.automation.database.UserDB;
import com.automation.pages.LoginPage;
import com.automation.pages.SelfPage;
import com.automation.utils.ConfigurationReader;
import com.automation.utils.Driver;
import com.automation.utils.Environment;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.hamcrest.Matchers;


import java.util.List;
import java.util.Map;

public class UiStepDefinitons {
    LoginPage loginPage = new LoginPage();
    SelfPage selfPage = new SelfPage();
    UserDB userDB = new UserDB();


    @Then("user is on landing page")
    public void user_is_on_landing_page() {
        Driver.getDriver().get(Environment.URL);
    }

    @When("user logs in with username {string} and {string} password")
    public void user_logs_in_with_username_and_password(String user, String pass) {
        loginPage.login(user,pass);
    }

    @When("navigates to personal page")
    public void navigates_to_personal_page() {
       loginPage.goToSelfPage();
    }

    @Then("user verifies that information displays correctly")
    public void user_verifies_that_information_displays_correctly(List<Map<String,String>> dataTable) {
        Assert.assertEquals(dataTable.get(0).get("name"),selfPage.getUserList().get(0));
        Assert.assertEquals(dataTable.get(0).get("role"),selfPage.getUserList().get(1));
        Assert.assertEquals(dataTable.get(0).get("campus"),selfPage.getUserList().get(2));

        System.out.println("##################### database validations");
        Assert.assertTrue(userDB.checkUserExistance(dataTable.get(0).get("email")));




    }

}
