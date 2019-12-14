package app.hybrid.sample;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.FileDetector;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.annotations.Since;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

/**
 * Created by Prabhakar.
 */
public abstract class Helpers {

    private static AppiumDriver<WebElement> driver;
    private static WebDriverWait driverWait;

    /**
     * Initialize the webdriver. Must be called before using any helper methods. *
     */
    public static void init(AppiumDriver<WebElement> webDriver) {
        driver = webDriver;
        int timeoutInSeconds = 60;
        // must wait at least 60 seconds for running on Sauce.
        // waiting for 30 seconds works locally however it fails on Sauce.
        driverWait = new WebDriverWait(webDriver, timeoutInSeconds);
    }

    /**
     * Wrap WebElement in MobileElement *
     */
    private static MobileElement w(WebElement element) {
    	return (MobileElement) driver.findElement((By) element);
        /*return new MobileElement((RemoteWebElement) element, driver);*/
    }

    /**
     * Wrap WebElement in MobileElement *
     */
    private static List<MobileElement> w(List<WebElement> elements) {
        List<MobileElement> list = new ArrayList<MobileElement>(elements.size());
        for (WebElement element : elements) {
            list.add(w(element));
        }

        return list;
    }

    /**
     * Return an element by locator *
     */
    public static MobileElement element(By locator) {
    	System.out.println("By value is:"+locator);
    	System.out.println("Driver is:"+driver);
        return w(driver.findElement(locator));
    }

    /**
     * Return a list of elements by locator *
     */
    public static List<MobileElement> elements(By locator) {
        return w(driver.findElements(locator));
    }

    /**
     * Press the back button *
     */
    public static void back() {
        driver.navigate().back();
    }

    /**
     * Return a list of elements by tag name *
     */
    public static List<MobileElement> tags(String tagName) {
        return elements(for_tags(tagName));
    }

    /**
     * Return a tag name locator *
     */
    public static By for_tags(String tagName) {
        return By.className(tagName);
    }

    /**
     * Return a static text element by xpath index *
     */
    public static MobileElement text(int xpathIndex) {
        return element(for_text(xpathIndex));
    }

    /**
     * Return a static text locator by xpath index *
     */
    public static By for_text(int xpathIndex) {
        return By.xpath("//UIAStaticText[" + xpathIndex + "]");
    }

    /**
     * Return a static text element that contains text *
     */
    public static MobileElement text(String text) {
        return element(for_text(text));
    }

    /**
     * Return a static text locator that contains text *
     */
    public static By for_text(String text) {
        String up = text.toUpperCase();
        String down = text.toLowerCase();
        return By.xpath("//UIAStaticText[@visible=\"true\" and (contains(translate(@name,\"" + up
                + "\",\"" + down + "\"), \"" + down + "\") or contains(translate(@hint,\"" + up
                + "\",\"" + down + "\"), \"" + down + "\") or contains(translate(@label,\"" + up
                + "\",\"" + down + "\"), \"" + down + "\") or contains(translate(@value,\"" + up
                + "\",\"" + down + "\"), \"" + down + "\"))]");
    }

    /**
     * Return a static text element by exact text *
     */
    public static MobileElement text_exact(String text) {
        return element(for_text_exact(text));
    }

    /**
     * Return a static text locator by exact text *
     */
    public static By for_text_exact(String text) {
        return By.xpath("//UIAStaticText[@visible=\"true\" and (@name=\"" + text
                + "\" or @hint=\"" + text + "\" or @label=\"" + text
                + "\" or @value=\"" + text + "\")]");
    }

    /**
     * Wait 30 seconds for locator to find an element *
     */
    public static MobileElement wait(By locator) {
        return w(driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator)));
    }

    /**
     * Wait 60 seconds for locator to find all elements *
     */
    public static List<MobileElement> waitAll(By locator) {
        return w(driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)));
    }

    public static FileDetector fileDetector() {
        return new FileDetector() {
            public File getLocalFile(CharSequence... keys) {
                return null;
            }
        };
    }
    
    public static By getByLocator(String identifier, String locator) {
    	MobileBy by=null;
    	
    	switch(identifier) {
    	case "id": 	by = (MobileBy) MobileBy.AccessibilityId(locator);
    				break;
    	case "class": 	by = (MobileBy) MobileBy.className(locator);
    					break;
    	}
    	
    	return by;
    }
    
    public static  MobileElement getMobileElement(String identifier, String locator) {
    	MobileElement mbElement = null;
    	switch(identifier) {
    	case "id": mbElement =(MobileElement) driver.findElementById(locator);
    		break;
    	case "class": mbElement = (MobileElement) driver.findElementByClassName(locator);
		break;
    	case "content": mbElement = (MobileElement) driver.findElementsByAccessibilityId(locator); // content desc value
		break;
    	}
    	return mbElement;
    }
    
    public static void inputByXPath(String identifier, String locator, String data) {
    	//isElementPresent(identifier, locator)..sendKeys(data);
    	getMobileElement(identifier, locator).sendKeys(data);
    }
    
    public static void clickByElement(String identifier, String locator) {
    	getMobileElement(identifier, locator).click();;
    }
    
    public static void waitForElementType(String identifier, String locator) {
    	Assert.assertTrue(isElementPresent(identifier,locator).isEnabled());
    }
    
    public static void captureScreenShots() throws IOException {
        String folder_name="screenshot";
        File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //Date format fot screenshot file name
        SimpleDateFormat df=new  SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
        //create dir with given folder name
        new File(folder_name).mkdir();
        //Setting file name
        String file_name=df.format(new Date())+".png";
        //coppy screenshot file into screenshot folder.
        FileUtils.copyFile(f, new File(folder_name + "/" + file_name));
    }
    
    public static void waitForSeconds(int seconds) {
    	try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    public static WebElement isElementPresent(String identifier, String locator) {
    	return driverWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(identifier,locator)));
    }
    
    
}