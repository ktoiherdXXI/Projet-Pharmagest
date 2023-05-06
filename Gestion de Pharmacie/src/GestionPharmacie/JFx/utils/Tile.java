package GestionPharmacie.JFx.utils;

import javafx.scene.control.Control;
import javafx.scene.paint.Color;

public class Tile extends Control {
    public enum TileColor {
        GRAY(Color.rgb(139, 144, 146), "GRAY"),
        RED(Color.rgb(229, 80, 76), "RED"),
        LIGHT_RED(Color.rgb(255, 84, 56), "LIGHT_RED"),
        GREEN(Color.rgb(143, 198, 94), "GREEN"),
        LIGHT_GREEN(Color.rgb(132, 228, 50), "LIGHT_GREEN"),
        BLUE(Color.rgb(55, 179, 252), "BLUE"),
        DARK_BLUE(Color.rgb(55, 94, 252), "DARK_BLUE"),
        ORANGE(Color.rgb(237, 162, 57), "ORANGE"),
        YELLOW_ORANGE(Color.rgb(229, 198, 76), "YELLOW_ORANGE"),
        YELLOW(Color.rgb(229, 229, 76), "YELLOW"),
        MAGENTA(Color.rgb(198, 75, 232), "MAGENTA"),
        PINK(Color.rgb(233, 14, 139), "PINK");

        public final Color color;
        public final String styleName;

        TileColor(final Color COLOR, final String STYLE_NAME) {
            color = COLOR;
            styleName = STYLE_NAME;
        }
    }

    public enum TextSize {
        SMALL(0.04),
        SMALLER(0.05),
        NORMAL(0.06),
        BIGGER(0.8);

        public final double factor;

        TextSize(final double FACTOR) {
            factor = FACTOR;
        }
    }

    public static final Color BACKGROUND = Color.rgb(42, 42, 42); // #2a2a2a
    public static final Color FOREGROUND = Color.rgb(223, 223, 223); // #dfdfdf
    public static final Color GRAY = TileColor.GRAY.color;
    public static final Color RED = TileColor.RED.color;
    public static final Color LIGHT_RED = TileColor.LIGHT_RED.color;
    public static final Color GREEN = TileColor.GREEN.color;
    public static final Color LIGHT_GREEN = TileColor.LIGHT_GREEN.color;
    public static final Color BLUE = TileColor.BLUE.color;
    public static final Color DARK_BLUE = TileColor.DARK_BLUE.color;
    public static final Color ORANGE = TileColor.ORANGE.color;
    public static final Color YELLOW_ORANGE = TileColor.YELLOW_ORANGE.color;
    public static final Color YELLOW = TileColor.YELLOW.color;
    public static final Color MAGENTA = TileColor.MAGENTA.color;
    public static final Color PINK = TileColor.PINK.color;
}