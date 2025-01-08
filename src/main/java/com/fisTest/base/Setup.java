package com.fisTest.base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Setup {
    public static Properties properties;

    public Setup(){

    }

    @BeforeSuite(groups = {"All"})
    public void setUp(){
        try{
            InputStream inputStream = new FileInputStream("configs/config.properties");
            properties = new Properties();
            properties.load(inputStream);
        }catch (Exception e){
            throw new RuntimeException("config file not found, or unable to read it", e);
        }
    }

    @AfterSuite
    public void cleanUp(){
        DriverFactory.quitDriver();
    }


}
