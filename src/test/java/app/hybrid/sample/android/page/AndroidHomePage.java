package app.hybrid.sample.android.page;

import static app.hybrid.sample.Helpers.*;

import java.io.IOException;

/**
 * Created by Javon Davis on 7/26/17.
 */
public class AndroidHomePage {

    /** Verify the home page has loaded **/
    public void logintoApplication() {
    	
        /*MobileElement headingLabel = element(MobileBy.AccessibilityId("Heading"));
        String labelText = headingLabel.getAttribute("text");
        String labelTextCorrect = "Quality Works Appium Android Sample";
        Assert.assertTrue(labelText + " should be " + labelTextCorrect, Objects.equals(labelText, labelTextCorrect));*/
    	//waitForElementType("id","com.experitest.ExperiBank:id/usernameTextField");
    	//waitForElementType("id","com.experitest.ExperiBank:id/usernameTextField");
    	inputByXPath("id","com.experitest.ExperiBank:id/usernameTextField","company");
    	waitForSeconds(2);
    	inputByXPath("id","com.experitest.ExperiBank:id/passwordTextField","company");
    	waitForSeconds(2);
    	clickByElement("id", "com.experitest.ExperiBank:id/loginButton");
    }
    
    public void verifyLoginSuccess() throws IOException {
    	//waitForElementType("id", "com.experitest.ExperiBank:id/makePaymentButton");
    	waitForSeconds(5);
    	captureScreenShots();
    	waitForSeconds(2);
    	clickByElement("id", "com.experitest.ExperiBank:id/logoutButton");
    }
}
