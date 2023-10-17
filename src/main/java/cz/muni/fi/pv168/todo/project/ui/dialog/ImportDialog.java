package cz.muni.fi.pv168.todo.project.ui.dialog;

import javax.swing.JFileChooser;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.io.File;

public class ImportDialog extends JDialog {
    public ImportDialog() {
        selectImportFile();
    }

    private File showFileChooserDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }

        return null;
    }

    private void selectImportFile() {
        File selectedFile = showFileChooserDialog();
        if (selectedFile != null) {
            JOptionPane.showConfirmDialog(null,
                    "Import Selected file: " + selectedFile.getAbsolutePath(),
                    "Confirm import file",
                    JOptionPane.YES_NO_OPTION
            );

            // TODO Handle the selected file
            // ret == 0 = file confirmed
            // ret == 1 = file declined
        }
    }
}
