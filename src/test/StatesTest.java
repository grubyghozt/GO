import main.ClientSide.GUI;
import main.States.*;
import main.color;

import static org.junit.Assert.*;
public class StatesTest {
    GUI gui;
    @org.junit.Before
    public void setUp() throws Exception {
        gui = new GUI();
    }
    @org.junit.Test
    public void testAcceptOrRefuseYourOpponentChoiceState(){
        gui.SetState(new AcceptOrRefuseYourOpponentChoiceState());
        assertTrue(gui.AcceptDead.isVisible());
        assertTrue(gui.RefuseDead.isVisible());
    }
    @org.junit.Test
    public void testChooseBoardSizeState(){
        gui.SetState(new ChooseBoardSizeState());
        assertTrue(gui.Choose9x9.isVisible());
        assertTrue(gui.Choose13x13.isVisible());
        assertTrue(gui.Choose19x19.isVisible());
    }
    @org.junit.Test
    public void testChooseDeadStonesAndTerritoriesState(){
        gui.SetState(new ChooseDeadStonesAndTerritoriesState());
        assertTrue(gui.ChooseDead.isVisible());
        assertTrue(gui.ConfirmDead.isVisible());
        assertTrue(gui.Resume.isVisible());
    }
    @org.junit.Test
    public void testChooseOpponentState(){
        gui.SetState(new ChooseOpponentState());
        assertTrue(gui.ChoosePlayer.isVisible());
        assertTrue(gui.ChooseBot.isVisible());
    }
    @org.junit.Test
    public void testMakeOrJoinGameState(){
        gui.SetState(new MakeOrJoinGameState());
        assertTrue(gui.NewGame.isVisible());
        assertTrue(gui.JoinGame.isVisible());
    }
    @org.junit.Test
    public void testNormalGameState(){
        gui.SetState(new NormalGameState());
        assertTrue(gui.Pass.isVisible());
    }
    @org.junit.Test
    public void testOpponentFoundState() {
        gui.SetState(new OpponentFoundState(color.Black, 9));
        assertTrue(gui.YouPlayAs.isVisible());
        assertTrue(gui.GiveUp.isVisible());
        assertTrue(gui.Board.isVisible());
        assertEquals(gui.YouPlayAs.getLabel(), "You play as Black");
        assertEquals(gui.Board.getWidth(), 179);
        assertEquals(gui.Board.getHeight(), 179);
    }
    @org.junit.Test
    public void testResultState(){
        gui.SetState(new ResultState("Victory"));
        assertTrue(gui.AcceptResults.isVisible());
        assertEquals(gui.AcceptResults.getLabel(), "Victory");
        assertFalse(gui.GiveUp.isVisible());
    }
    @org.junit.Test
    public void testWaitingForOpponentState(){
        gui.SetState(new WaitingForOpponentState());
        assertTrue(gui.WaitingForOpponent.isVisible());
        gui.SetState(new NormalGameState());
        assertFalse(gui.WaitingForOpponent.isVisible());
    }
}
