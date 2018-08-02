package com.acat.view.window;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.acat.util.WindowXY;
import com.acat.view.service.NetService;
import com.acat.view.util.Config;

import net.sf.json.JSONObject;

public class LoginDialog extends JDialog implements WindowListener{

	private JPasswordField reg_password2;
	private JPasswordField reg_password1;
	private JTextField code;
	private JTextField reg_username;
	private JPasswordField password;
	private JTextField username;
	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String args[]) {
		
//		test.Main.main(args);
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
//		test.Main.main(args);

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginDialog frame = new LoginDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame
	 */
	public LoginDialog() {
		super();
		setTitle("ACAT实锟斤拷锟斤拷锟节诧拷锟斤拷锟届工锟斤拷");
		setResizable(false);
		setAlwaysOnTop(true);//一直锟斤拷示锟斤拷锟斤拷锟斤拷锟斤拷
		getContentPane().setLayout(null);
		setBounds(100, 100, 293, 314);//646  314
		
		setLocation(WindowXY.getXY(this.getSize()));
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		final JLabel label = new JLabel();
		label.setText("锟街伙拷锟�");
		label.setBounds(10, 102, 65, 24);
		getContentPane().add(label);

		final JLabel emailLabel = new JLabel();
		emailLabel.setText("Email:");
		emailLabel.setBounds(10, 123, 65, 24);
		getContentPane().add(emailLabel);

		username = new JTextField();
		username.setBounds(55, 99, 219, 48);
		getContentPane().add(username);

		final JLabel label_1 = new JLabel();
		label_1.setText("锟杰★拷锟斤拷:");
		label_1.setBounds(10, 186, 65, 18);
		getContentPane().add(label_1);

		password = new JPasswordField();
		password.setBounds(55, 171, 219, 48);
		getContentPane().add(password);

		
		final JButton loginbutton = new JButton();
		loginbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				String username_str = username.getText().trim(); 
				String password_str = password.getText().trim();
				if(username_str.trim().equals("") || password_str.trim().equals("")){
					javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "锟矫伙拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷写");
					return;
				}
				Config.username = username_str;
				Config.password = password_str;
				try {
					JSONObject json = NetService.getNetService().login();
					if(json.getInt("state")==0){
						
						//锟斤拷陆锟缴癸拷锟斤拷锟斤拷示锟斤拷锟斤拷锟叫憋拷
						new HaoyouliebiaoDialog().setVisible(true);
						
						//锟斤拷锟截碉拷录锟叫憋拷
						LoginDialog.this.setVisible(false);
						
						//锟斤拷俚锟铰硷拷斜锟�
						LoginDialog.this.dispose();
						
						
						//javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "锟斤拷录锟缴癸拷");
					}else{
						javax.swing.JOptionPane.showMessageDialog(LoginDialog.this,json.getString("msg"));
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "锟斤拷锟斤拷锟斤拷锟斤拷失锟斤拷");
				} 
			}
		});
		
		loginbutton.setText("锟角★拷录");
		loginbutton.setBounds(177, 225, 97, 51);
		getContentPane().add(loginbutton);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				
				if(LoginDialog.this.getHeight()==646){//646  314
					LoginDialog.this.setSize(293, 314);
				}else{
					LoginDialog.this.setSize(293, 646);
				}
				setLocation(WindowXY.getXY(LoginDialog.this.getSize()));
				
			}
		});
		button_1.setText("注锟斤拷锟斤拷");
		button_1.setBounds(10, 225, 97, 51);
		getContentPane().add(button_1);

		final JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "注锟斤拷锟矫伙拷", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel.setBounds(10, 306, 264, 271);
		getContentPane().add(panel);

		final JLabel label_2 = new JLabel();
		label_2.setText("锟斤拷  锟斤拷  锟斤拷:");
		label_2.setBounds(10, 33, 65, 18);
		panel.add(label_2);

		final JLabel emailLabel_1 = new JLabel();
		emailLabel_1.setText("锟斤拷 Email:");
		emailLabel_1.setBounds(10, 52, 65, 18);
		panel.add(emailLabel_1);

		reg_username = new JTextField();
		reg_username.setBounds(63, 27, 180, 43);
		panel.add(reg_username);

		final JButton button_2 = new JButton();
		button_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				if(reg_username.getText().trim().equals("")){
					javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "锟矫伙拷锟斤拷锟斤拷为锟斤拷");
					return;
				}
				
				try{
					Socket socket = new Socket(Config.IP,Config.REG_PORT);
					InputStream input = socket.getInputStream();
					OutputStream output = socket.getOutputStream();
					
					output.write(("{\"type\":\"code\",\"username\":\"" + reg_username.getText() + "\"}").getBytes());
					output.flush();
					
					byte[] bytes = new byte[1024];
					int len = input.read(bytes);
					String str = new String(bytes,0,len);
//					System.out.println(str);
					JSONObject json = JSONObject.fromObject(str);
					if(json.getInt("state")==0){
						javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "锟斤拷锟酵成癸拷");
					}else{
						javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "锟斤拷锟斤拷失锟斤拷");
					}
					
					
					input.close();
					output.close();
					socket.close();
					
				}catch(Exception e1){
										
				}
			}
		});
		
		button_2.setText("锟斤拷锟斤拷锟斤拷证");
		button_2.setBounds(146, 76, 97, 30);
		panel.add(button_2);

		code = new JTextField();
		code.setBounds(63, 113, 85, 43);
		panel.add(code);

		final JLabel label_3 = new JLabel();
		label_3.setText("锟斤拷  证  锟斤拷:");
		label_3.setBounds(10, 125, 65, 18);
		panel.add(label_3);

		reg_password1 = new JPasswordField();
		reg_password1.setBounds(63, 162, 180, 43);
		panel.add(reg_password1);

		reg_password2 = new JPasswordField();
		reg_password2.setBounds(63, 211, 180, 43);
		panel.add(reg_password2);

		final JLabel label_4 = new JLabel();
		label_4.setText("锟杰★拷锟斤拷锟斤拷:");
		label_4.setBounds(10, 174, 65, 18);
		panel.add(label_4);

		final JLabel label_5 = new JLabel();
		label_5.setText("确锟斤拷锟斤拷锟斤拷:");
		label_5.setBounds(10, 223, 65, 18);
		panel.add(label_5);

		final JButton button_3 = new JButton();
		button_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				if(reg_username.getText().trim().equals("")){
					javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "锟矫伙拷锟斤拷锟斤拷为锟斤拷");
					return;
				}
				
				if(reg_password1.getText().trim().equals("")){
					javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "锟斤拷锟诫不锟斤拷为锟斤拷");
					return;
				}
				
				if(reg_password2.getText().trim().equals("")){
					javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "确锟斤拷锟斤拷锟诫不锟斤拷为锟斤拷");
					return;
				}
				
				if(code.getText().trim().equals("")){
					javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "锟斤拷证锟诫不锟斤拷为锟斤拷");
					return;
				}
				
				if(!reg_password1.getText().trim().equals(reg_password2.getText())){
					javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "锟斤拷锟斤拷锟斤拷锟诫不锟斤拷锟�");
					return;
				}
				
				try{
					Socket socket = new Socket(Config.IP,Config.REG_PORT);
					InputStream input = socket.getInputStream();
					OutputStream output = socket.getOutputStream();
					
					output.write(("{\"type\":\"reg\",\"username\":\"" + reg_username.getText() + "\",\"password\":\""
							+ reg_password1.getText() + "\",\"code\":\"" + code.getText() + "\"}").getBytes());
					output.flush();
					
					byte[] bytes = new byte[1024];
					int len = input.read(bytes);
					String str = new String(bytes,0,len);
//					System.out.println(str);
					JSONObject json = JSONObject.fromObject(str);
					if(json.getInt("state")==0){
						javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "注锟斤拷晒锟斤拷锟斤拷锟斤拷缘锟铰斤拷锟�");
						reg_username.setText("");
						reg_password1.setText("");
						reg_password2.setText("");
						code.setText("");
						
					}else if(json.getInt("state")==1){
						javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "锟矫伙拷锟斤拷锟窖达拷锟斤拷");
					}else if(json.getInt("state")==2){
						javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "锟斤拷证锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷禄锟斤拷");
					}else if(json.getInt("state")==3){
						javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "未知锟斤拷锟斤拷");
					}
					
					
					input.close();
					output.close();
					socket.close();
					
				}catch(Exception e1){
					e1.printStackTrace();	
				}		
			}
		});
		
		button_3.setText("注锟斤拷锟矫伙拷");
		button_3.setBounds(177, 583, 97, 30);
		getContentPane().add(button_3);

		final JButton button_4 = new JButton();
		button_4.setText("锟斤拷锟斤拷");
		button_4.setBounds(10, 583, 97, 30);
		getContentPane().add(button_4);
		//
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
