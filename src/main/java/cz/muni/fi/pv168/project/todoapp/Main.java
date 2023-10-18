package cz.muni.fi.pv168.project.todoapp;

import com.formdev.flatlaf.FlatDarkLaf;
import cz.muni.fi.pv168.project.todoapp.ui.MainWindow;

public class Main {
    public static void main(String[] args) {
        /* Setup for FlatLookAndFeel:
         *  - FlatLightLaf
         *  - FlatDarkLaf
         *  - FlatDarculaLaf
         */
        FlatDarkLaf.setup();

        MainWindow mainWindow = new MainWindow();
        mainWindow.show();
    }
}