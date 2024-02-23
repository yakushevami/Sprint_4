package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PopUpWindow {
    WebDriver driver;
    private final By popUpHeaderAfterCreateOrder = By.xpath(".//div[text()='Заказ оформлен']"); // Текст второго всплывающего окна с заголовком "Заказ оформлен"
    private final By buttonYes = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']"); // Кнопка "Да" первого всплывающего окна

    public PopUpWindow(WebDriver driver) {
        this.driver = driver;
    }

    // Метод клика по кнопке "Да" первого всплывающего окна
    public void clickButtonYes() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(buttonYes)).click();
    }

   // Метод подтверждения появления второго всплывающего окна
    public String getHeaderAfterCreateOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(popUpHeaderAfterCreateOrder).getText() != null
                && !driver.findElement(popUpHeaderAfterCreateOrder).getText().isEmpty()
        ));
        return driver.findElement(popUpHeaderAfterCreateOrder).getText();
    }
}