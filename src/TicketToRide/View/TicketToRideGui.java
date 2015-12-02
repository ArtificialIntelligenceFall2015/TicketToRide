/**
 * TicketToRideGui.java
 * This class represent the main gui of the game. It contains the Swing API code
 * which has been autogenerated to display Swing objects in a JFrame for
 * interacting with the Model classes via the ActionListeners and Control classes.
 */
package TicketToRide.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import TicketToRide.Control.Game;
import TicketToRide.Control.PathHandler;
import TicketToRide.Control.PlayerHandler;
import TicketToRide.Model.Constants;
import TicketToRide.Model.Constants.playerColor;
import TicketToRide.Model.Constants.strategies;
import TicketToRide.Model.Constants.trainCard;
import TicketToRide.Model.Deck;
import TicketToRide.Model.DestinationCard;
import TicketToRide.Model.Path;
import TicketToRide.Model.Player;
import TicketToRide.Model.PlayerAI;
import TicketToRide.Model.TrainCard;

/**
 * @author Sean Fast
 *
 */
public class TicketToRideGui extends JFrame {

	private JPanel contentPane;
	private static JTextArea jtaScores;
	private static JTextArea jtaLog;
	private DefaultListModel destList;
	private JLabel lblFaceUpTrainCard4;
	private JLabel lblFaceUpTrainCard3;
	private JLabel lblFaceUpTrainCard2;
	private JLabel lblFaceUpTrainCard1;
	private JLabel lblFaceUpTrainCard0;
	private JLabel[] lblFaceUpTrainCard = new JLabel[5];
	private JButton btnPickTrainCards;
	private JButton btnClaimARoute;
	private JButton btnPickDestCards;
	private JButton btnTrainCardDeck;
	private static JProgressBar pbrTrainCardDeck;
	private JLabel lblCurrentPlayerTrainCardPink;
	private JLabel lblCurrentPlayerTrainCardWhite;
	private JLabel lblCurrentPlayerTrainCardBlue;
	private JLabel lblCurrentPlayerTrainCardYellow;
	private JLabel lblCurrentPlayerTrainCardRed;
	private JLabel lblCurrentPlayerTrainCardBlack;
	private JLabel lblCurrentPlayerTrainCardOrange;
	private JLabel lblCurrentPlayerTrainCardGreen;
	private JLabel lblCurrentPlayerTrainCardRainbow;
	private JLabel lblPlaceHolderAvatarLabel;
	private JPanel pnlGraph;
	private GraphView gv;
	private List<Player> p;
	private int occurrences = 0;
	private int trainCardCount = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicketToRideGui frame = new TicketToRideGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TicketToRideGui() {
		setTitle("Ticket To Ride");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// initialize players
		p = new ArrayList<Player>();
		// 1 human, 4 ai
		p.add(new Player(playerColor.BLACK));
		p.add(new PlayerAI(playerColor.BLUE, strategies.PP, strategies.RR,
				strategies.RC, strategies.CR));
		p.add(new PlayerAI(playerColor.GREEN, strategies.CR, strategies.RC,
				strategies.PL, strategies.PP));
		p.add(new PlayerAI(playerColor.RED, strategies.PM, strategies.CR,
				strategies.RR, strategies.RC));
		p.add(new PlayerAI(playerColor.YELLOW, strategies.PL, strategies.PP,
				strategies.CR, strategies.RR));

		// 5 human, debug only
		// p.add(new Player(playerColor.BLACK));
		// p.add(new Player(playerColor.BLUE));
		// p.add(new Player(playerColor.GREEN));
		// p.add(new Player(playerColor.RED));
		// p.add(new Player(playerColor.YELLOW));

		Deck.drawStartingHand(p);
		Deck.drawFreshFaceUpTrainCards();
		new Game(p, this);

		// ADD SWING GUI ELEMENTS TO JFRAME (AUTOGENERATED)
		JSplitPane spBoard = new JSplitPane();
		contentPane.add(spBoard, BorderLayout.CENTER);

		JPanel pnlBoard = new JPanel();
		spBoard.setRightComponent(pnlBoard);
		pnlBoard.setLayout(new BorderLayout(0, 0));

		final JPanel pnlDecks = new JPanel();
		spBoard.setLeftComponent(pnlDecks);
		pnlDecks.setLayout(new GridLayout(8, 1, 0, 0));

		JPanel pnlScoreLogPanel = new JPanel();
		pnlBoard.add(pnlScoreLogPanel, BorderLayout.NORTH);
		pnlScoreLogPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JScrollPane scpScores = new JScrollPane();
		pnlScoreLogPanel.add(scpScores);

		jtaScores = new JTextArea();
		jtaScores.setRows(7);
		jtaScores.setEditable(false);
		jtaScores.setFont(new Font("Monospaced", Font.PLAIN, 13));
		scpScores.setViewportView(jtaScores);

		JScrollPane scpLog = new JScrollPane();
		pnlScoreLogPanel.add(scpLog);

		jtaLog = new JTextArea();
		jtaLog.setLineWrap(true);
		jtaLog.setRows(7);
		jtaLog.setEditable(false);
		jtaLog.setFont(new Font("Monospaced", Font.PLAIN, 13));
		scpLog.setViewportView(jtaLog);

		pnlGraph = new JPanel();
		pnlGraph.setToolTipText("");
		pnlGraph.setBackground(Color.CYAN);
		pnlBoard.add(pnlGraph, BorderLayout.CENTER);
		pnlGraph.setLayout(new BorderLayout(0, 0));

		JPanel pnlCurrentPlayer = new JPanel();
		pnlBoard.add(pnlCurrentPlayer, BorderLayout.SOUTH);
		pnlCurrentPlayer.setLayout(new BorderLayout(0, 0));

		JPanel pnlCurrentPlayerDestCards = new JPanel();
		pnlCurrentPlayer.add(pnlCurrentPlayerDestCards, BorderLayout.WEST);
		pnlCurrentPlayerDestCards.setLayout(new BorderLayout(0, 0));

		JScrollPane scpCurrentPlayerDestCards = new JScrollPane();
		pnlCurrentPlayerDestCards.add(scpCurrentPlayerDestCards,
				BorderLayout.CENTER);

		JList jlstCurrentPlayerDestCards = new JList();
		jlstCurrentPlayerDestCards.setFont(new Font("Courier New", Font.PLAIN,
				11));
		jlstCurrentPlayerDestCards.setVisibleRowCount(3);
		jlstCurrentPlayerDestCards
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		destList = new DefaultListModel(); // SF
		jlstCurrentPlayerDestCards.setModel(destList);
		scpCurrentPlayerDestCards.setViewportView(jlstCurrentPlayerDestCards);

		JPanel pnlCurrentPlayerTrainCards = new JPanel();
		pnlCurrentPlayer.add(pnlCurrentPlayerTrainCards, BorderLayout.CENTER);
		pnlCurrentPlayerTrainCards.setLayout(new GridLayout(1, 12, 0, 0));

		// Current Player's Hand of Train Cards
		occurrences = occurrenceOfTrainCardColor(
				Game.currentPlayer.getTrainCards(), trainCard.PINK);
		lblCurrentPlayerTrainCardPink = new JLabel(
				Integer.toString(occurrences));
		lblCurrentPlayerTrainCardPink
				.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentPlayerTrainCardPink
				.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPlayerTrainCardPink.setOpaque(true);
		lblCurrentPlayerTrainCardPink.setBackground(Color.PINK);
		pnlCurrentPlayerTrainCards.add(lblCurrentPlayerTrainCardPink);

		occurrences = occurrenceOfTrainCardColor(
				Game.currentPlayer.getTrainCards(), trainCard.WHITE);
		lblCurrentPlayerTrainCardWhite = new JLabel(
				Integer.toString(occurrences));
		lblCurrentPlayerTrainCardWhite
				.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentPlayerTrainCardWhite
				.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPlayerTrainCardWhite.setBackground(Color.WHITE);
		lblCurrentPlayerTrainCardWhite.setOpaque(true);
		pnlCurrentPlayerTrainCards.add(lblCurrentPlayerTrainCardWhite);

		occurrences = occurrenceOfTrainCardColor(
				Game.currentPlayer.getTrainCards(), trainCard.BLUE);
		lblCurrentPlayerTrainCardBlue = new JLabel(
				Integer.toString(occurrences));
		lblCurrentPlayerTrainCardBlue
				.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentPlayerTrainCardBlue
				.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPlayerTrainCardBlue.setForeground(Color.WHITE);
		lblCurrentPlayerTrainCardBlue.setBackground(Color.BLUE);
		lblCurrentPlayerTrainCardBlue.setOpaque(true);
		pnlCurrentPlayerTrainCards.add(lblCurrentPlayerTrainCardBlue);

		occurrences = occurrenceOfTrainCardColor(
				Game.currentPlayer.getTrainCards(), trainCard.YELLOW);
		lblCurrentPlayerTrainCardYellow = new JLabel(
				Integer.toString(occurrences));
		lblCurrentPlayerTrainCardYellow.setFont(new Font("Tahoma", Font.BOLD,
				12));
		lblCurrentPlayerTrainCardYellow
				.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPlayerTrainCardYellow.setBackground(Color.YELLOW);
		lblCurrentPlayerTrainCardYellow.setOpaque(true);
		pnlCurrentPlayerTrainCards.add(lblCurrentPlayerTrainCardYellow);

		occurrences = occurrenceOfTrainCardColor(
				Game.currentPlayer.getTrainCards(), trainCard.RED);
		lblCurrentPlayerTrainCardRed = new JLabel(Integer.toString(occurrences));
		lblCurrentPlayerTrainCardRed.setForeground(Color.WHITE);
		lblCurrentPlayerTrainCardRed.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentPlayerTrainCardRed
				.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPlayerTrainCardRed.setBackground(Color.RED);
		lblCurrentPlayerTrainCardRed.setOpaque(true);
		pnlCurrentPlayerTrainCards.add(lblCurrentPlayerTrainCardRed);

		occurrences = occurrenceOfTrainCardColor(
				Game.currentPlayer.getTrainCards(), trainCard.BLACK);
		lblCurrentPlayerTrainCardBlack = new JLabel(
				Integer.toString(occurrences));
		lblCurrentPlayerTrainCardBlack
				.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentPlayerTrainCardBlack
				.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPlayerTrainCardBlack.setForeground(Color.WHITE);
		lblCurrentPlayerTrainCardBlack.setBackground(Color.BLACK);
		lblCurrentPlayerTrainCardBlack.setOpaque(true);
		pnlCurrentPlayerTrainCards.add(lblCurrentPlayerTrainCardBlack);

		occurrences = occurrenceOfTrainCardColor(
				Game.currentPlayer.getTrainCards(), trainCard.ORANGE);
		lblCurrentPlayerTrainCardOrange = new JLabel(
				Integer.toString(occurrences));
		lblCurrentPlayerTrainCardOrange.setFont(new Font("Tahoma", Font.BOLD,
				12));
		lblCurrentPlayerTrainCardOrange
				.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPlayerTrainCardOrange.setForeground(Color.BLACK);
		lblCurrentPlayerTrainCardOrange.setBackground(Color.ORANGE);
		lblCurrentPlayerTrainCardOrange.setOpaque(true);
		pnlCurrentPlayerTrainCards.add(lblCurrentPlayerTrainCardOrange);

		occurrences = occurrenceOfTrainCardColor(
				Game.currentPlayer.getTrainCards(), trainCard.GREEN);
		lblCurrentPlayerTrainCardGreen = new JLabel(
				Integer.toString(occurrences));
		lblCurrentPlayerTrainCardGreen
				.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentPlayerTrainCardGreen
				.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPlayerTrainCardGreen.setBackground(Color.GREEN);
		lblCurrentPlayerTrainCardGreen.setOpaque(true);
		pnlCurrentPlayerTrainCards.add(lblCurrentPlayerTrainCardGreen);

		occurrences = occurrenceOfTrainCardColor(
				Game.currentPlayer.getTrainCards(), trainCard.RAINBOW);
		lblCurrentPlayerTrainCardRainbow = new JLabel(
				Integer.toString(occurrences));
		lblCurrentPlayerTrainCardRainbow
				.setHorizontalTextPosition(JButton.CENTER);
		lblCurrentPlayerTrainCardRainbow
				.setVerticalTextPosition(JButton.CENTER);
		lblCurrentPlayerTrainCardRainbow.setOpaque(true);
		lblCurrentPlayerTrainCardRainbow.setIcon(new ImageIcon("rainbow2.jpg"));
		lblCurrentPlayerTrainCardRainbow.setFont(new Font("Tahoma", Font.BOLD,
				12));
		lblCurrentPlayerTrainCardRainbow
				.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPlayerTrainCardRainbow.setForeground(Color.BLACK);
		pnlCurrentPlayerTrainCards.add(lblCurrentPlayerTrainCardRainbow);

		JPanel pnlCurrentPlayerTurnChoices = new JPanel();
		pnlDecks.add(pnlCurrentPlayerTurnChoices);

		pnlCurrentPlayerTurnChoices.setLayout(new GridLayout(3, 1, 0, 0));

		btnPickTrainCards = new JButton("Pick Train Cards");
		btnPickTrainCards.setEnabled(false);
		pnlCurrentPlayerTurnChoices.add(btnPickTrainCards);

		btnClaimARoute = new JButton("Claim a Route");
		btnClaimARoute.setEnabled(false);
		pnlCurrentPlayerTurnChoices.add(btnClaimARoute);

		btnPickDestCards = new JButton("Pick Destination Cards");
		btnPickDestCards.setEnabled(false);
		pnlCurrentPlayerTurnChoices.add(btnPickDestCards);

		// Face Up Train Cards
		lblFaceUpTrainCard4 = new JLabel();
		lblFaceUpTrainCard4.setBorder(new BevelBorder(BevelBorder.RAISED, null,
				null, null, null));
		setFaceUpTrainCardColor(lblFaceUpTrainCard4, 4);
		lblFaceUpTrainCard4.setOpaque(true);
		pnlDecks.add(lblFaceUpTrainCard4);

		lblFaceUpTrainCard3 = new JLabel();
		lblFaceUpTrainCard3.setBorder(new BevelBorder(BevelBorder.RAISED, null,
				null, null, null));
		setFaceUpTrainCardColor(lblFaceUpTrainCard3, 3);
		lblFaceUpTrainCard3.setOpaque(true);
		pnlDecks.add(lblFaceUpTrainCard3);

		lblFaceUpTrainCard2 = new JLabel();
		lblFaceUpTrainCard2.setBorder(new BevelBorder(BevelBorder.RAISED, null,
				null, null, null));
		setFaceUpTrainCardColor(lblFaceUpTrainCard2, 2);
		lblFaceUpTrainCard2.setOpaque(true);
		pnlDecks.add(lblFaceUpTrainCard2);

		lblFaceUpTrainCard1 = new JLabel();
		lblFaceUpTrainCard1.setBorder(new BevelBorder(BevelBorder.RAISED, null,
				null, null, null));
		setFaceUpTrainCardColor(lblFaceUpTrainCard1, 1);
		lblFaceUpTrainCard1.setOpaque(true);
		pnlDecks.add(lblFaceUpTrainCard1);

		lblFaceUpTrainCard0 = new JLabel();
		lblFaceUpTrainCard0.setBorder(new BevelBorder(BevelBorder.RAISED, null,
				null, null, null));
		setFaceUpTrainCardColor(lblFaceUpTrainCard0, 0);
		lblFaceUpTrainCard0.setOpaque(true);
		pnlDecks.add(lblFaceUpTrainCard0);

		lblFaceUpTrainCard[0] = lblFaceUpTrainCard0;
		lblFaceUpTrainCard[1] = lblFaceUpTrainCard1;
		lblFaceUpTrainCard[2] = lblFaceUpTrainCard2;
		lblFaceUpTrainCard[3] = lblFaceUpTrainCard3;
		lblFaceUpTrainCard[4] = lblFaceUpTrainCard4;

		JPanel pnlTrainCardDeck = new JPanel();
		pnlDecks.add(pnlTrainCardDeck);
		pnlTrainCardDeck.setLayout(new BorderLayout(0, 0));

		pbrTrainCardDeck = new JProgressBar();
		pbrTrainCardDeck.setOrientation(SwingConstants.VERTICAL);
		pbrTrainCardDeck
				.setValue((int) ((Deck.trainCardsDeck.size() / 110.0) * 100));
		pnlTrainCardDeck.add(pbrTrainCardDeck, BorderLayout.EAST);

		btnTrainCardDeck = new JButton("Train Card Deck");
		pnlTrainCardDeck.add(btnTrainCardDeck, BorderLayout.CENTER);

		disableTrainCardChoices();
		updateScoreboard();
		refreshIfTripleRainbow();
		enableTurnChoiceButtons();

		JPanel pnlCurrentPlayerAvatar = new JPanel();
		pnlDecks.add(pnlCurrentPlayerAvatar);
		pnlCurrentPlayerAvatar.setLayout(new BorderLayout(0, 0));

		lblPlaceHolderAvatarLabel = new JLabel(
				"[Current Player Image Placeholder]");
		lblPlaceHolderAvatarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pnlCurrentPlayerAvatar.add(lblPlaceHolderAvatarLabel,
				BorderLayout.CENTER);
		lblPlaceHolderAvatarLabel.setText(Game.currentPlayer.getColor()
				.toString());

		// ActionListeners for Button Clicking Actions
		lblFaceUpTrainCard4.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				if (lblFaceUpTrainCard4.isEnabled())
					clickFaceUpTrainCard(lblFaceUpTrainCard4, 4);
			}
		});

		lblFaceUpTrainCard3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (lblFaceUpTrainCard3.isEnabled())
					clickFaceUpTrainCard(lblFaceUpTrainCard3, 3);
			}
		});

		lblFaceUpTrainCard2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (lblFaceUpTrainCard2.isEnabled())
					clickFaceUpTrainCard(lblFaceUpTrainCard2, 2);
			}
		});

		lblFaceUpTrainCard1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (lblFaceUpTrainCard1.isEnabled())
					clickFaceUpTrainCard(lblFaceUpTrainCard1, 1);
			}
		});

		lblFaceUpTrainCard0.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (lblFaceUpTrainCard0.isEnabled())
					clickFaceUpTrainCard(lblFaceUpTrainCard0, 0);
			}
		});

		btnTrainCardDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickFaceDownTrainCard();
			}
		});

		btnPickTrainCards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pickTrainCards();
			}
		});

		btnClaimARoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (playerHasNoTrainCards())
					JOptionPane.showMessageDialog(null,
							"You need train cards first!", "Error",
							JOptionPane.ERROR_MESSAGE);
				else
					claimARoute();
			}
		});

		btnPickDestCards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pickDestinationCards();
			}
		});

		// display dynamically generated graph
		gv = new GraphView();
		pnlGraph.add(gv);

		// All AI Players
		// while(!Game.gameover)
		// switchToNextPlayer();
	}

	/**
	 * Check if the five face up cards contain three or more rainbow cards
	 * (disallowed)
	 */
	private boolean checkForTripleRainbow() {
		boolean hasTripleRainbow = false;
		// see if 5 face up cards contain 3 rainbow cards
		int occurrences = Collections.frequency(Deck.trainFaceUpCards,
				new TrainCard(trainCard.RAINBOW));
		if (occurrences > 2)
			hasTripleRainbow = true;

		return hasTripleRainbow;
	}

	/**
	 * Count occurrence of a specific train card color in player's hand
	 */
	private int occurrenceOfTrainCardColor(List<TrainCard> playerHand,
			trainCard tc) {
		int occurrences = Collections.frequency(playerHand, new TrainCard(tc));
		return occurrences;
	}

	/**
	 * Redraw the face up train cards from arraylist
	 */
	public void repaintFaceUpTrainCards() {
		for (int i = 0; i < lblFaceUpTrainCard.length; i++) {
			JLabel label = lblFaceUpTrainCard[i];
			if (i < Deck.trainFaceUpCards.size()) {
				setFaceUpTrainCardColor(label, i);
			} else {
				label.setIcon(null);
				label.setBackground(Color.LIGHT_GRAY);
			}
		}
	}

	/**
	 * Draw a single face up train card given the index of the gui element
	 */
	private void setFaceUpTrainCardColor(JLabel faceUpTrainCard, int index) {
		trainCard tc = Deck.trainFaceUpCards.get(index).getColor();
		if (tc == trainCard.RAINBOW) {
			faceUpTrainCard.setIcon(new ImageIcon("rainbow.jpg"));
		} else {
			faceUpTrainCard.setIcon(null);
		}
		faceUpTrainCard.setBackground(tc.getRealColor());
	}

	/**
	 * Update the gui display of current player's hand of train cards
	 */
	public void retallyPlayerTrainCardHand() {
		occurrences = occurrenceOfTrainCardColor(
				Game.currentPlayer.getTrainCards(), trainCard.PINK);
		lblCurrentPlayerTrainCardPink.setText(Integer.toString(occurrences));
		occurrences = occurrenceOfTrainCardColor(
				Game.currentPlayer.getTrainCards(), trainCard.WHITE);
		lblCurrentPlayerTrainCardWhite.setText(Integer.toString(occurrences));
		occurrences = occurrenceOfTrainCardColor(
				Game.currentPlayer.getTrainCards(), trainCard.BLUE);
		lblCurrentPlayerTrainCardBlue.setText(Integer.toString(occurrences));
		occurrences = occurrenceOfTrainCardColor(
				Game.currentPlayer.getTrainCards(), trainCard.YELLOW);
		lblCurrentPlayerTrainCardYellow.setText(Integer.toString(occurrences));
		occurrences = occurrenceOfTrainCardColor(
				Game.currentPlayer.getTrainCards(), trainCard.RED);
		lblCurrentPlayerTrainCardRed.setText(Integer.toString(occurrences));
		occurrences = occurrenceOfTrainCardColor(
				Game.currentPlayer.getTrainCards(), trainCard.BLACK);
		lblCurrentPlayerTrainCardBlack.setText(Integer.toString(occurrences));
		occurrences = occurrenceOfTrainCardColor(
				Game.currentPlayer.getTrainCards(), trainCard.ORANGE);
		lblCurrentPlayerTrainCardOrange.setText(Integer.toString(occurrences));
		occurrences = occurrenceOfTrainCardColor(
				Game.currentPlayer.getTrainCards(), trainCard.GREEN);
		lblCurrentPlayerTrainCardGreen.setText(Integer.toString(occurrences));
		occurrences = occurrenceOfTrainCardColor(
				Game.currentPlayer.getTrainCards(), trainCard.RAINBOW);
		lblCurrentPlayerTrainCardRainbow.setText(Integer.toString(occurrences));
	}

	/**
	 * human player selects a face up train card to obtain
	 */
	private void clickFaceUpTrainCard(JLabel jp, int index) {
		if (getTrainCardCount() == 0) {
			TrainCard trainCardSelected = deliverFaceUpTrainCard(jp, index);
			// if first pick is face up and rainbow, players turn is done
			if (trainCardSelected.getColor().equals(trainCard.RAINBOW)) {
				JOptionPane
						.showMessageDialog(
								contentPane,
								"By choosing a rainbow card, you have forfeited your second selection.",
								"Rainbow pick", JOptionPane.INFORMATION_MESSAGE);
				endPickTrainCardsTurn();
			}
		} else {
			// if second pick is face up, player cannot choose rainbow card
			if (jp.getBackground().equals(trainCard.RAINBOW.getRealColor())) {
				JOptionPane
						.showMessageDialog(
								contentPane,
								"Sorry, you can only pick a rainbow card as your first selection.",
								"Selection error", JOptionPane.ERROR_MESSAGE);
			} else {
				deliverFaceUpTrainCard(jp, index);
				endPickTrainCardsTurn();
			}
		}
	}

	/**
	 * deliver card that corresponds to color clicked by player
	 */
	private TrainCard deliverFaceUpTrainCard(JLabel jp, int index) {
		// give the player the face up train card
		TrainCard trainCardSelected = PlayerHandler.drawTrainCard(
				Game.currentPlayer, index);
		setTrainCardCount(getTrainCardCount() + 1);
		updateTrainCardDeckProgressBar();
		enableTrainCardChoices();
		retallyPlayerTrainCardHand();
		refreshIfTripleRainbow();
		return trainCardSelected;
	}

	/**
	 * refresh the face up cards with 5 new ones if triple rainbow
	 */
	public void refreshIfTripleRainbow() {
		while (checkForTripleRainbow()) {
			appendLog("Triple Face Up Rainbow card detected! Dealing five new face up cards.");
			TicketToRideGui.appendLogInfo("TRIPLE RAINBOW DETECTED!");
			Deck.discardAllFaceUpTrainCards(Deck.trainFaceUpCards);
			Deck.drawFreshFaceUpTrainCards();
			updateTrainCardDeckProgressBar();
			repaintFaceUpTrainCards();
		}
	}

	/**
	 * human player selects train card from face down deck button
	 */
	private void clickFaceDownTrainCard() {
		// give player face down train card
		TrainCard faceDownCard = PlayerHandler
				.drawTrainCard(Game.currentPlayer);
		setTrainCardCount(getTrainCardCount() + 1);
		updateTrainCardDeckProgressBar();
		retallyPlayerTrainCardHand();
		JOptionPane.showMessageDialog(contentPane, "You picked a "
				+ faceDownCard.getColor() + " card from the deck.");

		if (getTrainCardCount() == 2)
			endPickTrainCardsTurn();
	}

	/**
	 * update the progress bar indicating size of train card deck
	 */
	public static void updateTrainCardDeckProgressBar() {
		pbrTrainCardDeck
				.setValue((int) ((Deck.trainCardsDeck.size() / 110.0) * 100));
	}

	/**
	 * human player obtains new destination cards
	 */
	private void pickDestinationCards() {
		disableTurnChoiceButtons();
		int minDestCardNum = 1;

		if (Game.firstTurn)
			minDestCardNum = 2;

		// Get initial destination cards
		List<DestinationCard> initialDesCards = PlayerHandler.drawDesTickets();
		List<DestinationCard> rejectedDesCards = new ArrayList<DestinationCard>();

		if (initialDesCards.size() == 3) {
			JCheckBox cbDestCardOpt0 = new JCheckBox(initialDesCards.get(0)
					.toString());
			JCheckBox cbDestCardOpt1 = new JCheckBox(initialDesCards.get(1)
					.toString());
			JCheckBox cbDestCardOpt2 = new JCheckBox(initialDesCards.get(2)
					.toString());

			String message;
			if (Game.firstTurn)
				message = "Please choose your initial Destination Cards. You must pick at minimum two cards.";
			else
				message = "Please choose additional Destination Cards. You must pick at minimum one card.";

			Object[] params = { message, cbDestCardOpt0, cbDestCardOpt1,
					cbDestCardOpt2 };

			int selectionCount = 0;

			do {
				JOptionPane
						.showMessageDialog(null, params,
								"Choose Destination Card(s)",
								JOptionPane.PLAIN_MESSAGE);

				selectionCount = 0;

				if (cbDestCardOpt0.isSelected())
					selectionCount++;
				if (cbDestCardOpt1.isSelected())
					selectionCount++;
				if (cbDestCardOpt2.isSelected())
					selectionCount++;

			} while (selectionCount < minDestCardNum);

			if (!cbDestCardOpt2.isSelected())
				rejectedDesCards.add(initialDesCards.remove(2));
			if (!cbDestCardOpt1.isSelected())
				rejectedDesCards.add(initialDesCards.remove(1));
			if (!cbDestCardOpt0.isSelected())
				rejectedDesCards.add(initialDesCards.remove(0));

			PlayerHandler.returnDesCardToDeck(rejectedDesCards);
			Game.currentPlayer.getDesCards().addAll(initialDesCards);

			displayCurrentPlayerDestinationCards();

			appendLog("took the following destination cards:\n\t"
					+ initialDesCards);

		} else {
			JOptionPane.showMessageDialog(null,
					"Destination Card Deck is empty.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		switchToNextPlayer();
	}

	/**
	 * begin pick train card turn methods
	 */
	private void pickTrainCards() {
		setTrainCardCount(0);
		disableTurnChoiceButtons();
		enableTrainCardChoices();
	}

	/**
	 * finalize pick train card turn methods
	 */
	private void endPickTrainCardsTurn() {
		disableTrainCardChoices();
		switchToNextPlayer();
	}

	/**
	 * human player chooses to claim a route in graph
	 */
	private void claimARoute() {

		disableTurnChoiceButtons();

		// display dialog for user to choose which path to claim
		Path routeToClaim = displayPathOptionsToClaim();

		if (routeToClaim != null) {
			// display dialog for user to choose how to pay for route
			trainCard tc = displayPaymentOptions(routeToClaim);

			// generate list of turned in cards
			List<TrainCard> cardsToSpend = generateListOfTurnedInCards(tc,
					routeToClaim);

			if (cardsToSpend.size() > 0)
				PlayerHandler.claimARoute(Game.currentPlayer, routeToClaim,
						cardsToSpend);
			else
				System.out.println("Invalid State");
		}

		retallyPlayerTrainCardHand(); // update hand of cards before switching
		switchToNextPlayer();
	}

	/**
	 * update graph display by repainting edges and nodes
	 */
	public void repaintGraph() {
		pnlGraph.repaint();
	}

	/**
	 * generate list based on player's choice of how to spend train cards on
	 * route
	 */
	private List<TrainCard> generateListOfTurnedInCards(trainCard tc,
			Path routeToBuy) {
		List<TrainCard> tcList = new ArrayList<TrainCard>();

		if (occurrenceOfTrainCardColor(Game.currentPlayer.getTrainCards(), tc) >= routeToBuy
				.getCost()) {
			for (int i = 0; i < routeToBuy.getCost(); i++)
				tcList.add(new TrainCard(tc));
		} else { // cards plus rainbow
			for (int i = 0; i < occurrenceOfTrainCardColor(
					Game.currentPlayer.getTrainCards(), tc); i++)
				tcList.add(new TrainCard(tc));
			int remaining = routeToBuy.getCost() - tcList.size();
			for (int i = 0; i < remaining; i++)
				tcList.add(new TrainCard(trainCard.RAINBOW));
		}

		return tcList;
	}

	/**
	 * dynamically generate choices of routes to claim for human player based on
	 * what's unclaimed, and possible based on his hand of train cards color and
	 * quantities and inventory size
	 */
	private Path displayPathOptionsToClaim() {
		Path routeToClaim = null;

		// generate list of available paths for user
		List<Path> unclaimedPaths = PathHandler.generateUnclaimedRoutes();

		// convert to strings for display of route
		List<String> unclaimedPathsStrings = new ArrayList<String>();

		for (Path p : unclaimedPaths) {
			unclaimedPathsStrings.add(p.toString());
		}

		if (unclaimedPathsStrings.isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Sorry, you don't have any claimable routes.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			// convert to array for display in dialog
			Object[] possibleValues = unclaimedPathsStrings.toArray();

			Object selectedRoute = null;
			do {
				selectedRoute = JOptionPane.showInputDialog(null,
						"Choose a route to claim:", "Claim a Route",
						JOptionPane.INFORMATION_MESSAGE, null, possibleValues,
						possibleValues[0]);
			} while (selectedRoute == null);

			// find path using index of string chosen in string array
			routeToClaim = unclaimedPaths.get(unclaimedPathsStrings
					.indexOf(selectedRoute));
		}

		return routeToClaim;
	}

	/**
	 * dynamically generate all possible payment options for route human player
	 * wants to buy based on train cards in his hand
	 */
	private trainCard displayPaymentOptions(Path routeToBuy) {
		// disable payment option if color + rainbow < cost

		List<String> colorStrings = new ArrayList<String>();
		if (trueIfQuantityMeetsCost(trainCard.PINK, routeToBuy))
			colorStrings.add("PINK");
		if (trueIfQuantityMeetsCost(trainCard.WHITE, routeToBuy))
			colorStrings.add("WHITE");
		if (trueIfQuantityMeetsCost(trainCard.BLUE, routeToBuy))
			colorStrings.add("BLUE");
		if (trueIfQuantityMeetsCost(trainCard.YELLOW, routeToBuy))
			colorStrings.add("YELLOW");
		if (trueIfQuantityMeetsCost(trainCard.RED, routeToBuy))
			colorStrings.add("RED");
		if (trueIfQuantityMeetsCost(trainCard.BLACK, routeToBuy))
			colorStrings.add("BLACK");
		if (trueIfQuantityMeetsCost(trainCard.ORANGE, routeToBuy))
			colorStrings.add("ORANGE");
		if (trueIfQuantityMeetsCost(trainCard.GREEN, routeToBuy))
			colorStrings.add("GREEN");
		if (occurrenceOfTrainCardColor(Game.currentPlayer.getTrainCards(),
				trainCard.RAINBOW) >= routeToBuy.getCost())
			colorStrings.add("RAINBOW");

		Object selectedColor = null;
		trainCard tc = null;

		if (colorStrings.size() > 0) {
			// convert to array for display in dialog
			Object[] colorPay = colorStrings.toArray();
			do {
				selectedColor = JOptionPane
						.showInputDialog(
								null,
								"Which color would you like to pay with? If below cost, rainbow will be added if available."
										+ "", "Choose Payment Option",
								JOptionPane.INFORMATION_MESSAGE, null,
								colorPay, colorPay[0]);
			} while (selectedColor == null);

			tc = trainCard.valueOf(selectedColor.toString());
		} else
			System.out.println("no cards to pay with");

		return tc;
	}

	/**
	 * test a given train card color to see if the quantity is high enough to
	 * satisfy the cost of the route to be bought
	 */
	private boolean trueIfQuantityMeetsCost(trainCard trainCardColor,
			Path routeToBuy) {
		boolean retVal = false;

		if (occurrenceOfTrainCardColor(Game.currentPlayer.getTrainCards(),
				trainCardColor) > 0) {

			if ((routeToBuy.getColor().getRealColor().equals(trainCardColor
					.getRealColor()))
					|| (routeToBuy.getColor().equals(Constants.pathColor.GRAY))) {
				if ((occurrenceOfTrainCardColor(
						Game.currentPlayer.getTrainCards(), trainCardColor)
						+ occurrenceOfTrainCardColor(
								Game.currentPlayer.getTrainCards(),
								trainCard.RAINBOW) >= routeToBuy.getCost())) {
					retVal = true;
				}
			}
		}

		return retVal;
	}

	/**
	 * enable appropriate gui buttons at start of turn based on what player is
	 * allowed to do
	 */
	public void enableTurnChoiceButtons() {
		if (Game.firstTurn) {
			btnPickDestCards.setEnabled(true);
		} else {
			if (Deck.trainFaceUpCards.size() > 1)
				btnPickTrainCards.setEnabled(true);
			if (Game.currentPlayer.getTrainCards().size() > 0)
				btnClaimARoute.setEnabled(true);
			if (Deck.desCardDeck.size() > 0)
				btnPickDestCards.setEnabled(true);
		}
	}

	/**
	 * disable gui buttons after choosing so user can only do one option per
	 * turn
	 */
	public void disableTurnChoiceButtons() {
		btnPickTrainCards.setEnabled(false);
		btnClaimARoute.setEnabled(false);
		btnPickDestCards.setEnabled(false);
	}

	/**
	 * if choosing to take train cards, enable the gui buttons for the 5 face up
	 * cards and the face down deck button
	 */
	public void enableTrainCardChoices() {
		for (int i = 0; i < lblFaceUpTrainCard.length; i++) {
			if (i < Deck.trainFaceUpCards.size())
				lblFaceUpTrainCard[i].setEnabled(true);
			else
				lblFaceUpTrainCard[i].setEnabled(false);
		}

		if (!Deck.trainCardsDeck.isEmpty())
			btnTrainCardDeck.setEnabled(true);
	}

	/**
	 * disable 5 face up gui buttons and face down deck button after user has
	 * selected both cards
	 */
	public void disableTrainCardChoices() {
		lblFaceUpTrainCard4.setEnabled(false);
		lblFaceUpTrainCard3.setEnabled(false);
		lblFaceUpTrainCard2.setEnabled(false);
		lblFaceUpTrainCard1.setEnabled(false);
		lblFaceUpTrainCard0.setEnabled(false);
		btnTrainCardDeck.setEnabled(false);
	}

	/**
	 * keep track of how many cards taken during current turn
	 */
	public int getTrainCardCount() {
		return trainCardCount;
	}

	/**
	 * update how many cards taken during current turn
	 */
	public void setTrainCardCount(int trainCardCount) {
		this.trainCardCount = trainCardCount;
	}

	/**
	 * show in gui the current player's selection of destination tickets
	 */
	public void displayCurrentPlayerDestinationCards() {
		destList.clear();

		// display destination cards in JList
		for (DestinationCard dc : Game.currentPlayer.getDesCards()) {
			destList.addElement(dc.toString());
		}
	}

	/**
	 * update gui scoreboard based on player info
	 */
	public void updateScoreboard() {
		String outputString = "Player\tScore\tDest Cards\tTrain Cards\tInventory\n";
		List<String> playerScores = generateScoreboardRows();
		for (String s : playerScores) {
			outputString += s;
		}

		jtaScores.setText(outputString);
	}

	/**
	 * generate a single line of the gui scoreboard
	 */
	private List<String> generateScoreboardRows() {
		List<String> playerScores = new ArrayList<String>();

		for (Player p : Game.players) {
			playerScores.add(p.printTotals() + "\n");
		}

		return playerScores;
	}

	/**
	 * display on gui the current player's name
	 */
	public void updateCurrentPlayerAvatar() {
		lblPlaceHolderAvatarLabel.setText(Game.currentPlayer.getColor()
				.toString());
	}

	/**
	 * switch methods for changing players
	 */
	public void switchToNextPlayer() {
		for (Player p : Game.players) { // comment this out to player all human
										// player
			Game.nextPlayer();
			if (Game.gameover)
				return;
		} // comment this out to player all human player
		enableTurnChoiceButtons();
	}

	/**
	 * update the gui log with additional info about a players action
	 */
	public static void appendLog(String actionTaken) {
		jtaLog.append(getCurrentTime() + " " + Game.currentPlayer.getColor()
				+ " " + actionTaken + "\n");
	}

	/**
	 * update the gui log with additional info in general
	 */
	public static void appendLogInfo(String actionTaken) {
		jtaLog.append(getCurrentTime() + " " + actionTaken + "\n");
	}

	/**
	 * get current time for displaying in log messages
	 */
	public static String getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE) + ":"
				+ calendar.get(Calendar.SECOND);
	}

	/**
	 * provide a dynamic popup dialog for gui
	 */
	public void popupMessage(String s) {
		JOptionPane.showMessageDialog(contentPane, s);
	}

	/**
	 * take log at end of game and write it to an output text file for later
	 * analysis
	 */
	public static void writeLogToFile() {
		File targetfile = new File("log.txt");

		PrintWriter output = null;
		try {
			output = new PrintWriter(targetfile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		output.println(jtaLog.getText() + "\n" + jtaScores.getText());
		output.close();
	}

	/**
	 * check if current player has an empty hand
	 */
	private boolean playerHasNoTrainCards() {
		if (Game.currentPlayer.getTrainCards().size() == 0)
			return true;
		else
			return false;
	}
}
