package shared.Colored;

import shared.Colored.Models.BackgroundColor;
import shared.Colored.Models.Color;
import shared.Colored.Models.TextColor;

public class Colors {
    public static final Color RESET = new Color("\u001B[0m");

    public static final TextColor TEXT_BLACK = new TextColor("\u001B[30m");
    public static final TextColor TEXT_RED = new TextColor("\u001B[31m");
    public static final TextColor TEXT_GREEN = new TextColor("\u001B[32m");
    public static final TextColor TEXT_YELLOW = new TextColor("\u001B[33m");
    public static final TextColor TEXT_BLUE = new TextColor("\u001B[34m");
    public static final TextColor TEXT_PURPLE = new TextColor("\u001B[35m");
    public static final TextColor TEXT_CYAN = new TextColor("\u001B[36m");
    public static final TextColor TEXT_WHITE = new TextColor("\u001B[37m");

    public static final BackgroundColor BACKGROUND_BLACK = new BackgroundColor("\u001B[40m");
    public static final BackgroundColor BACKGROUND_RED = new BackgroundColor("\u001B[41m");
    public static final BackgroundColor BACKGROUND_GREEN = new BackgroundColor("\u001B[42m");
    public static final BackgroundColor BACKGROUND_YELLOW = new BackgroundColor("\u001B[43m");
    public static final BackgroundColor BACKGROUND_BLUE = new BackgroundColor("\u001B[44m");
    public static final BackgroundColor BACKGROUND_PURPLE = new BackgroundColor("\u001B[45m");
    public static final BackgroundColor BACKGROUND_CYAN = new BackgroundColor("\u001B[46m");
    public static final BackgroundColor BACKGROUND_WHITE = new BackgroundColor("\u001B[47m");
}
