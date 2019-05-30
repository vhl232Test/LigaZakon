import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class TestRegistr {

    WebDriver driver;
    Util util = new Util();
    Properties properties = new Properties();
    FileInputStream fileInputStream;
    MainPage mainPage;

    String name;
    String lastName;
    String email;
    String phoneNumber;
    String companyName;

    @BeforeClass
    public void setProperty(){
        System.setProperty(util.webdriverChrom,util.chromDriverPath); // создание обьекта драйвера
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(util.urlLigaZacon); // запуск браузера и переход по url
    }

    @Test(priority = 1)
    public void testValidData(){

            // переход в блок регистрации
        mainPage = new MainPage(driver);
        mainPage.uviitiButton.click();
        mainPage.registrButton.click();



        try {
            // получение данных для регистрации из проперти-файла
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
            // заполнение формы регистрации
        mainPage.nameField.sendKeys(name);
        mainPage.surnemeField.sendKeys(lastName);
        mainPage.emailField.sendKeys(email);
        mainPage.phoneField.sendKeys(phoneNumber);
        mainPage.companyField.sendKeys(companyName);
        mainPage.checkMark.click();
        mainPage.popupRegistrButton.click();


    }

    @Test(priority = 2)
    public void checkEmail(){

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            fileInputStream = new FileInputStream(util.dataForRegistr);
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         catch (IOException e) {
            e.printStackTrace();
        }


        String user = properties.getProperty("email"); // имя пользователя
        String passForEmail = properties.getProperty("emailPassword");   // пароль
        String host = "imap.ukr.net";     // адрес почтового сервера



        Session session = Session.getDefaultInstance(new Properties( ));
        Store store = null;
        try {
            store = session.getStore("imaps");
            store.connect(host, 993, user, passForEmail);
            Folder inbox = null;
            inbox = store.getFolder( "INBOX" );
            inbox.open( Folder.READ_ONLY );

            //  получить непрочитанные сообщения
            Message[] messages = new Message[0];
            messages = inbox.search(
                    new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            // сортироватка сообщений
            Arrays.sort( messages, (m1, m2 ) -> {
                try {
                    return m2.getSentDate().compareTo( m1.getSentDate() );
                } catch ( MessagingException e ) {
                    throw new RuntimeException( e );
                }
            } );

            for ( Message message : messages ) {
                try {
                    System.out.println(
                            "sendDate: " + message.getSentDate()
                                    + " subject:" + message.getSubject() );
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }






    }


}
