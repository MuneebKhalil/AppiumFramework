import PageObjects.Android.CartPage;
import PageObjects.Android.FormPage;
import PageObjects.Android.ProductCatalogue;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;


public class eCommerce_tc_2 extends BaseTest{
    public FormPage formPage;
    public CartPage cartPage ;
    public ProductCatalogue productCatalogue ;


    @BeforeClass(alwaysRun=true)
    public void preSetup() throws MalformedURLException {
        formPage = new FormPage(driver);
        cartPage = new CartPage(driver);
        productCatalogue = new ProductCatalogue(driver);

    }

    @Test(priority = 0)
    public void FillForm_ErrorValidation() throws InterruptedException {
//
//        formPage.setNameField("Muneeb Khalil");
        formPage.selectGender("Female");
        formPage.setCountrySelection("Argentina");
        formPage.submitForm();
        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage, "Please enter your nam");

    }
    @Test(priority = 1,dataProvider = "getData",groups = {"smoke"})
    public void FillForm(String name, String gender, String country) throws InterruptedException {

        formPage.setNameField(name);
        formPage.selectGender(gender);
        formPage.setCountrySelection(country);
        formPage.submitForm();
        productCatalogue.addItemToCartByIndex(0);
        productCatalogue.addItemToCartByIndex(0);
        productCatalogue.goToCart();
        cartPage.waitForCartTittle();
        Assert.assertEquals(cartPage.convertTotalPrice(),cartPage.calculateAndConvertAllProdcutPrice());
        WebElement termAndCondition = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        longPressAction(termAndCondition);

       String alertTitle = driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText();
        Assert.assertEquals(alertTitle, "Terms Of Condition");
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        driver.findElement((By.id("com.androidsample.generalstore:id/btnProceed"))).click();
    }
    @DataProvider
    public Object[] [] getData(){
        return  new Object[][] {{"Muneeb Khalil", "Female","Argentina" }};
    }
}
