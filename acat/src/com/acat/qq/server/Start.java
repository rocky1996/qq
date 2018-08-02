package com.acat.qq.server;

public class Start {
	public static void main(String[] args) {
		new Thread(){
			public void run(){
				try{
					System.out.println("--------登录服务器---------");
					LoginServer.openServer();
				}catch(Exception e){
					e.printStackTrace();
				}
			};
		}.start();
		
		new Thread(){
			public void run(){
				try{
					System.out.println("---------注册服务器---------");
					RegServer.openServer();
				}catch(Exception e){
					e.printStackTrace();
				}
			};
		}.start();
		
		new Thread(){
			public void run(){
				try{
					System.out.println("---------消息中转服务器---------");
					UDPMessageServer.openServer();
				}catch(Exception e){
					e.printStackTrace();
				}
			};
		}.start();
	}
}
