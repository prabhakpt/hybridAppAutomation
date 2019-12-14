package app.hybrid.sample.ios.page;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.junit.Assert;

import app.hybrid.sample.Helpers;

import static app.hybrid.sample.Helpers.back;
import static app.hybrid.sample.Helpers.element;
import static app.hybrid.sample.Helpers.fileDetector;

import java.util.Objects;

/**
 * Page Object for the Work Location Page
 * Created by Prabhakar.
 */
public class IOSWorkLocationPage {

    private String heyThereAccessibilityIdentifier = "Hey There";
    private String questionLabelAccessibilityIdentifier = "Where do you work";
    private String workplaceAccessibilityIdentifier = "Workplace";
    private String submitAccessibilityIdentifier = "Submit";
    private String feedbackAccessibilityIdentifier = "Feedback";

    public void enterWorkLocation(String location) {
        MobileElement workplaceTextField = element(MobileBy.AccessibilityId(workplaceAccessibilityIdentifier));
        workplaceTextField.click();
        workplaceTextField.setFileDetector(fileDetector()); // Needed to send data to the remote
        workplaceTextField.sendKeys(location);
    }

    public void clickSubmit() {
        MobileElement submitButton = element(MobileBy.AccessibilityId(submitAccessibilityIdentifier));
        submitButton.click();
    }


    public void loaded() {
        MobileElement heyThereLabel = element(MobileBy.AccessibilityId(heyThereAccessibilityIdentifier));
        MobileElement questionLabel = element(MobileBy.AccessibilityId(questionLabelAccessibilityIdentifier));
    }
}
