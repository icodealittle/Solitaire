package cs5004.marblesolitaire.model;

/**
 * A class that represent the controller interface.
 */
public interface IController {

  /**
   * Plays the given model of the game marble solitaire
   *     based on inputs that the controller is initialized with.
   * @param model is the model for the game that is passed into the constructor.
   */

  void play(MarbleSolitaireModel model);
}
