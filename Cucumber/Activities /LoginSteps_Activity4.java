package stepDefinitions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginSteps_Activity4 extends BaseClass{

    @BeforeAll
    public static void setup(){
        //GeckoDriver install
        WebDriverManager.firefoxdriver().setup();

        //Driver Instialization
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

@Given("the user is on the login page")
    public void openPage(){
    //Open the Login Page
    driver.get("https://v1.training-support.net/selenium/login-form");
    //Assert part title
    Assertions.assertEquals("Login Form", driver.getTitle());
}

@When("the user enters username and password")
    public void enterCredentials(){
    //Find username field and enter username
    driver.findElement(By.id("username")).sendKeys("admin");
    //Find password field and enter password
    driver.findElement(By.id("password")).sendKeys("password");
}

    @When("the user enters {string} and {string}")
    public void enterCredentialsFromInputs(String username, String password){
        //Find the input fields
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        //Clear the fields
        usernameField.clear();
        passwordField.clear();
        //Find username field and enter username
        usernameField.sendKeys(username);
        //Find password field and enter password
        passwordField.sendKeys(password);
    }

@And("clicks the submit button")
    public void clickSubmit(){
    //Find the submit button and click it
    driver.findElement(By.xpath("//button [@type='submit']")).click();
}

@Then("get the confirmation text to verify message")
    public void confirmationMessage(){
    //Find the confirmation message
    String message = driver.findElement(By.id("action-confirmation")).getText();
    //Assert message
    Assertions.assertEquals("Welcome Back, admin", message);
    }
    @Then("get the confirmation text to verify message as {string}")
    public void confirmationMessageAsInput(String expectedMessage){
        //Find the confirmation message
        String message = driver.findElement(By.id("action-confirmation")).getText();
        //Assert message
        Assertions.assertEquals(expectedMessage, message);
    }
    @AfterAll
    public static void closeTheBrowser() {
        //To close the browser
        driver.quit();
    }
}
