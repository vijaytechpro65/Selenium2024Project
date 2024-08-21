package com.qa.opencart.Factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserOptions {
	private Properties prop;

    public BrowserOptions(Properties prop) {
        this.prop = prop;
    }

    public ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            options.addArguments("--incognito");
        }
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            options.addArguments("--headless=new");
        }
        return options;
    }

    public FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            options.addArguments("-private");
        }
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            options.addArguments("-headless");
        }
        return options;
    }

    public EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            options.addArguments("-inprivate");
        }
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            options.addArguments("-headless");
        }
        return options;
    }
}


