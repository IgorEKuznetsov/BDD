package com.ui.driver;

import com.ui.exceptions.DriverTypeNotSupported;

import org.openqa.selenium.support.events.EventFiringWebDriver;



public class DriverFactory {

  //private String browserType = System.getProperty("browser", "chrome").toLowerCase(Locale.ROOT);

  public EventFiringWebDriver getBrowserDriver(String browserName) {
    switch (browserName) {
      case "chrome":
        return new EventFiringWebDriver(new ChromeWebDriver().getDriver());
      case "firefox":
        return new EventFiringWebDriver(new FirefoxWebDriver().getDriver());
      case "opera":
        return new EventFiringWebDriver(new OperaWebDriver().getDriver());
      default:
        try {
          throw new DriverTypeNotSupported(browserName);
        } catch (DriverTypeNotSupported e) {
          e.printStackTrace();
        }
        return null;
    }
  }
}
