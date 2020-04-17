package cs5004.marblesolitaire.model.hw09;

import java.util.ArrayList;

import cs5004.marblesolitaire.model.MarbleSolitaireModel;
import cs5004.marblesolitaire.model.MarbleSolitaireModelImpl;


/**
 * A class that represents the abstract class that holds the code
 *     for an abstract marble solitaire model.
 */
public abstract class AMarbleSolitaire implements MarbleSolitaireModel {

  protected int armThickness;
  protected int sRow;
  protected int sColumn;
  protected ArrayList<ArrayList<PlayerPiece>> gameBoard;

  /**
   * The constructor method that represents a move in the game. A legal move needs a marble in
   *     between and marble and an empty space, and makes the middle marble disappear.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if the move cannot be made.
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (fromRow >= 3 * armThickness - 2 || fromCol >= 3 * armThickness - 2
            || toRow >= 3 * armThickness - 2 || toCol >= 3 * armThickness - 2
            || fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0) {
      throw new IllegalArgumentException("Invalid move. Please, choose a different"
              + " co-ordinate point");
    } if (moveUp(fromRow, fromCol, toRow, toCol)) {
      gameBoard.get(fromRow).set(fromCol, PlayerPiece.X);
      gameBoard.get(fromRow - 1).set(fromCol, PlayerPiece.X);
      gameBoard.get(fromRow - 2).set(fromCol, PlayerPiece.O);
    } else if (down(fromRow, fromCol, toRow, toCol)) {
      gameBoard.get(fromRow).set(fromCol, PlayerPiece.X);
      gameBoard.get(fromRow + 1).set(fromCol, PlayerPiece.X);
      gameBoard.get(fromRow + 2).set(fromCol, PlayerPiece.O);

    } else if (left(fromRow, fromCol, toRow, toCol)) {
      gameBoard.get(fromRow).set(fromCol, PlayerPiece.X);
      gameBoard.get(fromRow).set(fromCol - 1, PlayerPiece.X);
      gameBoard.get(fromRow).set(fromCol - 2, PlayerPiece.O);

    } else if (right(fromRow, fromCol, toRow, toCol)) {
      gameBoard.get(fromRow).set(fromCol, PlayerPiece.X);
      gameBoard.get(fromRow).set(fromCol + 1, PlayerPiece.X);
      gameBoard.get(fromRow).set(fromCol + 2, PlayerPiece.O);

    } else {
      throw new IllegalArgumentException("Invalid move. Please, choose a different"
              + "co-ordinate point");
    }
  }

  /**
   * A constructor that determine if there are no more legal moves to be made.
   *
   * @return true if the game is over, and false otherwise.
   */
  @Override
  public boolean isGameOver() {
    return !(horizontal() || vertical());
  }

  /**
   * A constructor method that exact be used to print the game state in the format illustrated.
   *
   * @return the current state of the game as a String object.
   */
  @Override
  public String getGameState() {
    StringBuilder str = new StringBuilder();
    for (ArrayList<PlayerPiece> playerPieces : gameBoard) {
      for (PlayerPiece p : playerPieces) {
        switch (p) {
          case INVALID:
            str.append("  ");
            continue;
          case O:
            str.append("O ");
            continue;
          case X:
            str.append("_ ");
            continue;
          default:
            continue;
        }
      }
      while (str.charAt(str.length() - 1) == ' ') {
        str.deleteCharAt(str.length() - 1);
      }
      str.append("\n");
    }
    str.deleteCharAt(str.length() - 1);
    return str.toString();
  }

  /**
   * A constructor method that determine the player scores.
   *
   * @return returns the current score in the game.
   */
  @Override
  public int getScore() {
    int score = 0;

    for (ArrayList<MarbleSolitaireModelImpl.PlayerPiece> playerPieces : gameBoard) {
      for (MarbleSolitaireModelImpl.PlayerPiece p : playerPieces) {
        if (p == MarbleSolitaireModelImpl.PlayerPiece.O) {
          score = score + 1;
        }
      }
    }
    return score;
  }

  /**
   * A helper constructor method that  initializes the board based on armThickness, sRow, and sCol,
   * placing Marbles on the board.
   */
  protected void board() {
    ArrayList<ArrayList<PlayerPiece>> playerResult = new ArrayList<ArrayList<PlayerPiece>>();
    ArrayList<PlayerPiece> temporaryVertical;
    for (int vertical = 0; vertical < armThickness * 2 + armThickness - 2; vertical++) {
      temporaryVertical = new ArrayList<PlayerPiece>();
      for (int horizontal = 0; horizontal < armThickness * 2 + armThickness - 2;
           horizontal++) {
        if (this.outsideLoop(vertical, horizontal)) {
          temporaryVertical.add(PlayerPiece.INVALID);
        } else if (vertical == sRow && horizontal == sColumn) {
          temporaryVertical.add(PlayerPiece.X);
        } else {
          temporaryVertical.add(PlayerPiece.O);
        }
      }
      playerResult.add(temporaryVertical);
    }
    gameBoard = playerResult;
  }

  /**
   * A helper method constructor that checks if any given co-ordinate points that are on the board.
   *
   * @throws IllegalArgumentException any co-ordinate points that not on the board.
   */
  protected void empty() throws IllegalArgumentException {
    if (this.outsideLoop(sRow, sColumn)) {
      throw new IllegalArgumentException("Invalid empty cell (" + sRow + "," + sColumn + ")");
    }
  }

  /**
   * A helper method constructor that checks if any given co-ordinate points that are outside the
   * board.
   *
   * @param r the x-axis co-ordinate point.
   * @param c the y-axis co-ordinate point.
   */
  protected abstract boolean outsideLoop(int r, int c);

  /**
   * A helper method that determine if the piece can move right.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0).
   * @param fromCol the column number of the position to be moved from (starts at 0).
   * @param toRow   the row number of the position to be moved to (starts at 0).
   * @param toCol   the column number of the position to be moved to (starts at 0).
   * @return true if the piece can move right, false otherwise.
   */
  private boolean right(int fromRow, int fromCol, int toRow, int toCol) {
    if (outsideLoop(fromRow, fromCol)) {
      return false;

    } else if (outsideLoop(toRow, toCol)) {
      return false;

    } else {
      return (fromRow == toRow) && (fromCol + 2 == toCol)
              && (gameBoard.get(fromRow).get(fromCol)
              == MarbleSolitaireModelImpl.PlayerPiece.O)
              && (gameBoard.get(fromRow).get(fromCol + 1)
              == MarbleSolitaireModelImpl.PlayerPiece.O)
              && (gameBoard.get(fromRow).get(fromCol + 2)
              == MarbleSolitaireModelImpl.PlayerPiece.X);
    }
  }

  /**
   * A helper method that determine if the piece can move left.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0).
   * @param fromCol the column number of the position to be moved from (starts at 0).
   * @param toRow   the row number of the position to be moved to (starts at 0).
   * @param toCol   the column number of the position to be moved to (starts at 0).
   * @return true if the piece can move right, false otherwise.
   */
  private boolean left(int fromRow, int fromCol, int toRow, int toCol) {
    if (outsideLoop(fromRow, fromCol)) {
      return false;

    } else if (outsideLoop(toRow, toCol)) {
      return false;

    } else {
      return (fromRow == toRow) && (fromCol - 2 == toCol)
              && (gameBoard.get(fromRow).get(fromCol)
              == MarbleSolitaireModelImpl.PlayerPiece.O)
              && (gameBoard.get(fromRow).get(fromCol - 1)
              == MarbleSolitaireModelImpl.PlayerPiece.O)
              && (gameBoard.get(fromRow).get(fromCol - 2)
              == MarbleSolitaireModelImpl.PlayerPiece.X);
    }
  }

  /**
   * A helper method that determine if the piece can move up.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0).
   * @param fromCol the column number of the position to be moved from (starts at 0).
   * @param toRow   the row number of the position to be moved to (starts at 0).
   * @param toCol   the column number of the position to be moved to (starts at 0).
   * @return true if the piece can move right, false otherwise.
   */
  private boolean moveUp(int fromRow, int fromCol, int toRow, int toCol) {
    if (outsideLoop(fromRow, fromCol)) {
      return false;

    } else if (outsideLoop(toRow, toCol)) {
      return false;

    } else {
      return (fromRow - 2 == toRow) && (fromCol == toCol)
              && (gameBoard.get(fromRow).get(fromCol)
              == MarbleSolitaireModelImpl.PlayerPiece.O)
              && (gameBoard.get(fromRow - 1).get(fromCol)
              == MarbleSolitaireModelImpl.PlayerPiece.O)
              && (gameBoard.get(fromRow - 2).get(fromCol)
              == MarbleSolitaireModelImpl.PlayerPiece.X);
    }
  }

  /**
   * A helper method that determine if the piece can move down.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0).
   * @param fromCol the column number of the position to be moved from (starts at 0).
   * @param toRow   the row number of the position to be moved to (starts at 0).
   * @param toCol   the column number of the position to be moved to (starts at 0).
   * @return true if the piece can move right, false otherwise.
   */
  private boolean down(int fromRow, int fromCol, int toRow, int toCol) {
    if (outsideLoop(fromRow, fromCol)) {
      return false;

    } else if (outsideLoop(toRow, toCol)) {
      return false;

    } else {
      return (fromRow + 2 == toRow)
              && (fromCol == toCol)
              && (gameBoard.get(fromRow).get(fromCol)
              == MarbleSolitaireModelImpl.PlayerPiece.O)
              && (gameBoard.get(fromRow + 1).get(fromCol)
              == MarbleSolitaireModelImpl.PlayerPiece.O)
              && (gameBoard.get(fromRow + 2).get(fromCol)
              == MarbleSolitaireModelImpl.PlayerPiece.X);
    }
  }

  /**
   * A helper constructor that would check the board to see if there is a move in the vertical
   * direction that exists.
   *
   * @return true if the vertical movement is valid. Otherwise, return false.
   */
  protected boolean vertical() {
    for (int a = 0; a < gameBoard.size() - 2; a++) {
      for (int j = 0; j < gameBoard.get(0).size(); j++) {
        if ((gameBoard.get(a).get(j) == MarbleSolitaireModelImpl.PlayerPiece.O
                && gameBoard.get(a + 1).get(j) == MarbleSolitaireModelImpl.PlayerPiece.O
                && gameBoard.get(a + 2).get(j) == MarbleSolitaireModelImpl.PlayerPiece.X)
                || (gameBoard.get(a).get(j) == MarbleSolitaireModelImpl.PlayerPiece.X
                && gameBoard.get(a + 1).get(j) == MarbleSolitaireModelImpl.PlayerPiece.O
                && gameBoard.get(a + 2).get(j) == MarbleSolitaireModelImpl.PlayerPiece.O)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * A helper constructor that would check the board to see if there is a move in the horizontal
   * direction that exists.
   *
   * @return true if the horizontal movement is valid. Otherwise, return false.
   */
  protected boolean horizontal() {
    for (int i = 0; i < gameBoard.size(); i++) {
      for (int j = 0; j < gameBoard.get(0).size() - 2; j++) {
        if ((gameBoard.get(i).get(j) == MarbleSolitaireModelImpl.PlayerPiece.O
                && gameBoard.get(i).get(j + 1) == MarbleSolitaireModelImpl.PlayerPiece.O
                && gameBoard.get(i).get(j + 2) == MarbleSolitaireModelImpl.PlayerPiece.X)
                || (gameBoard.get(i).get(j) == MarbleSolitaireModelImpl.PlayerPiece.X
                && gameBoard.get(i).get(j + 1) == MarbleSolitaireModelImpl.PlayerPiece.O
                && gameBoard.get(i).get(j + 2) == MarbleSolitaireModelImpl.PlayerPiece.O)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * A method enum constructor where it pieces of the player and their game. X represents the slot
   * is empty. O represents the marble on the game board. INVALID represents the slots that are
   * outside the game board.
   */
  protected enum PlayerPiece {
    X, O, INVALID
  }
}
