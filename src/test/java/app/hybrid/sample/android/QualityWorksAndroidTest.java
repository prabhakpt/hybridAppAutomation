package app.hybrid.sample.android;

import org.junit.Test;

import app.hybrid.sample.android.util.AppiumAndroidTest;

/**
 * Created by Javon Davis on 7/26/17.
 */
public class QualityWorksAndroidTest extends AppiumAndroidTest {

    @Test
    public void checkHomeLabel() throws Exception {
        home.logintoApplication();
        home.verifyLoginSuccess();
    }
}
