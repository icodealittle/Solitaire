import org.junit.Test;

import cs5004.marblesolitaire.model.hw09.TriangleSolitaireModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Represents the tester class for the Triangle Solitaire Model.
 */
public class TriangleSolitaireModelImplTest {


  /**
   * Tests the default constructor for the triangle board.
   */
  @Test
  public void testConstructor1() {
    TriangleSolitaireModelImpl defaultModel = new TriangleSolitaireModelImpl();
    assertEquals("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O", defaultModel.getGameState());
  }

  /**
   * Tests the default constructor for the triangle board.
   */
  @Test
  public void testConstructor2() {
    TriangleSolitaireModelImpl model = new TriangleSolitaireModelImpl(5);
    assertEquals("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O", model.getGameState());
  }

  /**
   * Tests the default constructor for the triangle board.
   */
  @Test
  public void testConstructor3() {
    TriangleSolitaireModelImpl model = new TriangleSolitaireModelImpl(5, 0, 0);
    assertEquals("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O", model.getGameState());
  }

  @Test
  public void testConstructor4() {
    TriangleSolitaireModelImpl model = new TriangleSolitaireModelImpl(4);
    assertEquals("   _\n"
            + "  O O\n"
            + " O O O\n"
            + "O O O O", model.getGameState());
  }

  @Test
  public void testConstructor5() {
    TriangleSolitaireModelImpl model = new TriangleSolitaireModelImpl(1);
    assertEquals("_", model.getGameState());
  }

  @Test
  public void testConstructor6() {
    TriangleSolitaireModelImpl model = new TriangleSolitaireModelImpl(2);
    assertEquals(" _\n"
            + "O O", model.getGameState());
  }
}
