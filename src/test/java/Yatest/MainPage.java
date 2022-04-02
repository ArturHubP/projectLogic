package Yatest;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.ByteArrayInputStream;


public class MainPage {

    public WebDriver driver;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    // Поле поиска
    @FindBy(xpath = "//*[@id=\"header-search\"]")
    private WebElement searchField;

    // Кнопка "Найти"
    @FindBy(xpath = "//*[@type = \"submit\"]")
    private WebElement searchBtn;

    @Step("Вводим текст в поле поиска {product}")
    public void inputSearch(String product) {
        searchField.sendKeys(product);
        Allure.addAttachment("MainPageshot",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
    @Step("Нажимаем кнопку \"Найти\"")
    public void clickSearchBtn() {
        searchBtn.click();

    }


}