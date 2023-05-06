package com.ui.pages;

import com.google.inject.Inject;
import com.ui.support.GuiceScoped;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CoursePage extends BasePageAbs<CoursePage> {


  @Inject
  public CoursePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }


  @FindBy(xpath = "//h1[contains(text(), 'Преподаватели')]")
  private WebElement teachersInfo;

  public void teachersInfoIsVisible() {
    Assertions.assertTrue(teachersInfo.isDisplayed());
  }

}
