package com.ui.steps.common;

import com.google.inject.Inject;
import com.ui.driver.DriverFactory;

import com.ui.support.GuiceScoped;
import io.cucumber.java.ru.Пусть;

public class CommonSteps {

  @Inject
  public GuiceScoped guiceScoped;
  @Inject
  public DriverFactory driverFactory;


  @Пусть("Открыт браузер {string}")
  public void openBrowser(String browserName) {
    guiceScoped.driver = driverFactory.getBrowserDriver(browserName);


  }

}
