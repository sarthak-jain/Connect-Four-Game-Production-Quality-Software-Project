package edu.nyu.cs.connectfour;

public class ConnectFourHumanPlayerView extends Type implements ConnectFourListener {

  private final ConnectFourModel gameInstance;

  /**
   * Constructor which constructs object of class human player and registers the
   * human player.
   */

  public ConnectFourHumanPlayerView(final ConnectFourModel gameInstance) {
    this.gameInstance = gameInstance;
    gameInstance.connectListener(this);
  }

  @Override
  public void connect4Initiated(ConnectFourModel gameInstance) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void moveMade(ConnectFourModel gameInstance) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void notifyOnWinState(ConnectFourModel gameInstance) {
    // TODO Auto-generated method stub
    
  }

}
