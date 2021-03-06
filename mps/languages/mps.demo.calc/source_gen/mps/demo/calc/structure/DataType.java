package mps.demo.calc.structure;

/*Generated by MPS */

import java.util.List;
import jetbrains.mps.internal.collections.runtime.ListSequence;
import jetbrains.mps.internal.collections.runtime.backports.LinkedList;

public enum DataType {
  floating_point("floating point", "double"),
  integer("integer", "int");

  private String myName;

  public String getName() {
    return this.myName;
  }

  public String getValueAsString() {
    return this.myValue;
  }

  public static List<DataType> getConstants() {
    List<DataType> list = ListSequence.fromList(new LinkedList<DataType>());
    ListSequence.fromList(list).addElement(DataType.floating_point);
    ListSequence.fromList(list).addElement(DataType.integer);
    return list;
  }

  public static DataType getDefault() {
    return DataType.floating_point;
  }

  public static DataType parseValue(String value) {
    if (value == null) {
      return DataType.getDefault();
    }
    if (value.equals(DataType.floating_point.getValueAsString())) {
      return DataType.floating_point;
    }
    if (value.equals(DataType.integer.getValueAsString())) {
      return DataType.integer;
    }
    return DataType.getDefault();
  }

  private String myValue;

  DataType(String name, String value) {
    this.myName = name;
    this.myValue = value;
  }

  public String getValue() {
    return this.myValue;
  }
}
