package io.github.pityx.bot;

enum Bot {
    CONFIG;

    public String getToken() {
        return System.getenv("TOKEN");
    }

    public String getBotName() {
        return "PITYXBOT";
    }
}
