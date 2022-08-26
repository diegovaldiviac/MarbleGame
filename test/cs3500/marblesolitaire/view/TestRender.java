package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * test the rendering of the view class.
 */
public class TestRender {

  @Test
  public void testRenderMessage() throws IllegalStateException {
    Appendable out = System.out;
    MarbleSolitaireView view =
        new MarbleSolitaireTextView(new EnglishSolitaireModel(), out);

    try {
      view.renderMessage("Hello World!");
    } catch (IOException e) {
      throw new IllegalStateException();
    }

    assertEquals(out.toString(), "Hello World!");
    assertEquals("Hello World!", out.toString());

  }

  @Test
  public void testRenderBoard() throws IllegalStateException {
    Appendable out = System.out;
    MarbleSolitaireView view =
        new MarbleSolitaireTextView(new EnglishSolitaireModel(), out);

    try {
      view.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException();
    }

    assertEquals(out.toString(), "    O O O \n"
        + "    O _ O \n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O \n"
        + "    O O O");
    assertEquals("    O O O \n"
        + "    O _ O \n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O \n"
        + "    O O O", out.toString());
  }
}
