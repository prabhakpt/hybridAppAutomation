package app.hybrid.sample.ios.page;

import static app.hybrid.sample.Helpers.element;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

/**
 * Created by Javon Davis on 7/28/17.
 */
public class IOSClickCounterPage {

    private String decreaseAccessibilityIdentifier = "Decrease Button";
    private String increaseAccessibilityIdentifier = "Increase Button";
    private String countAccissibilityIdentifier = "Count";


    public void clickIncreaseButton() {
        MobileElement increaseButton = element(MobileBy.AccessibilityId(increaseAccessibilityIdentifier));
        increaseButton.click();

    }

    public void clickDecreaseButton() {
        MobileElement decreaseButton = element(MobileBy.AccessibilityId(increaseAccessibilityIdentifier));
        decreaseButton.click();
    }

    public void loaded() {
        MobileElement countLabel = element(MobileBy.AccessibilityId(countAccissibilityIdentifier));
    }
}
