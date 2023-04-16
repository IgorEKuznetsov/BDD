package com.ui.baseobject;

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
  public BaseObj(WebDriver driver) {
    this.driver = driver;
    this.actions = new Actions(driver);
    this.log = LoggerFactory.getLogger("otus");
    this.waiter = new CustomWaiter(driver);
    PageFactory.initElements(driver, this);
    driver.manage().window().maximize();

  }
}
