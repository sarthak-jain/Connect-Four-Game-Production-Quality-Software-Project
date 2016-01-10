package edu.nyu.cs.connectfour;

import static org.junit.Assert.*;
import org.junit.Test;

public class ConnectFourTypeTest {

  ConnectFourModel game1 = new ConnectFourModel.Builder().row(6).column(7).retrieveOneObj();

  /**
   * Checking if we are able to get an object of type human
   */
  @Test
  public void testGetHumanObject() {
    Type objectofTypeType = new Type();
    assertTrue(objectofTypeType.getHumanOrComputerObject(game1, 1) instanceof ConnectFourHumanPlayerView);
  }

  /**
   * Checking if we are able to get an object of type computer
   */
  @Test
  public void testGetComputerObject() {
    Type objectofTypeType = new Type();
    assertTrue(objectofTypeType.getHumanOrComputerObject(game1, 0) instanceof ConnectFourComputerView);
  }
}
