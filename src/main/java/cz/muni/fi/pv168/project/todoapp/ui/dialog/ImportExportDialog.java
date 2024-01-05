package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import cz.muni.fi.pv168.project.todoapp.business.service.export.format.Format;
import cz.muni.fi.pv168.project.todoapp.ui.util.Filter;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import java.util.Collection;

public class ImportExportDialog extends JDialog {
    final Collection<Format> formats;

    public ImportExportDialog(Collection<Format> formats) {
        this.formats = formats;
    }

    public String showFileChooserDialog(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        formats.forEach(f -> fileChooser.addChoosableFileFilter(new Filter(f)));

        int returnValue = fileChooser.showSaveDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String exportFile = fileChooser.getSelectedFile().getAbsolutePath();
            var fileFilter = fileChooser.getFileFilter();
            if (fileFilter instanceof Filter filter) {
                exportFile = filter.decorate(exportFile);
            }

            return exportFile;
        }

        return null;
    }

    public boolean showConfirmationDialog(String path, JFrame frame) {
        int ret = JOptionPane.showConfirmDialog(frame,
                "Export into file: " + path,
                "Confirm export file",
                JOptionPane.YES_NO_OPTION);
        return ret == JOptionPane.YES_OPTION;
    }
}
