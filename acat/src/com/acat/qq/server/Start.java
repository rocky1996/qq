package com.acat.qq.server;

public class Start {
	public static void main(String[] args) {
		new Thread(){
			public void run(){
				try{
					System.out.println("--------��¼������---------");
					LoginServer.openServer();
				}catch(Exception e){
					e.printStackTrace();
				}
			};
		}.start();
		
		new Thread(){
			public void run(){
				try{
					System.out.println("---------ע�������---------");
					RegServer.openServer();
				}catch(Exception e){
					e.printStackTrace();
				}
			};
		}.start();
		
		new Thread(){
			public void run(){
				try{
					System.out.println("---------��Ϣ��ת������---------");
					UDPMessageServer.openServer();
				}catch(Exception e){
					e.printStackTrace();
				}
			};
		}.start();
	}
}
