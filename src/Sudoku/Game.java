package Sudoku;

import java.util.Scanner;
import java.util.Stack;

import Sudoku.models.Board;
import Sudoku.models.Slot;
import Sudoku.models.Status;
import shared.Helpers;
import shared.Colored.Colored;
import shared.Colored.Colors;
import shared.Colored.Models.Theme;

public class Game {
    private final Scanner scan = new Scanner(System.in);
    private final Stack<String> messages = new Stack<>();
    private int xPos = 0, yPos = 0;
    private Board board;

    public Game() {
        this.board = Board.getRandom();
    }

    public Game(Board board) {
        this.board = board;
    }

    public void run() {
        while (true) {
            render();
            handleInput();

            if (board.isSolved()) {
                messages.push("Jogo resolvido!");
            }
        }
    }

    private void render() {
        Helpers.Console.clear();

        StringBuilder stringBuilder = new StringBuilder("Sudoku")
                .append("\n");

        if (!messages.isEmpty()) {
            while (!messages.isEmpty()) {
                stringBuilder.append("\n\t- ").append(messages.pop());
            }
            stringBuilder.append("\n");
        }

        stringBuilder.append("\n\t")
                .append(getRenderedBoard().replace("\n", "\n\t"))
                .append("\n\t[W / A / S / D] - Mover cursor.")
                .append("\n\t[0 - 9] - Inserir número.")
                .append("\n\t[R] - Randomizar jogo.")
                .append("\n\t[Q] - Sair.")
                .append("\n> ");

        System.out.print(stringBuilder.toString());
    }

    private String getRenderedBoard() {
        Slot[][] slots = board.getSlots();
        StringBuilder boardStringBuilder = new StringBuilder();

        for (int y = 0; y < board.getSize(); y++) {
            for (int x = 0; x < board.getSize(); x++) {
                Slot slot = slots[x][y];
                Status slotStatus = board.getSlotStatus(slot);
                Theme theme;

                switch (slotStatus) {
                    case solved:
                        theme = new Theme(Colors.TEXT_WHITE, Colors.BACKGROUND_GREEN);
                        break;
                    case invalid:
                        theme = new Theme(Colors.TEXT_WHITE, Colors.BACKGROUND_RED);
                        break;
                    default:
                        theme = new Theme(Colors.TEXT_BLACK, Colors.BACKGROUND_WHITE);
                        break;
                }

                if (slot.getValue() == 0) {
                    theme = new Theme(Colors.TEXT_WHITE, Colors.BACKGROUND_WHITE);
                }

                boardStringBuilder.append(Colored.colored(slot.getValue(), theme));

                if (x == xPos && y == yPos) {
                    boardStringBuilder
                            .append(Colored.colored("_", new Theme(Colors.TEXT_BLACK, Colors.BACKGROUND_CYAN)));
                } else {
                    boardStringBuilder.append(Colored.colored(" ", theme));
                }

            }
            boardStringBuilder.append("\n");
        }

        return boardStringBuilder.toString();
    }

    private void handleInput() {
        String input = scan.nextLine();
        switch (input) {
            case "W":
            case "w":
                yPos = yPos - 1 > 0 ? yPos - 1 : 0;
                break;
            case "A":
            case "a":
                xPos = xPos - 1 > 0 ? xPos - 1 : 0;
                break;
            case "S":
            case "s":
                yPos = yPos + 1 >= board.getSize() ? board.getSize() - 1 : yPos + 1;
                break;
            case "D":
            case "d":
                xPos = xPos + 1 >= board.getSize() ? board.getSize() - 1 : xPos + 1;
                break;
            case "R":
            case "r":
                this.board = Board.getRandom();
                break;
            case "Q":
            case "q":
                System.exit(0);
            default:
                try {
                    board.setSlot(xPos, yPos, Helpers.Parsers.tryParseInteger(input));
                } catch (Exception e) {
                    messages.push("Input inválido.");
                }
                break;
        }
    }
}
