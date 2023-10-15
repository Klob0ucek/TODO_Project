package cz.muni.fi.pv168.project.model;

import java.awt.Color;

public enum CategoryColor {
    RED(Color.RED),
    GREEN(Color.GREEN),
    BLUE(Color.BLUE),
    YELLOW(Color.YELLOW),
    ORANGE(Color.ORANGE),
    PURPLE(new Color(128, 0, 128)), // Custom color
    PINK(Color.PINK);

    private final Color color;

    CategoryColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
