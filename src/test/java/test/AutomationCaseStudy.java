package test;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.AllPages;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestReport;

public class AutomationCaseStudy extends TestReport {
    AllPages allPages;

    @BeforeMethod
    void setUp() {
        allPages = new AllPages();
        Driver.getDriver().get(ConfigReader.getProperty("bestBuyProductionUrl"));
        allPages.landingPage().selectUSA.click();
        allPages.homePage().handleWithSignupPopUp();
    }


    @Test(priority = 1)
    void caseStudyOne() {
        extentTest = extentReports.createTest("caseStudyOne");
        extentTest.info("User is navigated to https://www.bestbuy.com");
        try {
            Assert.assertEquals(Driver.getDriver().getTitle(), ConfigReader.getProperty("pageTitle"));
            extentTest.pass("Page title verification is correct");
        } catch (AssertionError e) {
            extentTest.fail("Page title verification is failed");
            Assert.fail();
        }
        extentTest.info("User is searching for Drone");
        allPages.homePage().searchBox.sendKeys("Drone");
        allPages.homePage().searchIcon.click();
        extentTest.info("Search Results are listed");
        try {
            allPages.searchResultsPage().assertionOfSearchResults("Drone");
            extentTest.pass("Search Results are compatiple for Drone");
        } catch (Exception e) {
            extentTest.fail("Search Results are not compatible for Drone");
            Assert.fail();
        }
    }

    @Test(priority = 2, dataProvider = "loginCredentials")
    void caseStudyTwo(String email, String password) {
        extentTest = extentReports.createTest("caseStudyTwo");
        extentTest.info("User is navigated to https://www.bestbuy.com");
        try {
            allPages.loginPage().loginWithEmail(email, password);
            extentTest.info("User is logging with email");
        } catch (Exception e) {
            extentTest.fail("There is a problem about login process");
            Assert.fail();
        }
        if (allPages.homePage().salulationAfterLogin.isDisplayed()) {
            extentTest.pass("User is logged in sucessfully");
        } else {
            extentTest.fail("Login with email is failed");
            Assert.fail();
        }

    }

    @Test(priority = 3, dataProvider = "loginCredentials")
    void casaStudyThree(String email, String password) {
        extentTest = extentReports.createTest("caseStudyThree");
        extentTest.info("User is navigated to https://www.bestbuy.com");
        try {
            allPages.loginPage().loginWithEmail(email, password);
            extentTest.info("User is logging with email");
        } catch (Exception e) {
            extentTest.fail("There is a problem about login process");
            Assert.fail();
        }
        if (allPages.homePage().salulationAfterLogin.isDisplayed()) {
            extentTest.pass("User is logged in sucessfully");
        } else {
            extentTest.fail("Login with email is failed");
            Assert.fail();
        }
        extentTest.info("User opens a new tab");
        allPages.homePage().openNewBestBuyTab();
        extentTest.info("User verifies user is already logged in new tab");
        allPages.homePage().switchToNewTab(1);
        if (allPages.homePage().salulationAfterLogin.isDisplayed()) {
            extentTest.pass("User is already logged in new tab");
        } else {
            extentTest.fail("User is not logged in new tab");
            Assert.fail();
        }

    }

    @Test(priority = 4)
    void caseStudyFour() {
        extentTest = extentReports.createTest("caseStudyFour");
        extentTest.info("User is navigated to https://www.bestbuy.com");
        allPages.homePage().menuButton.click();
        extentTest.info("User is opened Hamburger Menu");
        allPages.homePage().selectDesiredCategory("Audio", "Headphones", "Wireless Headphones");
        extentTest.info("User is selected sub-category");
        allPages.searchResultsPage().filterWithDesiredBrand("Philips");
        extentTest.info("User is filtered for brand");
        ReusableMethods.waitFor(4);
        ReusableMethods.waitForVisibility(allPages.searchResultsPage().desiredProduct("2"), 20);
        allPages.searchResultsPage().desiredProduct("2").click();
        extentTest.info("User is clicked a product from search results");
        String expectedTitle = allPages.searchResultsPage().productDetailHeaderText.getText();
        allPages.searchResultsPage().saveForLaterButton.click();
        extentTest.info("User is clicked saved for later button");
        allPages.homePage().savedItemsButton.click();
        extentTest.info("User is opened saved for later items list");
        try{
            Assert.assertTrue(allPages.searchResultsPage().savedItemName.getText().equals(expectedTitle));
            extentTest.pass("Saved Product name is same as at saved items list");
        }catch (AssertionError e){
            extentTest.fail("Saved Product name is different at saved items list");
            Assert.fail();
        }

    }

    @DataProvider()
    public Object[][] loginCredentials() {
        String[][] emailPassword = {{"augurlugel@yahoo.com", "KloiaCase001"}};
        return emailPassword;
    }

}
