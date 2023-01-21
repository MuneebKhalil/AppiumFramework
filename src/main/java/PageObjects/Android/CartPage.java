package PageObjects.Android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AndroidActions;

import java.time.Duration;
import java.util.List;

public class CartPage extends AndroidActions {

    AndroidDriver driver;

    public CartPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

   @FindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    private WebElement cartTittle;

    @FindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productPrices;

    @FindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalPrice;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private   WebElement termsAndCondition;

    @FindBy(id = "com.androidsample.generalstore:id/alertTitle")
    private WebElement alertTittle;

    @FindBy(id = "android:id/button1")
    private WebElement acceptButton;



    public void waitForCartTittle(){
        WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(cartTittle,"text", "Cart"));
    }

    public double calculateAndConvertAllProdcutPrice(){
        int size = productPrices.size();
        double sum = 0;
        for(int i =0; i < size; i++){
            String priceString =  productPrices.get(i).getText();
            Double priceNumber =  Double.parseDouble(priceString.substring(1));
            sum += priceNumber;
        }
        return sum;
    }

    public double convertTotalPrice(){
        String totalSumString =  totalPrice.getText();
        Double totalNumberDouble =  Double.parseDouble(totalSumString.substring(1));

        return  totalNumberDouble;
    }

    public void acceptTermsAndCondition(){
        longPressAction(termsAndCondition);
        acceptButton.click();

    }



}
