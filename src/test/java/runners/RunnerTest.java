package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/feature"},
        glue = {"steps"},
        //tags = "@API",
        plugin = {"pretty",
        "html:target/cucumber",
        "json:target/cucumber.json"},
        dryRun = false,
        monochrome = false
)

public class RunnerTest {
    @AfterClass
    public static void tearDown() {
        System.out.println("Teardown");
    }
}
