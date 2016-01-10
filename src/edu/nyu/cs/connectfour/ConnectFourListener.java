package edu.nyu.cs.connectfour;

/**
 * Interface ConnectFourListener
 * 
 * @author sj182
 *
 */

public interface ConnectFourListener {

  /**
   * For starting ConnectFour Game
   * 
   * @param gameInstance
   */

  public void connect4Initiated(ConnectFourModel gameInstance);

  /**
   * Notifies that token was inserted
   * 
   * @param gameInstance
   */
  public void moveMade(ConnectFourModel gameInstance);

  /**
   * Notifies about winning player
   * 
   * @param gameInstance
   */
  public void notifyOnWinState(ConnectFourModel gameInstance);

}
