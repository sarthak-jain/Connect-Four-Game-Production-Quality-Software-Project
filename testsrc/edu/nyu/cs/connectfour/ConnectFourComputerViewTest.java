package edu.nyu.cs.connectfour;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.nyu.cs.connectfour.ClassForEnum.CellFilledByPlayer;

public class ConnectFourComputerViewTest {

  ConnectFourModel game1 = new ConnectFourModel.Builder().row(6).column(7).retrieveOneObj();

  /**
   * Testing the singleton pattern method. the test method should compare the
   * two objects received of type Computer and asssert that they are the same.
   */
  @Test
  public void testRetrieveOneObj() {
    ConnectFourComputerView compObject1 = null;
    ConnectFourComputerView compObject2 = null;
    compObject1 = ConnectFourComputerView.retrieveOneObj(game1);
    compObject2 = ConnectFourComputerView.retrieveOneObj(game1);
    assertEquals(compObject1, compObject2);
  }

  @Test
  public void testMoveMade() {
    ConnectFourComputerView compObject1 = null;
    compObject1 = ConnectFourComputerView.retrieveOneObj(game1);
    game1.insertToken(2, true);
    CellFilledByPlayer[][] network1;
    network1 = game1.retrieveNetwork();
    assertEquals(CellFilledByPlayer.P1, network1[5][2]);
  }

  @Test
  public void testMoveMade_columnIsFull() {
    ConnectFourComputerView compObject1 = null;
    compObject1 = ConnectFourComputerView.retrieveOneObj(game1);
    game1.insertToken(2, true);
    game1.insertToken(2, true);
    game1.insertToken(2, true);
    game1.insertToken(2, true);
    game1.insertToken(2, true);
    game1.insertToken(2, true);
    game1.insertToken(2, true);
    CellFilledByPlayer[][] network1;
    network1 = game1.retrieveNetwork();
    assertEquals(CellFilledByPlayer.P1, network1[5][2]);
  }

}
