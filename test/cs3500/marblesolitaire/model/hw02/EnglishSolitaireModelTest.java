package cs3500.marblesolitaire.model.hw02;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import org.junit.Test;

/**
 * This is the test class for English Solitaire Model.
 */
public class EnglishSolitaireModelTest {

  MarbleSolitaireModel m1;

  private void init() {
    m1 = new EnglishSolitaireModel();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRowCol() {
    new EnglishSolitaireModel(0, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRowCol2() {
    new EnglishSolitaireModel(3, 0, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegArm() {
    new EnglishSolitaireModel(-2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArm() {
    new EnglishSolitaireModel(2, 0, 4);
  }

  @Test
  public void testValid() {
    init();
    assertEquals(SlotState.Marble, m1.getSlotAt(3, 3));
  }

  @Test
  public void testValidRowCol() {
    m1 = new EnglishSolitaireModel(2,3);
    assertEquals(SlotState.Empty, m1.getSlotAt(2,3));
  }

  @Test
  public void testValidArm() {
    m1 = new EnglishSolitaireModel(5);
    assertEquals(SlotState.Invalid, m1.getSlotAt(0,3));
  }

  @Test
  public void testValidAll() {
    m1 = new EnglishSolitaireModel(5, 4, 3);
    assertEquals(SlotState.Empty, m1.getSlotAt(4,3));
  }


  @Test
  public void testGetBoardSize() {
    assertEquals(7, new EnglishSolitaireModel(3).getBoardSize());
    assertEquals(13, new EnglishSolitaireModel(5, 5, 4).getBoardSize());
  }

  @Test
  public void testGetScore() {
    assertEquals(32, new EnglishSolitaireModel().getScore());
    assertEquals(104, new EnglishSolitaireModel(5).getScore());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtError() {
    init();
    m1.getSlotAt(100,100);
  }

  @Test
  public void testGetSlotAtMarble() {
    init();
    assertEquals(SlotState.Marble, m1.getSlotAt(5,4));
  }

  @Test
  public void testGetSlotAtInvalid() {
    init();
    assertEquals(SlotState.Invalid, m1.getSlotAt(0,0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMove1() {
    init();
    m1.move(100,100, 4,3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMove2() {
    init();
    m1.move(3,3, 100,100);
  }

  // testing if before has a marble
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMove3() {
    init();
    m1.move(3,3, 4,3);
  }

  // testing if two is empty
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMove4() {
    init();
    m1.move(5,3, 3,4);
  }

  // test if in-between there is a marble
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMove5() {
    init();
    m1.move(3,1, 3,3);
    m1.move(6,3,4,3);
  }

  // Move is diagonal.
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMove6() {
    init();
    m1.move(3,1, 2,5);
  }

  // More than two spots apart.
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMove7() {
    init();
    m1.move(3, 1, 3, 5);
  }

  // Should test move method from all direction.
  @Test
  public void testMoveBelowCol() {
    init();
    m1.move(5,3,3,3);
    assertEquals(SlotState.Empty, m1.getSlotAt(4,3));
    assertEquals(31,m1.getScore());
  }

  @Test
  public void testMoveAboveCol() {
    init();
    m1.move(1,3,3,3);
    assertEquals(SlotState.Empty, m1.getSlotAt(2,3));
    assertEquals(31,m1.getScore());
  }

  @Test
  public void testMoveLeftCol() {
    init();
    m1.move(3,1,3,3);
    assertEquals(SlotState.Empty, m1.getSlotAt(3,2));
    assertEquals(31,m1.getScore());
  }

  @Test
  public void testMoveRightCol() {
    init();
    m1.move(3,5,3,3);
    assertEquals(SlotState.Empty, m1.getSlotAt(3,4));
    assertEquals(31,m1.getScore());
  }

  @Test
  public void testGameOverFalse() {
    init();
    assertFalse(m1.isGameOver());
  }

  @Test
  public void testGameOverTrue() {
    init();
    m1.move(3,1,3,3);
    m1.move(3,4,3,2);
    m1.move(3,6,3,4);
    m1.move(5,3,3,3);
    m1.move(2,3,4,3);
    m1.move(0,3,2,3);
    assertTrue(m1.isGameOver());
  }


}