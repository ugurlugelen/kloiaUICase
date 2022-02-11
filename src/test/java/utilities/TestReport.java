package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;


public abstract class TestReport {

    protected static ExtentReports extentReports;
    protected static ExtentTest extentTest;
    protected static ExtentSparkReporter extentSparkReporter;



    @BeforeTest(alwaysRun = true)
    public void setUpTest() {
        String filePath = System.getProperty("user.dir") + "/test-output/kloiaCaseStudyReport.html";
        extentSparkReporter = new ExtentSparkReporter(filePath);
        extentSparkReporter.config().setReportName("Kloia Case Study");
        extentSparkReporter.config().setDocumentTitle("Best Buy Test Results");
        extentSparkReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("QA Tester", "Adem Ugurlugelen");
        extentReports.setSystemInfo("Environmet","PRODUCTION");
        extentReports.setSystemInfo("Browser","CHROME");

    }

    @AfterTest(alwaysRun = true)
    public void tearDownTest() {
        extentReports.flush();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownMethod(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail(result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.skip("Test Case is skipped: " + result.getName());
        }

       Driver.closeDriver();
    }
}


