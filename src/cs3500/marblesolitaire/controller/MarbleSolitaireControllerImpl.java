package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class represent the Marble Controller implementation.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  MarbleSolitaireModel model;
  MarbleSolitaireView view;
  Readable in;
  Scanner scan;

  /**
   * Initializes the controller with model, view and readable object.
   * @param model English Solitaire Model
   * @param view Marble Solitaire Text View
   * @param in Readable object
   * @throws IllegalArgumentException if any of the objects are null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
      Readable in) throws IllegalArgumentException {
    if ((model == null) || (view == null) || (in == null)) {
      throw new IllegalArgumentException("Null");
    }
    this.model = model;
    this.view = view;
    this.in = in;
    this.scan = new Scanner(this.in);
  }

  private void gameOver() {
    try {
      this.view.renderMessage("\n");
      this.view.renderMessage("Game over!");
      this.view.renderMessage("\n");
      this.view.renderBoard();
      this.view.renderMessage("\n");
      this.view.renderMessage("Score: " + model.getScore());
      //this.view.renderMessage("\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  private void gameQuit() {
    try {
      this.view.renderMessage("\n");
      this.view.renderMessage("Game quit!");
      this.view.renderMessage("\n");
      this.view.renderMessage("State of game when quit:");
      this.view.renderMessage("\n");
      this.view.renderBoard();
      this.view.renderMessage("\n");
      this.view.renderMessage("Score: " + model.getScore());
      //this.view.renderMessage("\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  private void moveRender() {
    try {
      this.view.renderMessage("\n");
      this.view.renderMessage("Board state: ");
      this.view.renderMessage("\n");
      this.view.renderBoard();
      this.view.renderMessage("\n");
      this.view.renderMessage("Score: " + model.getScore());
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  private boolean posInt(String input) {
    int output = 0;
    try {
      output = Integer.parseInt(input);
    } catch (IllegalArgumentException e) {
    }
    return output > 0;
  }

  private int askUser(String q) {
    try {
      view.renderMessage("Starting from (1, 1)");
      this.view.renderMessage("\n");
      view.renderMessage("Input " + q + " or Q to quit: ");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
    return this.validInput();
  }

  private int validInput() {
    if (!this.scan.hasNext()) {
      throw new IllegalStateException("Ran out of Inputs");
    }
    String in = this.scan.next();

    while (!this.posInt(in) && !in.equalsIgnoreCase("q")) {
      try {
        this.view.renderMessage("Invalid input try again: \n");
      } catch (IOException e) {
        throw new IllegalStateException();
      }
      in = this.scan.next();
    }
    if (in.equalsIgnoreCase("q")) {
      return 0;
      // this.gameQuit();
      // System.runFinalization();
    }
    else if (this.posInt(in)) {
      return Integer.parseInt(in);
    }
    throw new IllegalArgumentException("HELLO LARRY");
  }

  /**
   * Plays a new game of Marble Solitaire. Throws an exception if the controller is unable to
   * successfully read input or transmit output.
   */
  public void playGame() throws IllegalStateException {
    int fromRow;
    int fromCol;
    int toRow;
    int toCol;
    boolean quit = false;

    this.moveRender();
    while (!(model.isGameOver())) {
      fromRow = askUser("From Row") - 1;
      System.out.println(fromRow);
      if (fromRow == -1) {
        this.gameQuit();
        //System.runFinalization();
        quit = true;
        break;
      }
      fromCol = askUser("From Column") - 1;
      if (fromCol == -1) {
        this.gameQuit();
        //System.runFinalization();
        quit = true;
        break;
      }
      toRow = askUser("To Row") - 1;
      if (toRow == -1) {
        this.gameQuit();
        //System.runFinalization();
        quit = true;
        break;
      }
      toCol = askUser("To Column") - 1;
      if (toCol == -1) {
        this.gameQuit();
        //System.runFinalization();
        quit = true;
        break;
      }

      try {
        model.move(fromRow, fromCol, toRow, toCol);
        this.moveRender();
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }
    } // WHILE LOOP END
    if (!quit) {
      this.gameOver();
      //System.runFinalization();
    }
  }
}
