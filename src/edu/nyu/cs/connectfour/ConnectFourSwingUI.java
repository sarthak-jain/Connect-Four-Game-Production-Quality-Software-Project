package edu.nyu.cs.connectfour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import edu.nyu.cs.connectfour.ClassForEnum.CellFilledByPlayer;
import edu.nyu.cs.connectfour.ClassForEnum.PlayerType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConnectFourSwingUI implements ConnectFourListener {

  public boolean againstComp = false;
  private PlayerType turn;
  private PlayerType winningPlayer;
  private int row;
  private int column;
  private Type typeOfObjectis = new Type();
  private Type playerNoOne;
  private Type playerNoTwo;
  private final ConnectFourModel gameInstance;
  CellFilledByPlayer[][] cellNetwork;
  Iterator willIterate;
  JFrame openingFrame;
  JFrame primaryGameFrame;
  JFrame gameResultFrame;
  JFrame openingSinglePlayerInfoFrame;
  JPanel openingSinglePlayerInfoPanel;
  JPanel openingPanel;
  JPanel PanelForCells;
  JPanel buttonsToInsertInCols;
  JPanel cellNetworkPanel;
  JPanel displayingThings;
  JPanel operatingButtons;
  JPanel PanelInResultFrame;
  JLabel mainWindowTitle;
  JLabel winLabelString;
  JLabel labelForTurn;
  JLabel stringWithWinnerName;
  JLabel turnNameDisplay;
  JLabel DisplayMsg;
  JButton reloadButton;
  JButton buttonToExitGame;
  JButton exit;
  JButton cellAsButton;
  JButton finish;
  JButton okSPButton;
  JButton SinglePlayer;
  JButton TwoPlayer;
  List<JButton> insertTokenButtonList;
  List<JButton> cells;
  GridLayout layoutOfCells;
  JTextArea Description;
  JTextArea DescriptionSP;

  public ConnectFourSwingUI(final ConnectFourModel gameInstance) {
    this.gameInstance = gameInstance;
    gameInstance.connectListener(this);
    startGameInitialWindow();
  }

  /**
   * Constructs the starting window panel's User Interface.
   */
  private void startGameInitialWindow() {

    openingFrame = new JFrame("Welcome to the Exciting Game of Connect Four !!");
    openingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    openingPanel = new JPanel();
    openingPanel.setLayout(new BoxLayout(openingPanel, BoxLayout.Y_AXIS));
    // text area for description
    Description = new JTextArea(
        "Mode of Play : You can play with either another player or the computer. ");
    Description.setEditable(false);
    Description.setSize(100, 40);
    SinglePlayer = new JButton("Play with One Player");
    SinglePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
    SinglePlayer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        openingFrame.setVisible(false);
        againstComp = true;
        playerNoOne = typeOfObjectis.getHumanOrComputerObject(gameInstance, 1);
        playerNoTwo = typeOfObjectis.getHumanOrComputerObject(gameInstance, 0);
        loadSecondarySPWindow();
      }
    });
    TwoPlayer = new JButton("Play with Two Players");
    TwoPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
    TwoPlayer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        openingFrame.setVisible(false);
        playerNoOne = typeOfObjectis.getHumanOrComputerObject(gameInstance, 1);
        playerNoTwo = typeOfObjectis.getHumanOrComputerObject(gameInstance, 1);
        loadSecondaryTPWindow();
      }
    });
    exit = new JButton("EXIT");
    exit.setAlignmentX(Component.CENTER_ALIGNMENT);
    exit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    openingPanel.add(Description);
    openingPanel.add(SinglePlayer);
    openingPanel.add(TwoPlayer);
    openingPanel.add(exit);
    openingFrame.getContentPane().add(BorderLayout.CENTER, openingPanel);
    openingFrame.setSize(500, 500);
    openingFrame.pack();
    openingFrame.setVisible(true);
  }

  /**
   * loads secondary window for Single Player option.
   */
  private void loadSecondarySPWindow() {
    openingSinglePlayerInfoFrame = new JFrame("you chose single player");
    openingSinglePlayerInfoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    openingSinglePlayerInfoPanel = new JPanel();
    // text area for description
    DescriptionSP = new JTextArea("Mode of Play :You will play against the computer !! ");
    Description.setEditable(false);
    Description.setSize(100, 40);
    okSPButton = new JButton("OK");
    okSPButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    okSPButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        openingSinglePlayerInfoFrame.setVisible(false);
        displayGameBoardWindow();
        gameInstance.runConnectFourGame();
      }
    });
    openingSinglePlayerInfoPanel.add(DescriptionSP);
    openingSinglePlayerInfoPanel.add(okSPButton);
    openingSinglePlayerInfoFrame.getContentPane().add(BorderLayout.CENTER,
        openingSinglePlayerInfoPanel);
    openingSinglePlayerInfoFrame.setSize(500, 500);
    openingSinglePlayerInfoFrame.pack();
    openingSinglePlayerInfoFrame.setVisible(true);

  }

  /**
   * loads secondary window for Two Player option.
   */

  private void loadSecondaryTPWindow() {
    openingSinglePlayerInfoFrame = new JFrame("you chose TWO player");
    openingSinglePlayerInfoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    openingSinglePlayerInfoPanel = new JPanel();
    // text area for description
    DescriptionSP = new JTextArea("Mode of Play :You will play against a human !! ");
    Description.setEditable(false);
    Description.setSize(100, 40);
    okSPButton = new JButton("OK");
    okSPButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    okSPButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        openingSinglePlayerInfoFrame.setVisible(false);
        displayGameBoardWindow();
        gameInstance.runConnectFourGame();
      }
    });
    openingSinglePlayerInfoPanel.add(DescriptionSP);
    openingSinglePlayerInfoPanel.add(okSPButton);
    openingSinglePlayerInfoFrame.getContentPane().add(BorderLayout.CENTER,
        openingSinglePlayerInfoPanel);
    openingSinglePlayerInfoFrame.setSize(500, 500);
    openingSinglePlayerInfoFrame.pack();
    openingSinglePlayerInfoFrame.setVisible(true);
  }

  /**
   * Constructs the interface for the main game window. A person can click on
   * the buttons below or the grid cells to enter a token.
   */
  private void displayGameBoardWindow() {
    cells = new ArrayList<JButton>();
    int k, l;
    row = gameInstance.getRow();
    column = gameInstance.getColumn();
    String storesPlayerName = null;
    insertTokenButtonList = new ArrayList<JButton>();
    turn = gameInstance.checkWhoseTurn();
    winningPlayer = gameInstance.findWhoWon();
    primaryGameFrame = new JFrame("Game Window : Connect Four");
    mainWindowTitle = new JLabel("You can now start playing Connect Four !!");
    primaryGameFrame.getContentPane().add(BorderLayout.NORTH, mainWindowTitle);
    mainWindowTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    displayingThings = new JPanel();
    displayingThings.setBackground(Color.WHITE);
    labelForTurn = new JLabel("It is now the turn of: ");
    storesPlayerName = turn.name();
    turnNameDisplay = new JLabel(storesPlayerName);
    displayingThings.add(labelForTurn);
    displayingThings.add(turnNameDisplay);
    primaryGameFrame.getContentPane().add(BorderLayout.SOUTH, displayingThings);
    operatingButtons = new JPanel();
    displayingThings.setBackground(Color.WHITE);
    reloadButton = new JButton("RELOAD");
    reloadButton.setEnabled(false);
    reloadButton.setAlignmentY(Component.CENTER_ALIGNMENT);
    reloadButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        gameResultFrame.setVisible(false);
        gameInstance.runConnectFourGame();
        ;
        reloadButton.setEnabled(false);
      }
    });
    buttonToExitGame = new JButton("EXIT GAME");
    buttonToExitGame.setAlignmentY(Component.CENTER_ALIGNMENT);
    buttonToExitGame.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    operatingButtons.add(reloadButton);
    operatingButtons.add(buttonToExitGame);
    primaryGameFrame.getContentPane().add(BorderLayout.EAST, operatingButtons);
    cellNetworkPanel = new JPanel();
    cellNetworkPanel.setLayout(new BoxLayout(cellNetworkPanel, BoxLayout.Y_AXIS));
    layoutOfCells = new GridLayout(6, 7);
    PanelForCells = new JPanel(layoutOfCells);
    /*
     * Here the network of clickable cell buttons is being created. A person can
     * either click on the grid of buttons or the Insert token buttons below a
     * column to insert a token in that column.
     */
    for (k = 0; k < row; k++) {
      for (l = 0; l < column; l++) {
        cellAsButton = new JButton();
        cellAsButton.setBackground(Color.gray);
        cells.add(cellAsButton);
        final int colIsInt = l;
        cellAsButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            gameInstance.insertToken(colIsInt, againstComp);
          }
        });
        PanelForCells.add(cellAsButton);
      }
    }
    buttonsToInsertInCols = new JPanel();
    for (k = 0; k < column; k++) {
      int count = k + 1;
      JButton InsertTokenButton = new JButton("INSERT TOKEN in Col " + (count));
      InsertTokenButton.setName(k + "");
      insertTokenButtonList.add(InsertTokenButton);
      buttonsToInsertInCols.add(InsertTokenButton);
      InsertTokenButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          int colPosition = Integer.parseInt(InsertTokenButton.getName());
          gameInstance.insertToken(colPosition, againstComp);
        }
      });
    }
    cellNetworkPanel.add(PanelForCells);
    cellNetworkPanel.add(buttonsToInsertInCols);
    primaryGameFrame.getContentPane().add(BorderLayout.WEST, cellNetworkPanel);
    primaryGameFrame.setSize(375, 375);
    primaryGameFrame.pack();
    primaryGameFrame.setVisible(true);
  }

  @Override
  public void moveMade(ConnectFourModel gameInstance) {
    turn = gameInstance.checkWhoseTurn();
    refreshBoard(gameInstance);
    refreshTurnStatus(gameInstance);
  }

  @Override
  public void connect4Initiated(ConnectFourModel gameInstance) {
    turn = gameInstance.checkWhoseTurn();
    refreshBoard(gameInstance);
    refreshTurnStatus(gameInstance);
    primaryGameFrame.setVisible(true);
  }

  private void refreshBoard(ConnectFourModel gameInstance) {
    int k, l;
    cellNetwork = gameInstance.retrieveNetwork();
    willIterate = cells.iterator();
    JButton currCellButton;
    for (k = 0; k < row; k++) {
      for (l = 0; l < column; l++) {
        if (willIterate.hasNext()) {
          currCellButton = (JButton) willIterate.next();
          if (cellNetwork[k][l].equals(CellFilledByPlayer.P1)) {
            currCellButton.setBackground(Color.green);
          } else if (cellNetwork[k][l].equals(CellFilledByPlayer.P2)) {
            currCellButton.setBackground(Color.blue);
          } else if (cellNetwork[k][l].equals(CellFilledByPlayer.none)) {
            currCellButton.setBackground(Color.white);
          } else if (cellNetwork[k][l].equals(CellFilledByPlayer.C)) {
            currCellButton.setBackground(Color.orange);
          }
        }
      }
    }
  }

  @Override
  public void notifyOnWinState(ConnectFourModel gameInstance) {
    String winnerDetails = "";
    turn = gameInstance.checkWhoseTurn();
    winningPlayer = gameInstance.findWhoWon();
    refreshBoard(gameInstance);
    gameResultFrame = new JFrame("Connect Four Result");
    reloadButton.setEnabled(true);
    PanelInResultFrame = new JPanel();
    PanelInResultFrame.setLayout(new BoxLayout(PanelInResultFrame, BoxLayout.Y_AXIS));
    winLabelString = new JLabel("The game was won by: ");
    winnerDetails = winningPlayer.name();
    stringWithWinnerName = new JLabel(winnerDetails);
    stringWithWinnerName.setAlignmentX(Component.CENTER_ALIGNMENT);
    stringWithWinnerName.setAlignmentY(Component.CENTER_ALIGNMENT);
    winLabelString.setAlignmentX(Component.CENTER_ALIGNMENT);
    winLabelString.setAlignmentY(Component.CENTER_ALIGNMENT);
    finish = new JButton("Finish");
    finish.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        gameResultFrame.setVisible(false);
      }
    });
    PanelInResultFrame.add(winLabelString);
    PanelInResultFrame.add(stringWithWinnerName);
    PanelInResultFrame.add(finish);
    gameResultFrame.getContentPane().add(BorderLayout.NORTH, PanelInResultFrame);
    gameResultFrame.setSize(375, 375);
    gameResultFrame.setVisible(true);
  }

  private void refreshTurnStatus(ConnectFourModel gameInstance) {
    String turnTextNameIs = "";
    if (turn.equals(PlayerType.firstPlayer)) {
      turnTextNameIs = PlayerType.firstPlayer.name();
    } else if (turn.equals(PlayerType.computer)) {
      turnTextNameIs = PlayerType.computer.name();
    } else {
      turnTextNameIs = PlayerType.secondPlayer.name();
    }
    turnNameDisplay.setText(turnTextNameIs);
  }

}