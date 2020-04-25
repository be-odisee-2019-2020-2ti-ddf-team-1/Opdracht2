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

public class addEvaluatieFicheTest {

    WebDriver driver;

    @Given("^I am on the Entry page where I can add a new Entry$")
    public void i_am_on_the_Entry_page_where_I_can_add_a_new_Entry() throws Throwable {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");

        driver = new FirefoxDriver();
        driver.navigate().to("https://localhost:8443/travelbase");
    }

    @When("^I select EF_BXL_Atomium in the BXL_Atomium field$")
    public void i_select_EF_BXL_Atomium_in_the_BXL_Atomium_field() throws Throwable {
        Select drpAtomium = new Select(driver.findElement(By.id("bxlAtomium")));
        drpAtomium.selectByVisibleText("BXL_Atomium");
    }

    @When("^I select the date of today in the Date field$")
    public void i_select_the_date_of_today_in_the_Date_field() throws Throwable {
        driver.findElement(By.id("date")).sendKeys("24042020");
    }

    @When("^I enter \"([^\"]*)\" in the ([^\"]*) field$")
    public void i_enter_in_the_field(String enteredText, String fieldName) throws Throwable {
        driver.findElement(By.id(fieldName)).sendKeys(enteredText);
    }

    @When("^I press on the Submit button$")
    public void i_press_on_the_Submit_button() throws Throwable {
        driver.findElement(By.id("submit")).click();
    }

    @Then("^I should see the following on the screen$")
    public void i_should_see_the_following_on_the_screen(List<String> checklist) throws Throwable {
        new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Details van Entry"));

        String bodyText = driver.findElement(By.tagName("body")).getText();
        for (String st: checklist){
            String text2bFound = st;
            Assert.assertTrue("Did not find this text: "+text2bFound+"\n", bodyText.contains(text2bFound));
        }
    }
}

