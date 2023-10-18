package cz.muni.fi.pv168.project.todoapp.ui.resources;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.net.URL;

public final class Icons {
    public static final Icon ADD_ICON = createIcon("clipboard-add-big-icon.png");
    public static final Icon DELETE_ICON = createIcon("clipboard-cross-big-icon.png");
    public static final Icon EDIT_ICON = createIcon("pen-test.png");
    public static final Icon EXPORT_ICON = createIcon("file-download-big-icon.png");
    public static final Icon IMPORT_ICON = createIcon("file-upload-big-icon.png");
    public static final Icon EXIT_ICON = createIcon("exit-left-big-icon.png");

    private Icons() {
        throw new AssertionError("This class is not instantiable");
    }

    private static ImageIcon createIcon(String name) {
        try {
            URL url = Icons.class.getResource(name);
            if (url == null) {
                throw new IllegalArgumentException("Icon resource not found on classpath: " + name);
            }
            Image originalImage = ImageIO.read(url);
            Image scaledImage = originalImage.getScaledInstance(34, 34, Image.SCALE_AREA_AVERAGING);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            throw new IllegalArgumentException("Icon resource not found on classpath: " + name);
        }
    }
}
