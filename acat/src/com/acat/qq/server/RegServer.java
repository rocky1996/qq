package com.acat.qq.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.acat.qq.db.UserService;
import com.acat.qq.db.UsernameException;

import net.sf.json.JSONObject;

/**
 * ע�������
 * 1.ע������
 * 2��֤������
 * @author wujinfan
 *
 */
public class RegServer implements Runnable{
	private Socket socket;
	
	public RegServer(Socket socket){
		this.socket=socket;
	}
	
	//дһ����֤���
	private static HashMap<String, String> hashMap_code = new HashMap<String,String>();
	
	public void run(){
		InputStream input = null;
		OutputStream output = null;
		try{
			input = socket.getInputStream();
			output = socket.getOutputStream();
			
			//�ȴ�ͻ��˷�����Ϣ����
			byte[] bytes = new byte[1024];
			int len = input.read(bytes);
			String str = new String(bytes,0,len);
			JSONObject json = JSONObject.fromObject(str);
			String type = json.getString("type");
//			String username = json.getString("username");
//			
//			StringBuffer code = new StringBuffer();
//			Random random = new Random();
//			for(int i=0;i<6;i++){
//				code.append(random.nextInt(10));
//			}
			
			if(type.equals("code")){//��֤�������
				
				String username = json.getString("username");
				
				StringBuffer code = new StringBuffer();
				Random random = new Random();
				for(int i=0;i<6;i++){
					code.append(random.nextInt(10));
				}
				
				if(type.trim().length()==11){
					try{
						Long.parseLong(type);
						
						hashMap_code.put(username, code.toString());
						SendCode.send(username, code.toString());	
						
						output.write("{\"state\":0,\"msg\":\"��֤�뷢�ͳɹ�!\"}".getBytes());
						output.flush();
					}catch(Exception e){
						output.write("{\"state\":1,\"msg\":\"��֤�뷢��ʧ��!\"}".getBytes());
						output.flush();
					}
					
				}else{
					if(username.indexOf("@")>=0){
						
						hashMap_code.put(username, code.toString());
						SendCode.sendEmail(username, code.toString());	
						
						output.write("{\"state\":0,\"msg\":\"��֤�뷢�ͳɹ�!\"}".getBytes());
						output.flush();
						
						
					}else{
						output.write("{\"state\":1,\"msg\":\"��֤�뷢��ʧ��!\"}".getBytes());
						output.flush();
					}
				}
				
				
			}else if(type.equals("reg")){//ע�������
				String username = json.getString("username");
				String password = json.getString("password");
				String code = json.getString("code");
				String code1 = hashMap_code.get(username);
				
				System.out.println("**********----------$$$$$$$$$");
				
				if(code1 != null){
					hashMap_code.remove(username);//һ��Ҫɾ����֤�룬
				}
				
				System.out.println("@@@@@@@@@##############^^^^^^^^");
				
				if(code1.equals(code)){//��֤���Ƿ���ȷ
					
					try{
						System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
						
						//��������
						new UserService().regUser(username, password);
						System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					}catch(UsernameException e){
						output.write("{\"state\":1,\"msg\":\"�û����Ѿ�����!\"}".getBytes());
						output.flush();
						return;
					}catch(SQLException e){
						output.write("{\"state\":3,\"msg\":\"δ֪����!\"}".getBytes());
						output.flush();
						return;
					}
					output.write("{\"state\":0,\"msg\":\"ע��ɹ������Ե�¼��\"}".getBytes());
					output.flush();
					
				}else{
					output.write("{\"state\":2,\"msg\":\"��֤�뷢��ʧ��,�����»��!\"}".getBytes());
					output.flush();
				}
	
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				input.close();
				output.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public static void openServer() throws IOException{
		ExecutorService service = Executors.newFixedThreadPool(1000);
		ServerSocket server = new ServerSocket(4002);
		
		while(true){
			Socket socket = server.accept();
			
			service.execute(new RegServer(socket));
		}
	}
}
