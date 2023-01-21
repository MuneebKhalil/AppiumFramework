package PageObjects.Android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;
import org.openqa.selenium.support.FindBy;

public class FormPage extends AndroidActions {
    AndroidDriver driver;

    public FormPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @FindBy(xpath="//android.widget.RadioButton[@text='Male']")
    private WebElement maleOption;

    @FindBy(xpath="//android.widget.RadioButton[@text='Female']")
    private WebElement femaleOption;

    @FindBy(id = "android:id/text1")
    private  WebElement countrySelection;

    @FindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;

    public void setNameField(String name){
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }
    public void selectGender(String gender){
        if(gender.contains("Female")){
            femaleOption.click();
        }
        else maleOption.click();
    }



    public  void setCountrySelection(String country){
        countrySelection.click();
        scrollToText(country);
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+country+"']")).click();
    }

    public void submitForm(){
        shopButton.click();
    }
}
