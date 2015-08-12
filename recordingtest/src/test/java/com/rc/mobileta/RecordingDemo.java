package com.rc.mobileta;

import com.rc.mobileta.util.MonitorHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class RecordingDemo {
    private AndroidDriver driverDevice1;
    private static final String packageName = "com.ringcentral.android";
    private static final String acceptBtnId = "android:id/button1";
    private static final String clearBtnId = "android:id/button1";
    private static final String alertTitle911Text = "E911 Terms of Service";
    private static final String loginPhoneFieldId = "com.ringcentral.android:id/phone";
    private static final String loginPasswordFieldId = "com.ringcentral.android:id/password";
    private static final String signInBtnId = "com.ringcentral.android:id/btnSignIn";
    private static final String skipBtnId = "com.ringcentral.android:id/whats_new_button_left";
    private static final String tabId = "com.ringcentral.android:id/text_tab_name";
    private static final String titleId = "com.ringcentral.android:id/title";
    private static final String topLeftImageId =  "com.ringcentral.android:id/btnTopLeftImage";
    private static final String topLeftBtnId =  "com.ringcentral.android:id/btnTopLeft";
    private static final String topRightBtnId =  "com.ringcentral.android:id/btnTopRight";
    private static final String newSmsBtnId = "com.ringcentral.android:id/btn_main_menu_action_sms";
    private static final String editContactAreaId = "com.ringcentral.android:id/edit_contact_area";
    private static final String messageEditTextId = "com.ringcentral.android:id/message_edittext";
    private static final String messageListId = "com.ringcentral.android:id/text_messages_list";
    private static final String okBtn = "com.ringcentral.android:id/btn_ok";
    private static final String dialPadBtnName = "Dial Pad";
    private static final String key1Id = "com.ringcentral.android:id/one";
    private static final String key2Id = "com.ringcentral.android:id/two";
    private static final String key0Id = "com.ringcentral.android:id/zero";
    private static final String callBtnId = "com.ringcentral.android:id/btnCall";
    private static final String endCallBtnId = "com.ringcentral.android:id/btn_call_endcall";


    @BeforeMethod
    public void setUp() throws Exception {
        // set up appium
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "./apps");
        File app = new File(appDir, "RCMobile_7.3.0.1.28_XIA_UP_Automation.apk");

        // 1st device
        DesiredCapabilities capabilitiesDevice1 = new DesiredCapabilities();
        capabilitiesDevice1.setCapability("deviceName", "04cbfaa51986510a");
        capabilitiesDevice1.setCapability("platformVersion", "5.0.1");
        capabilitiesDevice1.setCapability("app", app.getAbsolutePath());
        capabilitiesDevice1.setCapability("appPackage", packageName);
        MonitorHelper.startRec("test");
        driverDevice1 = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilitiesDevice1);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driverDevice1.quit();
        MonitorHelper.stopRec();
        Thread.sleep(3000);
    }

    @Test
    public void test() throws InterruptedException {
        // first login
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("*** Timer *** + start: " + dateFormat.format(new Date()));

//        clickDialPad();
//        click1();
//        click0();
//        click2();
//        clickCall();
//        Thread.sleep(20000);
//        clickEndCall();
        Thread.sleep(5000);
        System.out.println("*** Timer *** + end: " + dateFormat.format(new Date()));

//        firstLogin();

//        // clear msg
//        clearMsg();
//
//        // 1st edit
//        firstEdit();
//
//        // back and in to check saved message
//        checkSavedMessage("abc");
//
//        // 2nd edit
//        // delete invalid recipient
//        secondEdit();
//
//        // back and in to check saved message
//        checkSavedMessage("Admin User");
//
//        // 3rd edit
//        thirdEdit();
//
//        // back and in to check saved message
//        checkSavedMessageMoreRecipient("abc", "Admin User");
//
//        // back to message and check
//        driverDevice1.findElement(By.id(topLeftImageId)).click();
//        WebDriverWait waitDriver = new WebDriverWait(driverDevice1, 20);
//        waitDriver.until(ExpectedConditions.textToBePresentInElementLocated(By.id(titleId), "Messages"));
//        assertEquals(0, driverDevice1.findElement(By.id(messageListId)).findElements(By.className("android.widget.RelativeLayout")).size());
    }

    private void click1() {
        driverDevice1.findElement(By.id(key1Id)).click();
    }

    private void click2() {
        driverDevice1.findElement(By.id(key2Id)).click();
    }

    private void click0() {
        driverDevice1.findElement(By.id(key0Id)).click();
    }

    private void clickCall() {
        driverDevice1.findElement(By.id(callBtnId)).click();
    }

    private void clickEndCall() {
        driverDevice1.findElement(By.id(endCallBtnId)).click();
    }

    private void clickDialPad() {
        WebDriverWait waitDriver = new WebDriverWait(driverDevice1, 20);
        waitDriver.until(ExpectedConditions.elementToBeClickable(By.name(dialPadBtnName)));
        driverDevice1.findElement(By.name(dialPadBtnName)).click();
    }

    private void thirdEdit() {
        // click new sms btn
        WebDriverWait waitDriver = new WebDriverWait(driverDevice1, 20);
        waitDriver.until(ExpectedConditions.textToBePresentInElementLocated(By.id(titleId), "New Message"));
        // input invalid recipient
        driverDevice1.sendKeyEvent(29);
        driverDevice1.sendKeyEvent(30);
        driverDevice1.sendKeyEvent(31);
        driverDevice1.findElement(By.id(topLeftImageId)).click();
    }

    private void secondEdit() {
        driverDevice1.findElement(By.id(editContactAreaId)).click();
        driverDevice1.findElement(By.id(editContactAreaId)).sendKeys("Admin User");// click UI back btn
        driverDevice1.findElement(By.id(topLeftImageId)).click();
    }

    private void checkSavedMessage(String recipientName) {
        // click new sms btn
        driverDevice1.findElement(By.id(newSmsBtnId)).click();
        // check 1st edit info saved
        List<WebElement> elems = driverDevice1.findElement(By.id(editContactAreaId)).findElements(By.className("android.widget.TextView"));
        int index = 0;
        for (WebElement elem : elems) {
            if (elem.getText().equals("To:"))
                break;
            index++;
        }
        elems.remove(index);
        assertEquals(elems.get(0).getText(), recipientName, "Check To: field");
        assertEquals(driverDevice1.findElement(By.id(messageEditTextId)).getText(), "invalid", "Check Text Message");
    }

    private void checkSavedMessageMoreRecipient(String... expectedRecipientNames) {
        // click new sms btn
        driverDevice1.findElement(By.id(newSmsBtnId)).click();
        WebDriverWait waitDriver = new WebDriverWait(driverDevice1, 5);
        waitDriver.until(ExpectedConditions.elementToBeClickable(By.id(okBtn)));
        driverDevice1.findElement(By.id(okBtn)).click();
        waitDriver.until(ExpectedConditions.textToBePresentInElementLocated(By.id(titleId), "New Message"));
        // check  edit info saved
        List<WebElement> elems = driverDevice1.findElement(By.id(editContactAreaId)).findElements(By.className("android.widget.TextView"));
        List<String> recipientNameList = new ArrayList<>();
        for (WebElement elem : elems) {
            if (!elem.getText().equals("To:"))
                recipientNameList.add(elem.getText());
        }

        for (String recipientName : expectedRecipientNames) {
            assertThat("Check To: field", recipientNameList, hasItem(recipientName));
        }
        assertEquals(recipientNameList.size(), expectedRecipientNames.length, "Check recipient size");
        assertEquals(driverDevice1.findElement(By.id(messageEditTextId)).getText(), "invalid", "Check Text Message");
    }

    private void firstEdit() {
        // click new sms btn
        WebDriverWait waitDriver = new WebDriverWait(driverDevice1, 10);
        waitDriver.until(ExpectedConditions.elementToBeClickable(By.id(newSmsBtnId)));
        driverDevice1.findElement(By.id(newSmsBtnId)).click();
        waitDriver.until(ExpectedConditions.textToBePresentInElementLocated(By.id(titleId), "New Message"));
        // input invalid recipient
        driverDevice1.findElement(By.id(editContactAreaId)).findElement(By.className("android.widget.EditText")).sendKeys("abc");
        // input msg content
        driverDevice1.findElement(By.id(messageEditTextId)).sendKeys("invalid");
        // click UI back btn
        driverDevice1.findElement(By.id(topLeftImageId)).click();
    }

    private void clearMsg() {
        // click edit btn
        WebDriverWait waitDriver = new WebDriverWait(driverDevice1, 10);
        driverDevice1.findElement(By.id(topRightBtnId)).click();
        // click clear btn
        waitDriver.until(ExpectedConditions.textToBePresentInElementLocated(By.id(topLeftBtnId), "Clear"));
        driverDevice1.findElement(By.id(topLeftBtnId)).click();
        // click clear
        waitDriver.until(ExpectedConditions.textToBePresentInElementLocated(By.id(clearBtnId), "Clear"));
        driverDevice1.findElement(By.id(clearBtnId)).click();
        // back to Message screen
        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.id(titleId)));
        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.id(tabId)));
    }

    private void firstLogin() {
        // click 'Summary of Legal Terms'
        WebDriverWait waitDriver = new WebDriverWait(driverDevice1, 10);
        waitDriver.until(ExpectedConditions.elementToBeClickable(By.id(acceptBtnId)));
        driverDevice1.findElement(By.id(acceptBtnId)).click();
        // click 'E911 Terms of Service'
        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.name(alertTitle911Text)));
        waitDriver.until(ExpectedConditions.elementToBeClickable(By.id(acceptBtnId)));
        driverDevice1.findElement(By.id(acceptBtnId)).click();
        // login
        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.id(loginPhoneFieldId)));
        // account type: RC_OFFICE.3324_9U_2DP1AO1TMO_9DID
        driverDevice1.findElement(By.id(loginPhoneFieldId)).sendKeys("18888770231");
        driverDevice1.findElement(By.id(loginPasswordFieldId)).sendKeys("Test!123");
        waitDriver.until(ExpectedConditions.elementToBeClickable(By.id(signInBtnId)));
        driverDevice1.findElement(By.id(signInBtnId)).click();
        // skip What's New
        waitDriver.until(ExpectedConditions.elementToBeClickable(By.id(skipBtnId)));
        driverDevice1.findElement(By.id(skipBtnId)).click();
        // todo: input mobile phone number


        // check Message Screen shown
        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.id(titleId)));
        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.id(tabId)));
    }
}
