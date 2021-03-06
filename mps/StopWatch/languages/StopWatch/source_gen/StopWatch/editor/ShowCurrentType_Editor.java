package StopWatch.editor;

/*Generated by MPS */

import jetbrains.mps.nodeEditor.DefaultNodeEditor;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.EditorContext;
import org.jetbrains.mps.openapi.model.SNode;
import jetbrains.mps.nodeEditor.cells.EditorCell_Collection;
import jetbrains.mps.nodeEditor.cells.EditorCell_Constant;
import jetbrains.mps.openapi.editor.style.Style;
import jetbrains.mps.editor.runtime.style.StyleImpl;
import jetbrains.mps.editor.runtime.style.StyleAttributes;
import jetbrains.mps.nodeEditor.cellProviders.CellProviderWithRole;
import jetbrains.mps.lang.editor.cellProviders.PropertyCellProvider;
import jetbrains.mps.smodel.IOperationContext;
import jetbrains.mps.nodeEditor.EditorManager;

public class ShowCurrentType_Editor extends DefaultNodeEditor {
  public EditorCell createEditorCell(EditorContext editorContext, SNode node) {
    return this.createCollection_wbzat7_a(editorContext, node);
  }

  private EditorCell createCollection_wbzat7_a(EditorContext editorContext, SNode node) {
    EditorCell_Collection editorCell = EditorCell_Collection.createVertical(editorContext, node);
    editorCell.setCellId("Collection_wbzat7_a");
    editorCell.setBig(true);
    editorCell.addEditorCell(this.createConstant_wbzat7_a0(editorContext, node));
    editorCell.addEditorCell(this.createCollection_wbzat7_b0(editorContext, node));
    editorCell.addEditorCell(this.createCollection_wbzat7_c0(editorContext, node));
    return editorCell;
  }

  private EditorCell createConstant_wbzat7_a0(EditorContext editorContext, SNode node) {
    EditorCell_Constant editorCell = new EditorCell_Constant(editorContext, node, "CURRENT TIME:");
    editorCell.setCellId("Constant_wbzat7_a0");
    editorCell.setDefaultText("");
    return editorCell;
  }

  private EditorCell createCollection_wbzat7_b0(EditorContext editorContext, SNode node) {
    EditorCell_Collection editorCell = EditorCell_Collection.createHorizontal(editorContext, node);
    editorCell.setCellId("Collection_wbzat7_b0");
    Style style = new StyleImpl();
    style.set(StyleAttributes.SELECTABLE, false);
    editorCell.getStyle().putAll(style);
    editorCell.addEditorCell(this.createConstant_wbzat7_a1a(editorContext, node));
    editorCell.addEditorCell(this.createProperty_wbzat7_b1a(editorContext, node));
    return editorCell;
  }

  private EditorCell createConstant_wbzat7_a1a(EditorContext editorContext, SNode node) {
    EditorCell_Constant editorCell = new EditorCell_Constant(editorContext, node, "showTime:");
    editorCell.setCellId("Constant_wbzat7_a1a");
    editorCell.setDefaultText("");
    return editorCell;
  }

  private EditorCell createProperty_wbzat7_b1a(EditorContext editorContext, SNode node) {
    CellProviderWithRole provider = new PropertyCellProvider(node, editorContext);
    provider.setRole("showTime");
    provider.setNoTargetText("<no showTime>");
    EditorCell editorCell;
    editorCell = provider.createEditorCell(editorContext);
    editorCell.setCellId("property_showTime");
    editorCell.setSubstituteInfo(provider.createDefaultSubstituteInfo());
    SNode attributeConcept = provider.getRoleAttribute();
    Class attributeKind = provider.getRoleAttributeClass();
    if (attributeConcept != null) {
      IOperationContext opContext = editorContext.getOperationContext();
      EditorManager manager = EditorManager.getInstanceFromContext(opContext);
      return manager.createNodeRoleAttributeCell(editorContext, attributeConcept, attributeKind, editorCell);
    } else
    return editorCell;
  }

  private EditorCell createCollection_wbzat7_c0(EditorContext editorContext, SNode node) {
    EditorCell_Collection editorCell = EditorCell_Collection.createHorizontal(editorContext, node);
    editorCell.setCellId("Collection_wbzat7_c0");
    Style style = new StyleImpl();
    style.set(StyleAttributes.SELECTABLE, false);
    editorCell.getStyle().putAll(style);
    editorCell.addEditorCell(this.createConstant_wbzat7_a2a(editorContext, node));
    editorCell.addEditorCell(this.createComponent_wbzat7_b2a(editorContext, node));
    return editorCell;
  }

  private EditorCell createConstant_wbzat7_a2a(EditorContext editorContext, SNode node) {
    EditorCell_Constant editorCell = new EditorCell_Constant(editorContext, node, "button:");
    editorCell.setCellId("Constant_wbzat7_a2a");
    editorCell.setDefaultText("");
    return editorCell;
  }

  private EditorCell createComponent_wbzat7_b2a(EditorContext editorContext, SNode node) {
    EditorCell editorCell = editorContext.getCellFactory().createEditorComponentCell(node, "StopWatch.editor.ButtonComponent");
    return editorCell;
  }
}
