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
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(8, 1, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_3 = new JButton("Destination Card Deck");
		panel_7.add(btnNewButton_3, BorderLayout.CENTER);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(85);
		panel_7.add(progressBar, BorderLayout.EAST);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(Color.RED);
		panel.add(panel_13);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Color.ORANGE);
		panel.add(panel_12);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.BLACK);
		panel.add(panel_11);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.BLUE);
		panel.add(panel_10);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.GREEN);
		panel.add(panel_8);
		
		JPanel panel_9 = new JPanel();
		panel.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setValue(45);
		panel_9.add(progressBar_1, BorderLayout.EAST);
		
		JButton btnNewButton_4 = new JButton("Train Card Deck");
		panel_9.add(btnNewButton_4, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		panel.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("[Current Player Image Placeholder]");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblNewLabel_1, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1);
		
		JTextArea txtrScores = new JTextArea();
		txtrScores.setText("scores\n"
				+ "1\n"
				+ "2\n"
				+ "3\n"
				+ "4\n"
				+ "5");
		scrollPane_1.setViewportView(txtrScores);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_2.add(scrollPane_2);
		
		JTextArea txtrLog = new JTextArea();
		txtrLog.setText("log\n"
				+ "1\n"
				+ "2\n"
				+ "3\n"
				+ "4\n"
				+ "5");
		scrollPane_2.setViewportView(txtrLog);
		
		JPanel panel_3 = new JPanel();
		panel_3.setToolTipText("");
		panel_3.setBackground(Color.CYAN);
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JSplitPane splitPane_1 = new JSplitPane();
		panel_1.add(splitPane_1, BorderLayout.SOUTH);
		
		JPanel panel_4 = new JPanel();
		splitPane_1.setLeftComponent(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		panel_4.add(scrollPane_3, BorderLayout.EAST);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"dest1:Los Angeles->New York", "dest2:Winnipeg->Miami", "dest3:Vancouver->Washington", "DEST4", "DEST5", "DEST6", "DEST7", "D", "D", "D", "D", "D", "D"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane_3.setViewportView(list);
		
		JPanel panel_5 = new JPanel();
		splitPane_1.setRightComponent(panel_5);
		panel_5.setLayout(new GridLayout(3, 4, 0, 0));
		
		JLabel lblNewLabel = new JLabel("# PINK CARDS");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.PINK);
		panel_5.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("# WHITE CARDS");
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setOpaque(true);
		panel_5.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("# BLUE CARDS");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBackground(Color.BLUE);
		lblNewLabel_5.setOpaque(true);
		panel_5.add(lblNewLabel_5);
		
		JButton btnNewButton_1 = new JButton("Obtain New Train Cards");
		panel_5.add(btnNewButton_1);
		
		JLabel lblNewLabel_10 = new JLabel("# YELLOW CARDS");
		lblNewLabel_10.setBackground(Color.YELLOW);
		lblNewLabel_10.setOpaque(true);
		panel_5.add(lblNewLabel_10);
		
		JLabel lblNewLabel_8 = new JLabel("# ORANGE CARDS");
		lblNewLabel_8.setBackground(Color.ORANGE);
		lblNewLabel_8.setOpaque(true);
		panel_5.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("# BLACK CARDS");
		lblNewLabel_9.setForeground(Color.WHITE);
		lblNewLabel_9.setBackground(Color.BLACK);
		lblNewLabel_9.setOpaque(true);
		panel_5.add(lblNewLabel_9);
		
		JButton btnNewButton = new JButton("Claim a Route on the Map");
		panel_5.add(btnNewButton);
		
		JLabel lblNewLabel_6 = new JLabel("# RED CARDS");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBackground(Color.RED);
		lblNewLabel_6.setOpaque(true);
		panel_5.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("# GREEN CARDS");
		lblNewLabel_7.setBackground(Color.GREEN);
		lblNewLabel_7.setOpaque(true);
		panel_5.add(lblNewLabel_7);
		
		JLabel lblNewLabel_4 = new JLabel("# WILD CARDS");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBackground(Color.MAGENTA);
		lblNewLabel_4.setOpaque(true);
		panel_5.add(lblNewLabel_4);
		
		JButton btnNewButton_2 = new JButton("Obtain New Destination Cards");
		panel_5.add(btnNewButton_2);
	}

}
