package com.acat.qq.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 * �û��б�
 * @author wujinfan
 *
 */
public class UserOnlineList {
	/**
	 * ʹ�õ���ģʽ
	 */
	private UserOnlineList(){}
	
	private static UserOnlineList userOnlineList = new UserOnlineList();
	
	public static UserOnlineList getUserOnlineList(){
		return userOnlineList;
	}
	
	//���������ߵ��˻��Ǽ��ڼ�����
	/**
	 * String ���û��ı��
	 */
	private HashMap<String, UserInfo> hashMap = new HashMap<String, UserInfo>();
	
	//ע�������û�
	public void regOnline(String uid,Socket socket,String email,String phoneNumber){
		
		//�ж������Ŀͻ����Ƿ��¼һ���Ķ��û�����ǿ������
		UserInfo userInfo = hashMap.get(uid);
		if(userInfo != null){
			try {
				try{
					userInfo.getSocket().getOutputStream().write(4);
				}catch(Exception e){
					
				}
				userInfo.getSocket().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//������ߣ�ֱ��ǿ�йر�
		}
		
		userInfo = new UserInfo();
		userInfo.setUid(uid);
		userInfo.setEmail(email);
		userInfo.setPhone(phoneNumber);
		userInfo.setSocket(socket);
		hashMap.put(uid, userInfo);//�ȼ�����
	}
	
	/**
	 * 
	 * ���¿ͻ��˵�udp��Ϣ
	 * 
	 * @param uid�û����
	 * @param ip�û�udp ip��ַ
	 * @param port�˿�
	 */
	public void updateOnlineUDP(String uid,String ip,int port){
		//UserInfo userInfo = new UserInfo();
		UserInfo userInfo = hashMap.get(uid);
		userInfo.setUdpip(ip);
		userInfo.setUdpport(port);
	}
	
	//�ж��û��Ƿ����ߣ�true�����ߣ�false�ǲ�����
	public boolean isUserOnline(String uid){
		return hashMap.containsKey(uid);
	}
	
	/**
	 * ��������û���Ϣ
	 * @param uid
	 * @return
	 */
	public UserInfo getOnlineUserInfo(String uid){
		return hashMap.get(uid);
	}
	
	/**
	 * �û�����
	 * @param uid
	 */
	public void logout(String uid){
		hashMap.remove(uid);
	}
	
	/**
	 * ��ȡ���е�������Ϣ
	 * @return
	 */
	public Set<String> getUserInfos(){
		return hashMap.keySet();
	}
}
