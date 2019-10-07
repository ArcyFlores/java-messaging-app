package edu.northeastern.ccs.im.Utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoggerSwitch {

    LoggerSwitch() {
        switchAppender();
    }

    public static void main(String s) {
        if (s != null) {
            LoggerSwitch loggerSwitch = new LoggerSwitch();
        }
        return;
    }

    private void switchAppender() {
        Properties props = new Properties();
        try {
            InputStream configStream = getClass().getResourceAsStream("/log4j.properties");
            props.load(configStream);
            configStream.close();
        } catch (IOException e) {
            System.out.println("Error: Cannot laod configuration file ");
        }
        props.setProperty("log4j.rootLogger", "OFF");
//        props.setProperty("log4j.appender.file.File", "out.log");
        LogManager.resetConfiguration();
        PropertyConfigurator.configure(props);
    }
}
