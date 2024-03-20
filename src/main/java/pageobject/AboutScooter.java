package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static pageobject.constants.ScooterColours.*;

public class AboutScooter {
    WebDriver driver;
    private final By rentHeader = By.className("Order_Header__BZXOb"); // Заголовок "Про аренду"
    private final By date = By.xpath(".//input[@placeholder='* Когда привезти самокат']"); // Поле ввода "Когда привезти самокат"
    private final By durationRent = By.xpath(".//span[@class='Dropdown-arrow']"); // Выпадающий список "Срок аренды"
    private final By colourBlack = By.id("black"); // Чекбокс с чёрным цветом самоката
    private final By colourGrey = By.id("grey"); // Чекбокс с серым цветом самоката
    private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']"); // Поле ввода "Комментарий для курьера"
    private final By createOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"); // Кнопка заказать внизу формы "Про аренду"
    private final By yandexButton = By.xpath(".//*[@alt='Yandex']"); // Элемент логотипа "Яндекс"
    private final By scooterButton = By.xpath(".//*[@alt='Scooter']"); // Элемент логотипа "Самокат"

    public AboutScooter(WebDriver driver) {
        this.driver = driver;
    }

    // Метод ожидания загрузки страницы
    public AboutScooter waitAboutRentHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(rentHeader).getText() != null
                && !driver.findElement(rentHeader).getText().isEmpty()
        ));
        return this;
    }

    // Метод заполнения поля ввода "Дата"
    public AboutScooter inputDate(String newDate) {
        driver.findElement(date).sendKeys(newDate);
        return this;
    }

    // Метод выбора значения "Срок аренды"
    public AboutScooter inputDuration(String newDuration) {
        driver.findElement(durationRent).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.className("Dropdown-menu"))).click();
        return this;
    }

    // Метод выбора цвета самоката
    public AboutScooter changeColour(Enum colour) {
        if (colour.equals(BLACK)) {
            driver.findElement(colourBlack).click();
        } else if (colour.equals(GREY)) {
            driver.findElement(colourGrey).click();
        }
        return this;
    }

    // Метод заполнения поля ввода "Комментарий"
    public AboutScooter inputComment(String newComment) {
        driver.findElement(comment).sendKeys(newComment);
        return this;
    }

    // Метод клика на кнопку "Заказать" внизу формы
    public void clickButtonCreateOrder() {
        driver.findElement(createOrderButton).click();
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