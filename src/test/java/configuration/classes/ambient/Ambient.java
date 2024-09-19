package configuration.classes.ambient;

import java.util.List;

public class Ambient {

    public Ambient() {
    }

    public Ambient(String name, String version, String date, String ambient,
                    List<URL> url) {
        this.name = name;
        this.version = version;
        this.date = date;
        this.ambient = ambient;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Ambient{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", date='" + date + '\'' +
                ", ambient='" + ambient + '\'' +
                ", url=" + url +
                '}';
    }
    private String name;
    private String version;
    private String date;
    private String ambient;
    private List<URL> url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmbient() {
        return ambient;
    }

    public void setAmbient(String ambient) {
        this.ambient = ambient;
    }

    public List<URL> getUrl() {
        return url;
    }

    public void setUrl(List<URL> url) {
        this.url = url;
    }
}