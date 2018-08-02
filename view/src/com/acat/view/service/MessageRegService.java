package com.acat.view.service;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.acat.view.util.Config;

import net.sf.json.JSONObject;

public class MessageRegService extends Thread {

	// 每10秒钟 向服务器注册心跳一下
	public void run() {

		System.out.println("##############################");
		
		String uid = JSONObject.fromObject(Config.geren_json_data).getString("uid");
		String jsonStr = "{\"type\":\"reg\",\"myUID\":\"" + uid + "\"}";
		byte[] bytes = jsonStr.getBytes();

		System.out.println("#^#^#^#^#^#^#^#^#^#^#^#^#^#^#^#");
		
		while (true) {
			try {
				
				System.out.println("******((((((((((((((())))))))))");
				DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length,
						InetAddress.getByName(Config.IP), 4003);
				
				System.out.println("mmmmmmmmmmmmmmmmm");
				
				// 将更新消息发送给服务器
				client.send(datagramPacket);
				
				System.out.println("nnnnnnnnnnnnnnnnnnnn");
				Thread.sleep(9999);
				
				System.out.println("jjjjjjjjjjjjjjjjjjjjjjjs");
			} catch (Exception e) {
			}
		}

	}

	private DatagramSocket client = null; 
	public MessageRegService(DatagramSocket client) {
		this.client = client;
		this.start();
	}
}
