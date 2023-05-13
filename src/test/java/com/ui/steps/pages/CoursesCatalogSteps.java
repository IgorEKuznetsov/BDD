package com.ui.steps.pages;

import com.google.inject.Inject;
import com.ui.pages.CoursesCatalogPage;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;

import java.io.IOException;


public class CoursesCatalogSteps {
  @Inject
  CoursesCatalogPage coursesCatalogPage;


  @Если("Открыта категория {string}")
  public void openProgrammingCategoryPage(String categoryName) {
    coursesCatalogPage.open(categoryName);
  }

  @И("Найдены подготовительные курсы")
  public void searchPreparatoryCourses() {
    coursesCatalogPage.searchPreparatoryCourseElements();
  }

  @Тогда("Открыт курс {string}")
  public void openCourseByFilter(String filter) throws IOException {
    coursesCatalogPage.filterCourseByPrice(filter);
  }

}
