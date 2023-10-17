package cz.muni.fi.pv168.todo.project.model;

import java.awt.Color;

public enum CategoryColor {
    ORANGE(new Color(255, 128, 16)),
    GREEN(new Color(42, 114, 1)),
    BLUE(new Color(5, 65, 143)),
    YELLOW(new Color(168, 156, 17)),
    RED(new Color(154, 23, 25)),
    PURPLE(new Color(101, 8, 93)),
    PINK(new Color(241, 33, 209)),
    CYAN(new Color(4, 128, 147));

    private final Color color;

    CategoryColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
