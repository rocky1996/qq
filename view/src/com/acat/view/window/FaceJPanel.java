package com.acat.view.window;

import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.acat.view.util.Config;

public class FaceJPanel extends JPanel implements Comparable<FaceJPanel>,MouseListener,Runnable{
	private String image;
	private String netName;
	private String info;
	private String uid;
	private JLabel label_image;
	private JLabel label_netName;
	private JLabel label_info;
	
	public FaceJPanel(String image,String netName,String info,String uid){
		this.image = image;
		this.netName = netName;
		this.info = info;
		this.uid = uid;
		
		this.setLayout(null);
		
//		if(image.equals("def")){
//			image = "0";
//		}
		
		label_image = new JLabel();
		label_image.setBounds(4, 4, 48, 48);
		add(label_image);
		setImage(image);
		
		label_netName = new JLabel();
		label_netName.setBounds(58, 4, 478, 18);
		add(label_netName);
		label_netName.setFont(new Font("微软雅黑",Font.BOLD,14));
		label_netName.setText(netName);
		
		label_info = new JLabel();
		label_info.setBounds(58, 34, 478, 18);
		add(label_info);
		label_info.setText(info);
		
		this.addMouseListener(this);
	}
	
	boolean run = true; 
	
	public void run(){
		int x = this.getX();
		int y = this.getY();
		
		while(run){
			
			this.setLocation(x-2, y-2);
			
			try{
				Thread.sleep(500);
			}catch(Exception e){
				
			}
			this.setLocation(x+4, y+4);
			try{
				Thread.sleep(500);
			}catch(Exception e){
				
			}
		}
		
		this.setLocation(x,y);
	}
	
	private Thread thread = null; 
	
	//所有的消息
	private Vector<Msg> msgs = new Vector<Msg>();
	
	//寄存消息
	public void addMessage(Msg msg){
		msgs.add(msg);//添加消息进去
		if(thread==null){
			run = true;
			thread = new Thread(this);
			thread.start();
		}else if(thread.getState()==Thread.State.TERMINATED){//如果死亡了
			thread = new Thread(this);
			thread.start();
		}else if(run==false){//如果死亡了
			thread = new Thread(this);
			thread.start();
		}
	}
	
	
	public void setImage(String image){
		if(image.equals("def")){
			image = "0";
		}
		
		this.image = image;
		
		if(online){
			label_image.setIcon(new ImageIcon("image1/"+image+".png"));
		}else{
			label_image.setIcon(new ImageIcon("image2/"+image+".png"));
		}
	}
	
	public void setNetname(String netName){
		label_netName.setText(netName);
		this.netName = netName;
	}
	
	public void setInfo(String info){
		label_info.setText(info);
		this.info = info;
	}
	
	int xx=0;
	int yy=0;
	
	private boolean online = false;
	
	
	public void setOnline(boolean online){
		this.online = online;
		if(online){
			label_image.setIcon(new ImageIcon("image1/"+image+".png"));
		}else{
			label_image.setIcon(new ImageIcon("image2/"+image+".png"));
		}
	}

	@Override
	public int compareTo(FaceJPanel o) {
		if(o.online){
			return 1;
		}else if(this.online){
			return -1;
		}else{
			return 0;
		}
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getClickCount()==2){
			//如果在线则可以打开
			if(online){
				run = false;//终止线程
				Config.showMessageJFrame(uid, netName, info, image,msgs);
			}
			
			//new LiaotianFrame(uid, netName, image, info);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		xx = this.getX();
		yy = this.getY();
		
		this.setLocation(xx-3,yy-3);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.setLocation(xx,yy);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
