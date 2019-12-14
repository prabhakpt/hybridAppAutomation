package app.hybrid.sample.ios;

import io.appium.java_client.MobileBy;
import org.junit.Test;

import app.hybrid.sample.ios.util.AppiumIOSTest;

/**
 * Created by Prabhakar.
 */
public class QualityWorksIOSTest extends AppiumIOSTest {
	
	  //@Test
	    public void checkHomeLabel() throws Exception {
	        home.logintoApplication();
	        home.verifyLoginSuccess();
	    }
   
}
