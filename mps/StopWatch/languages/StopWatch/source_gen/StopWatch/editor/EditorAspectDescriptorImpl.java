package StopWatch.editor;

/*Generated by MPS */

import jetbrains.mps.openapi.editor.descriptor.EditorAspectDescriptor;
import java.util.Collection;
import jetbrains.mps.openapi.editor.descriptor.ConceptEditor;
import jetbrains.mps.smodel.runtime.ConceptDescriptor;
import java.util.Arrays;
import java.util.Collections;
import jetbrains.mps.openapi.editor.descriptor.ConceptEditorComponent;

public class EditorAspectDescriptorImpl implements EditorAspectDescriptor {


  public Collection<ConceptEditor> getEditors(ConceptDescriptor descriptor) {
    switch (Arrays.binarySearch(stringSwitchCases_xbvbvu_a0a0b, descriptor.getConceptFqName())) {
      case 0:
        return Collections.<ConceptEditor>singletonList(new ColorReference_Editor());
      case 1:
        return Collections.<ConceptEditor>singletonList(new StopWatch_Editor());
      case 2:
        return Collections.<ConceptEditor>singletonList(new Time_Editor());
      case 3:
        return Collections.<ConceptEditor>singletonList(new UI_Editor());
      default:
    }
    return Collections.<ConceptEditor>emptyList();
  }

  public Collection<ConceptEditorComponent> getEditorComponents(ConceptDescriptor descriptor, String editorComponentId) {
    switch (Arrays.binarySearch(stringSwitchCases_xbvbvu_a0a0c, descriptor.getConceptFqName())) {
      case 0:
        switch (Arrays.binarySearch(stringSwitchCases_xbvbvu_a0a0a0a2, editorComponentId)) {
          case 0:
            return Collections.<ConceptEditorComponent>singletonList(new ShapeColor());
          case 1:
            return Collections.<ConceptEditorComponent>singletonList(new ShapeComponent());
          default:
        }
        break;
      default:
    }
    return Collections.<ConceptEditorComponent>emptyList();
  }



  private static String[] stringSwitchCases_xbvbvu_a0a0b = new String[]{"StopWatch.structure.ColorReference", "StopWatch.structure.StopWatch", "StopWatch.structure.Time", "StopWatch.structure.UI"};
  private static String[] stringSwitchCases_xbvbvu_a0a0a0a2 = new String[]{"StopWatch.editor.ShapeColor", "StopWatch.editor.ShapeComponent"};
  private static String[] stringSwitchCases_xbvbvu_a0a0c = new String[]{"StopWatch.structure.Shape"};
}
