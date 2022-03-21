package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/features",
		glue = "stepDefintion",
		stepNotifications = true,
		tags = "@AddPlace",
		plugin ={ "pretty","json:target/jsonReports/cucumber-reports.json" ,},//pretty for gt into verbose mode and console output is in pretty format
		monochrome = true // the monochrome option is set to false, then the console output is not as readable as it should be
		)	

//"html:target/jsonReports/cucumber-reports.html",


public class TestRunner {
	
	

}
