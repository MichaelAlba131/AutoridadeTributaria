package steps;

import functions.Driver;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.util.logging.Level;
import java.util.logging.Logger;

import static functions.FunctionsHook.mountDriverType;

public class Hook {

@Before
    public void before(Scenario scenario) throws Exception {
    Logger.getLogger("org").setLevel(Level.OFF);
    Logger.getLogger("akka").setLevel(Level.OFF);

    Driver.driver = mountDriverType();
}
}
