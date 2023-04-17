package com.ui.baseobject;

import com.google.inject.Inject;
import com.ui.support.GuiceScoped;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ui.waiters.CustomWaiter;

public abstract class BaseObj<T> {

  protected WebDriver driver;
  protected CustomWaiter waiter;
  protected Actions actions;
  protected Logger log;
  protected GuiceScoped guiceScoped;
  @Inject
  public BaseObj(GuiceScoped guiceScoped) {
    this.guiceScoped = guiceScoped;
    this.driver = guiceScoped.driver;

    this.actions = new Actions(driver);
    this.log = LoggerFactory.getLogger("otus");
    this.waiter = new CustomWaiter(driver);

    PageFactory.initElements(driver, this);
    driver.manage().window().maximize();

  }
}
