package cs5004.marblesolitaire.model.hw09;

import java.util.ArrayList;

import cs5004.marblesolitaire.model.MarbleSolitaireModel;

/**
 * A class that represents the Triangle version of the game Peg Solitaire,
 *     with a different board size.
 */
public class TriangleSolitaireModelImpl extends AMarbleSolitaire implements MarbleSolitaireModel {

  private final int rowNumbers;

  /**
   * The first constructor of the method.
   * Take no parameters, and initialize the game board as shown above
   *     (arm thickness 3 with the empty slot at the center).
   */
  public TriangleSolitaireModelImpl() {
    this.sRow = 0;
    this.sColumn = 0;
    this.rowNumbers = 5;
    board();
  }

  /**
   * The third constructor of the method.
   * Take the arm thickness as its only parameter and initialize
   *     a game board with the empty slot at the center.
   * @param dimensionOfPegs the thickness of each arms.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number.
   */
  public TriangleSolitaireModelImpl(int dimensionOfPegs) throws IllegalArgumentException {
    if (dimensionOfPegs < 1) {
      throw new IllegalArgumentException("Peg dimension have to at least 1 or greater");
    }
    this.sRow = 0;
    this.sColumn = 0;
    this.rowNumbers = dimensionOfPegs;
    board();
  }

  /**
   * The second constructor of the method.
   * Take two parameters: sRow and sCol. It should initialize the game board so
   *     that the arm thickness is 3 and the empty slot is at the position (sRow, sCol).
   * @param sRow The row for the center position.
   * @param sColumn The column for the center position.
   * @throws IllegalArgumentException when the input is not in a valid position – (x, y).
   */
  public TriangleSolitaireModelImpl(int sRow, int sColumn) throws IllegalArgumentException {
    this.sRow = sRow;
    this.sColumn = sColumn;
    this.rowNumbers = 5;
    board();
    empty();
  }

  /**
   * The fourth and last constructor for the method.
   *     Take the arm thickness, row and column of the empty slot in that order.
   * @param dimensionOfPegs the thickness of each arm of the board.
   * @param sRow is the row value for where the initial empty slot goes.
   * @param sColumn is the column value for where the initial empty slot goes.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number,
   *     or the empty cell position is invalid.
   */
  public TriangleSolitaireModelImpl(int dimensionOfPegs, int sRow, int sColumn)
          throws IllegalArgumentException {
    this.sRow = sRow;
    this.sColumn = sColumn;
    this.rowNumbers = dimensionOfPegs;
    empty();
    board();

    if (dimensionOfPegs <= 1) {
      throw new IllegalArgumentException("Peg dimension have to at least 1 or greater");
    }
  }

  @Override
  protected void board() {
    ArrayList<ArrayList<PlayerPiece>> piece = new ArrayList<ArrayList<PlayerPiece>>();
    ArrayList<PlayerPiece> tempVertical;
    for (int r = 0; r < rowNumbers; r++) {
      tempVertical = new ArrayList<PlayerPiece>();
      for (int c = 0; c < rowNumbers; c++) {
        if (this.outsideLoop(r, c)) {
          tempVertical.add(PlayerPiece.INVALID);
        } else if (r == sRow && c == sColumn) {
          tempVertical.add(PlayerPiece.X);
        } else {
          tempVertical.add(PlayerPiece.O);
        }
      }
      piece.add(tempVertical);
    }
    gameBoard = piece;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (fromRow < 0 || toRow < 0 || fromCol < 0
            || toCol < 0 || fromCol > fromRow
            || toCol > toRow || fromRow >= rowNumbers
            || fromCol >= rowNumbers || toRow >= rowNumbers
            || toCol >= rowNumbers) {
      throw new IllegalArgumentException("Invalid move. Please, choose a different"
              + " co-ordinate point");
    } else if (((toRow == fromRow && toCol == fromCol + 2)
            || (toRow == fromRow + 2 && toCol == fromCol)
            || (toRow == fromRow && toCol == fromCol - 2)
            || (toRow == fromRow - 2 && toCol == fromCol)
            || (toRow == fromRow + 2 && toCol == fromCol + 2)
            || (toRow == fromRow - 2 && toCol == fromCol + 2)
            || (toRow == fromRow + 2 && toCol == fromCol - 2)
            || (toRow == fromRow - 2 && toCol == fromCol - 2))
            && gameBoard.get(toRow).get(toCol) == PlayerPiece.X
            && gameBoard.get(fromRow).get(fromCol) == PlayerPiece.O
            && gameBoard.get((toRow - fromRow) / 2 + fromRow)
            .get((toCol - fromCol) / 2 + fromCol) == PlayerPiece.O) {
      this.gameBoard.get(toRow).set(toCol, PlayerPiece.O);
      this.gameBoard.get(fromRow).set(fromCol, PlayerPiece.X);
      this.gameBoard.get((toRow - fromRow) / 2 + fromRow)
              .set((toCol - fromCol) / 2 + fromCol, PlayerPiece.X);
    } else {
      throw new IllegalArgumentException("Invalid move. Please, choose a different"
              + "co-ordinate point");
    }
  }

  @Override
  public boolean isGameOver() {
    return !(horizontal() || vertical() || diagonal());
  }

  @Override
  public String getGameState() {
    StringBuilder builder = new StringBuilder();
    for (int row = 0; row < rowNumbers; row++) {
      for (int i = row + 1; rowNumbers - i > 0; i++) {
        builder.append(" ");
      }
      for (int col = 0; col < rowNumbers; col++) {
        switch (gameBoard.get(row).get(col)) {
          case O:
            builder.append("O ");
            continue;
          case X:
            builder.append("_ ");
            continue;
          default:
            continue;
        }
      }
      while (builder.charAt(builder.length() - 1) == ' ') {
        builder.deleteCharAt(builder.length() - 1);
      }
      builder.append("\n");
    }
    builder.deleteCharAt(builder.length() - 1);
    return builder.toString();
  }

  @Override
  protected boolean outsideLoop(int r, int c) {
    return c > r;
  }

  @Override
  protected void empty() throws IllegalArgumentException {
    if (sColumn < 0 || sRow < 0 || sColumn >= rowNumbers || sRow >= rowNumbers || sColumn > sRow) {
      throw new IllegalArgumentException("Invalid empty cell (" + sRow + "," + sColumn + ")");
    }
  }

  private boolean diagonal() {
    for (int i = 0; i < gameBoard.size() - 2; i++) {
      for (int j = 0; j < gameBoard.get(0).size() - 2; j++) {
        if ((gameBoard.get(i).get(j) == PlayerPiece.O
                && gameBoard.get(i + 1).get(j + 1) == PlayerPiece.O
                && gameBoard.get(i + 2).get(j + 2) == PlayerPiece.X)
                || (gameBoard.get(i).get(j) == PlayerPiece.X
                && gameBoard.get(i + 1).get(j + 1) == PlayerPiece.O
                && gameBoard.get(i + 2).get(j + 2) == PlayerPiece.O)
                || (gameBoard.get(i + 2).get(j) == PlayerPiece.O
                && gameBoard.get(i + 1).get(j + 1) == PlayerPiece.O
                && gameBoard.get(i).get(j + 2) == PlayerPiece.X)
                || (gameBoard.get(i + 2).get(j) == PlayerPiece.X
                && gameBoard.get(i + 1).get(j + 1) == PlayerPiece.O
                && gameBoard.get(i).get(j + 2) == PlayerPiece.O)) {
          return true;
        }
      }
    }
    return false;
  }
}
