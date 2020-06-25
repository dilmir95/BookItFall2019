package com.automation.pages;

import com.automation.utils.BrowserUtilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(name = "email")
    private WebElement email;

    @FindBy (name = "password")
    private WebElement password;

    public void login(String emailText, String passwordText){
        BrowserUtilities.waitForPageToLoad(3);
        email.sendKeys(emailText);
        password.sendKeys(passwordText, Keys.ENTER);
    }
}
