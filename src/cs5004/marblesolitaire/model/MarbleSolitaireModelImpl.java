package cs5004.marblesolitaire.model;

import cs5004.marblesolitaire.model.hw09.AMarbleSolitaire;

/**
 * A constructor called MarbleSolitaireModelImpl that implement Implement the MarbleSolitaireModel
 *     interface.
 */
public class MarbleSolitaireModelImpl extends AMarbleSolitaire implements MarbleSolitaireModel {

  /**
   * The first constructor of the method.
   * Take no parameters, and initialize the game board as shown above
   *     (arm thickness 3 with the empty slot at the center).
   */
  public MarbleSolitaireModelImpl() {
    this.sRow = 3;
    this.sColumn = 3;
    this.armThickness = 3;
    this.board();
  }

  /**
   * The second constructor of the method.
   * Take two parameters: sRow and sCol. It should initialize the game board so
   *     that the arm thickness is 3 and the empty slot is at the position (sRow, sCol).
   * @param sRow The row for the center position.
   * @param sColumn The column for the center position.
   * @throws IllegalArgumentException when the input is not in a valid position – (x, y).
   */
  public MarbleSolitaireModelImpl(int sRow, int sColumn) throws IllegalArgumentException {
    if ((sRow < 2 || sRow > 4) && (sColumn < 2 || sColumn > 4)) {
      throw new IllegalArgumentException("Try new co-ordinate points for a valid position.");
    }
    this.sRow = sRow;
    this.sColumn = sColumn;
    this.armThickness = 3;
    this.board();
  }

  /**
   * The third constructor of the method.
   * Take the arm thickness as its only parameter and initialize
   *     a game board with the empty slot at the center.
   * @param armThickness the thickness of each arms.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number.
   */
  public MarbleSolitaireModelImpl(int armThickness) throws IllegalArgumentException {
    if (armThickness % 2 == 0) {
      throw new IllegalArgumentException("The thickness has to be in an odd integer form, "
              + "but not in even integer form");
    }

    if (armThickness < 0) {
      throw new IllegalArgumentException("The thickness has to be in positive integers.");
    }
    this.sRow = (armThickness / 2) * 3;
    this.sColumn = (armThickness / 2) * 3;
    this.armThickness = armThickness;
    this.empty();
    this.board();
  }

  /**
   * The fourth and last constructor for the method.
   * Take the arm thickness, row and column of the empty slot in that order.
   * @param armThickness the thickness of each arm of the board.
   * @param sRow is the row value for where the initial empty slot goes.
   * @param sColumn is the column value for where the initial empty slot goes.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number,
   *     or the empty cell position is invalid.
   */
  public MarbleSolitaireModelImpl(int armThickness, int sRow, int sColumn)
          throws IllegalArgumentException {

    if (sRow <= armThickness - 2 && sColumn <= armThickness - 2
            || sRow <= armThickness - 2 && sColumn >= (armThickness * 2) - 1
            || sRow >= (armThickness * 2) - 1 && sColumn <= armThickness - 2
            || sRow >= (armThickness * 2) - 1 && sColumn >= (armThickness * 2) - 1) {
      throw new IllegalArgumentException("Invalid empty cell position(" + sRow + ","
              + sColumn + ")");
    }

    if (armThickness % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness is not an odd number");
    }
    if (armThickness < 0) {
      throw new IllegalArgumentException("Arm thickness is not positive number");
    }
    this.sRow = sRow;
    this.sColumn = sColumn;
    this.armThickness = armThickness;
    this.board();
  }

  @Override
  protected boolean outsideLoop(int r, int c) {
    return (r >= armThickness * 2 - 1 && c < armThickness - 1
            || r >= armThickness * 2 - 1 && c >= armThickness * 2 - 1
            || r < armThickness - 1 && c < armThickness - 1
            || r < armThickness - 1 && c >= armThickness * 2 - 1);
  }
}
