package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public void init() {
        createNewDocument();
    }

    public void exit() {
        System.exit(0);
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }

    public HTMLDocument getDocument() {
        return document;
    }

    public void resetDocument() {
        UndoListener undoListener = view.getUndoListener();
        if (document != null) {
            document.removeUndoableEditListener(undoListener);
        }

        HTMLEditorKit kit = new HTMLEditorKit();
        document = (HTMLDocument)kit.createDefaultDocument();
        document.addUndoableEditListener(undoListener);

        view.update();
    }

    public void setPlainText(String text) {
        resetDocument();
        StringReader reader = new StringReader(text);
        HTMLEditorKit kit = new HTMLEditorKit();
        try {
            kit.read(reader, document, 0);
        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText() {
        StringWriter stringWriter = new StringWriter();
        HTMLEditorKit kit = new HTMLEditorKit();
        try {
            kit.write(stringWriter, document, 0, document.getLength());
        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }
        return stringWriter.toString();
    }

    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
    }

    public void openDocument() {
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        int result = fileChooser.showOpenDialog(view);
        if (result != JFileChooser.APPROVE_OPTION) return;
        currentFile = fileChooser.getSelectedFile();
        resetDocument();
        view.setTitle(currentFile.getName());
        try (FileReader in = new FileReader(currentFile)) {
            HTMLEditorKit kit = new HTMLEditorKit();
            kit.read(in, document, 0);
            view.resetUndo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public void saveDocument() {
        view.selectHtmlTab();
        if (currentFile == null) {
            saveDocumentAs();
        } else {
            try (FileWriter writer = new FileWriter(currentFile)) {
                HTMLEditorKit kit = new HTMLEditorKit();
                kit.write(writer, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        int result = fileChooser.showSaveDialog(view);
        if (result != JFileChooser.APPROVE_OPTION) return;
        if (document == null) return;

        currentFile = fileChooser.getSelectedFile();
        view.setTitle(currentFile.getName());

        try (FileWriter writer = new FileWriter(currentFile)) {
            HTMLEditorKit kit = new HTMLEditorKit();
            kit.write(writer, document, 0, document.getLength());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }
}
