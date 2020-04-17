import org.junit.Test;

import cs5004.marblesolitaire.model.hw09.EuropeanSolitaireModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for European model.
 */
public class EuropeanSolitaireModelImplTest {

  @Test
  public void testCheckDefaultConstructor() {
    EuropeanSolitaireModelImpl defaultModel = new EuropeanSolitaireModelImpl();
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", defaultModel.getGameState());
  }

  @Test
  public void testCheckDefaultConstructorII() {
    EuropeanSolitaireModelImpl defaultModel = new EuropeanSolitaireModelImpl(3,
            0, 0);
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", defaultModel.getGameState());
  }


  @Test
  public void testCheckDefaultConstructorArmThickness() {
    EuropeanSolitaireModelImpl defaultModel = new EuropeanSolitaireModelImpl(5);
    assertEquals("        O O O O O\n"
            + "      O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "      O O O O O O O\n"
            + "        O O O O O", defaultModel.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckDefaultIllegalConstructor() {
    EuropeanSolitaireModelImpl defaultModel = new EuropeanSolitaireModelImpl(-1);
  }

  @Test
  public void testCheckDefaultConstructorRowAndColumn() {
    EuropeanSolitaireModelImpl defaultModel = new EuropeanSolitaireModelImpl(5, 8, 9);
    assertEquals("        O O O O O\n"
            + "      O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O _ O O O\n"
            + "  O O O O O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "      O O O O O O O\n"
            + "        O O O O O", defaultModel.getGameState());
  }

  @Test
  public void testCheckDefaultConstructorJustRowAndColumn() {
    EuropeanSolitaireModelImpl defaultModel = new EuropeanSolitaireModelImpl(1,1);
    assertEquals("    O O O\n"
            + "  _ O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", defaultModel.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckDefaultZeroConstructor() {
    EuropeanSolitaireModelImpl defaultModel = new EuropeanSolitaireModelImpl(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckDefaultEvenConstructor() {
    EuropeanSolitaireModelImpl defaultModel = new EuropeanSolitaireModelImpl(4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckDefaultLessThan3Constructor() {
    EuropeanSolitaireModelImpl defaultModel = new EuropeanSolitaireModelImpl(0, 1, 2);
  }

  @Test
  public void testCheckDefaultConstructorMoveRight() {
    EuropeanSolitaireModelImpl defaultModel = new EuropeanSolitaireModelImpl();
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", defaultModel.getGameState());
    defaultModel.move(3,1,3,3);
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", defaultModel.getGameState());
  }

  @Test
  public void testCheckDefaultConstructorMoveLeft() {
    EuropeanSolitaireModelImpl defaultModel = new EuropeanSolitaireModelImpl();
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", defaultModel.getGameState());
    defaultModel.move(3,5,3,3);
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O _ _ O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", defaultModel.getGameState());
  }

  @Test
  public void testCheckDefaultConstructorMoveUp() {
    EuropeanSolitaireModelImpl model = new EuropeanSolitaireModelImpl();
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", model.getGameState());
    model.move(5,3,3,3);
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "  O O _ O O\n"
            + "    O O O", model.getGameState());
  }

  @Test
  public void testCheckDefaultConstructorMoveDown() {
    EuropeanSolitaireModelImpl model = new EuropeanSolitaireModelImpl();
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", model.getGameState());
    model.move(1,3,3,3);
    assertEquals("    O O O\n"
            + "  O O _ O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", model.getGameState());
  }

  @Test
  public void testCheckDefaultConstructorGameOver() {
    EuropeanSolitaireModelImpl model = new EuropeanSolitaireModelImpl();
    assertEquals(model.isGameOver(), false);
    model.move(3, 1,3,3);
    model.move(3, 4,3,2);
    model.move(3, 6,3,4);
    model.move(1, 1,3,1);
    model.move(1, 3,3,3);
    model.move(1, 5,3,5);
    model.move(4, 3,2,3);
    model.move(6, 3,4,3);
    model.move(4, 1,2,1);
    model.move(4, 5,2,5);
    model.move(5, 1,5,3);
    model.move(5, 4,5,2);
    model.move(4, 3,4,5);
    model.move(4, 6,4,4);
    model.move(3, 4,5,4);
    model.move(6, 4,4,4);
    model.move(1, 4,3,4);
    model.move(4, 4,2,4);
    assertEquals(model.isGameOver(), true);
  }

  @Test
  public void testCheckDefaultConstructorGameScore() {
    EuropeanSolitaireModelImpl model = new EuropeanSolitaireModelImpl();
    assertEquals(model.getScore(), 36);
    model.move(3, 1,3,3);
    assertEquals(model.getScore(), 35);
    model.move(3, 4,3,2);
    assertEquals(model.getScore(), 34);
    model.move(3, 6,3,4);
    assertEquals(model.getScore(), 33);
    model.move(1, 1,3,1);
    assertEquals(model.getScore(), 32);
    model.move(1, 3,3,3);
    assertEquals(model.getScore(), 31);
    model.move(1, 5,3,5);
    assertEquals(model.getScore(), 30);
  }
}
