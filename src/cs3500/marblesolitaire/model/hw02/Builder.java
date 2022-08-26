package cs3500.marblesolitaire.model.hw02;

/**
 * Builder class for marble solitaire class.
 */
public abstract class Builder {
  public int armThickness;
  public int row;
  public int col;

  /**
   * Builder with default board values.
   */
  public Builder() {
    this.armThickness = 3;
    this.row = 3;
    this.col = 3;
  }

  /**
   * Sets the arm thickness of the game grid.
   * @param a the arm thickness (positive)
   * @return {@code this}, for method chaining
   */
  public Builder armTickness(int a) {
    armThickness = a;
    return this;
  }

  /**
   * Sets the empty cell row index of the game grid.
   * @param r the row (positive)
   * @return {@code this}, for method chaining
   */
  public Builder row(int r) {
    row = r;
    return this;
  }

  /**
   * Sets the empty cell column index of the game grid.
   * @param c the col (positive)
   * @return {@code this}, for method chaining
   */
  public Builder col(int c) {
    col = c;
    return this;
  }

  /**
   * Builds and returns the specified {@link MarbleSolitaireModel}.
   *
   * @return a new {@code ConnectNModel}
   */
  public abstract MarbleSolitaireModel build();

  protected abstract Builder returnBuilder();

}
