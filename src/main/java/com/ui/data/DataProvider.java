package com.ui.data;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class DataProvider {
  static Stream<Arguments> courseNamesProvider() {
    return Stream.of(
        Arguments.of(CoursesData.JAVA_DEVELOPER),
        Arguments.of(CoursesData.MACHINE_LEARNING),
        Arguments.of(CoursesData.IOS)
    );
  }
}
