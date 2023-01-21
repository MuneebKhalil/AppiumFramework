package PageObjects.Android;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

import java.util.List;

public class ProductCatalogue extends AndroidActions {

    AndroidDriver driver;

    public ProductCatalogue(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//android.widget.TextView[@text='ADD TO CART'])[1]")
    private List<WebElement> addToCart;

    @FindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cartButton;

    public void addItemToCartByIndex(int index){

      addToCart.get(index).click();
    }

    public void goToCart() throws InterruptedException {
        cartButton.click();
        Thread.sleep(2000);
    }
}
