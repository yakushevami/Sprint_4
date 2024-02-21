package pageObject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;
import static pageObject.constants.CreateOrderButton.DOWN_BUTTON;
import static pageObject.constants.CreateOrderButton.UP_BUTTON;
import static pageObject.constants.RentDurationConstants.*;
import static pageObject.constants.ScooterColours.*;

@RunWith(Parameterized.class)
public class OrderCreateTest {
    private WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";
    private final String name;
    private final String surname;
    private final String address;
    private final int stateMetroNumber;
    private final String telephoneNumber;
    private final String date;
    private final String duration;
    private final Enum colour;
    private final String comment;
    private final String expectedHeader = "Заказ оформлен";
    private final Enum button;

    public OrderCreateTest(Enum button, String name, String surname, String address, int stateMetroNumber, String telephoneNumber,
                           String date, String duration, Enum colour, String comment) {
        this.button = button;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stateMetroNumber = stateMetroNumber;
        this.telephoneNumber = telephoneNumber;
        this.date = date;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {UP_BUTTON, "ИмяА", "ФамилияА", "Адрес1", 1, "79990000001", "01.03.2024", ONE_DAY, GREY, "comments one"},
                {UP_BUTTON, "ИмяБ", "ФамилияБ", "Адрес2", 2, "79990000002", "01.03.2024", FOUR_DAYS, BLACK, "comments two"},
                {UP_BUTTON, "ИмяВ", "ФамилияВ", "Адрес3", 3, "79990000003", "01.03.2024", SIX_DAYS, GREY, "comments three"},
                {DOWN_BUTTON, "ИмяА", "ФамилияА", "Адрес1", 1, "79990000001", "01.03.2024", ONE_DAY, GREY, "comments one"},
                {DOWN_BUTTON, "ИмяБ", "ФамилияБ", "Адрес2", 2, "79990000002", "01.03.2024", FOUR_DAYS, BLACK, "comments two"},
                {DOWN_BUTTON, "ИмяВ", "ФамилияВ", "Адрес3", 3, "79990000003", "01.03.2024",  SIX_DAYS, GREY, "comments three"},
        };
    }

    @Before
    public void startUp() {
        driver = new FirefoxDriver(); // Сменить на ChromeDriver(); для тестов в Google Chrome - нельзя оформить заказ из-за бага
        driver.get(site);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testCreateOrderWithUpButton() {
        new HomePage(driver)
                .waitForLoadHomePage()
                .clickCreateOrderButton(button);

        new AboutRenter(driver)
                .waitForLoadOrderPage()
                .inputName(name)
                .inputSurname(surname)
                .inputAddress(address)
                .changeStateMetro(stateMetroNumber)
                .inputTelephone(telephoneNumber)
                .clickNextButton();

        new AboutScooter(driver)
                .waitAboutRentHeader()
                .inputDate(date)
                .inputDuration(duration)
                .changeColour(colour)
                .inputComment(comment)
                .clickButtonCreateOrder();

        PopUpWindow popUpWindow = new PopUpWindow(driver);
        popUpWindow.clickButtonYes();

        assertTrue(popUpWindow.getHeaderAfterCreateOrder().contains(expectedHeader));
    }
}