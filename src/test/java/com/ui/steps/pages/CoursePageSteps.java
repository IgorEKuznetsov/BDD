package com.ui.steps.pages;

import com.google.inject.Inject;
import com.ui.data.CoursesData;
import com.ui.pages.CoursePage;
import io.cucumber.java.ru.Тогда;

public class CoursePageSteps {
  @Inject
  public CoursePage coursePage;

  @Тогда("Отображен хедер курса {string}")
  public void headerIsVisible(String course) {
    coursePage.headerIsEqualTo(CoursesData.valueOf(course), true);
  }

  @Тогда("^Открыта страница курса указанной даты или позже указанной даты$")
  public void coursePageIsOpen() {
    coursePage.teachersInfoIsVisible();
  }



}
