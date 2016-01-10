package edu.nyu.cs.connectfour;

import static org.junit.Assert.*;
import org.junit.Test;

public class ConnectFourHumanTest {

  ConnectFourModel game1 = new ConnectFourModel.Builder().row(6).column(7).retrieveOneObj();

  @Test
  public void testHumanPlayerView() {
    final ConnectFourHumanPlayerView HumanObject1 = new ConnectFourHumanPlayerView(game1);
    final ConnectFourHumanPlayerView HumanObject2 = new ConnectFourHumanPlayerView(game1);
    assertNotEquals(HumanObject1, HumanObject2);
  }

}
