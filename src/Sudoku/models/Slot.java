package Sudoku.models;

public class Slot {
    private final boolean isLocked;
    private int value;

    public Slot() {
        this(0);
    }

    public Slot(int value) {
        this.value = value;
        this.isLocked = (value != 0);
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) throws Exception {
        if (isLocked) {
            throw new Exception("Space is locked.");
        }

        if (value < 0 || value > 9) {
            throw new IllegalArgumentException("Value must be in range [0, 9].");
        }

        this.value = value;
    }
}
