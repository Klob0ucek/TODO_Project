package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import javax.swing.*;

import java.io.File;

public class ExportDialog extends JDialog {

    public ExportDialog() {
        File selectedFile = showFileChooserDialog();
        if (selectedFile != null) {
            int ret = JOptionPane.showConfirmDialog(null,
                    "Export into folder: " + selectedFile.getAbsolutePath(),
                    "Confirm export folder",
                    JOptionPane.YES_NO_OPTION);
            // TODO finish file export
            // ret == 0 = export confirmed by user
            // ret == 1 = export folder declined
        }
    }

    private File showFileChooserDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showSaveDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }

        return null;
    }
}
