import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class addNewUser {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://shop.pragmatic.bg/admin/");
    }

    @Test
    public void testAddNewCustomer() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement usernameInputField = driver.findElement(By.id("input-username"));
        usernameInputField.sendKeys("admin1");
        WebElement passwordInputField = driver.findElement(By.id("input-password"));
        passwordInputField.sendKeys("parola123!");
        WebElement loginButton = driver.findElement(By.cssSelector(".text-right>.btn.btn-primary"));
        loginButton.click();
        WebElement customerDropDownMenu = driver.findElement(By.cssSelector("#menu-customer>.parent.collapsed"));
        customerDropDownMenu.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='menu-customer']/ul/li[1]/a")));
        WebElement customerOption = driver.findElement(By.xpath("//li[@id='menu-customer']/ul/li[1]/a"));
        customerOption.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".pull-right .btn.btn-primary")));
        WebElement plusButton = driver.findElement(By.cssSelector(".pull-right .btn.btn-primary"));
        plusButton.click();
        WebElement firstNameInputField = driver.findElement(By.id("input-firstname"));
        firstNameInputField.sendKeys("Polly");
        WebElement lastNameInputField = driver.findElement(By.id("input-lastname"));
        lastNameInputField.sendKeys("Deneva");
        WebElement emailInputField = driver.findElement(By.id("input-email"));
        String prefix = RandomStringUtils.randomAlphabetic(10);
        String sufix = RandomStringUtils.randomAlphabetic(5);
        String emailAddress = prefix + "@" + sufix + ".com";
        emailInputField.sendKeys(emailAddress);
        WebElement telephoneInputField = driver.findElement(By.id("input-telephone"));
        String lastDigitsNumber = RandomStringUtils.randomNumeric(8);
        String telephoneNumber = "08" + lastDigitsNumber;
        telephoneInputField.sendKeys(telephoneNumber);
        WebElement passwordInput = driver.findElement(By.id("input-password"));
        passwordInput.sendKeys("Pass123456789");
        WebElement confirmPassword = driver.findElement(By.id("input-confirm"));
        confirmPassword.sendKeys("Pass123456789");
        WebElement dropDownNewsletter = driver.findElement(By.id("input-newsletter"));
        Select selectOption = new Select(dropDownNewsletter);
        selectOption.selectByValue("1");
        WebElement statusInputField = driver.findElement(By.id("input-status"));
        Select statusOption = new Select(statusInputField);
        statusOption.selectByValue("0");
        WebElement saveButton = driver.findElement(By.cssSelector(".page-header .fa.fa-save"));
        saveButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")));
        WebElement emailFilterCustomers = driver.findElement(By.id("input-email"));
        emailFilterCustomers.click();
        emailFilterCustomers.sendKeys(emailAddress);
        WebElement filterButton = driver.findElement(By.id("button-filter"));
        filterButton.click();
        WebElement customerPaulinaDeneva = driver.findElement(By.xpath("//div[@class='table-responsive']//tbody//td[contains(text(), 'Polly Deneva')]"));
        boolean PaulinaIsDisplayed = customerPaulinaDeneva.isDisplayed();
        Assert.assertTrue(PaulinaIsDisplayed, "Polly Deneva");
    }

//        ---Second Option---

//        String text = customerPaulinaDeneva.getText();
//        Assert.assertTrue(text.contains("Polly Deneva"));


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}