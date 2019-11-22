import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseMotionAdapter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class MainWindow extends JFrame {

	private Theme contentPane;
	private JPanel listPanel;
	private JTextField searchBar;
	int posX=0,posY=0;

	/**
	 * Launch the application.
	 */
	public static void main1(String fullname,int admin) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Zmienne_globalne.is_admin1= admin;
					Zmienne_globalne.fullname1 = fullname;
					//JOptionPane.showMessageDialog(null, Zmienne_globalne.fullname1);
					MainWindow window = new MainWindow();
					window.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1000, 780);
		contentPane = new Theme(true, this);
		contentPane.setBackground(Colors.DTPurple);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel DragBar = new JLabel("");
		DragBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		DragBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				posX=e.getX();
                posY=e.getY();
			}
		});
		DragBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent evt) {
				setLocation (evt.getXOnScreen()-posX,evt.getYOnScreen()-posY);
			}
		});
		DragBar.setBounds(0, 0, 954, 30);
		contentPane.add(DragBar);
		
		JPanel navigationPanel = new JPanel();
		navigationPanel.setBackground(Colors.DTPurple);
		navigationPanel.setBounds(0, 0, 80, 780);
		getContentPane().add(navigationPanel);
		navigationPanel.setLayout(null);
		
		JLabel Home = new JLabel("");
		Home.setIcon(new ImageIcon(MainWindow.class.getResource("/assets/home.png")));
		Home.setHorizontalAlignment(SwingConstants.CENTER);
		Home.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Home.setBounds(0, 130, 80, 55);
		navigationPanel.add(Home);
		
		JLabel playlist = new JLabel("");
		playlist.setIcon(new ImageIcon(MainWindow.class.getResource("/assets/playlist.png")));
		playlist.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		playlist.setHorizontalAlignment(SwingConstants.CENTER);
		playlist.setBounds(0, 190, 80, 55);
		navigationPanel.add(playlist);
		
		JLabel cart = new JLabel("");
		cart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cart.setHorizontalAlignment(SwingConstants.CENTER);
		cart.setIcon(new ImageIcon(MainWindow.class.getResource("/assets/shopping_cart.png")));
		cart.setBounds(0, 250, 80, 55);
		navigationPanel.add(cart);
		
		JLabel invert_colors = new JLabel("");
		invert_colors.setIcon(new ImageIcon(MainWindow.class.getResource("/assets/invert_colors.png")));
		invert_colors.setHorizontalAlignment(SwingConstants.CENTER);
		invert_colors.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		invert_colors.setBounds(0, 310, 80, 55);
		navigationPanel.add(invert_colors);

		JLabel Settings = new JLabel("");
		Settings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Settings.setHorizontalAlignment(SwingConstants.CENTER);
		Settings.setIcon(new ImageIcon(MainWindow.class.getResource("/assets/settings.png")));
		Settings.setBounds(0, 630, 80, 55);
		navigationPanel.add(Settings);
		
		JLabel LogOut = new JLabel("");
		LogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String[] errorSoon = new String[1];
				dispose();
				JOptionPane.showMessageDialog(null, "Wylogowano!");
				Login nw = new Login();
				Login.main(errorSoon);
			}
		});
		LogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		LogOut.setHorizontalAlignment(SwingConstants.CENTER);
		LogOut.setIcon(new ImageIcon(MainWindow.class.getResource("/assets/exit.png")));
		LogOut.setBounds(0, 690, 80, 55);
		navigationPanel.add(LogOut);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.WHITE);
		topPanel.setBounds(80, 0, 920, 110);
		contentPane.add(topPanel);

		if(Zmienne_globalne.is_admin1==1) {
		JButton delButton = new JButton("Panel Administratora");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Add_add nw = new Add_add();
				Add_add.add_song(true);
				
				
			}
		});
		delButton.setBounds(327, 58, 167, 29);
		topPanel.add(delButton);
		}
		topPanel.setLayout(null);
		JButton listButton = new JButton("Users");
		listButton.setBounds(518, 58, 84, 29);
		topPanel.add(listButton);
		
		JLabel textArea = new JLabel();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textArea.setBounds(651, 58, 191, 23);
		topPanel.add(textArea);
		textArea.setText(Zmienne_globalne.fullname1);
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				//okno z danymi uzytkownika
				
				UserEdit nw = new UserEdit();
				UserEdit.okno_edycji1(Zmienne_globalne.fullname1,Zmienne_globalne.is_admin1);
				
				
			}
			
		});
		textArea.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel czy_admin = new JLabel("New label");
		if(Zmienne_globalne.is_admin1==1) {
			czy_admin.setText("Administrator");
			czy_admin.setForeground(Color.RED);
		}else {
			czy_admin.setText("Standard user");
			czy_admin.setForeground(Color.GRAY);
		}
		
		czy_admin.setBounds(651, 85, 123, 14);
		topPanel.add(czy_admin);
		
		JLabel close = new JLabel("");
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0); 
			}
		});
		close.setIcon(new ImageIcon(MainWindow.class.getResource("/assets/closeIcon.png")));
		close.setHorizontalAlignment(SwingConstants.CENTER);
		close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		close.setBounds(874, 0, 46, 30);
		topPanel.add(close);
		
		listPanel = new JPanel();
		listPanel.setBackground(Color.WHITE);
		listPanel.setBounds(90, 120, 608, 660);
		getContentPane().add(listPanel);
		listPanel.setLayout(null);
		
		Heading panelH = new Heading("Title", Colors.DTPurple);
		panelH.setBounds(27, 27, 72, 38);
		listPanel.add(panelH);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setBounds(710, 165, 280, 615);
		getContentPane().add(infoPanel);
		infoPanel.setLayout(null);
		
		JLabel albumImage = new JLabel("avatar");
		albumImage.setOpaque(true);
		albumImage.setBackground(Color.ORANGE);
		albumImage.setBounds(15, 100, 250, 250);
		infoPanel.add(albumImage);
		
		JLabel albumTitle = new JLabel("BELLA CIAO");
		albumTitle.setBounds(15, 365, 250, 16);
		infoPanel.add(albumTitle);
		
		JLabel albumAuthor = new JLabel("Sergio y Andres");
		albumAuthor.setForeground(Color.BLUE);
		albumAuthor.setBounds(15, 385, 250, 16);
		infoPanel.add(albumAuthor);
		
		JLabel albumAlbum = new JLabel("Album:");
		albumAlbum.setBounds(15, 425, 50, 16);
		infoPanel.add(albumAlbum);
		
		JLabel albumDuration = new JLabel("Duration:");
		albumDuration.setBounds(15, 445, 59, 16);
		infoPanel.add(albumDuration);
		
		JLabel albumMusicGenre = new JLabel("Music genre:");
		albumMusicGenre.setBounds(15, 465, 84, 16);
		infoPanel.add(albumMusicGenre);
		
		JLabel albumReleaseDate = new JLabel("Release date:");
		albumReleaseDate.setBounds(15, 485, 84, 16);
		infoPanel.add(albumReleaseDate);
		
		JLabel albumDescription = new JLabel("Description:");
		albumDescription.setBounds(15, 505, 84, 16);
		infoPanel.add(albumDescription);
		
		JLabel dataAlbum = new JLabel("La Casa de Papel - Soundtrack");
		dataAlbum.setForeground(Color.LIGHT_GRAY);
		dataAlbum.setBounds(68, 425, 198, 16);
		infoPanel.add(dataAlbum);
		
		JLabel dataTime = new JLabel("4:25");
		dataTime.setForeground(Color.LIGHT_GRAY);
		dataTime.setBounds(80, 445, 185, 16);
		infoPanel.add(dataTime);
		
		JLabel dataMusicGenre = new JLabel("Pop");
		dataMusicGenre.setForeground(Color.LIGHT_GRAY);
		dataMusicGenre.setBounds(102, 465, 164, 16);
		infoPanel.add(dataMusicGenre);
		
		JLabel dataReleaseDate = new JLabel("01 January 2018");
		dataReleaseDate.setForeground(Color.LIGHT_GRAY);
		dataReleaseDate.setBounds(102, 485, 164, 16);
		infoPanel.add(dataReleaseDate);
		
		JTextArea dataDescription = new JTextArea();
		dataDescription.setForeground(Color.LIGHT_GRAY);
		dataDescription.setWrapStyleWord(true);
		dataDescription.setLineWrap(true);
		dataDescription.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ...");
		dataDescription.setEditable(false);
		dataDescription.setBounds(15, 525, 250, 69);
		infoPanel.add(dataDescription);
		
		Heading panelh2 = new Heading("Something", Colors.DTPurple);
		panelh2.setBounds(32, 43, 145, 38);
		infoPanel.add(panelh2);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(Color.WHITE);
		searchPanel.setBounds(710, 120, 280, 46);
		getContentPane().add(searchPanel);
		searchPanel.setLayout(null);
		
		searchBar = new JTextField();
		searchBar.setBorder(null);
		searchBar.setForeground(Color.LIGHT_GRAY);
		searchBar.setCaretColor(Color.BLUE);
		searchBar.setBounds(0, 0, 280, 46);
		searchBar.setText("Search");
		searchPanel.add(searchBar);
		searchBar.setColumns(10);
	}
}
