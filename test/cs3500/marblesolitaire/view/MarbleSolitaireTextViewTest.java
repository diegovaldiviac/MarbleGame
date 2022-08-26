package cs3500.marblesolitaire.view;

import static org.junit.Assert.assertEquals;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import org.junit.Test;

/**
 * This is the test class for Marble Solitaire text view.
 */
public class MarbleSolitaireTextViewTest {

  MarbleSolitaireModelState s1 = new EnglishSolitaireModel();
  MarbleSolitaireView m1 = new MarbleSolitaireTextView(s1);

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRowCol() {
    new MarbleSolitaireTextView(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() {
    new MarbleSolitaireTextView(null, System.out);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAppendable() {
    new MarbleSolitaireTextView(s1, null);
  }


  @Test
  public void testToString() {
    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O", m1.toString());
  }

  @Test
  public void testToStringRowCol() {
    MarbleSolitaireModelState s2 = new EnglishSolitaireModel(3,2);
    MarbleSolitaireView m2 = new MarbleSolitaireTextView(s2);

    assertEquals("    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O _ O O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O", m2.toString());
  }

}