import org.junit.jupiter.api.Test;

import cs1002.*;

public class Tests {
    @Test
    public void TestInitialBoard() {
        FileTest.run(W09Practical::main, "0_initialBoard");
    }
    @Test
    public void TestVeryBasicMoves() {
        FileTest.run(W09Practical::main, "1_veryBasicMoves");
    }
    @Test
    public void TestLegalMoves() {
        FileTest.run(W09Practical::main, "2_legalMoves");
    }
    @Test
    public void TestIllegalMoves() {
        FileTest.run(W09Practical::main, "3_illegalMoves");
    }
    @Test
    public void TestBoundaryChecks() {
        FileTest.run(W09Practical::main, "4_boundaryChecks");
    }
    @Test
    public void TestIncompleteGame() {
        FileTest.run(W09Practical::main, "5_incompleteGame");
    }
    @Test
    public void TestGeeseWin() {
        FileTest.run(W09Practical::main, "z_geeseWin");
    }
    
}
