package com.automation.pages;

import io.cucumber.java.de.Wenn;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class SelfPage extends BasePage {


    public String getUserInfo(String value){
        String xpath = "//p[text()='"+value+"']/preceding-sibling::p";
        return xpath;
    }

    @FindBy(xpath = "//p[text()='name']/preceding-sibling::p")
    private WebElement name;

    @FindBy(xpath = "//p[text()='role']/preceding-sibling::p")
    private WebElement role;

    @FindBy(xpath = "//p[text()='campus']/preceding-sibling::p")
    private WebElement campus;




    public List<String> getUserList(){
        List<String> tests = new ArrayList<>();
        tests.add(name.getText());
        tests.add(role.getText());
        tests.add(campus.getText());


        return tests;
    }






}
