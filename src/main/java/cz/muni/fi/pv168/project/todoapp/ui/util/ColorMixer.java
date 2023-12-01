package cz.muni.fi.pv168.project.todoapp.ui.util;

import java.awt.Color;
import java.util.List;

public abstract class ColorMixer {
    public static Color calculateGradient(List<Color> colors) {
        int redSum = 0;
        int greenSum = 0;
        int blueSum = 0;

        for (Color color : colors) {
            redSum += color.getRed();
            greenSum += color.getGreen();
            blueSum += color.getBlue();
        }

        int averageRed = redSum / colors.size();
        int averageGreen = greenSum / colors.size();
        int averageBlue = blueSum / colors.size();

        return new Color(averageRed, averageGreen, averageBlue);
    }
}
