package com.acat.view.window;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.acat.view.util.Config;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HaoyouListJPanel extends JPanel {
	public HaoyouListJPanel(){
		super();
		setLayout(null);
		
//		for(int i=0;i<40;i++){
//			final JPanel panel = new JPanel();
//			panel.setLayout(null);
//			panel.setBounds(0, i*50, 546, 59);
//			
//			final JLabel label = new JLabel(new ImageIcon("image1/"+i+".png"));
//			label.setBounds(4, 4, 48, 48);
//			panel.add(label);
//			
//			final JLabel label_1 = new JLabel();
//			label_1.setBounds(58, 4, 478, 18);
//			panel.add(label_1);
//			label_1.setFont(new Font("微软雅黑",Font.BOLD,14));
//			label_1.setText("New JLabel");
//			
//			final JLabel label_2 = new JLabel();
//			label_2.setBounds(58, 34, 478, 18);
//			panel.add(label_2);
//			label_2.setText("New JLabel");
//			
//			add(panel);
//		}
		
//		this.setPreferredSize(new Dimension(0, 40*50));
//		
		gengxin();
		
		Config.haoyouListJPanel = this; 
	}
	
	//好友在线更新
	public void haoyouOnline(){
		String zaixianliebiao = Config.haoyou_online;
		
//		if(zaixianliebiao.equals("notFound")){
//			return;
//		}
		
		String[] uids = zaixianliebiao.split(","); 
 		
		Set<String> keys = Config.list.keySet();
		for(String string:keys){//把他里面的所有的状态全部置为下线
			Config.list.get(string).setOnline(false);
		}
		
		if(!zaixianliebiao.equals("notFound") && !zaixianliebiao.trim().equals("")){
			for(String uid:uids){
				//System.out.println("uid"+uid);
				if(!uid.trim().equals("")){
					FaceJPanel faceJPanel = (FaceJPanel)Config.list.get(uid);
					faceJPanel.setOnline(true);
				}
//				FaceJPanel faceJPanel = (FaceJPanel)list.get(uid);
//				faceJPanel.setOnline(true);
			}
		}
		
		//再排序
		Collection<FaceJPanel> faceJPanels = Config.list.values();
		
		List<FaceJPanel> list = new ArrayList<FaceJPanel>(faceJPanels);
		Collections.sort(list);
		
		this.removeAll();
		
		int i = 0;
		for(FaceJPanel faceJPanel:list){
			faceJPanel.setBounds(0, i++ * 50, 546, 59);
			this.add(faceJPanel);
		}
		
		this.setPreferredSize(new Dimension(0, 40*list.size()));
		this.updateUI();
		
 	}
	
	//private Hashtable<String, FaceJPanel> list = new Hashtable<>();
	
	public void gengxin(){
		//好友列表
		String haoyouliebiao = Config.haoyou_json_data;
		
		System.out.println(haoyouliebiao);
		
		JSONArray jsonArray = JSONArray.fromObject(haoyouliebiao); 
				
		if(Config.list.size()==0){//第一次加载列表
			for(int i=0;i<jsonArray.size();i++){
				JSONObject jsonObject = (JSONObject)jsonArray.get(i);
				
				Config.list.put(jsonObject.getString("uid"), new FaceJPanel(jsonObject.getString("image"), jsonObject.getString("netname"), jsonObject.getString("info"), jsonObject.getString("uid")));
			}
			
		}else{//已经有列表数据了
			
			for(int i=0;i<jsonArray.size();i++){
				JSONObject jsonObject = (JSONObject)jsonArray.get(i);
				String uid = jsonObject.getString("uid");
				
				FaceJPanel faceJPanel = (FaceJPanel)Config.list.get(uid);
				if(faceJPanel != null){//已经存在,更新以下就好了
					faceJPanel.setNetname(jsonObject.getString("netname"));
					faceJPanel.setInfo(jsonObject.getString("info"));
					faceJPanel.setImage(jsonObject.getString("image"));
				}else{//不存在
					Config.list.put(jsonObject.getString("uid"), new FaceJPanel(jsonObject.getString("image"), jsonObject.getString("netname"), jsonObject.getString("info"), jsonObject.getString("uid")));
				}
			}
		}
		haoyouOnline();
		
	}
}
