package com.munteanu.models;

import com.munteanu.enums.Channel;

public class DemoMessage {

  private Channel channel;
  private String text;

  public DemoMessage() {
  }

  public DemoMessage(Channel channel, String text) {
    this.channel = channel;
    this.text = text;
  }

  public Channel getChannel() {
    return channel;
  }

  public void setChannel(Channel channel) {
    this.channel = channel;
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
        "channel=" + channel +
        ", text='" + text + '\'' +
        '}';
  }
}
