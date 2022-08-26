package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import java.util.Objects;

/**
 * Overrides move method to return values passed.
 */
public class ConfirmInputs extends EnglishSolitaireModel {

  final StringBuilder log;

  public ConfirmInputs(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) {
    log.append("The numbers passed are: " + fromRow + " " + fromCol + " " + toRow + " " + toCol);
  }
}
