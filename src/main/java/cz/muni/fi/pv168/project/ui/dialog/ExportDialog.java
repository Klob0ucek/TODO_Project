package cz.muni.fi.pv168.project.ui.dialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ExportDialog extends JDialog {

    public ExportDialog() {
        // TODO need to select a folder
        File selectedFile = showFileChooserDialog();
        if (selectedFile != null) {

            // TODO save export file into selected folder

            JOptionPane.showMessageDialog(null, "Selected export file: " + selectedFile.getAbsolutePath());
        }
    }

    private File showFileChooserDialog() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }

        return null;
    }
}
