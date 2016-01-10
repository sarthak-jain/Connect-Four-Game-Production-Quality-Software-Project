package edu.nyu.cs.connectfour;

import static org.junit.Assert.*;
import org.junit.Test;

public class ConnectFourSwingUITest {
  ConnectFourModel game1 = new ConnectFourModel.Builder().row(6).column(7).retrieveOneObj();
  ConnectFourSwingUI objUI = new ConnectFourSwingUI(game1);

  @Test(expected = IllegalArgumentException.class)
  public void testCreateGame() {
    new ConnectFourModel.Builder().row(1).column(1).retrieveOneObj();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateTwoGameEquals() {
    ConnectFourModel game1 = new ConnectFourModel.Builder().row(3).column(2).retrieveOneObj();
    ConnectFourModel game2 = new ConnectFourModel.Builder().row(3).column(2).retrieveOneObj();
    assertEquals(game1, game2);
  }

}