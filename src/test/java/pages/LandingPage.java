package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LandingPage {

    public LandingPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//h4[text()='United States']")
    public WebElement selectUSA;


    public void selectCountry(String countryName){
        Driver.getDriver().findElement(By.xpath("//h4[text()='"+countryName+"']")).click();
    }


}
