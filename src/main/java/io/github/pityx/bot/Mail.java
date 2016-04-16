package io.github.pityx.bot;

enum Mail {
    CONFIG;

    public String devAddress() {
        return System.getenv("DEV_EMAIL");
    }

    public String server() {
        return System.getenv("EMAIL_SERVER");
    }

    public String username() {
        return System.getenv("EMAIL_LOGIN");
    }

    public String password() {
        return System.getenv("EMAIL_PASSWORD");
    }

}
