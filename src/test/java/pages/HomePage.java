package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    public HomePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css="input#gh-search-input")
    public WebElement searchBox;

    @FindBy(css="button.header-search-button")
    public WebElement searchIcon;

    @FindBy(css="button.c-close-icon.c-modal-close-icon")
    public WebElement signupPopupCloseButton;

    @FindBy(xpath = "//span[text()='Account']")
    public WebElement accountButton;

    @FindBy(xpath = "//span[text()='Saved Items']")
    public WebElement savedItemsButton;

    @FindBy(css = "span.v-p-right-xxs.v-ellipsis")
    public WebElement salulationAfterLogin;

    @FindBy(css = "nav.hamburger-menu")
    public WebElement menuButton;

    @FindBy(css="ul.hamburger-menu-flyout-list li")
    public List<WebElement> subMenuCategories;

    @FindBy(css="ul.hamburger-menu-flyout-list.hamburger-menu-flyout-sidecar-list li")
    public List<WebElement> subCategoryOptions;


    public void switchToNewTab(int tabIndex){
        ArrayList<String> windows = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(windows.get(tabIndex));
    }

    public void openNewBestBuyTab(){
        ((JavascriptExecutor)Driver.getDriver()).executeScript("window.open('https://www.bestbuy.com/')");
    }

    public void selectDesiredCategory(String subMenu, String category, String subCategory){

        for (WebElement w:subMenuCategories) {
            if (w.getText().equals(subMenu)){
                w.click();
                break;
            }
        }

        subMenuCategories.stream().filter(x->x.getText().equals(category)).forEach(x->x.click());

        for (WebElement x: subCategoryOptions) {
            if(x.getText().equals(subCategory)){
                x.click();
                break;
            }
        }
    }

    public void handleWithSignupPopUp(){
        try{
            signupPopupCloseButton.click();
        }catch (NoSuchElementException e){

        }

    }
}
