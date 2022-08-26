package cs3500.marblesolitaire.model.hw02;

/**
 * This class represents English Solitaire Model.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {

  private int armThickness;
  private int score;
  private SlotState [][] board;

  /**
   * Create an English Solitaire Model with default starting empty cell coordinates
   * and arm thickness.
   */
  public EnglishSolitaireModel() {
    this(3,3,3);
  }

  /**
   * Create an English Solitaire Model given the starting empty cell coordinates.
   * @param sRow Row of the starting empty cell
   * @param sCol Column of the starting empty cell
   * @throws  IllegalArgumentException if the starting cell is invalid
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this(3, sRow, sCol);
  }

  /**
   * Create an English Solitaire Model given arm thickness. The staring empty cell coordinates are
   * the middle of the board.
   * @param armThickness Arm thickness of the thinnest row
   * @throws  IllegalArgumentException if the arm thickness is a negative number
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    this(armThickness, (armThickness - 1) + (armThickness / 2),
        (armThickness - 1) + (armThickness / 2));
  }

  /**
   * Create an English Solitaire Model given arm thickness. The staring empty cell coordinates are
   * the middle of the board.
   * @param armThickness Arm thickness of the thinnest row
   * @param sRow Row  of the starting empty cell
   * @param sCol Column of the starting empty cell
   * @throws  IllegalArgumentException if the arm thickness is a negative number
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
      throws IllegalArgumentException {
    if ((armThickness <= 1) || (armThickness % 2 == 0)) {
      throw new IllegalArgumentException("Incorrect arm Thickness"); }
    else if (invalidPos(armThickness, sRow, sCol)) {
      throw new IllegalArgumentException(
          "Invalid empty cell position (" + sRow + ", " + sCol + ")");
    }
    /*
    new EnglishSolitaireBuilder()
        .armTickness(armThickness)
        .row(sRow)
        .col(sCol)
        .build();

     */

    this.armThickness = armThickness;
    int side = armThickness - 1;
    int completeSide = armThickness + (2 * side);
    this.score = (completeSide * completeSide) - ((side * side) * 4) - 1;
    this.board = initBoard(this.armThickness, sRow, sCol);
  }

  /**
   * English Solitaire Builder class.
   */
  public class EnglishSolitaireBuilder extends Builder {

    /**
     * Builds Marble Solitaire model.
     * @return built model with default arm thickness, row and col
     * @throws IllegalStateException if starting cell is invalid or arm thickness is a negative
     */
    public MarbleSolitaireModel build() throws IllegalStateException {
      if ((armThickness <= 1) || (armThickness % 2 == 0)) {
        throw new IllegalArgumentException("Incorrect arm Thickness"); }
      else if (invalidPos(armThickness, row, col)) {
        throw new IllegalArgumentException(
            "Invalid empty cell position (" + row + ", " + col + ")");
      }
      return new EnglishSolitaireModel(this.armThickness, this.col, this.row);
    }

    @Override
    protected Builder returnBuilder() {
      return this;
    }
  }

  /**
   * Checks if position is a valid position on the board.
   * @param row the row of the position sought, starting at 0.
   * @param col the column of the position sought, starting at 0
   * @return Boolean expression if the position is invalid
   */
  private boolean invalidPos(int armThickness, int row, int col) {
    int side = armThickness - 1;
    return ((col < side) || (col > 2 * side))
        && ((row < side) || (row > 2 * side));
  }

  /**
   * Getter for armThickness.
   * @return int arm thickness
   */
  private int getArmThickness() {
    return this.armThickness;
  }

  /**
   * Initializes the board with slot states.
   * @param armThickness Arm thickness of the thinnest row.
   * @param row the row of the position sought, starting at 0.
   * @param col the column of the position sought, starting at 0.
   */
  private SlotState[][] initBoard(int armThickness, int row, int col) {
    int side = armThickness - 1;
    int complete = this.getBoardSize();
    SlotState[][] board = new SlotState[complete][complete];
    for (int i = 0; i < side; i++) {
      for (int j = 0; j < complete; j++) {
        if ((j < side) || (j >= complete - side)) {
          board[i][j] = SlotState.Invalid;
        }
        else {
          board[i][j] = SlotState.Marble;
        }
      }
    }
    for (int i = side; i < complete - side; i++) {
      for (int j = 0; j < complete ; j++) {
        board[i][j] = SlotState.Marble;
      }
    }
    for (int i = complete - side; i < complete; i++) {
      for (int j = 0; j < complete; j++) {
        if ((j < side) || (j >= complete - side)) {
          board[i][j] = SlotState.Invalid;
        }
        else {
          board[i][j] = SlotState.Marble;
        }
      }
    }
    board[row][col] = SlotState.Empty;
    return board;
  }

  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid. Specific
   * implementations may place additional constraints on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  public void move(int fromRow, int fromCol, int toRow, int toCol)
      throws IllegalArgumentException {
    int inRow = (fromRow + toRow) / 2;
    int inCol = (fromCol + toCol) / 2;
    if (!(this.possibleMove(fromRow, fromCol, toRow, toCol))) {
      throw new IllegalArgumentException("Invalid move. Play again");
    }
    board[fromRow][fromCol] = SlotState.Empty;
    board[toRow][toCol] = SlotState.Marble;
    board[inRow][inCol] = SlotState.Empty;
    score = score - 1;
  }

  private boolean possibleMove(int fromRow, int fromCol, int toRow, int toCol) {
    int inRow = (fromRow + toRow) / 2;
    int inCol = (fromCol + toCol) / 2;
    int complete = this.getBoardSize();
    if ((fromRow < 0 || fromRow > complete) || (fromCol < 0 || fromCol > complete)) {
      return false;
    }
    else if ((toRow < 0 || toRow > complete) || (toCol < 0 || toCol > complete)) {
      return false;
    }
    if (this.getSlotAt(fromRow, fromCol) != SlotState.Marble) {
      return false;
    }
    else if (this.getSlotAt(toRow, toCol) != SlotState.Empty) {
      return false;
    }
    else if (((Math.abs(fromRow - toRow) != 2) && (Math.abs(fromCol - toCol) != 2))) {
      return false;
    }
    else if ((fromRow != toRow) && (fromCol != toCol)) {
      return false;
    }
    else if (this.getSlotAt(inRow, inCol) != SlotState.Marble) {
      return false;
    }
    return true;
  }

  /**
   * Determine and return if the game is over or not. A game is over if no
   * more moves can be made.
   * @return true if the game is over, false otherwise
   */
  public boolean isGameOver() {
    int complete = this.getBoardSize();
    boolean state = true;
    for (int i = 0; i < complete; i++) {
      for (int j = 0; j < complete - 2; j++) {
        if (possibleMove(i, j, i, j + 2)) {
          state = false;
        }
      }
    }
    for (int i = 0; i < complete - 2; i++) {
      for (int j = 0; j < complete; j++) {
        if (possibleMove(i, j, i + 2, j)) {
          state = false;
        }
      }
    }
    for (int i = 0; i < complete; i++) {
      for (int j = 0; j < complete - 2; j++) {
        if (possibleMove(i, j + 2, i, j)) {
          state = false;
        }
      }
    }
    for (int i = 0; i < complete - 2; i++) {
      for (int j = 0; j < complete; j++) {
        if (possibleMove(i + 2, j, i, j)) {
          state = false;
        }
      }
    }
    return state;
  }

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board.
   * @return the size as an integer
   */
  public int getBoardSize() {
    int side = this.getArmThickness() - 1;
    return this.getArmThickness() + 2 * side;
  }

  /**
   * Get the state of the slot at a given position on the board.
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond
   *         the dimensions of the board
   */
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    int complete = this.getBoardSize();
    if ((row < 0 || row > complete) || (col < 0 || col > complete)) {
      throw new IllegalArgumentException(
          "Invalid empty cell position (" + row + ", " + col + ")");
    }
    return board[row][col];
  }

  /**
   * Return the number of marbles currently on the board.
   * @return the number of marbles currently on the board
   */
  public int getScore() {
    return this.score;
  }


}



