package configuration.classes.users;

import java.util.List;

public class Users {

    public Users() {
    }

    public Users(List<userSIGIPDEV> userSIGIPDEV,List<userSIGIPDEV> userSIGIPQUA) {
        this.userSIGIPDEV = userSIGIPDEV;
        this.userSIGIPQUA = userSIGIPQUA;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userSIGIPDEV=" + userSIGIPDEV +
                "userSIGIPQUA=" + userSIGIPQUA +
                '}';
    }

    public List<userSIGIPDEV> userSIGIPDEV;

    public List<userSIGIPDEV> userSIGIPQUA;

    public List<configuration.classes.users.userSIGIPDEV> getUserSIGIPDEV() {
        return userSIGIPDEV;
    }

    public List<configuration.classes.users.userSIGIPDEV> getUserSIGIPQUA() {
        return userSIGIPQUA;
    }
}