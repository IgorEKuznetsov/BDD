package com.ui.support;

import com.google.inject.Inject;
import com.ui.driver.DriverFactory;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;


@ScenarioScoped
public class GuiceScoped {
  @SuppressFBWarnings("UWF_NULL_FIELD")
  public WebDriver driver = null;

  public WebDriver getDriver() {
    return driver;
  }
}
