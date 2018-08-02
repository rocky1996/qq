package com.acat.view.service;

import java.util.HashMap;
import java.util.LinkedList;

import com.acat.view.util.Config;
import com.acat.view.window.FaceJPanel;
import com.acat.view.window.LiaotianFrame;
import com.acat.view.window.Msg;

import net.sf.json.JSONObject;

/**
 * ��Ϣ�أ������е���Ϣ������н��д洢
 * @author wujinfan
 *
 */
public class MessagePool {
	private MessagePool(){}
	
	private static MessagePool messagePool = new MessagePool();
	
	public static MessagePool getMessagePool(){
		return messagePool;
	} 
	
	private static HashMap<String, LinkedList<Msg>> hashMap = new HashMap<String, LinkedList<Msg>>();
	
	//�����Ǹ�˭����Ϣ����Ӧ�ô洢�ڳ���
	public void addMessage(String json){
		JSONObject jsonObject = JSONObject.fromObject(json);
		String toUID = jsonObject.getString("toUID");
		String myUID = jsonObject.getString("myUID");
		String msg = jsonObject.getString("msg");
	
		String type = jsonObject.getString("type");
		String code = jsonObject.getString("code");
		
		/**
		 * �ѽ��պõ���Ϣ��װ��Msg������
		 */
		Msg msgObj = new Msg();
		
		msgObj.setCode(code);
		msgObj.setMsg(msg);
		msgObj.setMyUID(myUID);
		msgObj.setToUID(toUID);
		msgObj.setType(type);
		
		try{
			LiaotianFrame frame = Config.liaotianTa.get(myUID);
			if(frame.isVisible()){
				frame.addMessage(msgObj);
			}else{
				throw new Exception();
			}
			
		}catch(Exception e){
			
			FaceJPanel faceJPanel = Config.list.get(myUID);
			faceJPanel.addMessage(msgObj);
			
			//����洢Msg����һ�ߺ��ڲ�ѯ
			LinkedList<Msg> list = hashMap.get(myUID);
			if(list==null){
				list = new LinkedList();
				list.add(msgObj);
				
			}
			
			list.add(msgObj);
			hashMap.put(myUID, list);
		}
	}
}
