package com.example.tcccore.utils.sequence;

/**
 * Created by MaXuewen on 2017/11/13.
 */
public enum IdentifyGenerated {
  INSTANCE;

  private Sequence sequence;

  IdentifyGenerated() {
    this.sequence = new Sequence();
  }

  public Long getNextId() {
    return sequence.nextId();
  }
}
