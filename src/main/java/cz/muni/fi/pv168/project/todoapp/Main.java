package cz.muni.fi.pv168.project.todoapp;

import com.formdev.flatlaf.FlatDarkLaf;
import cz.muni.fi.pv168.project.todoapp.business.model.UniqueNameProvider;
import cz.muni.fi.pv168.project.todoapp.ui.ApplicationErrorHandler;
import cz.muni.fi.pv168.project.todoapp.ui.MainWindow;

import java.util.List;
import java.util.Optional;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        var errorHandler = new ApplicationErrorHandler();
        Thread.setDefaultUncaughtExceptionHandler(errorHandler);

        Runnable init = () -> {
            FlatDarkLaf.setup();
            MainWindow mainWindow = new MainWindow();
            mainWindow.show();
        };
        SwingUtilities.invokeLater(init);
    }
}