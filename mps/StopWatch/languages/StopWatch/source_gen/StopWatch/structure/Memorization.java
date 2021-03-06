package StopWatch.structure;

/*Generated by MPS */

import java.util.List;
import jetbrains.mps.internal.collections.runtime.ListSequence;
import jetbrains.mps.internal.collections.runtime.backports.LinkedList;

public enum Memorization {
  not_enable("not enable", false),
  enable("enable", true);

  private String myName;

  public String getName() {
    return this.myName;
  }

  public String getValueAsString() {
    return "" + this.myValue;
  }

  public static List<Memorization> getConstants() {
    List<Memorization> list = ListSequence.fromList(new LinkedList<Memorization>());
    ListSequence.fromList(list).addElement(Memorization.not_enable);
    ListSequence.fromList(list).addElement(Memorization.enable);
    return list;
  }

  public static Memorization getDefault() {
    return null;
  }

  public static Memorization parseValue(String value) {
    if (value == null) {
      return Memorization.getDefault();
    }
    if (value.equals(Memorization.not_enable.getValueAsString())) {
      return Memorization.not_enable;
    }
    if (value.equals(Memorization.enable.getValueAsString())) {
      return Memorization.enable;
    }
    return Memorization.getDefault();
  }

  private boolean myValue;

  Memorization(String name, boolean value) {
    this.myName = name;
    this.myValue = value;
  }

  public boolean getValue() {
    return this.myValue;
  }
}
