package app.hybrid.sample.android.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import app.hybrid.sample.Helpers;
import app.hybrid.sample.android.page.AndroidHomePage;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Javon Davis on 7/26/17.
 */
public class AppiumAndroidTest {

    private AppiumDriver<WebElement> driver;

    /**
     * Page object references.*
     */
    protected AndroidHomePage home;

    /**
     * wait wraps Helpers.wait *
     */
    public static WebElement wait(By locator) {
        return Helpers.wait(locator);
    }

    /**
     * Keep the same date prefix to identify job sets. *
     */
    private static final Date date = new Date();


    /**
     * Run before each test *
     */
    @Before
    public void setUp() throws Exception {
        home = new AndroidHomePage();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium-version", "6.0.1");
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("deviceName", "Trelleborg S5");
        capabilities.setCapability("appPackage", "com.experitest.ExperiBank");
        capabilities.setCapability("appActivity", "com.experitest.ExperiBank.LoginActivity");

        // Set job name
        capabilities.setCapability("name", "Quality Works Android Sample" + date);
        File resourcesDirectory = new File("src/test/resources");
		 String appPath  = resourcesDirectory.getAbsolutePath()+("\\apps\\EriBank.apk");
        //String appPath = System.getenv("/src/test/resources/apps/LocalSample.apk");
        assert appPath != null: "Path to Android app is not set";
        System.out.println("Android App path: "+ appPath);
        capabilities.setCapability("app", appPath);
        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Helpers.init(driver);
    }

    /**
     * Run after each test *
     */
    @After
    public void tearDown() throws Exception {
        if (driver != null) driver.quit();
    }

}