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


  //protected WebDriver driver;
  @Inject
  public CustomWaiter waiter;

  protected Actions actions;
  protected Logger log;
  @Inject
  public GuiceScoped guiceScoped;

  @Inject
  public BaseObj(GuiceScoped guiceScoped) {
    this.guiceScoped = guiceScoped;
    this.actions = new Actions(this.guiceScoped.driver);
    this.log = LoggerFactory.getLogger("otus");
    this.waiter = new CustomWaiter(this.guiceScoped);
    PageFactory.initElements(this.guiceScoped.driver, this);

  }
}
