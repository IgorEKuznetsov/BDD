package com.ui.steps.pages;

import com.google.inject.Inject;
import com.ui.data.CoursesData;
import com.ui.pages.CoursePage;
import com.ui.pages.MainPage;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;



public class MainPageSteps {

  @Inject
  public MainPage mainPage;



  @Если("Открыта главная страница")
  public void openPageTest() {
    mainPage.open();
  }

  @И("Выбран курс {string}")
  public void openCourse(String course) {
    mainPage.clickCourseByName(CoursesData.valueOf(course));
  }


  @И("Выбрана дата курса {string}")
  public void clickDate(String date) {
    mainPage.clickCourseByDate(date);
  }
}
