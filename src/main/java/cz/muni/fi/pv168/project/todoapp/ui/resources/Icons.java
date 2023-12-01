package cz.muni.fi.pv168.project.todoapp.ui.resources;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.net.URL;

public enum Icons {
    ADD("clipboard-add-big-icon.png"),
    DELETE("clipboard-cross-big-icon.png"),
    EDIT("edit-pen-big-icon.png"),
    EXPORT("file-download-big-icon.png"),
    IMPORT("file-upload-big-icon.png"),
    EXIT("exit-left-big-icon.png");

    private final String iconPath;

    private Icons(String iconPath) {
        this.iconPath = iconPath;
    }

    public Icon getIcon() {
        try {
            URL url = Icons.class.getResource(iconPath);
            if (url == null) {
                throw new IllegalArgumentException("Icon resource not found on classpath: " + iconPath);
            }
            Image originalImage = ImageIO.read(url);
            Image scaledImage = originalImage.getScaledInstance(34, 34, Image.SCALE_AREA_AVERAGING);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            throw new IllegalArgumentException("Icon resource not found on classpath: " + iconPath);
        }
    }
}
