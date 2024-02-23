package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AboutRenter {
    WebDriver driver;
    private final By orderHeader = By.className("Order_Header__BZXOb"); // Заголовок "Для кого самокат"
    private final By name = By.xpath(".//input[@placeholder='* Имя']"); // Поле ввода "Имя"
    private final By surname = By.xpath(".//input[@placeholder='* Фамилия']"); // Поле ввода "Фамилия"
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']"); // Поле ввода "Адрес"
    private final By stateMetro = By.className("select-search__input"); // Выпадающий список "Станция метро"
    private final By telephone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']"); // Поле ввода "Телефон"
    private final By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"); // Кнопка "Далее"
    private final String nameStateMetro = ".//button[@value='%s']"; // Обозначение станции метро
    private final By yandexButton = By.xpath(".//*[@alt='Yandex']"); // Элемент логотипа "Яндекс"
    private final By scooterButton = By.xpath(".//*[@alt='Scooter']"); // Элемент логотипа "Самокат"

    public AboutRenter(WebDriver driver) {
        this.driver = driver;
    }

    // Метод ожидания загруки страницы заказа
    public AboutRenter waitForLoadOrderPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(orderHeader).getText() != null
                && !driver.findElement(orderHeader).getText().isEmpty()
        ));
        return this;
    }

    // Метод заполнения поля ввода "Имя"
    public AboutRenter inputName(String newName) {
        driver.findElement(name).sendKeys(newName);
        return this;
    }

    // Метод заполнения поля ввода "Фамилия"
    public AboutRenter inputSurname(String newSurname) {
        driver.findElement(surname).sendKeys(newSurname);
        return this;
    }

    // Метод заполнения поля ввода "Адрес"
    public AboutRenter inputAddress(String newAddress) {
        driver.findElement(address).sendKeys(newAddress);
        return this;
    }

    // Метод заполнения поля ввода "Станция метро"
    public AboutRenter changeStateMetro(int stateNumber) {
        driver.findElement(stateMetro).click();
        By newStateMetro = By.xpath(String.format(nameStateMetro, stateNumber));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(newStateMetro));
        driver.findElement(newStateMetro).click();
        return this;
    }

    // Метод заполнения поля ввода "Телефон"
    public AboutRenter inputTelephone(String newTelephone) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(telephone));
        driver.findElement(telephone).sendKeys(newTelephone);
        return this;
    }

    // Метод клика на кнопку "Далее"
    public void clickNextButton() {
        driver.findElement(buttonNext).click();
    }

    // Метод клика на элемент логотипа "Яндекс"
    public void clickYandex() {
        driver.findElement(yandexButton).click();
    }

    // Метод клика на элемент логотипа "Самокат"
    public void clickScooter() {
        driver.findElement(scooterButton).click();
    }
}