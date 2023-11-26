package cz.muni.fi.pv168.project.todoapp;

import com.formdev.flatlaf.FlatDarkLaf;
import cz.muni.fi.pv168.project.todoapp.business.model.UniqueNameProvider;
import cz.muni.fi.pv168.project.todoapp.ui.MainWindow;

import java.util.List;
import java.util.Optional;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        //TODO remove
        List<String> base = List.of("Name", "Name(1)", "Name(2)", "Unique", "Last(1)");
        String origin = "Name(1)";

        Optional<String> test = UniqueNameProvider.checkAndRename(origin, base);
        if (test.isPresent()) {
            System.out.println(test.get());
        } else {
            System.out.println(origin);
        }


        Runnable flatLaf = () -> {
            FlatDarkLaf.setup();
            MainWindow mainWindow = new MainWindow();
            mainWindow.show();
        };
        SwingUtilities.invokeLater(flatLaf);
    }
}