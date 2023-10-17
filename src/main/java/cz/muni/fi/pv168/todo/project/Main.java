package cz.muni.fi.pv168.todo.project;

import com.formdev.flatlaf.FlatDarkLaf;
import cz.muni.fi.pv168.todo.project.ui.MainWindow;

import javax.swing.*;

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