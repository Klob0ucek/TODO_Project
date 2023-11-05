package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import cz.muni.fi.pv168.project.todoapp.ui.MainWindow;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class NotificationDialog extends JDialog {
    private static final int AVERAGE_CHAR_SIZE = 9;
    private static final int NOTIFICATION_HEIGHT = 40;
    private static final int MIN_WIDTH = 160;
    private static final int HEIGHT_OFFSET = 20;
    private static final int WIDTH_OFFSET = 20;
    private static final int DISPLAY_TIME = 5000; // 5 seconds

    /**
     * @param message - The length of the message should not be more than 70 characters.
     *                If longer messages will be needed - message can not be displayed as title
     *                -> we will need to add "x" button
     */
    public NotificationDialog(JFrame parentFrame, String message) {
        super(parentFrame, false);
        setTitle(message);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        int finalWidth = calculatePreferredWidth(message);
        setSize(finalWidth, NOTIFICATION_HEIGHT);

        Point parentLocation = parentFrame.getLocation();
        int x = parentLocation.x + parentFrame.getWidth() - finalWidth - WIDTH_OFFSET;
        int y = parentLocation.y + parentFrame.getHeight() - NOTIFICATION_HEIGHT - HEIGHT_OFFSET;
        setLocation(new Point(x, y));

        // Moves notification when main window is resized
        parentFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Point newParentLocation = parentFrame.getLocation();
                int finalWidth = calculatePreferredWidth(message);
                int newX = newParentLocation.x + parentFrame.getWidth() - finalWidth - WIDTH_OFFSET;
                int newY = newParentLocation.y + parentFrame.getHeight() - NOTIFICATION_HEIGHT - HEIGHT_OFFSET;
                setLocation(new Point(newX, newY));
                setSize(finalWidth, NOTIFICATION_HEIGHT);
            }
        });
    }

    private int calculatePreferredWidth(String text) {
        int textWidth = text.length() * AVERAGE_CHAR_SIZE;
        return Math.max(MIN_WIDTH, Math.min(textWidth, MainWindow.getFrame().getWidth() - 70));
    }

    // Method to close the notification
    private void closeNotification() {
        setVisible(false);
        dispose();
    }

    // Method to display the notification
    public void showNotification() {
        setVisible(true);
        Timer timer = new Timer(DISPLAY_TIME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeNotification();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}
