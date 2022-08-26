package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import java.io.StringReader;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test input test from controller to move method in model.
 */
public class InputTest {

  @Test
  public void testInputs() {

    String[] inputs = {"1", "3", "3", "3"};
    Readable in = null;

    for (int i = 0; i < inputs.length; i++) {
      in = new StringReader(inputs[i]);
    }
    //Readable in = new StringReader("1 3 3 3");
    StringBuilder dontCareOutputs = new StringBuilder();

    StringBuilder log = new StringBuilder();
    MarbleSolitaireModel model = new ConfirmInputs(log);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, dontCareOutputs);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);
    controller.playGame();

    assertEquals("The numbers passed are: 1 3 3 3", log.toString());
  }
}
