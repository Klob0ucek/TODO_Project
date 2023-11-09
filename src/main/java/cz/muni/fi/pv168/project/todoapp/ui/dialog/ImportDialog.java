package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import cz.muni.fi.pv168.project.todoapp.business.service.export.format.Format;
import cz.muni.fi.pv168.project.todoapp.ui.util.Filter;

import javax.swing.JFileChooser;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.io.File;
import java.util.Collection;

public class ImportDialog extends JDialog {
    final Collection<Format> formats;

    public ImportDialog(Collection<Format> formats) {
        this.formats = formats;
    }

    public String showFileChooserDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        formats.forEach(f -> fileChooser.addChoosableFileFilter(new Filter(f)));

        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String exportFile = fileChooser.getSelectedFile().getAbsolutePath();
            var filter = fileChooser.getFileFilter();
            if (filter instanceof Filter) {
                exportFile = ((Filter) filter).decorate(exportFile);
            }

            return exportFile;
        }

        return null;
    }

    public boolean showConfirmationDialog(String path) {
        if (path != null) {
            int ret = JOptionPane.showConfirmDialog(null,
                    "Import from file: " + path,
                    "Confirm import path",
                    JOptionPane.YES_NO_OPTION);
            // TODO finish file export
            // ret == 0 = export confirmed by user
            // ret == 1 = export folder declined
            return ret == 0;
        }
        return false;
    }

}
