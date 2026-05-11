package com.qa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try {
            FileInputStream fis = new FileInputStream(
                "src/test/resources/config.properties"
            );
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("config.properties not found", e);
        }
    }

    public static String get(String key) {
        // System property overrides config file (used by CI)
        String sysProp = System.getProperty(key);
        return (sysProp != null && !sysProp.isEmpty())
            ? sysProp
            : properties.getProperty(key);
    }

    public static String getBaseUrl()     { return get("base.url"); }
    public static String getBrowser()     { return get("browser"); }
    public static boolean isHeadless()    { return Boolean.parseBoolean(get("headless")); }
    public static int getImplicitWait()   { return Integer.parseInt(get("implicit.wait")); }
    public static int getExplicitWait()   { return Integer.parseInt(get("explicit.wait")); }
}
