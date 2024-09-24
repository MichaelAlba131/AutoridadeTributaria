package configuration.classes.users;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Users {

    public Users() {
    }

    public Users(List<userSIGIPDEV> userSIGIPDEV) {
        this.userSIGIPDEV = userSIGIPDEV;
    }

    @Override
    public String toString() {
        return "Users{" +
                "usersSigipDev=" + userSIGIPDEV +
                '}';
    }


    public List<userSIGIPDEV> getUsersSigipDev() {
        return userSIGIPDEV;
    }

    public void setUsersSigipDev(List<userSIGIPDEV> userSIGIPDEV) {
        this.userSIGIPDEV = userSIGIPDEV;
    }

    public List<userSIGIPDEV> userSIGIPDEV;
}
