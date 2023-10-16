package cz.muni.fi.pv168.project.ui.dialog;

import javax.swing.JFileChooser;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.io.File;

public class ImportDialog extends JDialog {
    public ImportDialog() {
        File selectedFile = showFileChooserDialog();
        if (selectedFile != null) {

            // TODO Handle the selected file

            JOptionPane.showMessageDialog(null, "Selected file: " + selectedFile.getAbsolutePath());
        }
    }
    private File showFileChooserDialog() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }

        return null;
    }
}
