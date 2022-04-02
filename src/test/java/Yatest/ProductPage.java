package Yatest;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.NoSuchElementException;


public class ProductPage {

    public WebDriver driver;

    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    // Название магазина
    @FindBy(xpath = "//span[@class=\"Vu-M2\"]")
    private WebElement productShop;

    // Цена товара
    @FindBy(xpath = "//div[contains(@class,\"_3NaXx _3kWlK\")]/span/span[1]")
    private WebElement finalPrice;

    @Step("Получаем цену и магазин")
    public void getPriceAndShop(){
        Allure.addAttachment("ProductPage",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        System.out.println("Shop:"+productShop.getText());
        System.out.println("Price:"+finalPrice.getText());


    }
}
