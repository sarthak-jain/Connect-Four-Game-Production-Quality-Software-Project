package edu.nyu.cs.connectfour;

public class ConnectFourMainApp {

  private void run() {
    ConnectFourModel gameInstance = new ConnectFourModel.Builder().row(6).column(7)
        .retrieveOneObj();
    new ConnectFourSwingUI(gameInstance);
  }

  /**
   * Main method for our Connect Four game
   * 
   * @param args
   */
  public static void main(String args[]) {
    ConnectFourMainApp objectToInitiate = new ConnectFourMainApp();
    objectToInitiate.run();
  }

}
