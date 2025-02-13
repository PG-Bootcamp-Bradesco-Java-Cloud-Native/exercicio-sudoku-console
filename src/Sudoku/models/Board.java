package Sudoku.models;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board {
    private final int size = 9;
    private final Slot[][] slots;

    public static Board getEmpty() {
        Board newBoard = new Board();

        for (int x = 0; x < newBoard.size; x++) {
            for (int y = 0; y < newBoard.size; y++) {
                newBoard.slots[x][y] = new Slot();
            }
        }

        return new Board();
    }

    public static Board getRandom() {
        Board newBoard = Board.getEmpty();
        Random random = new Random();

        for (int x = 0; x < newBoard.size; x++) {
            for (int y = 0; y < newBoard.size; y++) {

                int value = 0;

                if (random.nextInt(10) == 0) {
                    value = random.nextInt(10);
                }

                newBoard.slots[x][y] = new Slot(value);
            }
        }

        return newBoard;
    }

    private Board() {
        this.slots = new Slot[size][size];
    }

    public Board(Slot[][] slots) {
        this.slots = slots;
    }

    public boolean isSolved() {
        return getSlotGroupsWith(Status.solved).length == Axis.values().length * size;
    }

    public int getSize() {
        return this.size;
    }

    public Slot[][] getSlots() {
        return this.slots;
    }

    public void setSlot(int x, int y, int value) throws Exception {
        slots[x][y].setValue(value);
    }

    public Slot[] getSlotsWith(Status status) {
        return Arrays.asList(getSlotGroupsWith(status)).stream()
                .map((slotGroup) -> Arrays.asList(slotGroup))
                .flatMap(List::stream)
                .distinct()
                .toArray(Slot[]::new);
    }

    public Slot[][] getSlotGroupsWith(Status status) {
        return Arrays.asList(getSlotGroups()).stream().filter((Slot[] g) -> getGroupStatus(g) == status)
                .toArray(Slot[][]::new);
    }

    private Slot[][] getSlotGroups() {
        Slot[][] slotGroups = new Slot[Axis.values().length * size][size];
        int i = 0;
        for (Axis axis : Axis.values()) {
            for (int j = 0; j < size; j++) {
                slotGroups[i++] = getSlotGroup(axis, j);
            }
        }
        return slotGroups;
    }

    private Slot[] getSlotGroup(Axis axis, int i) {
        switch (axis) {
            case section: {
                Slot[] s = new Slot[9];
                int j = 0;
                int x0 = 3 * (i % 3);
                int y0 = i - (i % 3);
                for (int x = x0; x < x0 + 3; x++) {
                    for (int y = y0; y < y0 + 3; y++) {
                        s[j++] = slots[x][y];
                    }
                }
                return s;
            }
            case column: {
                return slots[i];
            }
            case row: {
                Slot[] r = new Slot[9];
                for (int j = 0; j < slots.length; j++) {
                    r[j] = slots[j][i];
                }
                return r;
            }
            default: {
                return new Slot[0];
            }
        }
    }

    public Status getSlotStatus(Slot slot) {
        for (Status status : Status.values()) {
            if (Arrays.asList(getSlotsWith(status)).contains(slot)) {
                return status;
            }
        }
        return Status.invalid;
    }

    private Status getGroupStatus(Slot[] group) {
        List<Integer> groupValues = Arrays.asList(group).stream().map(Slot::getValue).toList();
        if (groupValues.contains(0)) {
            return Status.unsolved;
        } else if (groupValues.stream().distinct().count() < groupValues.size()) {
            return Status.invalid;
        }
        return Status.solved;
    }
}
