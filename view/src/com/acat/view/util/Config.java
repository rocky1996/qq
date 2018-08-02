package com.acat.view.util;

import java.net.DatagramSocket;
import java.util.Hashtable;
import java.util.Vector;

import com.acat.view.window.FaceJPanel;
import com.acat.view.window.HaoyouListJPanel;
import com.acat.view.window.LiaotianFrame;
import com.acat.view.window.Msg;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Config {
	//服务器地址
	public static final String IP = "127.0.0.1";
	
	//登录端口
	public static final int LOGIN_PORT = 4001;
	
	//验证注册端口
	public static final int REG_PORT = 4002;
	
	//用户名和密码的寄存
	public static String username;
	public static String password;
	
	
	//好友信息列表JSON格式的
	public static String haoyou_json_data = "";
	
	public static String geren_json_data = "";
	
	//好友信息列表非JSON格式的
	public static String haoyou_liebiao_data = "";
	
	public static String haoyou_online = "";
	
	public static HaoyouListJPanel haoyouListJPanel;
	
	//udp的发送和接收端
	public static DatagramSocket datagramSocket_client = null;
	
	/**
	 * 取出好友列表的的值
	 * @param haoyou_json_data
	 */
	public static void jiexi_haoyou_json_data(String haoyou_json_data){
		Config.haoyou_json_data = haoyou_json_data;
		
		JSONArray json = JSONArray.fromObject(haoyou_json_data);
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<json.size();i++){
			JSONObject jsonobj = (JSONObject)json.get(i);
			buffer.append(jsonobj.getString("uid"));
			buffer.append(",");
		}
		haoyou_liebiao_data = buffer.toString();
	} 
	
	//聊天的窗口登记
	public static Hashtable<String, LiaotianFrame> liaotianTa = new Hashtable<String, LiaotianFrame>();
	
	//显示聊天窗口
	public static void showMessageJFrame(String uid,String netName,String info,String image,Vector<Msg> msgs){
		if(liaotianTa.get(uid)==null){
			LiaotianFrame liaotian = new LiaotianFrame(uid, netName, image, info, msgs);
			liaotianTa.put(uid, liaotian);
		}else{
			liaotianTa.get(uid).setAlwaysOnTop(true);
			liaotianTa.get(uid).setVisible(true);
		}
	}
	
	public static void closeLiaotianFrame(String uid){
		liaotianTa.remove(uid);
	}
	
	//好友列表对象
	public static Hashtable<String, FaceJPanel> list = new Hashtable<>();
}
