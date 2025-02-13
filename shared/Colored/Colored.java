package shared.Colored;

import shared.Colored.Models.BackgroundColor;
import shared.Colored.Models.TextColor;
import shared.Colored.Models.Theme;

public class Colored {
    public static String colored(int s, Theme theme) {
        return colored(s, theme.textColor, theme.backgroundColor);
    }

    public static String colored(int s, TextColor textColor) {
        return colored(s, textColor, new BackgroundColor(""));
    }

    public static String colored(int s, BackgroundColor backgroundColor) {
        return colored(s, new TextColor(""), backgroundColor);
    }

    public static String colored(int s, TextColor textColor, BackgroundColor backgroundColor) {
        return textColor.ansiColorString + backgroundColor.ansiColorString + s + Colors.RESET.ansiColorString;
    }

    public static String colored(String s, Theme theme) {
        return colored(s, theme.textColor, theme.backgroundColor);
    }

    public static String colored(String s, TextColor textColor) {
        return colored(s, textColor, new BackgroundColor(""));
    }

    public static String colored(String s, BackgroundColor backgroundColor) {
        return colored(s, new TextColor(""), backgroundColor);
    }

    public static String colored(String s, TextColor textColor, BackgroundColor backgroundColor) {
        return textColor.ansiColorString + backgroundColor.ansiColorString + s + Colors.RESET.ansiColorString;
    }
}
