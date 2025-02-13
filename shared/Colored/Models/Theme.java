package shared.Colored.Models;

public class Theme {
    public TextColor textColor;
    public BackgroundColor backgroundColor;

    public Theme() {
        this(new TextColor(""), new BackgroundColor(""));
    }

    public Theme(TextColor textColor) {
        this(textColor, new BackgroundColor(""));
    }

    public Theme(BackgroundColor backgroundColor) {
        this(new TextColor(""), backgroundColor);
    }

    public Theme(TextColor textColor, BackgroundColor backgroundColor) {
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
    }
}
