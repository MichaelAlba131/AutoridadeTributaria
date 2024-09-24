package configuration.classes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import configuration.classes.ambient.Ambient;
import configuration.classes.ambient.URL;
import configuration.classes.users.Users;
import java.io.File;
import java.io.IOException;

public class ExtractInformationYAML {

    public enum TypeLogin {
        QUOTA2
    }

    public static Ambient GetInfoAmbient() throws IOException {
        File file = new File(System.getProperty("user.dir") + "/src/test/java/configuration/ambient.yaml");
        if (!file.exists()) file.mkdirs();
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        Ambient ambient = om.readValue(file, Ambient.class);
        return ambient;
    }

    public static Users GetInfoUsers() throws IOException {
        File file = new File(System.getProperty("user.dir") + "/src/test/java/configuration/users.yaml");
        if (!file.exists()) file.mkdirs();
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        Users ambient = om.readValue(file, Users.class);
        return ambient;
    }

    public static int FindIndexURLFile(String system_find) throws IOException {
        int index = 0;
        for (URL system : GetInfoAmbient().getUrl()) {
            if (system.getSystem().equals(system_find))
                return index;
            index++;
        }
        return 0;
    }

    public static String URLAmbient(String TypeLogin) throws IOException {
        return GetInfoAmbient().getUrl().get(FindIndexURLFile(TypeLogin)).getValue();
    }
}