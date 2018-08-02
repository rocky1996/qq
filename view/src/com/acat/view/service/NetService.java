                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           package com.acat.view.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.acat.view.util.Config;

import net.sf.json.JSONObject;

/**
 * ͨѶ���� ���������������״̬ 1.���º�������״̬ 5����һ������ 2.��¼��֤ 3.�˳��˻�
 * 
 * @author 
 *
 */
public class NetService implements Runnable {

	private NetService() {
	}

	// ����
	private static NetService netService = new NetService();

	public static NetService getNetService() {
		return netService;
	}

	// ����׼������������泤ʱ��ͨѶ
	public void run() {

		try {
			
			byte[] bytes = new byte[1024 * 10];
			int len=0;
						
			
			//�������ߵ�ʵʱ����
			
			while (run) {
				output.write("U0002".getBytes());
				output.flush();
				input.read();
				output.write(Config.haoyou_liebiao_data.getBytes());
				output.flush();
				len = input.read(bytes);
				String online = new String(bytes, 0, len);
				System.out.println("�����˻�:" + online);
				
				
				
//				Config.haoyou_online = online;
				
				try{
					if(!Config.haoyou_online.equals(online)){
						Config.haoyou_online = online;
						Config.haoyouListJPanel.haoyouOnline();
					}
					
				}catch(Exception e){
					
				}
				
				Config.haoyou_online = online;
				
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
				}
			}
		} catch (Exception e) {
			run = false;
		}

	}

	private Socket socket = null;
	private InputStream input = null;
	private OutputStream output = null;
	private Thread thread = null;
	private boolean run = false;

	public JSONObject login() throws UnknownHostException, IOException {
		socket = new Socket(Config.IP, Config.LOGIN_PORT);
		input = socket.getInputStream();
		output = socket.getOutputStream();
		String json_str = "{\"username\":\"" + Config.username + "\",\"password\":\"" + Config.password + "\"}";

		// ��ʼ����񴫵���Ϣ
		output.write(json_str.getBytes());
		output.flush();

		// �ȴ���������ִ��Ϣ
		byte[] bytes = new byte[1024];
		int len = input.read(bytes);

		json_str = new String(bytes, 0, len);
		JSONObject json = JSONObject.fromObject(json_str);

		// �����0 ���ǵ�¼�ɹ�!
		if (json.getInt("state") == 0) {
			// �����������������ӷ���

			if (thread != null) {
				// ѯ���߳��Ƿ񻹻���
				if (thread.getState() == Thread.State.RUNNABLE) {
					run = false;// ��ֹ�߳�����
					try {
						thread.stop();
					} catch (Exception e) {
					}
				}
			}

			
			//��¼֮ǰ�ȰѺ�����Ϣ��ã��ɴ����߳̿���֮ǰ���
			
			System.out.println("1");
			
			////////////////////////////////////////////������Ϣ���
			System.out.println("+++++++++++++++++++++++++");
			output.write("U0001".getBytes());
			output.flush();
			System.out.println("**************************");
			bytes = new byte[1024 * 10];
			
			System.out.println("2");
			
			len = input.read(bytes);
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			String jsonstr = new String(bytes,0,len);
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$");
			////////////////////////////////////////////
			//Config.haoyou_json_data = jsonstr;
			
			System.out.println("--------------------");
			
			//System.out.println("��������:" + Config.haoyou_json_data);
			
			// ���������б�
			Config.jiexi_haoyou_json_data(jsonstr);
			System.out.println("��������:" + Config.haoyou_json_data);
			
			//////////////////////////////////////////// �������ϻ��
			output.write("U0003".getBytes());
			output.flush();
			len = input.read(bytes);
			Config.geren_json_data = new String(bytes, 0, len);
			System.out.println("��������:" + Config.geren_json_data);
			/////////////////////////////////////////////
			
			
			///////////////����UDP������/////////////////////
			
			
			Config.datagramSocket_client = new DatagramSocket();
			
			System.out.println("))))))))))))))))))))))))))))))))");
			
			//����������
			new MessageRegService(Config.datagramSocket_client);
			
			System.out.println("((((((((((((((((((((((((((((((((((");
			
			//������Ϣ����
			new MessageService(Config.datagramSocket_client);
			
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			///////////////////////////////////////////
			
			////////////////�������ϵĸ���//////////
			
			
			/////////////////////////////////////////
			
			
			// ���¿��߳������������ͨѶ
			thread = new Thread(this);
			run = true;
			thread.start();
			System.out.println("��¼�ɹ��������߳�");
		}

		return json;
	}

}
