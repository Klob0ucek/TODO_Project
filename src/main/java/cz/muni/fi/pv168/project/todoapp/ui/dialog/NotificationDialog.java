package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.Timer;
import java.awt.Point;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.border.EmptyBorder;

public class NotificationDialog extends JDialog {
    private static final int AVERAGE_CHAR_SIZE = 9;
    private static final int ERROR_CHAR_SIZE = 6;
    private static final int HEADER_HEIGHT = 40;
    private static final int LINE_HEIGHT = 22;
    private static final int MIN_WIDTH = 160;
    private static final int HEIGHT_OFFSET = 20;
    private static final int WIDTH_OFFSET = 20;
    private static final int DISPLAY_TIME = 5000; // 5 seconds

    private int width;

    private final int lines;

    /**
     * @param message - The length of the message should not be more than 70 characters.
     *                If longer messages will be needed - message can not be displayed as title
     *                -> we will need to add "x" button
     */
    public NotificationDialog(JFrame parentFrame, String message) {
        super(parentFrame, false);
        this.lines = 0;
        this.width = message.length() * AVERAGE_CHAR_SIZE;

        setTitle(message);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        adjustNotificationPosition(parentFrame);

        // Moves notification when main window is resized
        parentFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                adjustNotificationPosition(parentFrame);
            }

            @Override
            public void componentResized(ComponentEvent e) {
                adjustNotificationPosition(parentFrame);
            }
        });
    }

    public NotificationDialog(JFrame parentFrame, String message, List<String> errors) {
        super(parentFrame, false);
        this.lines = errors.size();
        this.width = message.length() * AVERAGE_CHAR_SIZE;
        ;

        setTitle(message);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        for (String error : errors) {
            super.add(generateLabel(error));
            int maxWidth = error.length() * ERROR_CHAR_SIZE;
            width = Math.max(width, maxWidth);
        }

        adjustNotificationPosition(parentFrame);

        // Moves notification when main window is resized
        parentFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                adjustNotificationPosition(parentFrame);
            }

            @Override
            public void componentResized(ComponentEvent e) {
                adjustNotificationPosition(parentFrame);
            }
        });
    }

    private JLabel generateLabel(String error) {
        JLabel newLabel = new JLabel(error);
        newLabel.setBorder(new EmptyBorder(4, 6, 2, 0));
        return newLabel;
    }

    private int calculatePreferredWidth(JFrame parentFrame) {
        return Math.max(MIN_WIDTH, Math.min(width, parentFrame.getWidth() - 70));
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

    private void adjustNotificationPosition(JFrame parentFrame) {
        Point newParentLocation = parentFrame.getLocation();

        int height = HEADER_HEIGHT + LINE_HEIGHT * lines;
        int finalWidth = calculatePreferredWidth(parentFrame);
        int newX = newParentLocation.x + parentFrame.getWidth() - finalWidth - WIDTH_OFFSET;
        int newY = newParentLocation.y + parentFrame.getHeight() - height - HEIGHT_OFFSET;

        setLocation(new Point(newX, newY));
        setSize(finalWidth, height);
    }
}
