package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.ArrayList;

public class LoginPage {
    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    AllPages allPages = new AllPages();

    @FindBy(css="a.c-button.c-button-secondary.c-button-sm.sign-in-btn")
    public WebElement signinButton;

    @FindBy(xpath = "(//div[@class='cia-signin__social--actions-container'])[2]")
    public WebElement googleSingInButton;

    @FindBy(css = "#identifierId")
    public WebElement googleEmailTextBox;

    @FindBy(css = "#headingText")
    public WebElement loginFailedText;

    @FindBy(css = "input#fld-e")
    public WebElement emailAddressTextBox;

    @FindBy(css = "input#fld-p1")
    public WebElement passwordTextBox;


    public void loginWithEmail(String email, String password){
        allPages.homePage().accountButton.click();
        signinButton.click();
        String chord = Keys.chord(email,Keys.TAB,Keys.TAB, password, Keys.ENTER);
        emailAddressTextBox.sendKeys(chord);
    }
}
