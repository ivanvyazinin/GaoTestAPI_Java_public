package main.java.common;

public class Environment {
    private String host;

    Environment(String host) {
        this.host = host;
    }

    public String getAPIAddress() {
        return String.format("http://%s", host);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "Environment{" +
                "host='" + host + '\'' +
                '}';
    }
}
