package com.acat.qq.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 * 用户列表
 * @author wujinfan
 *
 */
public class UserOnlineList {
	/**
	 * 使用单例模式
	 */
	private UserOnlineList(){}
	
	private static UserOnlineList userOnlineList = new UserOnlineList();
	
	public static UserOnlineList getUserOnlineList(){
		return userOnlineList;
	}
	
	//将所有在线的账户登记在集合中
	/**
	 * String 是用户的编号
	 */
	private HashMap<String, UserInfo> hashMap = new HashMap<String, UserInfo>();
	
	//注册在线用户
	public void regOnline(String uid,Socket socket,String email,String phoneNumber){
		
		//判断其他的客户端是否登录一样的额用户名，强行下线
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
			}//如果在线，直接强行关闭
		}
		
		userInfo = new UserInfo();
		userInfo.setUid(uid);
		userInfo.setEmail(email);
		userInfo.setPhone(phoneNumber);
		userInfo.setSocket(socket);
		hashMap.put(uid, userInfo);//等级在线
	}
	
	/**
	 * 
	 * 更新客户端的udp信息
	 * 
	 * @param uid用户编号
	 * @param ip用户udp ip地址
	 * @param port端口
	 */
	public void updateOnlineUDP(String uid,String ip,int port){
		//UserInfo userInfo = new UserInfo();
		UserInfo userInfo = hashMap.get(uid);
		userInfo.setUdpip(ip);
		userInfo.setUdpport(port);
	}
	
	//判断用户是否在线，true是在线，false是不在线
	public boolean isUserOnline(String uid){
		return hashMap.containsKey(uid);
	}
	
	/**
	 * 获得在线用户信息
	 * @param uid
	 * @return
	 */
	public UserInfo getOnlineUserInfo(String uid){
		return hashMap.get(uid);
	}
	
	/**
	 * 用户下线
	 * @param uid
	 */
	public void logout(String uid){
		hashMap.remove(uid);
	}
	
	/**
	 * 获取所有的在线信息
	 * @return
	 */
	public Set<String> getUserInfos(){
		return hashMap.keySet();
	}
}
