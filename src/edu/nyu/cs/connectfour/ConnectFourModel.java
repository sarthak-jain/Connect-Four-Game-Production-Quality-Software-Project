package edu.nyu.cs.connectfour;

import java.util.ArrayList;
import java.util.List;
import edu.nyu.cs.connectfour.ClassForEnum.CellFilledByPlayer;
import edu.nyu.cs.connectfour.ClassForEnum.PlayerType;

public class ConnectFourModel {

  //stores number of rows present on our game board
  private final int row;
  //stores number of columns  present on our game board
  private final int column;
  private CellFilledByPlayer[][] cellNetwork;
  //stores listOfListeners
  private List<ConnectFourListener> listOfListeners;
  private static ConnectFourModel onlyInstance = null;
  private PlayerType winningPlayer;
  private PlayerType turn;

  /**
   * Use of the builder pattern to build a cell network of the number of rows
   * and columns that are fed.
   * 
   * @author sj182
   *
   */
  public static class Builder {
    // default values fed
    private int row = 6;
    private int column = 7;

    public ConnectFourModel retrieveOneObj() {
      onlyInstance = new ConnectFourModel(this);
      return onlyInstance;
    }

    /**
     * @throws IllegalArgumentException
     *           for row value entered as less than 4
     * @param intValue
     *          here is number of rows
     * @return Builder type of object
     */
    public Builder row(int intValue) {
      if (intValue < 4) {
        throw new IllegalArgumentException("Minimum number of rows is 4");
      }
      row = intValue;
      return this;
    }

    /**
     * @throws IllegalArgumentException
     *           for column value entered as less than 4
     * @param intValue
     *          here is number of columns
     * @return Builder object
     */
    public Builder column(int intValue) {
      if (intValue < 4) {
        throw new IllegalArgumentException("Minimum number of columns is 4");
      }
      column = intValue;
      return this;
    }
  }

  private ConnectFourModel(Builder building) {
    listOfListeners = new ArrayList<ConnectFourListener>();
    turn = PlayerType.firstPlayer;
    row = building.row;
    column = building.column;
    cellNetwork = new CellFilledByPlayer[row][column];
    for (int l = 0; l < column; l++) {
      for (int k = 0; k < row; k++) {
        cellNetwork[k][l] = CellFilledByPlayer.none;
      }
    }
  }

  /**
   * Starts a game
   */
  public void runConnectFourGame() {
    reload();
  }

  /**
   * Reloads game and clears up the board for next play.
   */
  private void reload() {
    int k;
    int l;
    turn = PlayerType.firstPlayer;
    for (l = 0; l < column; l++) {
      for (k = 0; k < row; k++) {
        cellNetwork[k][l] = CellFilledByPlayer.none;
      }
    }
    GameInitiated();
  }

  /**
   * Adds a Listener
   * 
   * @throws NullPointerException
   *           If the Listener is Null
   * @throws IllegalArgumentException
   *           If the Listener is already present
   * @param ListenerObject
   */

  public void connectListener(ConnectFourListener ListenerObject) {
    if (ListenerObject == null) {
      throw new NullPointerException("Null pointer exists");
    } else {
      if (!listOfListeners.contains(ListenerObject))
        listOfListeners.add(ListenerObject);
      else
        throw new IllegalArgumentException("Is already present in the list");
    }
  }

  /**
   * Returns the player whose turn it is
   * 
   * @return PlayerType
   */
  public PlayerType checkWhoseTurn() {
    return turn;
  }

  /**
   * @return CellFilledByPlayer[][]
   */
  public CellFilledByPlayer[][] retrieveNetwork() {
    CellFilledByPlayer[][] network1 = new CellFilledByPlayer[row][column];
    int k, l;
    for (k = 0; k < row; k++) {
      for (l = 0; l < column; l++) {
        network1[k][l] = cellNetwork[k][l];
      }
    }
    return network1;
  }

  private void notifyMoveMade() {
    for (ConnectFourListener iterListener : listOfListeners) {
      final ConnectFourModel model = this;
      Runnable runnable = new Runnable() {
        public void run() {
          iterListener.moveMade(model);
        }
      };
      Thread thinkerThread = new Thread(runnable);
      thinkerThread.start();
    }
  }

  /**
   * Inserts a token. check if network is filled. check if a column is filled.
   * check if can Insert. check if winning state on the move.
   * 
   * @param colPosition
   * @param againstComp
   */
  public void insertToken(int colPosition, boolean againstComp) {

    boolean canWin;
    int k;
    boolean colIsFilled = true;
    boolean canPutToken = false;
    boolean networkFull = true;

    networkFull = checkIfNetworkIsFull(networkFull);
    // if network is full display error.
    if (networkFull) {
      return;
    }
    // check if column is full.
    colIsFilled = checkIfColumnIsFull(colIsFilled, colPosition);
    k = 0;

    if (!colIsFilled) {
      k = getRowIndexToInsert(cellNetwork, colPosition, canPutToken);
      canPutToken = true;
      if (!canPutToken) {
        //We cannot Insert token as it is false
        return;
      } else {
        CanWinOnTheMove willThisWin = new CanWinOnTheMove();
        //we can insert a token here
        if (turn.equals(PlayerType.firstPlayer)) {
          cellNetwork[k][colPosition] = CellFilledByPlayer.P1;
          // colorToFill is the one corresponding to Player one
          winningPlayer = turn;
          canWin = willThisWin.isWonAfterAboveMoveOrNot(cellNetwork, turn, winningPlayer, this);
          if (canWin) {
            informAllListenersAboutWin();
            return;
          } else {
            if (againstComp) {
              turn = PlayerType.computer;
            } else {
              turn = PlayerType.secondPlayer;
            }
            notifyMoveMade();
          }
        } else if (turn.equals(PlayerType.secondPlayer)) {
          cellNetwork[k][colPosition] = CellFilledByPlayer.P2;
          winningPlayer = turn;
          canWin = willThisWin.isWonAfterAboveMoveOrNot(cellNetwork, turn, winningPlayer, this);
          if (canWin) {
            informAllListenersAboutWin();
            return;
          } else {
            turn = PlayerType.firstPlayer;
            notifyMoveMade();
          }
        } else if (turn.equals(PlayerType.computer)) {
          cellNetwork[k][colPosition] = CellFilledByPlayer.C;
          winningPlayer = turn;
          canWin = willThisWin.isWonAfterAboveMoveOrNot(cellNetwork, turn, winningPlayer, this);
          if (canWin) {
            informAllListenersAboutWin();
            return;
          } else {
            turn = PlayerType.firstPlayer;
            notifyMoveMade();
          }
        }
      }
    } else {
      //cannot insert token here
      return;
    }
  }

  /**
   * checking if network is filled. If it is not filled move ahead.
   * 
   * @param networkFull
   * @return boolean
   */
  private boolean checkIfNetworkIsFull(boolean networkFull) {
    int colIndex;
    int rowIndex;
    for (colIndex = 0; colIndex < column; colIndex++) {
      for (rowIndex = 0; rowIndex < row; rowIndex++) {
        if (cellNetwork[rowIndex][colIndex] == (CellFilledByPlayer.none)) {
          networkFull = false;
          return networkFull;
        }
      }
    }
    networkFull = true;
    return networkFull;
  }

  /**
   * Returns row index to insert token at.
   * 
   * @param cellNetwork
   * @param colPosition
   * @param canPutToken
   * @return int
   */
  private int getRowIndexToInsert(CellFilledByPlayer[][] cellNetwork, int colPosition,
      boolean canPutToken) {
    int k = 0;
    while (canPutToken != true) {
      if (cellNetwork[k][colPosition] == CellFilledByPlayer.none) {
        if (k == row - 1) {
          canPutToken = true;
        } else {
          if (cellNetwork[k + 1][colPosition] == CellFilledByPlayer.none) {
            canPutToken = false;
          } else {
            canPutToken = true;
          }
        }
      }
      if (!canPutToken) {
        k++;
      } else {
        canPutToken = true;
      }
    }
    return k;
  }

  /**
   * Checks if column is full. If full returns true otherwise returns false.
   * 
   * @param colIsFilled
   * @param colPosition
   * @return boolean
   */
  private boolean checkIfColumnIsFull(boolean colIsFilled, int colPosition) {
    int k;
    for (k = 0; k <= row - 1; k++) {
      if (cellNetwork[k][colPosition] == CellFilledByPlayer.none) {
        colIsFilled = false;
        return colIsFilled;
      }
    }
    colIsFilled = true;
    return colIsFilled;
  }

  private void informAllListenersAboutWin() {
    for (ConnectFourListener iterListener : listOfListeners) {
      iterListener.notifyOnWinState(this);
    }
    return;
  }

  /**
   * Returns the player who is winningPlayer
   * 
   * @return PlayerType
   */
  public PlayerType findWhoWon() {
    return winningPlayer;
  }

  /**
   * Returns number of rows
   * 
   * @return int
   */
  public int getRow() {
    return row;
  }

  /**
   * Returns number of columns
   * 
   * @return int
   */
  public int getColumn() {
    return column;
  }

  public void GameInitiated() {
    for (ConnectFourListener iterListener : listOfListeners) {
      iterListener.connect4Initiated(this);
    }
  }

}