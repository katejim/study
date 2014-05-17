import evaluator.Evaluator;
import evaluator.ExpressionVisitor;
import javafx.concurrent.Task;
import parser.Parser;
import parser.expressions.Exp;
import undo.Action;
import undo.ActionList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KateKate on 15.05.14.
 */

public class REPLConsole {
    final JComboBox<String> optionPane = new JComboBox<String>();
    JTextPane source;

    private ActionList actionList = new ActionList();
    private UndoManager undoListener = new UndoManager();

    public static final String SIMPLIFY = "Simplify";
    public static final String EVALUATE = "Evaluate";
    public static final String GREETING = System.lineSeparator() + ">";

    private class EditHighLightListener implements DocumentListener {
        public void insertUpdate(DocumentEvent e) {
            handleHighLight(e);
        }

        public void removeUpdate(DocumentEvent e) {
            handleHighLight(e);
        }

        public void changedUpdate(DocumentEvent e) {
        }

        private void handleHighLight(DocumentEvent e) {
            final StyledDocument document = (StyledDocument) e.getDocument();


            try {
                String text = document.getText(0, document.getLength());
                final int startIndex = lastLineIndex(document) + GREETING.length();
                final String userInput = text.substring(startIndex);

                Parser parser = new Parser(userInput);
                final Exp exp = parser.parse();
                document.removeUndoableEditListener(undoListener);
                if (exp == null) {
                    SwingUtilities.invokeLater(new Task() {
                        @Override
                        public void run() {
                            super.run();
                            document.removeUndoableEditListener(undoListener);
                            document.setCharacterAttributes(startIndex, userInput.length(), document.getStyle("error"), true);
                        }

                        @Override
                        protected Object call() throws Exception {
                            return null;
                        }
                    });

                } else {
                    SwingUtilities.invokeLater(new Task() {
                        @Override
                        public void run() {
                            document.removeUndoableEditListener(undoListener);
                            Printer printer = new Printer();
                            document.setCharacterAttributes(startIndex, userInput.length(), document.getStyle("sign"), true);
                            exp.accept(printer);
                            List<Printer.ElementDescription> op = printer.varAndNumElements;
                            for (Printer.ElementDescription el : op) {
                                document.setCharacterAttributes(startIndex + el.start, el.length, document.getStyle("variable"), true);
                                if ((el.type == Printer.ElementDescription.Type.VARIABLE)
                                        && !getEvalMode()
                                        && !getContext().containsKey(userInput.substring(el.start, el.start + el.length))) {
                                    document.setCharacterAttributes(startIndex + el.start,
                                            el.length, document.getStyle("underfind"), true);
                                }
                            }
                        }

                        @Override
                        protected Object call() throws Exception {
                            return null;
                        }
                    });
                }
                document.addUndoableEditListener(undoListener);
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }
    }

    private EditHighLightListener editHighLightListener = new EditHighLightListener();

    void init() {
        JFrame frame = new JFrame();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setLayout(new BorderLayout());
        optionPane.addItem(SIMPLIFY);
        optionPane.addItem(EVALUATE);
        frame.add(optionPane, "North");

        JTextPane textArea = new JTextPane(setStyles());
        final AbstractDocument document = (AbstractDocument) textArea.getDocument();

        textArea.setText("Welcome to REPL Console! ");
        textArea.setText(GREETING);


        textArea.setEditable(true);
        frame.add(textArea, "Center");

        document.addUndoableEditListener(undoListener);
        document.addDocumentListener(editHighLightListener);

        undoListener.discardAllEdits();
        textArea.getKeymap().addActionForKeyStroke(
                KeyStroke.getKeyStroke("control Z"),
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (undoListener.canUndo()) {
                            undoListener.undo();
                        }
                    }
                }
        );
        undoListener.discardAllEdits();
        textArea.getKeymap().addActionForKeyStroke(
                KeyStroke.getKeyStroke("control shift Z"),
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Action last = actionList.getLastState();
                        optionPane.setSelectedIndex(last.simplify ? 0 : 1);
                        removeLastExpression();
                    }

                    private void removeLastExpression() {
                        try {
                            String text = document.getText(0, document.getLength());
                            text = text.substring(0, text.lastIndexOf(System.lineSeparator()));
                            int startIndex = text.lastIndexOf('>') + 1;
                            document.remove(startIndex, document.getLength() - startIndex);
                        } catch (BadLocationException e1) {
                        }
                    }

                }
        );

        textArea.getKeymap().addActionForKeyStroke(KeyStroke.getKeyStroke("ENTER"), new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                source = (JTextPane) e.getSource();
                StyledDocument document = (StyledDocument) source.getDocument();
                document.removeDocumentListener(editHighLightListener);
                document.removeUndoableEditListener(undoListener);

                try {
                    String text = document.getText(0, document.getLength());
                    String userInput = text.substring(lastLineIndex(document) + GREETING.length());

                    Parser parser = new Parser(userInput);
                    Exp exp = parser.parse();
                    String result;
                    if (exp == null) {
                        result = "FAIL: wrong instruction";
                    } else {
                        Printer printer = new Printer();

                        Evaluator expressionVisitor = new Evaluator(getContext(), getEvalMode());
                        actionList.addAction(new Action(expressionVisitor.context, getEvalMode()));
                        try {
                            Exp rez = exp.evaluate(expressionVisitor);
                            rez.accept(printer);
                            result = printer.result;
                        } catch (ExpressionVisitor.Error error) {
                            result = "FAIL: " + error.getMessage();
                        }
                    }
                    document.insertString(endOffset(document), System.lineSeparator() + result, null);
                    document.insertString(endOffset(document), GREETING, null);
                    source.setCaretPosition(endOffset(document));
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }

                document.addDocumentListener(editHighLightListener);
                document.addUndoableEditListener(undoListener);
            }

            private int endOffset(Document document) {
                return document.getEndPosition().getOffset() - 1;
            }

        });

        frame.setVisible(true);
        frame.setSize(500, 300);
    }


    public static void main(String[] args) {
        REPLConsole replConsole = new REPLConsole();
        replConsole.init();

    }

    private static int lastLineIndex(Document document) throws BadLocationException {
        return document.getText(0, document.getLength()).lastIndexOf(System.lineSeparator());
    }

    private StyledDocument setStyles() {
        StyledDocument styledDocument = new DefaultStyledDocument();
        Style ok = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

        Style error = styledDocument.addStyle("error", ok);
        StyleConstants.setForeground(error, Color.RED);
        StyleConstants.setUnderline(error, true);

        Style underfind = styledDocument.addStyle("underfind", ok);
        StyleConstants.setBackground(underfind, Color.LIGHT_GRAY);
        StyleConstants.setForeground(underfind, Color.RED);
        StyleConstants.setUnderline(underfind, true);

        Style var = styledDocument.addStyle("variable", ok);
        StyleConstants.setBackground(var, Color.YELLOW);

        Style sign = styledDocument.addStyle("sign", ok);
        StyleConstants.setBackground(sign, Color.GREEN);
        return styledDocument;
    }

    private boolean getEvalMode() {
        return SIMPLIFY.equals(optionPane.getSelectedItem());
    }

    private Map<String, Exp> getContext() {
        Map<String, Exp> result = new HashMap<String, Exp>();
        if (!actionList.listActions.empty()) {
            result.putAll(actionList.listActions.peek().context);
        }
        return result;
    }
}