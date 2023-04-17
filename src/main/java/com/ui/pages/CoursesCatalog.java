package com.ui.pages;

import com.google.inject.Inject;
import com.ui.annotations.Path;
import com.ui.support.GuiceScoped;
import org.openqa.selenium.WebDriver;
@Path("/catalog/courses?categories=programming")
public class CoursesCatalog extends BasePageAbs<CoursesCatalog> {
  @Inject
  public CoursesCatalog(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

}
