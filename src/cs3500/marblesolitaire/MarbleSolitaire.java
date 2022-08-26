package cs3500.marblesolitaire;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import java.io.InputStreamReader;

/**
 * Marble Solitaire game running class.
 */
public class MarbleSolitaire {

  /**
   * Main method to running game.
   * @param args user inputs from the console
   * @throws IllegalStateException Game exception handled in model
   */
  public static void main(String[] args) throws IllegalStateException {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model,
          new MarbleSolitaireTextView(model), new InputStreamReader(System.in));
    controller.playGame();
  }
}
