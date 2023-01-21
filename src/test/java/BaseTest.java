import PageObjects.Android.CartPage;
import PageObjects.Android.FormPage;
import PageObjects.Android.ProductCatalogue;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;


    @BeforeSuite(alwaysRun=true)
    public void configureAppium() throws MalformedURLException {
        service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Muneeb Khalil\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("MuneebEmulator");
        options.setApp("E:\\Appium\\AppiumGeneralStore\\src\\test\\java\\Resources\\General-Store.apk");
//        DesiredCapabilities cap = new DesiredCapabilities();
//        cap.setCapability("deviceName", "Xiaomi M2012K11AG");
//        cap.setCapability("udid", "337f73c0");
//        cap.setCapability("platformName", "Android");
//        cap.setCapability("platformVersion", "12");
//        cap.setCapability("appPackage", "io.appium.android.apis");
//        cap.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
//        cap.setCapability("automationName", "UiAutomator2");
//
         driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));



    }
    public void longPressAction(WebElement ele){
        driver.executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(), "duration" , 2000));
    }

    public  void swipeAction(WebElement ele, String direction){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),
                "direction", direction,
                "percent", 0.75
        ));
    }
    public String getScreenshot(String testCaseName, AppiumDriver driver) throws IOException {
        File source  = driver.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir")+"//reports"+testCaseName+".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }

    public void dragAction(WebElement source, int endX, int endY){
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) source).getId(),
                "endX", endX,
                "endY", endY
        ));

    }

    public  void scrollToText(String text){
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
    }
    @AfterClass(alwaysRun=true)
    public void shutDown(){
        driver.quit();
       service.stop();
    }
}
