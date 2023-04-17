package com.ui.steps.pages;

import com.google.inject.Inject;
import com.ui.pages.MainPage;
import io.cucumber.java.ru.Тогда;

public class MainPageSteps {

  @Inject
  private MainPage mainPage;

  @Тогда("Открыта главная страница в браузере")
  public void openPageTest() {
    mainPage.open();
  }
}
