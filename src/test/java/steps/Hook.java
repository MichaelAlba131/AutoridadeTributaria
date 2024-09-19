package steps;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Hook {

@Before
    public void before(Scenario scenario) throws Exception {
    Logger.getLogger("org").setLevel(Level.OFF);
    Logger.getLogger("akka").setLevel(Level.OFF);
}
}
