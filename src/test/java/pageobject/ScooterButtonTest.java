package pageobject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class ScooterButtonTest {
    WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";

    @Before
    public void startUp() {
        driver = new FirefoxDriver(); // Сменить на ChromeDriver(); для тестов в Google Chrome
        driver.get(site);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void clickScooterFromAboutRenterPage() {
        HomePage homePage = new HomePage(driver);
        AboutRenter aboutRenter = new AboutRenter(driver);

        homePage.waitForLoadHomePage()
                .clickUpOrderButton();

        aboutRenter.waitForLoadOrderPage()
                .clickScooter();

        new WebDriverWait(driver, Duration.ofSeconds(5));

        assertEquals("https://qa-scooter.praktikum-services.ru/", driver.getCurrentUrl());
    }

    @Test
    public void clickScooterFromAboutScooterPage() {
        HomePage homePage = new HomePage(driver);
        AboutRenter aboutRenter = new AboutRenter(driver);
        AboutScooter aboutScooter = new AboutScooter(driver);

        homePage.waitForLoadHomePage()
                .clickUpOrderButton();

        aboutRenter.waitForLoadOrderPage()
                .inputName("Имя")
                .inputSurname("Фамилия")
                .inputAddress("Адрес")
                .changeStateMetro(77)
                .inputTelephone("+79999999999")
                .clickNextButton();

        aboutScooter.waitAboutRentHeader()
                .clickScooter();

        new WebDriverWait(driver, Duration.ofSeconds(5));

        assertEquals("https://qa-scooter.praktikum-services.ru/", driver.getCurrentUrl());
    }

    @Test
    public void clickScooterFromOrderStatusPage() {
        HomePage homePage = new HomePage(driver);
        OrderStatus orderStatus = new OrderStatus(driver);

        homePage.waitForLoadHomePage()
                .clickOrderState()
                .inputOrderNumber("12345")
                .clickGo();

        orderStatus.waitLoadOrderStatusPage()
                .clickScooter();

        new WebDriverWait(driver, Duration.ofSeconds(5));

        assertEquals("https://qa-scooter.praktikum-services.ru/", driver.getCurrentUrl());
    }

}