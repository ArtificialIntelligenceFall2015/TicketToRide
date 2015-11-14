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
import java.util.ArrayList;
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

import TicketToRide.Control.PlayerHandler;
import TicketToRide.Model.City;
import TicketToRide.Model.Constants.playerColor;
import TicketToRide.Model.Constants;
import TicketToRide.Model.Constants.trainCard;
import TicketToRide.Model.Deck;
import TicketToRide.Model.DestinationCard;
import TicketToRide.Model.ParseCSVData;
import TicketToRide.Model.Path;
import TicketToRide.Model.Player;
import TicketToRide.Model.TrainCard;
import TicketToRide.Model.World;

public class TicketToRideGui extends JFrame {

	private JPanel contentPane;
	DefaultListModel destList;
	JLabel lblFaceUpTrainCard4;
	JLabel lblFaceUpTrainCard3;
	JLabel lblFaceUpTrainCard2;
	JLabel lblFaceUpTrainCard1;
	JLabel lblFaceUpTrainCard0;
	JProgressBar pbrTrainCardDeck;
	JLabel lblCurrentPlayerTrainCardPink;
	JLabel lblCurrentPlayerTrainCardWhite;
	JLabel lblCurrentPlayerTrainCardBlue;
	JLabel lblCurrentPlayerTrainCardYellow;
	JLabel lblCurrentPlayerTrainCardRed;
	JLabel lblCurrentPlayerTrainCardBlack;
	JLabel lblCurrentPlayerTrainCardOrange;
	JLabel lblCurrentPlayerTrainCardGreen;
	JLabel lblCurrentPlayerTrainCardRainbow;
	List<Player> p;
	int occurrences = 0;
	
	
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

		//
		//initialize players
		p = new ArrayList<Player>();
		p.add(new Player(playerColor.BLACK));
		p.add(new Player(playerColor.BLUE));
		p.add(new Player(playerColor.GREEN));
		p.add(new Player(playerColor.RED));
		p.add(new Player(playerColor.YELLOW));
		
		Deck.drawStartingHand(p);
		Deck.drawFreshFaceUpTrainCards();
		//TODO: check for rainbow here
		//System.out.println(Deck.trainFaceUpCards.size());
		//
		
		
		
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

		JTextArea txtrScores = new JTextArea();
		txtrScores.setFont(new Font("Monospaced", Font.PLAIN, 13));
		txtrScores.setText("Player\tScore\tDestination Cards\tTrain Cards\tInventory\n" + "1\n" + "2\n" + "3\n" + "4\n" + "5");
		scpScores.setViewportView(txtrScores);

		JScrollPane scpLog = new JScrollPane();
		pnlScoreLogPanel.add(scpLog);

		JTextArea txtrLog = new JTextArea();
		txtrLog.setFont(new Font("Monospaced", Font.PLAIN, 13));
		txtrLog.setText("log\n" + "1\n" + "2\n" + "3\n" + "4\n" + "5");
		scpLog.setViewportView(txtrLog);

		JPanel pnlGraph = new JPanel();
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
		pnlCurrentPlayerDestCards.add(scpCurrentPlayerDestCards, BorderLayout.CENTER);

		JList jlstCurrentPlayerDestCards = new JList();
		jlstCurrentPlayerDestCards.setFont(new Font("Courier New", Font.PLAIN, 11));
		jlstCurrentPlayerDestCards.setVisibleRowCount(3);
		jlstCurrentPlayerDestCards.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//final DefaultListModel destList = new DefaultListModel(); //SF
		destList = new DefaultListModel(); //SF
		jlstCurrentPlayerDestCards.setModel(destList);
		scpCurrentPlayerDestCards.setViewportView(jlstCurrentPlayerDestCards);

		JPanel pnlCurrentPlayerTrainCards = new JPanel();
		pnlCurrentPlayer.add(pnlCurrentPlayerTrainCards, BorderLayout.CENTER);
		pnlCurrentPlayerTrainCards.setLayout(new GridLayout(1, 12, 0, 0));

		occurrences = occurrenceOfTrainCardColor(p.get(0).getTrainCards(), trainCard.PINK);		
		lblCurrentPlayerTrainCardPink = new JLabel(Integer.toString(occurrences));
		lblCurrentPlayerTrainCardPink.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentPlayerTrainCardPink.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPlayerTrainCardPink.setOpaque(true);
		lblCurrentPlayerTrainCardPink.setBackground(Color.PINK);
		pnlCurrentPlayerTrainCards.add(lblCurrentPlayerTrainCardPink);

		occurrences = occurrenceOfTrainCardColor(p.get(0).getTrainCards(), trainCard.WHITE);
		lblCurrentPlayerTrainCardWhite = new JLabel(Integer.toString(occurrences));
		lblCurrentPlayerTrainCardWhite.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentPlayerTrainCardWhite.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPlayerTrainCardWhite.setBackground(Color.WHITE);
		lblCurrentPlayerTrainCardWhite.setOpaque(true);
		pnlCurrentPlayerTrainCards.add(lblCurrentPlayerTrainCardWhite);

		occurrences = occurrenceOfTrainCardColor(p.get(0).getTrainCards(), trainCard.BLUE);
		lblCurrentPlayerTrainCardBlue = new JLabel(Integer.toString(occurrences));
		lblCurrentPlayerTrainCardBlue.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentPlayerTrainCardBlue.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPlayerTrainCardBlue.setForeground(Color.WHITE);
		lblCurrentPlayerTrainCardBlue.setBackground(Color.BLUE);
		lblCurrentPlayerTrainCardBlue.setOpaque(true);
		pnlCurrentPlayerTrainCards.add(lblCurrentPlayerTrainCardBlue);

		occurrences = occurrenceOfTrainCardColor(p.get(0).getTrainCards(), trainCard.YELLOW);		
		lblCurrentPlayerTrainCardYellow = new JLabel(Integer.toString(occurrences));
		lblCurrentPlayerTrainCardYellow.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentPlayerTrainCardYellow.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPlayerTrainCardYellow.setBackground(Color.YELLOW);
		lblCurrentPlayerTrainCardYellow.setOpaque(true);
		pnlCurrentPlayerTrainCards.add(lblCurrentPlayerTrainCardYellow);

		occurrences = occurrenceOfTrainCardColor(p.get(0).getTrainCards(), trainCard.RED);		
		lblCurrentPlayerTrainCardRed = new JLabel(Integer.toString(occurrences));
		lblCurrentPlayerTrainCardRed.setForeground(Color.WHITE);
		lblCurrentPlayerTrainCardRed.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentPlayerTrainCardRed.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPlayerTrainCardRed.setBackground(Color.RED);
		lblCurrentPlayerTrainCardRed.setOpaque(true);
		pnlCurrentPlayerTrainCards.add(lblCurrentPlayerTrainCardRed);

		occurrences = occurrenceOfTrainCardColor(p.get(0).getTrainCards(), trainCard.BLACK);		
		lblCurrentPlayerTrainCardBlack = new JLabel(Integer.toString(occurrences));
		lblCurrentPlayerTrainCardBlack.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentPlayerTrainCardBlack.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPlayerTrainCardBlack.setForeground(Color.WHITE);
		lblCurrentPlayerTrainCardBlack.setBackground(Color.BLACK);
		lblCurrentPlayerTrainCardBlack.setOpaque(true);
		pnlCurrentPlayerTrainCards.add(lblCurrentPlayerTrainCardBlack);

		occurrences = occurrenceOfTrainCardColor(p.get(0).getTrainCards(), trainCard.ORANGE);		
		lblCurrentPlayerTrainCardOrange = new JLabel(Integer.toString(occurrences));
		lblCurrentPlayerTrainCardOrange.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentPlayerTrainCardOrange.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPlayerTrainCardOrange.setForeground(Color.BLACK);
		lblCurrentPlayerTrainCardOrange.setBackground(Color.ORANGE);
		lblCurrentPlayerTrainCardOrange.setOpaque(true);
		pnlCurrentPlayerTrainCards.add(lblCurrentPlayerTrainCardOrange);

		occurrences = occurrenceOfTrainCardColor(p.get(0).getTrainCards(), trainCard.GREEN);		
		lblCurrentPlayerTrainCardGreen = new JLabel(Integer.toString(occurrences));
		lblCurrentPlayerTrainCardGreen.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentPlayerTrainCardGreen.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPlayerTrainCardGreen.setBackground(Color.GREEN);
		lblCurrentPlayerTrainCardGreen.setOpaque(true);
		pnlCurrentPlayerTrainCards.add(lblCurrentPlayerTrainCardGreen);

		occurrences = occurrenceOfTrainCardColor(p.get(0).getTrainCards(), trainCard.RAINBOW);		
		lblCurrentPlayerTrainCardRainbow = new JLabel(Integer.toString(occurrences));
		lblCurrentPlayerTrainCardRainbow.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentPlayerTrainCardRainbow.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPlayerTrainCardRainbow.setForeground(Color.WHITE);
		lblCurrentPlayerTrainCardRainbow.setBackground(Color.MAGENTA);
		lblCurrentPlayerTrainCardRainbow.setOpaque(true);
		pnlCurrentPlayerTrainCards.add(lblCurrentPlayerTrainCardRainbow);

		JPanel pnlCurrentPlayerTurnChoices = new JPanel();
		pnlDecks.add(pnlCurrentPlayerTurnChoices);

		pnlCurrentPlayerTurnChoices.setLayout(new GridLayout(3, 1, 0, 0));

		JButton btnPickTrainCards = new JButton("Pick Train Cards");
		pnlCurrentPlayerTurnChoices.add(btnPickTrainCards);

		JButton btnClaimARoute = new JButton("Claim a Route");
		pnlCurrentPlayerTurnChoices.add(btnClaimARoute);

		JButton btnPickDestCards = new JButton("Pick Destination Cards");
		pnlCurrentPlayerTurnChoices.add(btnPickDestCards);

		lblFaceUpTrainCard4 = new JLabel();
		lblFaceUpTrainCard4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblFaceUpTrainCard4.setBackground(Deck.trainFaceUpCards.get(4).getColor().getRealColor());
		lblFaceUpTrainCard4.setOpaque(true);
		pnlDecks.add(lblFaceUpTrainCard4);

		lblFaceUpTrainCard3 = new JLabel();
		lblFaceUpTrainCard3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblFaceUpTrainCard3.setBackground(Deck.trainFaceUpCards.get(3).getColor().getRealColor());
		lblFaceUpTrainCard3.setOpaque(true);
		pnlDecks.add(lblFaceUpTrainCard3);

		lblFaceUpTrainCard2 = new JLabel();
		lblFaceUpTrainCard2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblFaceUpTrainCard2.setBackground(Deck.trainFaceUpCards.get(2).getColor().getRealColor());
		lblFaceUpTrainCard2.setOpaque(true);
		pnlDecks.add(lblFaceUpTrainCard2);

		lblFaceUpTrainCard1 = new JLabel();
		lblFaceUpTrainCard1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblFaceUpTrainCard1.setBackground(Deck.trainFaceUpCards.get(1).getColor().getRealColor());
		lblFaceUpTrainCard1.setOpaque(true);
		pnlDecks.add(lblFaceUpTrainCard1);

		lblFaceUpTrainCard0 = new JLabel();
		lblFaceUpTrainCard0.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblFaceUpTrainCard0.setBackground(Deck.trainFaceUpCards.get(0).getColor().getRealColor());
		lblFaceUpTrainCard0.setOpaque(true);
		pnlDecks.add(lblFaceUpTrainCard0);

		JPanel pnlTrainCardDeck = new JPanel();
		pnlDecks.add(pnlTrainCardDeck);
		pnlTrainCardDeck.setLayout(new BorderLayout(0, 0));

		pbrTrainCardDeck = new JProgressBar();
		pbrTrainCardDeck.setOrientation(SwingConstants.VERTICAL);
		//pbrTrainCardDeck.setValue(100);
		pbrTrainCardDeck.setValue((int)((Deck.trainCardsDeck.size() / 110.0) * 100));
		pnlTrainCardDeck.add(pbrTrainCardDeck, BorderLayout.EAST);

		JButton btnTrainCardDeck = new JButton("Train Card Deck");
		pnlTrainCardDeck.add(btnTrainCardDeck, BorderLayout.CENTER);

		JPanel pnlCurrentPlayerAvatar = new JPanel();
		pnlDecks.add(pnlCurrentPlayerAvatar);
		pnlCurrentPlayerAvatar.setLayout(new BorderLayout(0, 0));

		JLabel lblPlaceHolderAvatarLabel = new JLabel("[Current Player Image Placeholder]");
		lblPlaceHolderAvatarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pnlCurrentPlayerAvatar.add(lblPlaceHolderAvatarLabel, BorderLayout.CENTER);

		// ActionListeners for Button Clicking Actions
		lblFaceUpTrainCard4.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				clickFaceUpTrainCard(lblFaceUpTrainCard4, 4);
			}
		});

		lblFaceUpTrainCard3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				clickFaceUpTrainCard(lblFaceUpTrainCard3, 3);
			}
		});

		lblFaceUpTrainCard2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				clickFaceUpTrainCard(lblFaceUpTrainCard2, 2);
			}
		});

		lblFaceUpTrainCard1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				clickFaceUpTrainCard(lblFaceUpTrainCard1, 1);
			}
		});

		lblFaceUpTrainCard0.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				clickFaceUpTrainCard(lblFaceUpTrainCard0, 0);
			}
		});

		btnTrainCardDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerHandler.drawTrainCard(p.get(0));
				//TODO: display joptionpane describing train card picked up from deck
				
			}
		});

		btnPickTrainCards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO:debug action listener code-SF
				System.out.println("Current Player Pick Train Cards Button"); 
				ArrayList<City> cityList = ParseCSVData.parseCities();
				for (City c : cityList)
					System.out.println(c.getCityName() + "\t" + c.getX_val() + "\t" + c.getY_val());
			}
		});

		btnClaimARoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO:debug action listener code-SF
				System.out.println("Current Player Claim A Route Button");
				ArrayList<Path> pathList = ParseCSVData.parseRoutes();
				for (Path p : pathList)
					System.out.println(p.getCity1() + "\t" + p.getCity2() + "\t" + p.getCost() + "\t" + p.getColor());
			}
		});

		btnPickDestCards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Get initial destination cards
				List<DestinationCard> initialDesCards = PlayerHandler.drawDesTickets(new Player(playerColor.BLACK));
				List<DestinationCard> rejectedDesCards = new ArrayList<DestinationCard>();
				
				//debug
				for (DestinationCard dc : initialDesCards)
					System.out.println(dc.getCity1() + "\t" + dc.getCity2() + "\t" + dc.getPoint());
				
				JCheckBox cbDestCardOpt0 = new JCheckBox(initialDesCards.get(0).toString());
				JCheckBox cbDestCardOpt1 = new JCheckBox(initialDesCards.get(1).toString());
				JCheckBox cbDestCardOpt2 = new JCheckBox(initialDesCards.get(2).toString());
				
				String message = "Please choose your initial Destination Cards. You must pick a minimum of two cards.";
				
				Object[] params = {message, cbDestCardOpt0, cbDestCardOpt1, cbDestCardOpt2};
				
				JOptionPane.showMessageDialog(null, params, "Choose Destination Card(s)", JOptionPane.PLAIN_MESSAGE);
				
				if (!cbDestCardOpt2.isSelected())
					rejectedDesCards.add(initialDesCards.remove(2));
				if (!cbDestCardOpt1.isSelected())
					rejectedDesCards.add(initialDesCards.remove(1));
				if (!cbDestCardOpt0.isSelected())
					rejectedDesCards.add(initialDesCards.remove(0));
						
				//System.out.println("cards chosen were:" + initialDesCards); //TODO: debug
				//System.out.println("cards discarded were:" + rejectedDesCards); //TODO: debug
				
				PlayerHandler.returnDesCardToDeck(rejectedDesCards); 
				Player p = new Player(playerColor.BLACK);
				p.getDesCards().addAll(initialDesCards);
				System.out.println("cards in players hand:" + p.getDesCards());//debug
				
				//display destination cards in JList
				for (DestinationCard dc : p.getDesCards()) {
					destList.addElement(dc.toString()); 
				}
			
				//debug
				ArrayList<DestinationCard> destinationCardList = ParseCSVData.parseDestinationCards();
				for (DestinationCard dc : destinationCardList)
					System.out.println(dc.getCity1() + "\t" + dc.getCity2() + "\t" + dc.getPoint());
			}
		});

		//test
		
		pnlGraph.add(new GraphView());
		
		//endtest
	}
	
	public boolean checkForTripleRainbow() {
		boolean hasTripleRainbow = false;
		
		//see if 5 face up cards contain 3 rainbow cards
		int occurrences = Collections.frequency(Deck.trainFaceUpCards, new TrainCard(trainCard.RAINBOW));
		if (occurrences > 2)
			hasTripleRainbow = true;
		
		return hasTripleRainbow;
	}
	
	public int occurrenceOfTrainCardColor(List<TrainCard> playerHand, trainCard tc) {
		int occurrences = Collections.frequency(playerHand, new TrainCard(tc));
		return occurrences;
	}

	public void repaintFaceUpTrainCards() {
		lblFaceUpTrainCard4.setBackground(Deck.trainFaceUpCards.get(4).getColor().getRealColor());
		lblFaceUpTrainCard3.setBackground(Deck.trainFaceUpCards.get(3).getColor().getRealColor());
		lblFaceUpTrainCard2.setBackground(Deck.trainFaceUpCards.get(2).getColor().getRealColor());
		lblFaceUpTrainCard1.setBackground(Deck.trainFaceUpCards.get(1).getColor().getRealColor());
		lblFaceUpTrainCard0.setBackground(Deck.trainFaceUpCards.get(0).getColor().getRealColor());
		
		//debug print
		for (TrainCard tc : Deck.trainFaceUpCards)
			System.out.print(tc.getColor().toString() + " ");
		System.out.println("");
	}
	
	public void retallyPlayerTrainCardHand() {
		occurrences = occurrenceOfTrainCardColor(p.get(0).getTrainCards(), trainCard.PINK);		
		lblCurrentPlayerTrainCardPink.setText(Integer.toString(occurrences));
		occurrences = occurrenceOfTrainCardColor(p.get(0).getTrainCards(), trainCard.WHITE);
		lblCurrentPlayerTrainCardWhite.setText(Integer.toString(occurrences));
		occurrences = occurrenceOfTrainCardColor(p.get(0).getTrainCards(), trainCard.BLUE);
		lblCurrentPlayerTrainCardBlue.setText(Integer.toString(occurrences));
		occurrences = occurrenceOfTrainCardColor(p.get(0).getTrainCards(), trainCard.YELLOW);		
		lblCurrentPlayerTrainCardYellow.setText(Integer.toString(occurrences));
		occurrences = occurrenceOfTrainCardColor(p.get(0).getTrainCards(), trainCard.RED);		
		lblCurrentPlayerTrainCardRed.setText(Integer.toString(occurrences));
		occurrences = occurrenceOfTrainCardColor(p.get(0).getTrainCards(), trainCard.BLACK);		
		lblCurrentPlayerTrainCardBlack.setText(Integer.toString(occurrences));
		occurrences = occurrenceOfTrainCardColor(p.get(0).getTrainCards(), trainCard.ORANGE);		
		lblCurrentPlayerTrainCardOrange.setText(Integer.toString(occurrences));
		occurrences = occurrenceOfTrainCardColor(p.get(0).getTrainCards(), trainCard.GREEN);		
		lblCurrentPlayerTrainCardGreen.setText(Integer.toString(occurrences));
		occurrences = occurrenceOfTrainCardColor(p.get(0).getTrainCards(), trainCard.RAINBOW);		
		lblCurrentPlayerTrainCardRainbow.setText(Integer.toString(occurrences));
	}
	
	public void clickFaceUpTrainCard(JLabel jp, int index) {
		//give the player the face up train card 
		PlayerHandler.drawTrainCard(p.get(0), index);
		System.out.println("Deck.trainCardsDeck.size =" + Deck.trainCardsDeck.size());
		System.out.println("Deck.trainCardsDeck % =" + ((int)((Deck.trainCardsDeck.size() / 110.0) * 100)));
		pbrTrainCardDeck.setValue((int)((Deck.trainCardsDeck.size() / 110.0) * 100));
		//display the new face up train card (if deck is nonempty)
		//check for null card //TODO:
		jp.setBackground(Deck.trainFaceUpCards.get(index).getColor().getRealColor());
		retallyPlayerTrainCardHand();
		while (checkForTripleRainbow()) {
			System.out.println("triple rainbow detected"); //debug
			System.out.println("size of train card deck:" + Deck.trainFaceUpCards.size()); //debug
			Deck.discardAllFaceUpTrainCards(Deck.trainFaceUpCards);
			Deck.drawFreshFaceUpTrainCards();
			repaintFaceUpTrainCards();
		}

	}

}
