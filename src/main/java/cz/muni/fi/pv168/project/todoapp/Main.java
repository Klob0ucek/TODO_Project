package cz.muni.fi.pv168.project.todoapp;

import com.formdev.flatlaf.FlatDarkLaf;
import cz.muni.fi.pv168.project.todoapp.ui.MainWindow;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        Runnable flatLaf = () -> {
            FlatDarkLaf.setup();
            MainWindow mainWindow = new MainWindow();
            mainWindow.show();
        };
        SwingUtilities.invokeLater(flatLaf);
    }
}