package edu.nyu.cs.connectfour;

import edu.nyu.cs.connectfour.ClassForEnum.CellFilledByPlayer;
import edu.nyu.cs.connectfour.ClassForEnum.PlayerType;

public class CanWinOnTheMove {

  private int row;
  private int column;
  private PlayerType winningPlayer;
  private PlayerType turn;

  /**
   * Returns true if the player is winning.
   * 
   * @param network1
   * @param turn
   * @param winningPlayer
   * @param gameInstance
   * @return boolean
   */
  public boolean isWonAfterAboveMoveOrNot(CellFilledByPlayer[][] network1, PlayerType turn,
      PlayerType winningPlayer, ConnectFourModel gameInstance) {
    boolean isWinOnNextMove = false;
    this.row = gameInstance.getRow();
    this.column = gameInstance.getColumn();
    this.turn = turn;
    this.winningPlayer = winningPlayer;
    CellFilledByPlayer colorToFill = CellFilledByPlayer.none;

    if (turn.equals(PlayerType.firstPlayer)) {
      colorToFill = CellFilledByPlayer.P1;
    } else if (turn.equals(PlayerType.computer)) {
      colorToFill = CellFilledByPlayer.C;
    } else {
      colorToFill = CellFilledByPlayer.P2;
    }

    isWinOnNextMove = checkRowLogic(isWinOnNextMove, colorToFill, network1);

    if (isWinOnNextMove) {
      return isWinOnNextMove;
    }

    isWinOnNextMove = checkColumnLogic(isWinOnNextMove, colorToFill, network1);

    if (isWinOnNextMove) {
      return isWinOnNextMove;
    }

    isWinOnNextMove = checkLeftToRightDiagonalLogic(isWinOnNextMove, colorToFill, network1);

    if (isWinOnNextMove) {
      return isWinOnNextMove;
    }

    isWinOnNextMove = checkRightToLeftDiagonalLogic(isWinOnNextMove, colorToFill, network1);

    if (isWinOnNextMove) {
      return isWinOnNextMove;
    }

    return false;
  }

  /**
   * 
   * @param isWinOnNextMove
   * @param colorToFill
   * @param network1
   * @return boolean
   */
  private boolean checkRowLogic(boolean isWinOnNextMove, CellFilledByPlayer colorToFill,
      CellFilledByPlayer[][] network1) {
    int stepper = 0;
    int k = 0;
    int l = 0;
    int q = 0;

    // computing for each row
    for (k = 0; k < row; k++) {
      stepper = 0;
      for (l = 0; l <= (column - 4); l++) {
        stepper = 0;
        for (q = l; q < column; q++) {
          if (network1[k][q] == colorToFill) {
            stepper = stepper + 1;
            // Four have been conected
            if (stepper == 4) {
              winningPlayer = turn;
              return true;
            }
          } else {
            stepper = 0;
          }
        }
      }
    }

    return false;
  }

  /**
   * 
   * @param isWinOnNextMove
   * @param colorToFill
   * @param network1
   * @return boolean
   */
  private boolean checkColumnLogic(boolean isWinOnNextMove, CellFilledByPlayer colorToFill,
      CellFilledByPlayer[][] network1) {
    int stepper = 0;
    int k = 0;
    int l = 0;
    int q = 0;

    //computing for each column
    for (k = 0; k < column; k++) {
      stepper = 0;
      for (l = 0; l <= (row - 4); l++) {
        stepper = 0;
        for (q = l; q < row; q++) {
          if (network1[q][k] == colorToFill) {
            stepper = stepper + 1;
            // four have been connected
            if (stepper == 4) {
              winningPlayer = turn;
              return true;
            }
          } else {
            stepper = 0;
          }
        }
      }
    }

    return false;
  }

  /**
   * @param isWinOnNextMove
   * @param colorToFill
   * @param network1
   * @return boolean
   */
  private boolean checkLeftToRightDiagonalLogic(boolean isWinOnNextMove,
      CellFilledByPlayer colorToFill, CellFilledByPlayer[][] network1) {

    int stepper = 0;
    int k = 0;
    int l = 0;

    // checking diagonals for  columns going from l to r
    int s = 0;
    int n = 0;
    for (k = row - 1; k >= 0; k--) {
      for (l = 0; l < column; l++) {
        s = k;
        n = l;
        stepper = 0;
        while (s >= 0 && n < column) {
          if (network1[s][n] != colorToFill) {
            stepper = 0;
          } else {
            if (network1[s][n] == colorToFill) {
              stepper++;
            }
            if (stepper == 4) {
              winningPlayer = turn;
              return true;
            }
          }
          s--;
          n++;
        }
      }
    }

    return false;
  }

  /**
   * @param isWinOnNextMove
   * @param colorToFill
   * @param network1
   * @return boolean
   */
  private boolean checkRightToLeftDiagonalLogic(boolean isWinOnNextMove,
      CellFilledByPlayer colorToFill, CellFilledByPlayer[][] network1) {

    //   checking diagonals for columns going from r to l 
    int stepper = 0;
    int k = 0;
    int l = 0;
    int s = 0;
    int n = 0;

    for (k = row - 1; k >= 0; k--) {
      for (l = column - 1; l >= 0; l--) {
        s = k;
        n = l;
        stepper = 0;
        while (s >= 0 && n >= 0) {
          if (network1[s][n] != colorToFill) {
            stepper = 0;
          } else {
            if (network1[s][n] == colorToFill) {
              stepper++;
            }
            if (stepper == 4) {
              winningPlayer = turn;
              return true;
            }
          }
          s--;
          n--;
        }
      }
    }

    return false;
  }
}
