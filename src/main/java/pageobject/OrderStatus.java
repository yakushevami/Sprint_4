package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderStatus {
    WebDriver driver;
    private final By yandexButton = By.xpath(".//*[@alt='Yandex']"); // Элемент логотипа "Яндекс"
    private final By scooterButton = By.xpath(".//*[@alt='Scooter']"); // Элемент логотипа "Самокат"
    private final By notFound = By.xpath(".//*[@alt='Not found']"); // Картинка с ошибкой (неправильно введён номер заказа для отслеживания)

    public OrderStatus(WebDriver driver) {
        this.driver = driver;
    }

    // Метод ожидания загрузки страницы
    public OrderStatus waitLoadOrderStatusPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(notFound));
        return this;
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