package cs5004.marblesolitaire.model.hw09;

import java.io.InputStreamReader;

import cs5004.marblesolitaire.model.MarbleSolitaireControllerImpl;
import cs5004.marblesolitaire.model.MarbleSolitaireModel;
import cs5004.marblesolitaire.model.MarbleSolitaireModelImpl;


/**
 * A class that represent main class that allows the user to control the game and choose settings.
 */
public final class MarbleSolitaire {

  private static Integer boardSize;
  private static Integer rowS;
  private static Integer columnS;

  /**
   * The main function that runs the Marble Solitaire Game.
   * @param args are the arguments that run the game with certain arrangements.
   */
  public static void main(String[] args) {
    MarbleSolitaireModel model;
    final MarbleSolitaireControllerImpl controller;
    String playerChooseGame = args[0];

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-size")) {
        i++;
        boardSize = Integer.valueOf(args[i]);
      }
      else if (args[i].equals("-hole")) {
        rowS = Integer.valueOf(args[i + 1]);
        columnS = Integer.valueOf(args[i + 2]);
        i += 2;
      }
    }

    switch (playerChooseGame) {
      case "english" :
        if (boardSize == null && columnS == null && rowS == null) {
          model = new MarbleSolitaireModelImpl();
        } else if (boardSize != null && rowS == null && columnS == null) {
          model = new MarbleSolitaireModelImpl(boardSize);
        } else if (boardSize == null && rowS != null && columnS != null) {
          model = new MarbleSolitaireModelImpl(rowS, columnS);
        } else {
          model = new MarbleSolitaireModelImpl(boardSize, rowS, columnS);
        }
        break;

      case "european" :
        if (boardSize == null && columnS == null && rowS == null) {
          model = new EuropeanSolitaireModelImpl();
        } else if (boardSize != null && rowS == null && columnS == null) {
          model = new EuropeanSolitaireModelImpl(boardSize);
        } else if (boardSize == null && rowS != null && columnS != null) {
          model = new EuropeanSolitaireModelImpl(rowS, columnS);
        } else {
          model = new EuropeanSolitaireModelImpl(boardSize, rowS, columnS);
        }
        break;


      case "triangular" :
        if (boardSize == null && columnS == null && rowS == null) {
          model = new TriangleSolitaireModelImpl();
        } else if (boardSize != null && rowS == null && columnS == null) {
          model = new TriangleSolitaireModelImpl(boardSize);
        } else if (boardSize == null && rowS != null && columnS != null) {
          model = new TriangleSolitaireModelImpl(rowS, columnS);
        } else {
          model = new TriangleSolitaireModelImpl(boardSize, rowS, columnS);
        }
        break;

      default:
        model = new MarbleSolitaireModelImpl();
    }

    controller = new MarbleSolitaireControllerImpl(new InputStreamReader(System.in), System.out);
    controller.play(model);
  }
}
