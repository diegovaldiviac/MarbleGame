package cs3500.marblesolitaire.controller;

/**
 * This interface represents the operations offered by the marble solitaire
 * controller. One object of the model represents one game of marble solitaire.
 */
public interface MarbleSolitaireController {

  /**
   * Plays a new game of Marble Solitaire. Throws an exception if the controller is unable to
   * successfully read input or transmit output.
   */
  void playGame() throws IllegalStateException;
}
