package pageobject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.assertEquals;


public class OrderStatusTest {
    WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";
    private final String numberOrder = "aaaaa";

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
    public void orderStatusWithoutNumber() {
        new HomePage(driver)
                .waitForLoadHomePage()
                .clickOrderState()
                .inputOrderNumber(numberOrder)
                .clickGo();
        new OrderStatus(driver)
                .waitLoadOrderStatusPage();
        assertEquals("https://qa-scooter.praktikum-services.ru/track?t=aaaaa", driver.getCurrentUrl());
    }
}