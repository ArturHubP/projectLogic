package Yatest;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.ByteArrayInputStream;
import java.util.*;



public class SearchResultsPage {

    public WebDriver driver;
    public List<String> data = new ArrayList<String>();

    public SearchResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Кнопка сортировки
    @FindBy(xpath = "//*[@data-autotest-id = \"dprice\"]")
    private WebElement sortPricebtn;

    // Список продуктов
    @FindBy(xpath = "//h3/a")
    private  List<WebElement> products;

    // Список цен
    @FindBy(xpath = "//*[@class=\"_3NaXx _33ZFz _2m5MZ\"]/span/span[1]")
    private List<WebElement> productsPrice;

    @Step("Собираем список продукт-цена")
    public void titleList() {
        driver.navigate().refresh();
        for (int i = 0; i < products.size(); i++){
            String text = products.get(i).getAttribute("title")+":  "+ productsPrice.get(i).getText();
            data.add(text);
        }
        Allure.addAttachment("Список продуктов-цен",data.toString());
        Allure.addAttachment("SearchResults",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Проверяем наличие товара в списке")
    public void checkData(String check) {
        int counter = 0;
        for (String str : data){
            if (str.toLowerCase().contains(check.toLowerCase())){
                counter+=1;
                break;
            }
        }
        Assert.assertTrue(counter>=1,"Продукта нет в списке");

    }

    @Step("Нажимаем сортировку по цене")
    public void clickSortPricebtn() {
        sortPricebtn.click();
        driver.navigate().refresh();
        Allure.addAttachment("SortedPage",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

    }
    @Step("Нажимаем на самый первый продукт")
    public void clickProductPage() {
        String winHandleBefore = driver.getWindowHandle();
        products.get(0).click();
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
    }
}

