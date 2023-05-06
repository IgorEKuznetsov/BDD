package com.ui.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxWebDriver implements IDriver {
  @Override
  public WebDriver getDriver() {
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    firefoxOptions.addArguments("--no-sandbox");
    firefoxOptions.addArguments("--ignore-certificate-errors");
    firefoxOptions.addArguments("--start-maximized");


    WebDriverManager.firefoxdriver().setup();

    return new FirefoxDriver(firefoxOptions);
  }
}
