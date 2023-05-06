package com.ui.hooks;

import com.google.inject.Inject;
import com.ui.support.GuiceScoped;
import io.cucumber.java.After;

public class Hooks {

  @Inject
  public GuiceScoped guiceScoped;

  @After
  public void afterScenario() {
    if (guiceScoped.driver != null) {
      guiceScoped.driver.close();

    }
  }
}
