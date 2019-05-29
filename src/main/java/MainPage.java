import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    WebDriver driver;
    @FindBy (xpath = "//div[@class='header_sign_in_btn']")
    WebElement uviitiButton;
    @FindBy(xpath = "//div[@class='reg ng-star-inserted']")
    WebElement registrButton;
    @FindBy(xpath = "//input[@name='firstname']")
    WebElement nameField;
    @FindBy(xpath = "//input[@name='lastname']")
    WebElement surnemeField;
    @FindBy(xpath = "//input[@name='email']")
    WebElement emailField;
    @FindBy(xpath = "//input[@name='phone']")
    WebElement phoneField;
    @FindBy(xpath = "//input[@name='company']")
    WebElement companyField;
    @FindBy(xpath = "//span[@class='checkmark']")
    WebElement checkMark;
    @FindBy(xpath = "//button[@id='platforma-main-click-register_email-button-popup_register-1']")
    WebElement popupRegistrButton;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
