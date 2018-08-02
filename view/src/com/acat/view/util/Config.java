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
	//��������ַ
	public static final String IP = "127.0.0.1";
	
	//��¼�˿�
	public static final int LOGIN_PORT = 4001;
	
	//��֤ע��˿�
	public static final int REG_PORT = 4002;
	
	//�û���������ļĴ�
	public static String username;
	public static String password;
	
	
	//������Ϣ�б�JSON��ʽ��
	public static String haoyou_json_data = "";
	
	public static String geren_json_data = "";
	
	//������Ϣ�б��JSON��ʽ��
	public static String haoyou_liebiao_data = "";
	
	public static String haoyou_online = "";
	
	public static HaoyouListJPanel haoyouListJPanel;
	
	//udp�ķ��ͺͽ��ն�
	public static DatagramSocket datagramSocket_client = null;
	
	/**
	 * ȡ�������б�ĵ�ֵ
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
	
	//����Ĵ��ڵǼ�
	public static Hashtable<String, LiaotianFrame> liaotianTa = new Hashtable<String, LiaotianFrame>();
	
	//��ʾ���촰��
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
	
	//�����б����
	public static Hashtable<String, FaceJPanel> list = new Hashtable<>();
}
