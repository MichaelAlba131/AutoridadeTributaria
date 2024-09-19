package configuration.classes.ambient;

public class URL {
    public URL() {
    }

    public URL(String system, String value) {
        this.system = system;
        this.value = value;
    }

    private String system;
    private String value;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "\nSystem: " + system + "\nvalue: " + value + "\n";
    }
}