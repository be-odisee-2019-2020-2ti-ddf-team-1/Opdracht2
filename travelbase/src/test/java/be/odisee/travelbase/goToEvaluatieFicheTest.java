package be.odisee.travelbase;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.*;

import org.hibernate.annotations.WhereJoinTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


public class goToEvaluatieFicheTest extends AbstractStepDefinition {

    //WebDriver driver = getDriver();
    WebDriver driver;
    String evaluatieFicheUrl = "https://localhost:8443/travelbase";

    @Given("^I am on the home page$")
    public void i_am_on_the_home_page() throws Throwable{
        //System.setProperty("webdriver.gecko.driver", "geckodriver");
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        driver = getDriver();
        driver.navigate().to("https://localhost:8443/");
    }

    /*
    @When("^I enter \"([^\"]*)\" in the ([^\"]*) field$")
    public void i_enter_in_the_username_field(String enteredText, String fieldName) throws Throwable {
        driver.findElement(By.id(fieldName)).sendKeys(enteredText);
    }*/

    @When("^I enter \"user\" in the username field$")
    public void i_enter_in_the_username_field() throws Throwable {
        driver.findElement(By.name("username")).sendKeys("user");
    }

    @And("^I enter \"user\" in the password field$")
    public void i_enter_in_the_password_field() throws Throwable {
        driver.findElement(By.name("password")).sendKeys("user");
    }

    @And("^I press on the Entry button$")
    public void i_press_on_the_Entry_button() throws Throwable{
        driver.findElement(By.id("btnEntry")).click();
    }

    @Then("^I should be on the EvaluatieFiche page$")
    public void i_should_be_on_the_EvaluatieFiche_page() throws Throwable{
        // wachten tot pagina geladen is
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe(evaluatieFicheUrl));

        Assert.assertTrue("Did not find the page: " + evaluatieFicheUrl + "\n", driver.getCurrentUrl().equals(evaluatieFicheUrl));
    }

    /*
    @Given("^I am on the EvaluatieFiche page$")
    public void i_am_on_the_EvaluatieFiche_page() throws Throwable {
        driver.navigate().to(evaluatieFicheUrl);
    }

    @When("^I select BXL_Atomium in the activiteit field$")
    public void i_select_BXL_Atomium_in_the_activiteit_field() throws Throwable {
        Select dropDown = new Select(driver.findElement(By.id("activiteit")));
        dropDown.selectByVisibleText("BXL_Atomium");
    }

    @And("^I select the date of today in the Date field$")
    public void i_select_the_date_of_today_in_the_Date_field() throws Throwable {
        driver.findElement(By.name("dateTime")).sendKeys("04242020");
    }

    @And("^I enter \"dit is feedback\" in the Feedback field$")
    public void i_enter_in_the_feedback_field() throws Throwable {
        driver.findElement(By.name("feedback")).sendKeys("dit is feedback");
    }

    @And("^I enter \"dit is een oordeel\" in the Oordeel field$")
    public void i_enter_in_the_oordeel_field() throws Throwable {
        driver.findElement(By.name("oordeel")).sendKeys("dit is een oordeel");
    }

    @And("^I enter \"dit is een beoordeling\" in the Beoordeling field$")
    public void i_enter_in_the_beoordeling_field() throws Throwable {
        driver.findElement(By.name("beoordeling")).sendKeys("dit is een beoordeling");
    }


    @And("^I press on the Submit button$")
    public void i_press_on_the_Submit_button() throws Throwable {
        driver.findElement(By.id("submit")).click();
    }

    @Then("^I should see the following on the screen$")
    public void i_should_see_the_following_on_the_screen(List<String> checklist) throws Throwable {
        new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Details van Entry"));

        String bodyText = driver.findElement(By.tagName("body")).getText();
        for (String st : checklist) {
            String text2bFound = st;
            Assert.assertTrue("Did not find this text: " + text2bFound + "\n", bodyText.contains(text2bFound));
        }
    }
    */
}

