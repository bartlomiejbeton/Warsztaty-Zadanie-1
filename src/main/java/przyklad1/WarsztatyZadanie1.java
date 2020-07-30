package przyklad1;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;



public class WarsztatyZadanie1 {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
        // Uruchom nowy egzemplarz przeglądarki Chrome
        driver = new ChromeDriver();

        // Zmaksymalizuj okno przeglądarki
        driver.manage().window().maximize();


    }
    @Test
    public void loginToShop() throws InterruptedException {
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account");

        WebElement emailWebElement = driver.findElement(By.name("email"));
        WebElement passwordWebElement = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.id("submit-login"));
        System.out.println(emailWebElement);
        System.out.println(passwordWebElement);

        emailWebElement.sendKeys("fiqaicnnbzhfwzbeoh@awdrt.com");
        passwordWebElement.sendKeys("januszkowalski");
        loginButton.click();

        WebElement addressesButton = driver.findElement(By.id("addresses-link"));
        addressesButton.click();

        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=address");
        String expectedAddress = "Mazowiecka";
        String expectedPostCode = "02-512";
        String expectedCity = "Warszawa";

        WebElement address = driver.findElement(By.name("address1"));
        WebElement postcode = driver.findElement(By.name("postcode"));
        WebElement city = driver.findElement(By.name("city"));
        Select idCountry = new Select(driver.findElement(By.name("id_country")));

        address.sendKeys(expectedAddress);
        postcode.sendKeys(expectedPostCode);
        city.sendKeys(expectedCity);
        idCountry.selectByIndex(1);

        WebElement buttonSave = driver.findElement(By.className("btn"));
        buttonSave.click();

        WebElement item = driver.findElement(By.className("address-body"));

        String[] splitedText = item.getText().split("\n");
        Assert.assertEquals(splitedText.length, 6);
        Assert.assertEquals(splitedText[2], expectedAddress);
        Assert.assertEquals(splitedText[3], expectedCity);
        Assert.assertEquals(splitedText[4], expectedPostCode);


    }

    @After
    public void tearDown() throws Exception {
//         Zamknij przeglądarkę
        driver.quit();
    }
}
