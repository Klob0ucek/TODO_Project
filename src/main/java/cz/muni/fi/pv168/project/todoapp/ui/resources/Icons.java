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
    EXPORT("export-icon.png"),
    IMPORT("import-icon.png"),
    EXIT("exit-left-big-icon.png"),
    UP("statistics-up-icon.png"),
    DOWN("statistics-down-icon.png"),
    LEFT("statistics-left-icon.png"),
    RIGHT("statistics-right-icon.png");

    private final String iconPath;

    private Icons(String iconPath) {
        this.iconPath = iconPath;
    }

    public Icon getSmallIcon() {
        Image newImage = setUpImage().getScaledInstance(24, 24, Image.SCALE_AREA_AVERAGING);
        return new ImageIcon(newImage);
    }

    public Icon getIcon() {
        Image newImage = setUpImage().getScaledInstance(34, 34, Image.SCALE_AREA_AVERAGING);
        ;
        return new ImageIcon(newImage);
    }

    public Image setUpImage() {
        try {
            URL url = Icons.class.getResource(iconPath);
            if (url == null) {
                throw new IllegalArgumentException("Icon resource not found on classpath: " + iconPath);
            }
            return ImageIO.read(url);
        } catch (IOException e) {
            throw new IllegalArgumentException("Icon resource not found on classpath: " + iconPath);
        }
    }
}
