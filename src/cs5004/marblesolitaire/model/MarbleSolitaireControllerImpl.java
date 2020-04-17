package cs5004.marblesolitaire.model;

import java.io.IOException;
import java.util.Scanner;


/**
 * Class that represents my implementation for the Marble Solitaire Controller.
 */
public class MarbleSolitaireControllerImpl implements IController {

  private Readable readable;
  private Appendable appendable;

  /**
   * Plays the given model of the game marble solitaire
   * based on inputs that the controller is initialized with.
   * @param model is the model for the game that is passed into the constructor.
   * @throws IllegalArgumentException if it is passed null as a model.
   */
  public void play(MarbleSolitaireModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Cannot pass null");
    }
    int fromRow = 0;
    int fromCol = 0;
    int toRow = 0;
    int toCol = 0;
    Scanner scan = new Scanner(this.readable);
    String next;
    int counter = 0;

    while (scan.hasNext()) {
      next = scan.next();
      if (numberString(next)) {
        if (counter % 4 == 0) {
          fromRow = Integer.valueOf(next) - 1;
        } else if (counter % 4 == 1) {
          fromCol = Integer.valueOf(next) - 1;
        } else if (counter % 4 == 2) {
          toRow = Integer.valueOf(next) - 1;
        } else if (counter % 4 == 3) {
          toCol = Integer.valueOf(next) - 1;
          try {
            model.move(fromRow, fromCol, toRow, toCol);
            if (model.isGameOver()) {
              appendHelper("Game over!\n" + model.getGameState() + "\nScore: " + model.getScore());
              return;
            }
          } catch (IllegalArgumentException e) {
            appendHelper("Invalid move. Play again. Try using better values.\n");
          }
        }
        counter++;
      } else if (next.equals("q") || next.equals("Q")) {
        appendHelper("Game quit!\nState of game when quit:\n"
                + model.getGameState() + "\nScore: " + model.getScore());
        return;
      } else {
        appendHelper(next + " is not a valid input, re-enter a value\n");
      }

    }
    throw new IllegalStateException("Reached the end of the reader without completion");
  }

  /**
   * Constructor for MarbleSolitaireControllerImpl that initializes its readable and appendable
   *     based on the given fields.
   * @param readable   is the Readable that represents the player's inputs.
   * @param appendable is the Appendable that will result in the outcome of the players moves.
   * @throws IllegalArgumentException if it is passed null as an argument.
   */
  public MarbleSolitaireControllerImpl(Readable readable, Appendable appendable)
          throws IllegalArgumentException {
    if (readable == null || appendable == null) {
      throw new IllegalArgumentException("Cannot pass null as an argument");
    }
    this.readable = readable;
    this.appendable = appendable;
  }

  /**
   * Checks if a given string is a number with a try catch.
   * @param s which is the string that the function checks if it is an integer.
   * @return a boolean that checks if the given string is an integer or not.
   */
  private boolean numberString(String s) {
    try {
      Integer.parseInt(s);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * The try-catch block for the append function.
   * @param s which is the string needed to be appended to the Appendable.
   * @throws IllegalStateException when the append fails.
   */
  private void appendHelper(String s) {
    try {
      appendable.append(s);
    } catch (IOException e) {
      throw new IllegalStateException("Cannot append that");
    }
  }

}
