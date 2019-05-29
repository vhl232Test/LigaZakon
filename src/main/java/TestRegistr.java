import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestRegistr {

    WebDriver driver;
    Util util = new Util();
    Properties properties = new Properties();
    FileInputStream fileInputStream;
    File file;
    MainPage mainPage;

    String name;
    String lastName;
    String email;
    String phoneNumber;
    String companyName;

    @BeforeClass
    public void setProperty(){
        System.setProperty(util.webdriverChrom,util.chromDriverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(util.urlLigaZacon);
    }

    @Test
    public void testValidData(){
        mainPage = new MainPage(driver);
        mainPage.uviitiButton.click();
        mainPage.registrButton.click();



        try {
            //file = new File(util.dataForRegistr);
            fileInputStream = new FileInputStream(util.dataForRegistr);
            properties.load(fileInputStream);

            name = properties.getProperty("name");
            lastName = properties.getProperty("surname");
            email = properties.getProperty("email");
            phoneNumber = properties.getProperty("phoneNumber");
            companyName = properties.getProperty("companyName");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         catch (IOException e) {
            e.printStackTrace();
        }

        mainPage.nameField.sendKeys(name);
        mainPage.surnemeField.sendKeys(lastName);
        mainPage.emailField.sendKeys(email);
        mainPage.phoneField.sendKeys(phoneNumber);
        mainPage.companyField.sendKeys(companyName);
        mainPage.checkMark.click();
        //mainPage.popupRegistrButton.click();



    }


}
