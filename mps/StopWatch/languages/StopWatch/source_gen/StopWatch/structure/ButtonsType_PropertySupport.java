package StopWatch.structure;

/*Generated by MPS */

import jetbrains.mps.smodel.PropertySupport;
import java.util.Iterator;
import jetbrains.mps.internal.collections.runtime.ListSequence;

public class ButtonsType_PropertySupport extends PropertySupport {
  public boolean canSetValue(String value) {
    if (value == null) {
      return true;
    }
    Iterator<ButtonsType> constants = ListSequence.fromList(ButtonsType.getConstants()).iterator();
    while (constants.hasNext()) {
      ButtonsType constant = constants.next();
      if (value.equals(constant.getName())) {
        return true;
      }
    }
    return false;
  }

  public String toInternalValue(String value) {
    if (value == null) {
      return null;
    }
    Iterator<ButtonsType> constants = ListSequence.fromList(ButtonsType.getConstants()).iterator();
    while (constants.hasNext()) {
      ButtonsType constant = constants.next();
      if (value.equals(constant.getName())) {
        return constant.getValueAsString();
      }
    }
    return null;
  }

  public String fromInternalValue(String value) {
    ButtonsType constant = ButtonsType.parseValue(value);
    if (constant != null) {
      return constant.getName();
    }
    return "";
  }
}