package StopWatch.structure;

/*Generated by MPS */

import jetbrains.mps.smodel.runtime.ConceptDescriptor;
import java.util.Arrays;
import jetbrains.mps.smodel.runtime.impl.ConceptDescriptorBuilder;
import jetbrains.mps.smodel.runtime.interpreted.StructureAspectInterpreted;

public class StructureAspectDescriptor implements jetbrains.mps.smodel.runtime.StructureAspectDescriptor {
  public StructureAspectDescriptor() {
  }

  public ConceptDescriptor getDescriptor(String conceptFqName) {
    switch (Arrays.binarySearch(stringSwitchCases_1htk8d_a0a0b, conceptFqName)) {
      case 0:
        return new ConceptDescriptorBuilder("StopWatch.structure.Analog").super_("StopWatch.structure.StopWatch").parents("StopWatch.structure.StopWatch").children(new String[]{"button"}, new boolean[]{false}).alias("analog", "").create();
      case 1:
        return new ConceptDescriptorBuilder("StopWatch.structure.Button").super_("jetbrains.mps.lang.core.structure.BaseConcept").parents("jetbrains.mps.lang.core.structure.BaseConcept").properties("form").children(new String[]{"place"}, new boolean[]{false}).create();
      case 2:
        return new ConceptDescriptorBuilder("StopWatch.structure.Coordinates").super_("jetbrains.mps.lang.core.structure.BaseConcept").parents("jetbrains.mps.lang.core.structure.BaseConcept").properties("leftX", "leftY", "size").create();
      case 3:
        return new ConceptDescriptorBuilder("StopWatch.structure.Dial").super_("jetbrains.mps.lang.core.structure.BaseConcept").parents("jetbrains.mps.lang.core.structure.BaseConcept").properties("form", "fontSize").children(new String[]{"place"}, new boolean[]{false}).create();
      case 4:
        return new ConceptDescriptorBuilder("StopWatch.structure.Digital").super_("StopWatch.structure.StopWatch").parents("StopWatch.structure.StopWatch").children(new String[]{"timePrecision", "startButton", "stopButton", "pauseButton", "specialButtons"}, new boolean[]{false, false, false, false, true}).alias("digital", "").create();
      case 5:
        return new ConceptDescriptorBuilder("StopWatch.structure.Dimensions").super_("jetbrains.mps.lang.core.structure.BaseConcept").parents("jetbrains.mps.lang.core.structure.BaseConcept").properties("height", "width", "depth").alias("dimensions", "").create();
      case 6:
        return new ConceptDescriptorBuilder("StopWatch.structure.LightButton").super_("StopWatch.structure.SpecialButtons").parents("StopWatch.structure.SpecialButtons").properties("light").create();
      case 7:
        return new ConceptDescriptorBuilder("StopWatch.structure.Main").super_("jetbrains.mps.lang.core.structure.BaseConcept").parents("jetbrains.mps.lang.core.structure.BaseConcept", "jetbrains.mps.lang.core.structure.INamedConcept").children(new String[]{"stopwatch"}, new boolean[]{false}).create();
      case 8:
        return new ConceptDescriptorBuilder("StopWatch.structure.Memorization").super_("StopWatch.structure.SpecialButtons").parents("StopWatch.structure.SpecialButtons").properties("memorization").children(new String[]{"button"}, new boolean[]{false}).create();
      case 9:
        return new ConceptDescriptorBuilder("StopWatch.structure.ShowCurrentType").super_("StopWatch.structure.SpecialButtons").parents("StopWatch.structure.SpecialButtons").properties("showTime").alias("showcurrenttime", "").create();
      case 10:
        return new ConceptDescriptorBuilder("StopWatch.structure.SpecialButtons").super_("StopWatch.structure.Button").parents("StopWatch.structure.Button").abstract_().create();
      case 11:
        return new ConceptDescriptorBuilder("StopWatch.structure.StopWatch").super_("jetbrains.mps.lang.core.structure.BaseConcept").parents("jetbrains.mps.lang.core.structure.BaseConcept", "jetbrains.mps.lang.core.structure.INamedConcept").properties("weight", "form").children(new String[]{"dial", "dimensions"}, new boolean[]{false, false}).abstract_().create();
      case 12:
        return new ConceptDescriptorBuilder("StopWatch.structure.Time").super_("jetbrains.mps.lang.core.structure.BaseConcept").parents("jetbrains.mps.lang.core.structure.BaseConcept").properties("value", "precision").create();
      default:
        return StructureAspectInterpreted.getInstance().getDescriptor(conceptFqName);
    }
  }

  private static String[] stringSwitchCases_1htk8d_a0a0b = new String[]{"StopWatch.structure.Analog", "StopWatch.structure.Button", "StopWatch.structure.Coordinates", "StopWatch.structure.Dial", "StopWatch.structure.Digital", "StopWatch.structure.Dimensions", "StopWatch.structure.LightButton", "StopWatch.structure.Main", "StopWatch.structure.Memorization", "StopWatch.structure.ShowCurrentType", "StopWatch.structure.SpecialButtons", "StopWatch.structure.StopWatch", "StopWatch.structure.Time"};
}
