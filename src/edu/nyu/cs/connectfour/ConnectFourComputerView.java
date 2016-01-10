package edu.nyu.cs.connectfour;

import java.util.Random;
import edu.nyu.cs.connectfour.ClassForEnum.CellFilledByPlayer;
import edu.nyu.cs.connectfour.ClassForEnum.PlayerType;

public class ConnectFourComputerView extends Type implements ConnectFourListener {

  private int row;
  private int column;
  /**
   * static variable declared because we want to implement a singleton pattern
   * of this class.
   */
  private static ConnectFourComputerView compPlayerInstance;
  private ConnectFourModel gameInstance;
  Random RandomObject = new Random();
  private PlayerType winningPlayer;
  private PlayerType turn;

  /**
   * the singleton pattern is implemented, thus the constructor is private.
   */
  private ConnectFourComputerView(final ConnectFourModel gameInstance) {
    this.gameInstance = gameInstance;
    row = gameInstance.getRow();
    column = gameInstance.getColumn();
    gameInstance.connectListener(this);
  }

  /**
   * Implements Singleton Pattern as it returns the same instance everytime.
   * 
   * @param gameInstance
   * @return ConnectFourComputerView type of Object
   */
  public static ConnectFourComputerView retrieveOneObj(ConnectFourModel gameInstance) {
    if (compPlayerInstance == null)
      compPlayerInstance = new ConnectFourComputerView(gameInstance);
    return compPlayerInstance;
  }

  @Override
  /**
   * Tells that player has made a turn. now if it is the computer's turn and the computer can find a winning move then
   * that position is chosen else the token is put randomly.
   * 
   * @param gameInstance	 
   *  
   */
  public void moveMade(ConnectFourModel gameInstance) {
    turn = gameInstance.checkWhoseTurn();
    if (turn.equals(PlayerType.computer)) {
      int colToInsert;
      int colPosition;
      int k = 0;
      boolean colIsFilled = true;
      boolean canWin;
      CellFilledByPlayer[][] cellNetwork = gameInstance.retrieveNetwork();
      for (colPosition = 0; colPosition < column; colPosition++) {
        int i;
        for (i = row - 1; i >= 0; i--) {
          if (cellNetwork[i][colPosition] == CellFilledByPlayer.none) {
            break;
          }
        }
        if (i < 0) {
          continue;
        }
        cellNetwork[i][colPosition] = CellFilledByPlayer.C;
        CanWinOnTheMove willThisWin = new CanWinOnTheMove();
        canWin = willThisWin.isWonAfterAboveMoveOrNot(cellNetwork, turn, winningPlayer,
            gameInstance);
        if (canWin) {
          colToInsert = colPosition;
          gameInstance.insertToken(colToInsert, true);
          return;
        } else {
          cellNetwork[i][colPosition] = CellFilledByPlayer.none;
        }
      }
      int colRandomPos;
      // if column is filled regenerate random
      while (true) {
        colIsFilled = true;
        colRandomPos = RandomObject.nextInt(column);
        for (k = row - 1; k >= 0; k--) {
          if (cellNetwork[k][colRandomPos] == CellFilledByPlayer.none) {
            colIsFilled = false;
            break;
          }
        }
        if (!colIsFilled) {
          break;
        }
      }
      gameInstance.insertToken(colRandomPos, true);
    }
  }

  @Override
  public void connect4Initiated(ConnectFourModel gameInstance) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void notifyOnWinState(ConnectFourModel gameInstance) {
    // TODO Auto-generated method stub
    
  }

}
