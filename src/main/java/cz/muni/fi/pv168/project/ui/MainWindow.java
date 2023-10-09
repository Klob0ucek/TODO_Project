package cz.muni.fi.pv168.project.ui;

import cz.muni.fi.pv168.project.ui.model.ScheduleTableModel;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    private final JFrame frame;

    public MainWindow() {
        frame = createFrame();
        var scheduleTableModel = createScheduleTableModel();
        frame.add(new JScrollPane(scheduleTableModel), BorderLayout.CENTER);
        frame.pack();
    }

    private JTable createScheduleTableModel() {
        return new JTable(new ScheduleTableModel());
    }

    private JFrame createFrame() {
        var frame = new JFrame("TODO App");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

    public void show() {
        frame.setVisible(true);
    }
}
