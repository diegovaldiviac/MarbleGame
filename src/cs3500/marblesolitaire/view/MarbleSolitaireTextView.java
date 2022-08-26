package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import java.io.IOException;

/**
 * This class represents the viewing option of the Marble Solitaire board.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {

  private final MarbleSolitaireModelState state;
  private Appendable out;

  /**
   * Initializes the view object.
   * @param state object that represents current state of the marble board
   * @throws IllegalArgumentException if the object is a null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState state)
      throws IllegalArgumentException {
    if (state == null) {
      throw new IllegalArgumentException("Null");
    }
    this.state = state;
    this.out = System.out;
  }

  /**
   * Initializes the view object.
   * @param state object that represents current state of the marble board
   * @param out object that this view uses as its destination.
   * @throws IllegalArgumentException if any of the objects are null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState state, Appendable out)
      throws IllegalArgumentException {
    if (state == null) {
      throw new IllegalArgumentException("Null");
    }
    else if (out == null) {
      throw new IllegalArgumentException("Null");
    }
    this.state = state;
    this.out = out;
  }

  /**
   * Turns the current board slot state into a string.
   * @return a String representation of the state of the board
   */
  @Override
  public String toString() {
    StringBuilder board = new StringBuilder();
    int complete = state.getBoardSize();
    for (int i = 0; i < complete; i++) {
      if (i != 0) {
        board.append("\n");
      }
      for (int j = 0; j < complete; j++) {
        if (state.getSlotAt(i, j).equals(SlotState.Invalid)) {
          if (j == 0) {
            board.append("  ");
          }
          else if (((j < (complete) / 2) &&
              (state.getSlotAt(i, j - 1).equals(SlotState.Invalid)))) {
            board.append("  ");
          }
        }
        else if (state.getSlotAt(i, j).equals(SlotState.Marble)) {
          board.append("O");
          if (j < complete - 1) {
            if (!(state.getSlotAt(i, j + 1).equals(SlotState.Invalid))) {
              board.append(" ");
            }
          }
        }
        else if (state.getSlotAt(i, j).equals(SlotState.Empty)) {
          board.append("_");
          if (j < complete - 1) {
            if (!(state.getSlotAt(i, j + 1).equals(SlotState.Invalid))) {
              board.append(" ");
            }
          }
        }
      }
    }
    return board.toString();
  }

  /**
   * Render the board to the provided data destination. The board should be rendered exactly
   * in the format produced by the toString method above
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  public void renderBoard() throws IOException {
    try {
      this.out.append(this.toString());
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  /**
   * Render a specific message to the provided data destination.
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  public void renderMessage(String message) throws IOException {
    try {
      this.out.append(message);
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

}