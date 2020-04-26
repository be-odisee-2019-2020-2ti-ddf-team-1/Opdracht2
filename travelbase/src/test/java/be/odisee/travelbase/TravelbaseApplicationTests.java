package be.odisee.travelbase;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.*;
import org.junit.runners.Suite;

@RunWith(Cucumber.class)
@CucumberOptions(format={"pretty", "html:target/cucumber"},tags={"~@skip"}, features = "src/test/resources/be.odisee.travelbase")
@Suite.SuiteClasses({goToEvaluatieFicheTest.class})
public class TravelbaseApplicationTests {

}

