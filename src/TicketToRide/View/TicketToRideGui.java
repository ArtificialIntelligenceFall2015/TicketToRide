package TicketToRide.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.ImageIcon;

public class TicketToRideGui extends JFrame {

	private JPanel contentPane;

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

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);

		JPanel pnlBoard = new JPanel();
		splitPane.setRightComponent(pnlBoard);
		pnlBoard.setLayout(new BorderLayout(0, 0));

		JPanel pnlDecks = new JPanel();
		splitPane.setLeftComponent(pnlDecks);
		pnlDecks.setLayout(new GridLayout(8, 1, 0, 0));

		JPanel panel_2 = new JPanel();
		pnlBoard.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1);

		JTextArea txtrScores = new JTextArea();
		txtrScores.setText("scores\n" + "1\n" + "2\n" + "3\n" + "4\n" + "5");
		scrollPane_1.setViewportView(txtrScores);

		JScrollPane scrollPane_2 = new JScrollPane();
		panel_2.add(scrollPane_2);

		JTextArea txtrLog = new JTextArea();
		txtrLog.setText("log\n" + "1\n" + "2\n" + "3\n" + "4\n" + "5");
		scrollPane_2.setViewportView(txtrLog);

		JPanel panel_3 = new JPanel();
		panel_3.setToolTipText("");
		panel_3.setBackground(Color.CYAN);
		pnlBoard.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		pnlBoard.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.WEST);
		panel_4.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_3 = new JScrollPane();
		panel_4.add(scrollPane_3, BorderLayout.CENTER);

		JList list = new JList();
		list.setFont(new Font("Courier New", Font.PLAIN, 11));
		list.setVisibleRowCount(3);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] { "20:\tLos Angeles->New York",
					"12:\tWinnipeg->Miami", " 7:\tVancouver->Washington",
					" 5:\tCalgary->Atlanta", " 9:\tSeattle->Nashville" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane_3.setViewportView(list);

		JPanel panel_5 = new JPanel();
		panel.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new GridLayout(1, 12, 0, 0));

		JLabel lblNewLabel = new JLabel("1");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.PINK);
		panel_5.add(lblNewLabel);

		JLabel lblNewLabel_3 = new JLabel("2");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setOpaque(true);
		panel_5.add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("3");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBackground(Color.BLUE);
		lblNewLabel_5.setOpaque(true);
		panel_5.add(lblNewLabel_5);

		JLabel lblNewLabel_10 = new JLabel("4");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setBackground(Color.YELLOW);
		lblNewLabel_10.setOpaque(true);
		panel_5.add(lblNewLabel_10);

		JLabel lblNewLabel_8 = new JLabel("5");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBackground(Color.ORANGE);
		lblNewLabel_8.setOpaque(true);
		panel_5.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("6");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setForeground(Color.WHITE);
		lblNewLabel_9.setBackground(Color.BLACK);
		lblNewLabel_9.setOpaque(true);
		panel_5.add(lblNewLabel_9);

		JLabel lblNewLabel_6 = new JLabel("7");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBackground(Color.RED);
		lblNewLabel_6.setOpaque(true);
		panel_5.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("8");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBackground(Color.GREEN);
		lblNewLabel_7.setOpaque(true);
		panel_5.add(lblNewLabel_7);

		JLabel lblNewLabel_4 = new JLabel("9");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBackground(Color.MAGENTA);
		lblNewLabel_4.setOpaque(true);
		panel_5.add(lblNewLabel_4);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new GridLayout(3, 1, 0, 0));

		JButton btnNewButton = new JButton("Pick Train Cards");
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Claim a Route");
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Pick Destination Cards");
		panel_1.add(btnNewButton_2);

		JPanel panel_7 = new JPanel();
		pnlDecks.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));

		JButton btnNewButton_3 = new JButton("Destination Card Deck");
		panel_7.add(btnNewButton_3, BorderLayout.CENTER);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setOrientation(SwingConstants.VERTICAL);
		progressBar.setValue(85);
		panel_7.add(progressBar, BorderLayout.EAST);

		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		panel_13.setBackground(Color.RED);
		pnlDecks.add(panel_13);

		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		panel_12.setBackground(Color.ORANGE);
		pnlDecks.add(panel_12);

		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		panel_11.setBackground(Color.BLACK);
		pnlDecks.add(panel_11);

		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		panel_10.setBackground(Color.BLUE);
		pnlDecks.add(panel_10);

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		panel_8.setBackground(Color.GREEN);
		pnlDecks.add(panel_8);

		JPanel panel_9 = new JPanel();
		pnlDecks.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));

		JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setOrientation(SwingConstants.VERTICAL);
		progressBar_1.setValue(45);
		panel_9.add(progressBar_1, BorderLayout.EAST);

		JButton btnNewButton_4 = new JButton("Train Card Deck");
		panel_9.add(btnNewButton_4, BorderLayout.CENTER);

		JPanel panel_6 = new JPanel();
		pnlDecks.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("[Current Player Image Placeholder]");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblNewLabel_1, BorderLayout.CENTER);
	}

}
