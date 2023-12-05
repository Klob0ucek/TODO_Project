package cz.muni.fi.pv168.project.todoapp.ui;

import cz.muni.fi.pv168.project.todoapp.storage.sql.dao.DataStorageException;
import cz.muni.fi.pv168.project.todoapp.storage.sql.dao.FailedToDeleteCategoryException;
import cz.muni.fi.pv168.project.todoapp.ui.action.QuitAction;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.EventQueue;


public class ApplicationErrorHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        // TODO: Handle exceptions better

        System.err.println(e.getCause().toString());
        e.printStackTrace(System.err);
        if (e instanceof FailedToDeleteCategoryException) {
            showGeneralError("Selected categories are linked to Event or Template.\nDelete that Event or Template first", false);
        } else if (e instanceof DataStorageException) {
            showGeneralError(e.toString(), false);
        } else {
            showGeneralError(e.toString(), true);
        }
    }

    private static void showGeneralError(String message, boolean isFatal) {
        final String title = isFatal ? "Fatal Application Error" : "Application Error";
        final Object[] options = getOptionsForDialog(isFatal);

        EventQueue.invokeLater(() ->
                JOptionPane.showOptionDialog(
                        null,
                        message,
                        title,
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE,
                        null,
                        options,
                        null
                ));
    }

    private static Object[] getOptionsForDialog(boolean isFatal) {
        if (!isFatal) {
            return null; // use default
        }

        return new Object[]{
                new JButton(new QuitAction())
        };
    }
}
