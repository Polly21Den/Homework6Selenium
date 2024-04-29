import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class GuestCheckout {

    WebDriver driver;
    private String text;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://shop.pragmatic.bg/index.php?route=product/product&product_id=40");
    }

    @Test
    public void testGuestCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement addCartButton = driver.findElement(By.id("button-cart"));
        addCartButton.click();
        WebElement cartButton = driver.findElement(By.id("cart-total"));
        cartButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cart .fa.fa-share")));
        WebElement checkoutButton = driver.findElement(By.cssSelector("#cart .fa.fa-share"));
        checkoutButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[value='guest']")));
        WebElement radioButtonGuest = driver.findElement(By.cssSelector("[value='guest']"));
        radioButtonGuest.click();
        WebElement continueButton = driver.findElement(By.id("button-account"));
        continueButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-payment-firstname")));
        WebElement firstNameInputField = driver.findElement(By.id("input-payment-firstname"));
        firstNameInputField.sendKeys("Polly");
        WebElement lastNameInputField = driver.findElement(By.id("input-payment-lastname"));
        lastNameInputField.sendKeys("Deneva");
        WebElement emailInputField = driver.findElement(By.id("input-payment-email"));
        String prefix = RandomStringUtils.randomAlphabetic(10);
        String sufix = RandomStringUtils.randomAlphabetic(5);
        String emailAddress = prefix + "@" + sufix + ".com";
        emailInputField.sendKeys(emailAddress);
        WebElement telephoneInputField = driver.findElement(By.id("input-payment-telephone"));
        String lastDigidsPhone = RandomStringUtils.randomNumeric(8);
        String telephoneNumber = "08" + lastDigidsPhone;
        telephoneInputField.sendKeys(lastDigidsPhone);
        WebElement address1InputField = driver.findElement(By.id("input-payment-address-1"));
        address1InputField.sendKeys("j.k. Musagenitsa 21");
        WebElement cityInputField = driver.findElement(By.id("input-payment-city"));
        cityInputField.sendKeys("Sofia");
        WebElement postcodeInputField = driver.findElement(By.id("input-payment-postcode"));
        postcodeInputField.sendKeys("1000");
        WebElement countrySelect = driver.findElement(By.id("input-payment-country"));
        Select countryDropdownMenu = new Select(countrySelect);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-payment-country")));
        countryDropdownMenu.selectByValue("33");
        WebElement regionSelect = driver.findElement(By.id("input-payment-zone"));
        Select regionDropdownMenu = new Select(regionSelect);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("option[value='505']")));
        regionDropdownMenu.selectByValue("498");
        WebElement continueButtonCheckout = driver.findElement(By.id("button-guest"));
        continueButtonCheckout.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("button-shipping-method")));
        WebElement continueShippingMethod = driver.findElement(By.id("button-shipping-method"));
        continueShippingMethod.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.name("agree")));
        WebElement agree = this.driver.findElement(By.name("agree"));
        agree.click();
        WebElement continueButtonPayment = driver.findElement(By.id("button-payment-method"));
        continueButtonPayment.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("button-confirm")));
        WebElement confirmOrderButton = driver.findElement(By.id("button-confirm"));
        confirmOrderButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".buttons .btn.btn-primary")));
        WebElement continueButtonFinalPage = driver.findElement(By.cssSelector(".buttons .btn.btn-primary"));
        String title = driver.getTitle();
        Assert.assertEquals(title, "Your order has been placed!");
    }

//        ---Second Option---

//        WebElement textPage = driver.findElement(By.cssSelector("#content>h1"));
//        String text = textPage.getText();
//        Assert.assertTrue(text.contains("Your order has been placed!"));


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
