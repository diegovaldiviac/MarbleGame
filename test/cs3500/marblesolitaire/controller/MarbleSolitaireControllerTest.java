package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import static org.junit.Assert.assertEquals;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;

/**
 * Class file to test the Marble Solitaire Control.
 */
public class MarbleSolitaireControllerTest {

  MarbleSolitaireModel model = new EnglishSolitaireModel();
  MarbleSolitaireView view = new MarbleSolitaireTextView(model);

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    new MarbleSolitaireControllerImpl(null, view, new InputStreamReader(System.in));
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNullView() {
    new MarbleSolitaireControllerImpl(model, null, new InputStreamReader(System.in));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullReadable() {
    new MarbleSolitaireControllerImpl(model, view, null);
  }


  @Test
  public void testPlayGame() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    StringBuilder dontCare = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, dontCare);
    Reader in = new StringReader("2 4 4 4");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
        in);
    controller.playGame();
    assertEquals("    O O O \n"
        + "    O _ O \n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O \n"
        + "    O O O"
        + "Score: 32", in.toString());
  }
}
