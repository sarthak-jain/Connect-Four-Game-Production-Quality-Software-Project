package edu.nyu.cs.connectfour;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.nyu.cs.connectfour.ClassForEnum.CellFilledByPlayer;
import edu.nyu.cs.connectfour.ClassForEnum.PlayerType;

public class ConnectFourModelTest {

  /**
   * test to check if we are getting the correct values of the row and column.
   * Also checking if the object being created is of class ConnectFourModel
   */
  @Test
  public void testRetrieveOneObj() {
    ConnectFourModel obj1;
    obj1 = new ConnectFourModel.Builder().row(6).column(7).retrieveOneObj();
    assertEquals(obj1.getColumn(), 7);
    assertEquals(obj1.getRow(), 6);
    assertEquals(obj1.getClass(), ConnectFourModel.class);
  }

  @Test(expected = NullPointerException.class)
  public void testConnectListener_nullAdded() {
    ConnectFourModel game1 = new ConnectFourModel.Builder().row(6).column(7).retrieveOneObj();
    game1.connectListener(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConnectListener_alreadyPresent() {
    ConnectFourModel game1 = new ConnectFourModel.Builder().row(6).column(7).retrieveOneObj();
    ConnectFourComputerView compObject1;
    compObject1 = ConnectFourComputerView.retrieveOneObj(game1);
    game1.connectListener(compObject1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRowLessThanMinimum() {
    new ConnectFourModel.Builder().row(3).column(4).retrieveOneObj();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColumnLessThanMinimum() {
    new ConnectFourModel.Builder().row(4).column(2).retrieveOneObj();
  }

  @Test
  public void testCheckWhoseTurn() {
    PlayerType newPlayer;
    ConnectFourModel gameInstance;
    gameInstance = new ConnectFourModel.Builder().row(6).column(7).retrieveOneObj();
    newPlayer = gameInstance.checkWhoseTurn();
    assertEquals(PlayerType.firstPlayer, newPlayer);
  }

  /**
   * checking if retrieve network is working properly
   */
  @Test
  public void testRetrieveNetwork() {
    ConnectFourModel obj2;
    obj2 = new ConnectFourModel.Builder().row(6).column(7).retrieveOneObj();
    CellFilledByPlayer[][] network12 = new CellFilledByPlayer[6][7];
    CellFilledByPlayer[][] network13 = new CellFilledByPlayer[6][7];
    network13 = obj2.retrieveNetwork();
    assertSame(network12.length, network13.length);
  }

  /**
   * checking if reset
   */
  @Test
  public void testReload() {
    ConnectFourModel gameInstance2;
    int rowIndex;
    gameInstance2 = new ConnectFourModel.Builder().row(6).column(7).retrieveOneObj();
    CellFilledByPlayer[][] network2;
    network2 = gameInstance2.retrieveNetwork();
    gameInstance2.insertToken(0, false);
    network2 = gameInstance2.retrieveNetwork();
    rowIndex = gameInstance2.getRow();
    assertEquals(CellFilledByPlayer.P1, network2[rowIndex - 1][0]);
    gameInstance2.runConnectFourGame();
    network2 = gameInstance2.retrieveNetwork();
    assertEquals(CellFilledByPlayer.none, network2[rowIndex - 1][0]);
  }

  @Test
  public void testIsWonAfterAboveMoveOrNot_false() {
    ConnectFourModel gameInstance;
    CellFilledByPlayer[][] network1;
    gameInstance = new ConnectFourModel.Builder().row(6).column(7).retrieveOneObj();
    gameInstance.insertToken(0, false);
    network1 = gameInstance.retrieveNetwork();
    CanWinOnTheMove willThisWin = new CanWinOnTheMove();
    assertFalse(willThisWin.isWonAfterAboveMoveOrNot(network1, PlayerType.firstPlayer,
        PlayerType.firstPlayer, gameInstance));
  }

  @Test
  public void testIsWonAfterAboveMoveOrNot_yesForComputer() {
    ConnectFourModel gameInstance;
    CellFilledByPlayer[][] network1;
    gameInstance = new ConnectFourModel.Builder().row(6).column(7).retrieveOneObj();
    gameInstance.insertToken(1, true);
    gameInstance.insertToken(2, true);
    gameInstance.insertToken(1, true);
    gameInstance.insertToken(2, true);
    gameInstance.insertToken(3, true);
    gameInstance.insertToken(2, true);
    gameInstance.insertToken(5, true);
    gameInstance.insertToken(2, true);
    network1 = gameInstance.retrieveNetwork();
    CanWinOnTheMove willThisWin = new CanWinOnTheMove();
    assertTrue(willThisWin.isWonAfterAboveMoveOrNot(network1, PlayerType.computer,
        PlayerType.computer, gameInstance));
  }

  @Test
  public void testFindWhoWon() {
    ConnectFourModel gameInstance;
    PlayerType winner;
    gameInstance = new ConnectFourModel.Builder().row(6).column(7).retrieveOneObj();
    winner = gameInstance.findWhoWon();
    assertEquals(null, winner);
  }

  @Test
  public void testInsertToken() {
    int rowIndex;
    CellFilledByPlayer[][] network1;
    ConnectFourModel gameInstance;
    gameInstance = new ConnectFourModel.Builder().row(6).column(7).retrieveOneObj();
    gameInstance.insertToken(2, false);
    network1 = gameInstance.retrieveNetwork();
    rowIndex = gameInstance.getRow();
    assertEquals(CellFilledByPlayer.P1, network1[rowIndex - 1][2]);
  }

  @Test
  public void testInsertToken_twice() {
    int rowIndex;
    CellFilledByPlayer[][] network1;
    ConnectFourModel gameInstance;
    gameInstance = new ConnectFourModel.Builder().row(6).column(7).retrieveOneObj();
    gameInstance.insertToken(2, false);
    gameInstance.insertToken(3, false);
    network1 = gameInstance.retrieveNetwork();
    rowIndex = gameInstance.getRow();
    assertEquals(CellFilledByPlayer.P1, network1[rowIndex - 1][2]);
    assertEquals(CellFilledByPlayer.P2, network1[rowIndex - 1][3]);
  }

  @Test
  public void testInsertToken_thrice() {
    int rowIndex;
    CellFilledByPlayer[][] network1;
    ConnectFourModel gameInstance;
    gameInstance = new ConnectFourModel.Builder().row(6).column(7).retrieveOneObj();
    network1 = gameInstance.retrieveNetwork();
    network1[5][0] = CellFilledByPlayer.P1;
    network1[5][1] = CellFilledByPlayer.P1;
    network1[5][2] = CellFilledByPlayer.P1;
    gameInstance.insertToken(2, false);
    gameInstance.insertToken(2, false);
    rowIndex = gameInstance.getRow();
    assertEquals(CellFilledByPlayer.P1, network1[rowIndex - 1][2]);
    assertEquals(CellFilledByPlayer.P1, network1[rowIndex - 1][1]);
  }

}