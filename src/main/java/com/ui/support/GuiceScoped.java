package com.ui.support;

import com.ui.driver.DriverFactory;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

@ScenarioScoped
public class GuiceScoped {
  public WebDriver driver = new DriverFactory().getBrowserDriver();
}
