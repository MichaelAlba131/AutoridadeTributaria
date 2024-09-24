package configuration.classes.users;

import lombok.Getter;
import lombok.Setter;

public class userSIGIPDEV {

    public userSIGIPDEV() {
    }

    public userSIGIPDEV(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "userSIGIPDEV{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
}
