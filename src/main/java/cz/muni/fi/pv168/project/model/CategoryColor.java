package cz.muni.fi.pv168.project.model;

import java.awt.Color;

public enum CategoryColor {
    ORANGE(new Color(238, 171, 109)),
    GREEN(new Color(152, 218, 108)),
    BLUE(new Color(142, 189, 255)),
    YELLOW(new Color(246, 237, 142)),
    RED(new Color(232, 97, 102)),
    PURPLE(new Color(175, 90, 170)),
    PINK(new Color(255, 133, 239)),

    CYAN(new Color(150, 240, 250));

    private final Color color;

    CategoryColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
