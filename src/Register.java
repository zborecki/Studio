import java.awt.EventQueue;
import java.sql.*;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Components.Button;
import Components.PasswordField;
import Components.Textfield;
import Theme.Colors;
import Theme.Theme;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Register extends JFrame {
	
//	ResourceBundle res= ResourceBundle.getBundle("lang_PL");
//	ResourceBundle res= ResourceBundle.getBundle("lang_EN");
//
	private Theme contentPane;
	private Textfield Nickname;
	private Textfield Imie_Nazwisko;
	private Textfield mail;
	private PasswordField password;
	private PasswordField repeat_password;
	int posX=0,posY=0;
	ResourceBundle res;
    static Boolean editable1;

	/**
	 * Launch the application.
	 */
	public static void main1(Boolean editable) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editable1=editable;
					Register frame = new Register();
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
	public Register() {
		
		if(GlobalVariables.jezyk==1)res = ResourceBundle.getBundle("lang_PL");
		if(GlobalVariables.jezyk==0)res = ResourceBundle.getBundle("lang_EN");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 250, 370, 630);
		contentPane = new Theme();
		contentPane.setBackground(Colors.DTBackground);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel NazwaStudia = new JLabel(GlobalVariables.name);
		NazwaStudia.setForeground(Colors.DTPurple);
		NazwaStudia.setFont(new Font("Tahoma", Font.ITALIC, 19));
		NazwaStudia.setBounds(94, 22, 242, 59);
		contentPane.add(NazwaStudia);
		
		JLabel Close = new JLabel("X");
		Close.setForeground(new Color(195, 195, 195));
		Close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		Close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Close.setFont(new Font("Tahoma", Font.BOLD, 15));
		Close.setHorizontalAlignment(SwingConstants.CENTER);
		Close.setBounds(326, 0, 44, 30);
		contentPane.add(Close);
		
		Nickname = new Textfield("default", res.getString("username"));
		Nickname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Nickname.setBounds(38, 133, 292, 70);
		contentPane.add(Nickname);
		
		Imie_Nazwisko = new Textfield("default", res.getString("fullname"));
		Imie_Nazwisko.setBounds(38, 225, 292, 70);
		contentPane.add(Imie_Nazwisko);
		
		mail = new Textfield("email", "E-mail");
		mail.setBounds(38, 312, 292, 70);
		contentPane.add(mail);
		
		password = new PasswordField(res.getString("password"));
		password.setBounds(38, 399, 292, 70);
		contentPane.add(password);
		repeat_password = new PasswordField(res.getString("password_repeat"));
		repeat_password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					int c = password.passwordfield.getText().length();
					if (Nickname.textfield.getText().equals("") || Imie_Nazwisko.textfield.getText().equals("") || mail.textfield.getText().equals("") || password.passwordfield.getText().toString().equals("")){
						JOptionPane.showMessageDialog(null, res.getString("req_data"));
					     }
					else if(c<6) {
						JOptionPane.showMessageDialog(null, res.getString("too_short"));
					}
					
					
					
					
					
					else if(!(String.valueOf(password.passwordfield.getText().toString())).equals(String.valueOf(repeat_password.passwordfield.getText().toString()))) {JOptionPane.showMessageDialog(null, "Podane hasla nie sa identyczne");}
					
					else{
				
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://remotemysql.com/Lf5M3N6QnK","Lf5M3N6QnK","7me26nI8IY");
						String sql="INSERT INTO users values(?,?,?,?,?)";
					
						PreparedStatement stmt = con.prepareStatement(sql);
						stmt.setString(1, Nickname.textfield.getText());
						stmt.setString(2, Imie_Nazwisko.textfield.getText());
						stmt.setString(3, password.passwordfield.getText().toString());
						stmt.setString(4, mail.textfield.getText());
						stmt.setInt(5, 0);
					
					    int rs = stmt.executeUpdate();
						
					
						if(rs!=0) {
							JOptionPane.showMessageDialog(null, res.getString("user_add"));
							String[] errorSoon = new String[1];
							dispose();
							if(editable1.equals(false)) {
							Login nw = new Login();
							Login.main(errorSoon);
							}
						}
						else
							JOptionPane.showMessageDialog(null, res.getString("n_user_add"));
						
						con.close();
					} 
					catch(Exception er){System.out.print(er);}
					
					}
				}
			}
		});
		repeat_password.setBounds(38, 486, 292, 70);
		contentPane.add(repeat_password);
		
		JLabel lblNewLabel = new JLabel(res.getString("have_acc"));
		lblNewLabel.setForeground(Colors.DTInactive);
		lblNewLabel.setBounds(38, 577, 110, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel(res.getString("signin"));
		lblNewLabel_2.setForeground(Colors.DTInactive);
		lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_2.setBounds(149, 577, 91, 28);
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String[] errorSoon = new String[1];
				dispose();
				Login nw = new Login();
				Login.main(errorSoon);
				
				
			}
		});
	
		contentPane.add(lblNewLabel_2);
		
		
		if(editable1.equals(true)  ) {
			lblNewLabel.setVisible(false);
			lblNewLabel_2.setVisible(false);
		}
		Button btnRg = new Button();
		btnRg.setBackground(Colors.DTPurple);
		btnRg.setText(res.getString("register"));
		if(editable1.equals(true)) {btnRg.setText(res.getString("add"));}
		btnRg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int c = password.passwordfield.getText().length();
				if (Nickname.textfield.getText().equals("") || Imie_Nazwisko.textfield.getText().equals("") || mail.textfield.getText().equals("") || password.passwordfield.getText().toString().equals("")){
					JOptionPane.showMessageDialog(null, res.getString("req_data"));
				     }
				else if(c<6) {
					JOptionPane.showMessageDialog(null, res.getString("too_short"));
				}
				
			
				else if(!(String.valueOf(password.passwordfield.getText().toString())).equals(String.valueOf(repeat_password.passwordfield.getText().toString()))) {JOptionPane.showMessageDialog(null, "Podane hasla nie sa identyczne");}
				
				else{
			
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://remotemysql.com/Lf5M3N6QnK","Lf5M3N6QnK","7me26nI8IY");
					String sql="INSERT INTO users values(?,?,?,?,?)";
				
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, Nickname.textfield.getText());
					stmt.setString(2, Imie_Nazwisko.textfield.getText());
					stmt.setString(3, password.passwordfield.getText().toString());
					stmt.setString(4, mail.textfield.getText());
					stmt.setInt(5, 0);
				
				    int rs = stmt.executeUpdate();
					
				
					if(rs!=0) {
						JOptionPane.showMessageDialog(null, "Dodano uzytkownika");
						String[] errorSoon = new String[1];
						dispose();
						if(editable1.equals(false)) {
						Login nw = new Login();
						Login.main(errorSoon);
						}
					}
					
					else
						JOptionPane.showMessageDialog(null, res.getString("n_user_add"));
					
					con.close();
				} 
				catch(Exception er){System.out.print(er);}
				
				}	
			}
		});
		btnRg.setBounds(238, 572, 106, 37);
		contentPane.add(btnRg);
		
		JLabel DragBar = new JLabel("");//Przenoszenie ramki
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
		DragBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		DragBar.setBounds(0, 0, 327, 30);
		contentPane.add(DragBar);
		setUndecorated(true); //usuwa ramke
		contentPane.setDarkTheme(this, GlobalVariables.isDark);
	}
}
