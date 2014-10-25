package StopWatch.structure;

/*Generated by MPS */

import jetbrains.mps.smodel.PropertySupport;
import java.util.Iterator;
import jetbrains.mps.internal.collections.runtime.ListSequence;

public class Memorization_PropertySupport extends PropertySupport {
  public boolean canSetValue(String value) {
    if (value == null) {
      return true;
    }
    Iterator<Memorization> constants = ListSequence.fromList(Memorization.getConstants()).iterator();
    while (constants.hasNext()) {
      Memorization constant = constants.next();
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
    Iterator<Memorization> constants = ListSequence.fromList(Memorization.getConstants()).iterator();
    while (constants.hasNext()) {
      Memorization constant = constants.next();
      if (value.equals(constant.getName())) {
        return constant.getValueAsString();
      }
    }
    return null;
  }

  public String fromInternalValue(String value) {
    Memorization constant = Memorization.parseValue(value);
    if (constant != null) {
      return constant.getName();
    }
    return "";
  }
}
