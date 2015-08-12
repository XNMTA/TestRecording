package com.rc.mobileta;

import com.rc.mobileta.util.MonitorHelper;
import com.rc.mobileta.util.SubtitleMaker;
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
import java.io.IOException;
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
    private static final String dialPadBtnName = "Dial Pad";
    private static final String key1Id = "com.ringcentral.android:id/one";
    private static final String key2Id = "com.ringcentral.android:id/two";
    private static final String key0Id = "com.ringcentral.android:id/zero";
    private static final String callBtnId = "com.ringcentral.android:id/btnCall";
    private static final String endCallBtnId = "com.ringcentral.android:id/btn_call_endcall";
    private SubtitleMaker subtitleMaker;



    @BeforeMethod
    public void setUp() throws Exception {
        // set up appium
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "../apps");
        File app = new File(appDir, "RCMobile_7.3.0.1.28_XIA_UP_Automation.apk");

        // 1st device
        DesiredCapabilities capabilitiesDevice1 = new DesiredCapabilities();
        capabilitiesDevice1.setCapability("deviceName", "04cbfaa51986510a");
        capabilitiesDevice1.setCapability("platformVersion", "5.0.1");
        capabilitiesDevice1.setCapability("app", app.getAbsolutePath());
        capabilitiesDevice1.setCapability("appPackage", packageName);
        MonitorHelper.startRec("TestName");
        subtitleMaker = new SubtitleMaker("TestName.srt");
        driverDevice1 = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilitiesDevice1);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driverDevice1.quit();
        MonitorHelper.stopRec();
        subtitleMaker.finishSubtitle();
        Thread.sleep(3000);
    }

    @Test
    public void test() throws InterruptedException, IOException {
        // first login
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("*** Timer *** + start: " + dateFormat.format(new Date()));

        subtitleMaker.addStepMsgToSubtitle("STEP 1: click DialPad");
        clickDialPad();
        subtitleMaker.addStepMsgToSubtitle("STEP 2: call 102");
        click1();
        click0();
        click2();
        clickCall();
        subtitleMaker.addStepMsgToSubtitle("STEP 3: call lasts for 20s");
        Thread.sleep(20000);
        subtitleMaker.addStepMsgToSubtitle("STEP 4: end the call");
        clickEndCall();
        System.out.println("*** Timer *** + end: " + dateFormat.format(new Date()));
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
}
