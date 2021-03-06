package com.acat.view.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.acat.view.util.Config;

import net.sf.json.JSONObject;

public class HaoyouliebiaoDialog extends JDialog {
	
	final JLabel myNetName = new JLabel();
	final JLabel myInfo = new JLabel();
	final JLabel myImage = new JLabel(new ImageIcon("image1/13.png"));
	
	public void gengxin(){
		JSONObject jsonObject = JSONObject.fromObject(Config.geren_json_data);
		myNetName.setText(jsonObject.getString("netname"));
		myInfo.setText(jsonObject.getString("info"));
		myImage.setIcon(new ImageIcon("image1/"+jsonObject.getString("image")+".png"));
		
	}
	

	/**
	 * Launch the application
	 * 
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
					HaoyouliebiaoDialog dialog = new HaoyouliebiaoDialog();
					dialog.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							System.exit(0);
						}
					});
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog
	 */
	public HaoyouliebiaoDialog() {
		super();
		setBounds(100, 100, 350, 743);

		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(5,5));
		getContentPane().add(panel, BorderLayout.NORTH);

		
		myImage.setPreferredSize(new Dimension(55, 55));

		panel.add(myImage, BorderLayout.WEST);

		final JPanel panel_1 = new JPanel();
		final BorderLayout borderLayout = new BorderLayout(5,5);
		panel_1.setLayout(borderLayout);
		panel.add(panel_1, BorderLayout.CENTER);

		
		myNetName.setFont(new Font("", Font.BOLD, 16));
		myNetName.setText("小唐唐想静静");
		panel_1.add(myNetName, BorderLayout.CENTER);

		
		myInfo.setFont(new Font("宋体", Font.PLAIN, 12));
		myInfo.setText("知识的价值不在于占有，而在于使用。");
		panel_1.add(myInfo, BorderLayout.SOUTH);

		final JPanel panel_2 = new JPanel();
		panel_2.setLayout(new BorderLayout());
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		final JPanel panel_3 = new JPanel();
		final FlowLayout flowLayout_1 = new FlowLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_3.setLayout(flowLayout_1);
		panel_2.add(panel_3);

		final JButton button = new JButton();
		button.setText("设置");
		panel_3.add(button);

		final JButton button_2 = new JButton();
		button_2.setText("查找");
		panel_3.add(button_2);

		final JPanel panel_4 = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		panel_4.setLayout(flowLayout);
		panel_2.add(panel_4, BorderLayout.EAST);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//极端方式
				System.exit(0);
				
			}
		});
		
		button_1.setText("退出");
		panel_4.add(button_1);

		final JTabbedPane tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		final JPanel panel_5 = new JPanel();
		panel_5.setLayout(new BorderLayout());
		tabbedPane.addTab("我的好友", null, panel_5, null);

		final JScrollPane scrollPane = new JScrollPane();
		panel_5.add(scrollPane, BorderLayout.CENTER);
		scrollPane.getViewport().add(new HaoyouListJPanel());
		//
		gengxin();
	}

}
