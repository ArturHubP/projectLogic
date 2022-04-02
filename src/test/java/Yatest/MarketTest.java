package Yatest;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.time.Duration;

@Epic("Тестовое задание")
@Feature("Market test")


public class MarketTest {

    public static MainPage mainPage;
    public static ProductPage productPage;
    public static SearchResultsPage searchResultsPage;
    public static WebDriver driver;



    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(ConfProperties.getProperty("mainpageurl"));
    }

    @Test(priority = 0,description = "Тестовое задание Я.Маркет")
    public void marketTest() {
        // Вводим название продукта в поле поиска
        mainPage.inputSearch(ConfProperties.getProperty("product"));
        // Нажимаем кнопку поиска
        mainPage.clickSearchBtn();
        // Собираем список продуктов
        searchResultsPage.titleList();
        // Проверяем есть ли продукт в списке
        searchResultsPage.checkData(ConfProperties.getProperty("product"));
        // Нажимаем сортировку по цене
        searchResultsPage.clickSortPricebtn();
        // нажимаем на первый продукт
        searchResultsPage.clickProductPage();
        //получаем цену и магазин
        productPage.getPriceAndShop();
    }
    @AfterClass
    //Закрываем браузер
    public void close(){

        driver.quit();
    }

}

