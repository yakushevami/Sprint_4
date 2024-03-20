package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static pageobject.constants.CreateOrderButton.DOWN_BUTTON;
import static pageobject.constants.CreateOrderButton.UP_BUTTON;

public class HomePage {
    WebDriver driver;
    private final By homeHeader = By.className("Home_Header__iJKdX"); // Заголовок страницы
    private final By upOrderButton = By.className("Button_Button__ra12g"); // Верхняя кнопка "Заказать"
    private final By downOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"); // Нижняя кнопка "Заказать"
    private final By questionsHeader = By.className("Home_FourPart__1uthg"); // Блок с вопросами
    private final By orderState = By.xpath(".//button[text()='Статус заказа']"); // Кнопка "Статус заказа"
    private final By numberOrder = By.xpath(".//input[@placeholder='Введите номер заказа']"); // Поле ввода "Введите номер заказа"
    private final By buttonGo = By.xpath(".//button[text()='Go!']"); // Кнопка "Go!"

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод ожидания загрузки главной страницы
    public HomePage waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver -> (driver.findElement(homeHeader).getText() != null
                && !driver.findElement(homeHeader).getText().isEmpty()
        ));
        return this;
    }

    // Метод ожидания загрузки ответа на вопрос
    public void waitLoadAfterClickQuestion(By accordionLabel) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> (driver.findElement(accordionLabel).getText() != null
                && !driver.findElement(accordionLabel).getText().isEmpty()
        ));
    }

    // Метод прокрутки к блоку "Вопросы о важном"
    public HomePage scrollToQuestions() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questionsHeader));
        return this;
    }

    // Метод прокрутки ко второй кнопке "Заказать"
    public HomePage scrollToDownOrderButton() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(downOrderButton));
        return this;
    }

    // Метод клика на верхнюю кнопку "Заказать"
    public HomePage clickUpOrderButton() {
        driver.findElement(upOrderButton).click();
        return this;
    }

    // Метод клика на нижнюю кнопку "Заказать"
    public HomePage clickDownOrderButton() {
        driver.findElement(downOrderButton).click();
        return this;
    }

    // Метод клика на выбранную кнопку "Заказать" (верхняя или нижняя)
    public void clickCreateOrderButton(Enum button) {
        if (button.equals(UP_BUTTON)) {
            clickUpOrderButton();
        } else if (button.equals(DOWN_BUTTON)) {
            scrollToDownOrderButton();
            clickDownOrderButton();
        }
    }

    // Метод клика на вопрос
    public HomePage clickQuestion(By question) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(question))
                .click();
        return this;
    }

    // Метод клика на кнопку "Статус заказа"
    public HomePage clickOrderState() {
        driver.findElement(orderState).click();
        return this;
    }

    // Метод ввода номера заказа в поле ввода "Введите номер заказа"
    public HomePage inputOrderNumber(String number) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(numberOrder))
                .sendKeys(number);
        return this;
    }

   // Метод клика по кнопке "Go!"
    public HomePage clickGo() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(buttonGo))
                .click();
        return this;
    }

}