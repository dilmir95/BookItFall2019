package com.automation.pages;

import com.automation.utils.BrowserUtilities;
import com.automation.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    public BasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy (linkText = "my")
    protected WebElement my;

    @FindBy (linkText = "self")
    protected WebElement self;

    protected WebDriverWait wait = new WebDriverWait(Driver.getDriver(),20);
    protected Actions actions = new Actions(Driver.getDriver());


    public void goToSelfPage(){
        wait.until(ExpectedConditions.elementToBeClickable(my)).click();
        actions.moveToElement(my).pause(1000).click(self).build().perform();
        BrowserUtilities.wait(4);
        //wait.until(ExpectedConditions.elementToBeClickable(self)).click();


    }
}
