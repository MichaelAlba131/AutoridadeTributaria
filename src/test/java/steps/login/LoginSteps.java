package steps.login;

import configuration.classes.ExtractInformationYAML;
import interactions.login.LoginInteractions;
import io.cucumber.java.en.When;
import io.cucumber.java.pt.Dado;

import java.io.IOException;

public class LoginSteps extends LoginInteractions {
    @Dado("que o utilizador acede ao backoffice usando {string} and {string}")
    public void queOUtilizadorAcedeAoBackofficeUsandoAnd(String user, String password) throws IOException, InterruptedException {
        OpenUrl();

        switch (user) {
            case "Temporary Email":
                user = "Email Teste1";
                break;
            case "Email Global Pages":
                user = "Email Teste 2";
                break;
            case "User Sigip":
                user = ExtractInformationYAML.GetInfoUsers().getUsersSigipDev().get(0).getUsername();
                password = ExtractInformationYAML.GetInfoUsers().getUsersSigipDev().get(1).getPassword();
        }
        action()
                .FillUserAndPassword(user, password);

    }
}
