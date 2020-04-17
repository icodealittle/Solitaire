import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


import cs5004.marblesolitaire.model.MarbleSolitaireModelImpl;

/**
 * A junit test cases for MarbleSolitaireModelImpl.
 */
public class MarbleSolitaireModelImplTest {

  private MarbleSolitaireModelImpl gamePlay;
  private MarbleSolitaireModelImpl defaultBoard;
  private MarbleSolitaireModelImpl gamePlay2;
  private MarbleSolitaireModelImpl gamePlay3;


  @Before
  public void setUp() {
    defaultBoard = new MarbleSolitaireModelImpl();
    gamePlay = new MarbleSolitaireModelImpl(5);
    gamePlay2 = new MarbleSolitaireModelImpl(2, 3);
    gamePlay3 = new MarbleSolitaireModelImpl(5, 4, 4);
  }

  @Test
  public void testCheckStandardStart() {
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", defaultBoard.getGameState());
  }

  @Test
  public void testCheckBoardWith5Thickness() {
    assertEquals("        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", gamePlay.getGameState());
  }

  @Test
  public void defaultValidityMove() {
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", gamePlay2.getGameState());
  }

  @Test
  public void armThicknessValidityMove() {
    assertEquals("        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O _ O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", gamePlay3.getGameState());
  }

  @Test
  public void moveDefaultBoard_Row6Column4() {
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O _", new MarbleSolitaireModelImpl(6, 4).getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckOutsideOfLoopUp() {
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", new MarbleSolitaireModelImpl(0, 7).getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckOutsideOfLoopDown() {
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", new MarbleSolitaireModelImpl(6, 7).getGameState());
  }

  @Test
  public void startScore() {
    assertEquals(32, defaultBoard.getScore());
  }

  @Test
  public void gameState() {
    defaultBoard.move(5, 3, 3, 3);
    defaultBoard.move(4, 1, 4, 3);
    defaultBoard.move(6, 2, 4, 2);
    defaultBoard.move(6, 4, 6, 2);
    defaultBoard.move(3, 2, 5, 2);
    defaultBoard.move(6, 2, 4, 2);
    defaultBoard.move(1, 2, 3, 2);
    defaultBoard.move(2, 0, 2, 2);
    defaultBoard.move(4, 0, 2, 0);
    defaultBoard.move(2, 3, 2, 1);
    defaultBoard.move(2, 0, 2, 2);
    defaultBoard.move(2, 5, 2, 3);
    defaultBoard.move(0, 4, 2, 4);
    defaultBoard.move(0, 2, 0, 4);
    defaultBoard.move(3, 4, 1, 4);
    defaultBoard.move(0, 4, 2, 4);
    defaultBoard.move(5, 4, 3, 4);
    defaultBoard.move(4, 6, 4, 4);
    defaultBoard.move(2, 6, 4, 6);
    defaultBoard.move(4, 3, 4, 5);
    defaultBoard.move(4, 6, 4, 4);
    defaultBoard.move(2, 3, 2, 5);
    defaultBoard.move(2, 5, 4, 5);
    defaultBoard.move(4, 5, 4, 3);
    defaultBoard.move(4, 3, 4, 1);
    defaultBoard.move(4, 1, 2, 1);
    assertEquals("    _ _ _\n"
            + "    _ O _\n"
            + "_ O O _ _ _ _\n"
            + "_ _ O O O _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _", defaultBoard.getGameState());
  }

  @Test
  public void testCheckIsGameOver() {
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(1, 3, 3,3);
    defaultBoard.move(4, 3, 2,3);
    defaultBoard.move(6, 3, 4,3);
    defaultBoard.move(3, 1, 3,3);
    defaultBoard.move(3, 4, 3,2);
    defaultBoard.move(3, 6, 3,4);
    assertEquals(true, defaultBoard.isGameOver());
  }

  @Test
  public void testCheckIsGameOverDiagramExpected() {
    defaultBoard.move(1, 3, 3,3);
    defaultBoard.move(4, 3, 2,3);
    defaultBoard.move(6, 3, 4,3);
    defaultBoard.move(3, 1, 3,3);
    defaultBoard.move(3, 4, 3,2);
    defaultBoard.move(3, 6, 3,4);
    assertEquals("    O O O\n"
            + "    O _ O\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O O O O O O O\n"
            + "    O _ O\n"
            + "    O _ O", defaultBoard.getGameState());
    assertEquals(true, defaultBoard.isGameOver());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveZero() {
    defaultBoard.move(0, 0,
            5, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveNeg() {
    defaultBoard.move(3, 4,
            3, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove3() {
    defaultBoard.move(4, 5,
            2, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove4() {
    defaultBoard.move(- 6, 3,
            6, 5);
  }

  @Test
  public void lastMove() {
    defaultBoard.move(5, 3, 3, 3);
    defaultBoard.move(4, 1, 4, 3);
    defaultBoard.move(6, 2, 4, 2);
    defaultBoard.move(6, 4, 6, 2);
    defaultBoard.move(3, 2, 5, 2);
    defaultBoard.move(6, 2, 4, 2);
    defaultBoard.move(1, 2, 3, 2);
    defaultBoard.move(2, 0, 2, 2);
    defaultBoard.move(4, 0, 2, 0);
    defaultBoard.move(2, 3, 2, 1);
    defaultBoard.move(2, 0, 2, 2);
    defaultBoard.move(2, 5, 2, 3);
    defaultBoard.move(0, 4, 2, 4);
    defaultBoard.move(0, 2, 0, 4);
    defaultBoard.move(3, 4, 1, 4);
    defaultBoard.move(0, 4, 2, 4);
    defaultBoard.move(5, 4, 3, 4);
    defaultBoard.move(4, 6, 4, 4);
    defaultBoard.move(2, 6, 4, 6);
    defaultBoard.move(4, 3, 4, 5);
    defaultBoard.move(4, 6, 4, 4);
    defaultBoard.move(2, 3, 2, 5);
    defaultBoard.move(2, 5, 4, 5);
    defaultBoard.move(4, 5, 4, 3);
    defaultBoard.move(4, 3, 4, 1);
    defaultBoard.move(4, 1, 2, 1);
    defaultBoard.move(2, 1, 2, 3);
    defaultBoard.move(3, 3, 3, 1);
    defaultBoard.move(2, 3, 0, 3);

    assertEquals(3, defaultBoard.getScore());
    assertEquals(true, defaultBoard.isGameOver());
    assertEquals("    _ O _\n"
            + "    _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ O _ _ O _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _", defaultBoard.getGameState());
  }

  @Test
  public void gameStateThroughOut() {
    assertEquals(32, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(5, 3, 3, 3);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "    O _ O\n"
            + "    O O O", defaultBoard.getGameState());
  }

  @Test
  public void gameStateThroughOut2() {
    assertEquals(32, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(3, 1, 3, 3);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", defaultBoard.getGameState());
    assertEquals(false, defaultBoard.isGameOver());
    assertEquals(31, defaultBoard.getScore());
  }

  @Test
  public void testMove() {
    defaultBoard.move(1, 3, 3, 3);
    assertEquals("    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", defaultBoard.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckCannotMove() {
    defaultBoard.move(1, 3, 5, 3);
    assertEquals("    O O O\n"
            + "    O _ O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O _ O\n"
            + "    O O O", defaultBoard.getGameState());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveSamePlace() {
    defaultBoard.move(3, 3, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveTooFar() {
    gamePlay2.move(5, 3, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveOver2Spots() {
    gamePlay2.move(4,2, 6,4);
  }

  //Center spot for 5 arm thickness is (6, 6) and move to (4, 8).
  @Test(expected = IllegalArgumentException.class)
  public void diagonalMove() {
    gamePlay2.move(6,6, 4,8);
  }

  @Test
  public void partiallyPlayGame() {
    defaultBoard.move(3, 1, 3, 3);
    defaultBoard.move(5, 2, 3, 2);
    defaultBoard.move(4, 4, 4, 2);
    defaultBoard.move(2, 3, 4, 3);
    assertEquals(28, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O _ O O O\n"
            + "O _ O _ O O O\n"
            + "O O O O _ O O\n"
            + "    _ O O\n"
            + "    O O O", defaultBoard.getGameState());
  }

  @Test
  public void beforeGameOver() {
    defaultBoard.move(5, 3, 3, 3);
    defaultBoard.move(4, 1, 4, 3);
    defaultBoard.move(6, 2, 4, 2);
    defaultBoard.move(6, 4, 6, 2);
    defaultBoard.move(3, 2, 5, 2);
    defaultBoard.move(6, 2, 4, 2);
    defaultBoard.move(1, 2, 3, 2);
    defaultBoard.move(2, 0, 2, 2);
    defaultBoard.move(4, 0, 2, 0);
    defaultBoard.move(2, 3, 2, 1);
    defaultBoard.move(2, 0, 2, 2);
    defaultBoard.move(2, 5, 2, 3);
    defaultBoard.move(0, 4, 2, 4);
    defaultBoard.move(0, 2, 0, 4);
    defaultBoard.move(3, 4, 1, 4);
    defaultBoard.move(0, 4, 2, 4);
    defaultBoard.move(5, 4, 3, 4);
    defaultBoard.move(4, 6, 4, 4);
    defaultBoard.move(2, 6, 4, 6);
    defaultBoard.move(4, 3, 4, 5);
    defaultBoard.move(4, 6, 4, 4);
    defaultBoard.move(2, 3, 2, 5);
    defaultBoard.move(2, 5, 4, 5);
    defaultBoard.move(4, 5, 4, 3);
    defaultBoard.move(4, 3, 4, 1);
    defaultBoard.move(4, 1, 2, 1);
    defaultBoard.move(2, 1, 2, 3);
    defaultBoard.move(3, 3, 3, 1);

    assertEquals(4, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    assertEquals("    _ _ _\n"
            + "    _ O _\n"
            + "_ _ _ O _ _ _\n"
            + "_ O _ _ O _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _", defaultBoard.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckIsGameOver5Arm() {
    gamePlay.move(5, 8, 2, 8);
    gamePlay.move(10, 4, 7, 4);
    assertEquals("        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O _\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O _ O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O _ O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        _ O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", gamePlay.getGameState());
  }

  @Test
  public void startToAlmostFinishGame() {
    assertEquals(32, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(5, 3, 3, 3);

    assertEquals(31, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(4, 1, 4, 3);

    assertEquals(30, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(6, 2, 4, 2);

    assertEquals(29, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(6, 4, 6, 2);

    assertEquals(28, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(3, 2, 5, 2);

    assertEquals(27, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(6, 2, 4, 2);

    assertEquals(26, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(1, 2, 3, 2);

    assertEquals(25, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(2, 0, 2, 2);

    assertEquals(24, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(4, 0, 2, 0);

    assertEquals(23, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(2, 3, 2, 1);

    assertEquals(22, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(2, 0, 2, 2);

    assertEquals(21, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(2, 5, 2, 3);

    assertEquals(20, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(0, 4, 2, 4);

    assertEquals(19, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(0, 2, 0, 4);

    assertEquals(18, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(3, 4, 1, 4);

    assertEquals(17, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(0, 4, 2, 4);

    assertEquals(16, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(5, 4, 3, 4);

    assertEquals(15, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(4, 6, 4, 4);

    assertEquals(14, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(2, 6, 4, 6);

    assertEquals(13, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(4, 3, 4, 5);

    assertEquals(12, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(4, 6, 4, 4);

    assertEquals(11, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(2, 3, 2, 5);

    assertEquals(10, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(2, 5, 4, 5);

    assertEquals(9, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(4, 5, 4, 3);

    assertEquals(8, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(4, 3, 4, 1);

    assertEquals(7, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(4, 1, 2, 1);

    assertEquals(6, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(2, 1, 2, 3);

    assertEquals(5, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(3, 3, 3, 1);

    assertEquals(4, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(1, 3, 3, 3);

    assertEquals(3, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(3, 4, 3, 2);

    assertEquals(2, defaultBoard.getScore());
    assertEquals(false, defaultBoard.isGameOver());
    defaultBoard.move(3, 1, 3, 3);
    assertEquals("    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ O _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _", defaultBoard.getGameState());
    assertEquals(true, defaultBoard.isGameOver());
    assertEquals(1, defaultBoard.getScore());
  }

}
