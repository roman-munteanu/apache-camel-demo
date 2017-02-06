package com.munteanu.models;

public class DemoMessage {

  private String text;

  public DemoMessage() {
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return "DemoMessage{" +
        "text='" + text + '\'' +
        '}';
  }
}
