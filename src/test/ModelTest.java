import main.ServerSide.Model;
import main.Stone;
import main.color;

import static org.junit.Assert.*;

public class ModelTest {
    private Model testBoard;
    @org.junit.Before
    public void setUP(){
        testBoard =new Model(9);
        testBoard.Board[0][0]=Stone.White;
        testBoard.Board[0][1]=Stone.White;
        testBoard.Board[1][0]=Stone.White;
        testBoard.Board[2][0]=Stone.Black;
    }

    @org.junit.Test
    public void testConstructor() throws Exception{
        assertNotNull(new Model(9));
    }
    @org.junit.Test
    public void testMakeChain() throws Exception{
        Stone[][] chain = testBoard.MakeChain(0, 0, new boolean[9][9], Stone.White, new Stone[9][9]);
        assertEquals(chain[0][0], Stone.White);
        assertEquals(chain[0][1], Stone.White);
        assertEquals(chain[1][0], Stone.White);
    }
    @org.junit.Test
    public void testCheckChainForProperty() throws Exception{
        assertTrue(testBoard.CheckChainForProperty(testBoard.MakeChain(0, 0, new boolean[9][9], Stone.White, new Stone[9][9]), Stone.Empty));
        assertTrue(testBoard.CheckChainForProperty(testBoard.MakeChain(0, 0, new boolean[9][9], Stone.White, new Stone[9][9]), Stone.Black));
    }
    @org.junit.Test
    public void testChangeChainTo() throws Exception{
        testBoard.ChangeChainTo(testBoard.MakeChain(0, 0, new boolean[9][9], Stone.White, new Stone[9][9]), Stone.Black);
        assertEquals(testBoard.Board[0][0], Stone.Black);
        assertEquals(testBoard.Board[0][1], Stone.Black);
        assertEquals(testBoard.Board[1][0], Stone.Black);
    }
    @org.junit.Test
    public void testCountChainElements() throws Exception{
        assertEquals(testBoard.CountChainElements(testBoard.MakeChain(0, 0, new boolean[9][9], Stone.White, new Stone[9][9])), 3);
    }
    @org.junit.Test
    public void testMakeValidMove() throws Exception{
        assertTrue(testBoard.MakeValidMove(2,2, color.Black));
        assertFalse(testBoard.MakeValidMove(2,2, color.Black));

    }
    @org.junit.Test
    public void testSetDeadAndTerritories() throws Exception{
        testBoard.SetDeadAndTerritories(0,0, color.Black);
        assertEquals(testBoard.Board[0][0], Stone.WhiteDead);
        assertEquals(testBoard.Board[0][1], Stone.WhiteDead);
        assertEquals(testBoard.Board[1][0], Stone.WhiteDead);
    }
    @org.junit.Test
    public void testClearBoard(){
        testBoard.SetDeadAndTerritories(0,0, color.Black);
        testBoard.ClearBoard();
        assertEquals(testBoard.Board[0][0], Stone.White);
        assertEquals(testBoard.Board[0][1], Stone.White);
        assertEquals(testBoard.Board[1][0], Stone.White);
    }
    @org.junit.Test
    public void testSetDefault(){
        testBoard.Board[2][0]=Stone.White;
        testBoard.SetDefault();
        assertEquals(testBoard.Board[5][5], Stone.WhiteTerritory);
    }
    @org.junit.Test
    public void testGetResults(){
        testBoard.Board[0][0]=Stone.Black;
        testBoard.Board[0][1]=Stone.Black;
        testBoard.Board[1][0]=Stone.Black;
        testBoard.SetDefault();
        assertEquals(color.Black, testBoard.GetResults());

    }
    @org.junit.Test
    public void testGetBoard(){
        assertEquals(testBoard.Board, testBoard.GetBoard());
    }

}
