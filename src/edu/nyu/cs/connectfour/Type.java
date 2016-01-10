package edu.nyu.cs.connectfour;

public class Type {

  public Type() {
  }

  /**
   * Object of Type is constructed and the function returns a Human or Computer
   * Object. Wherever this is called with a non 1 as argument, the person will
   * get only one object of computer type because we do not create a new object
   * every time it is called, unlike for a human player. Factory Pattern is
   * implemented below. Uses factory methods to deal with the problem of
   * creating objects without having to specify the exact class of the object
   * that will be created. So, the specific objects are created via the input
   * arguments.
   */
  public Type getHumanOrComputerObject(ConnectFourModel gameInstance, int signal) {
    if (signal == 1) {
      return new ConnectFourHumanPlayerView(gameInstance);
    } else {
      return ConnectFourComputerView.retrieveOneObj(gameInstance);
    }
  }

 

}
