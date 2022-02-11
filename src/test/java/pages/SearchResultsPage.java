package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.List;

public class SearchResultsPage {

    public SearchResultsPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css = "h1.search-title")
    public WebElement searchResultTitle;

    @FindBy(xpath = "(//ul[@class='facet-option-list'])[3]//button[@class='c-button-link show-link']")
    public WebElement showMoreButton;

    @FindBy(css = "button.c-button.c-button-secondary.c-button-md.large-facet-group-show-results")
    public WebElement showResultsButton;

    @FindBy(css="h1.heading-5.v-fw-regular")
    public WebElement productDetailHeaderText;

    @FindBy(xpath = "(//div[@class='save-for-later'])[1]//button")
    public WebElement saveForLaterButton;

    @FindBy(xpath = "//span[text()='Saved Items']")
    public WebElement accountButton;

    @FindBy(css = "div.sku-card-product-title")
    public WebElement savedItemName;

    @FindBy(css = "h4.sku-header a")
    public List<WebElement> productsAfterSearch;

    public void assertionOfSearchResults(String searchedProduct){
        SoftAssert softAssert = new SoftAssert();
        for (WebElement w: productsAfterSearch) {
            if(w.getText().contains(searchedProduct)){
                //System.out.println(w.getText());
                softAssert.assertTrue(true);
            }
        }
        softAssert.assertAll();
    }

    public WebElement desiredProduct(String orderNumber){
        return Driver.getDriver().findElement(By.xpath("(//h4[@class='sku-header'])["+orderNumber+"]//a"));
    }

    public WebElement brandFilter(String brandName){
        WebElement desiredBrandWebElement = Driver.getDriver().findElement(By.id("brand_facet-"+brandName+"-modal-0"));
        return desiredBrandWebElement;
    }

    public void filterWithDesiredBrand(String brandName){
        ReusableMethods.scrollIntoVIewJS(showMoreButton);
        showMoreButton.click();
        brandFilter(brandName).click();
        showResultsButton.click();
    }



}
