package be.odisee.travelbase;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.*;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class goToEntryTest {

    WebDriver driver;

    @Given("^I am on the home page$")
    public void i_am_on_the_home_page() throws Throwable{

        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");

        driver = new FirefoxDriver();
        driver.navigate().to("https://localhost:8443/");
    }

    @When("^I press on the Entry button$")
    public void i_press_on_the_Entry_button() throws Throwable{
        driver.findElement(By.id("btnEntry")).click();
    }

    @Then("^I should be on the Entry page$")
    public void i_should_be_on_the_Entry_page() throws Throwable{
        String entryUrl = "http://localhost:8443/travelbase/";

        // wachten tot pagina geladen is
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe(entryUrl));

        Assert.assertTrue("Did not find the page: " + entryUrl + "\n", driver.getCurrentUrl().equals(entryUrl));
    }
}
