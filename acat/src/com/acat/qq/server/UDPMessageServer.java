package com.acat.qq.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.json.JSONObject;

/**
 * UDP��Ϣ��ת������
 * 
 * @author ����
 *
 */
public class UDPMessageServer implements Runnable {

	private DatagramPacket packet = null;

	public UDPMessageServer(DatagramPacket packet) {
		this.packet = packet;
	}

	/// ҵ����
	public void run() {
		try {

			String jsonStr = new String(packet.getData(), 0, packet.getLength());
			JSONObject json = JSONObject.fromObject(jsonStr);

			// ���������
			if (json.getString("type").equals("reg")) {
				String MyUID = json.getString("myUID");

				// �������µ�IP�Ͷ˿ں�
				UserOnlineList.getUserOnlineList().updateOnlineUDP(MyUID, packet.getAddress().getHostAddress(),
						packet.getPort());
				System.out.println("��ע����Ϣ����:"+ jsonStr);

				// ������Ϣת�� // ������Ϣȷ��
				
				System.out.println("<><><>>>><><><><><><><><><>");
				
			} else if (json.getString("type").equals("msg") || json.getString("type").equals("qr")) {
				String MyUID = json.getString("myUID");
				String toUID = json.getString("toUID");
				// �������µ�IP�Ͷ˿ں�
				UserOnlineList.getUserOnlineList().updateOnlineUDP(MyUID, packet.getAddress().getHostAddress(),
						packet.getPort());

				System.out.println("{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{");
				
				// ���Ҫ��������Ϣ����
				UserInfo toUserinfo = UserOnlineList.getUserOnlineList().getOnlineUserInfo(toUID);

				System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}");
				
				// ׼��ת�����ͻ��˵���ݰ�
				DatagramPacket datagramPacket = new DatagramPacket(packet.getData(), packet.getLength(),
						InetAddress.getByName(toUserinfo.getUdpip()), toUserinfo.getUdpport());

				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				
				// ������ݰ�
				datagramSocket.send(datagramPacket);
				
				System.out.println("??????????????????????????????????????????");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static DatagramSocket datagramSocket = null;

	// ����������
	public static void openServer() throws Exception {
		datagramSocket = new DatagramSocket(4003);
		// �����̳߳�
		ExecutorService execute = Executors.newFixedThreadPool(1000);
		
		/**
		 * 保存的额线程数字是10
		 * 最大的线城数是15
		 * 有界队列大小为20
		 * 
		 * 饱和策略是CallerRuns策略
		 */
		
		while (true) {
			try {
				// �ȴ�ͻ��˵����
				byte[] b = new byte[1024 * 10];
				DatagramPacket datagramPacket = new DatagramPacket(b, b.length);
				datagramSocket.receive(datagramPacket);
				//////////////////////////////////////////

				// ���һ�����ֺ� ����ץ��һ���̴߳���
				execute.execute(new UDPMessageServer(datagramPacket));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
