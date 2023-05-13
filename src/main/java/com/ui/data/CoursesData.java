package com.ui.data;

import com.google.inject.Inject;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public enum CoursesData {

  KAFKA_COURSE("Apache Kafka"),
  RECOMMENDER_SYSTEMS("Рекомендательные системы"),
  JAVA_DEVELOPER("Специализация Java-разработчик"),
  MACHINE_LEARNING("Machine Learning"),
  IOS("iOS Developer");




  private String name;

  CoursesData(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
