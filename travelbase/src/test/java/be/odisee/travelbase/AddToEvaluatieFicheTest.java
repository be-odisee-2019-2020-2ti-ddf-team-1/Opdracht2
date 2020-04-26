package be.odisee.travelbase;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.validation.constraints.AssertTrue;
import java.util.List;

public class AddToEvaluatieFicheTest extends AbstractStepDefinition{
    //WebDriver driver = getDriver();
    WebDriver driver;
    String evaluatieFicheUrl = "https://localhost:8443/travelbase";

    @Given("^I am on the EvaluatieFiche page$")
    public void i_am_on_the_EvaluatieFiche_page() throws Throwable {
        //System.setProperty("webdriver.gecko.driver", "geckodriver");
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        driver = getDriver();
        //driver.navigate().to(evaluatieFicheUrl);
        Assert.assertTrue("Did not find the page: " + evaluatieFicheUrl + "\n", driver.getCurrentUrl().equals(evaluatieFicheUrl));
    }

    @When("^I select BXL_Atomium in the activiteit field$")
    public void i_select_BXL_Atomium_in_the_activiteit_field() throws Throwable {
        Select dropDown = new Select(driver.findElement(By.name("evaluatieficheIds")));
        dropDown.selectByVisibleText("BXL_Atomium");
    }

    @And("^I select the date of today in the Date field$")
    public void i_select_the_date_of_today_in_the_Date_field() throws Throwable {
        driver.findElement(By.name("dateTime")).click();
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
        driver.findElement(By.name("submit")).click();
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
}
