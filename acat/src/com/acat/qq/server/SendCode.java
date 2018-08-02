package com.acat.qq.server;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.HtmlEmail;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SendCode {
	public static boolean send(String phoneNumber,String code){
		try {
			TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23434458",
					"beedcab9cf9af63d1b7a502b140d4372");
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			req.setExtend("123456");
			req.setSmsType("normal");
			req.setSmsFreeSignName("ACAT");
			req.setSmsParamString("{\"code\":\"" + code + "\"}");
			req.setRecNum(phoneNumber);
			req.setSmsTemplateCode("SMS_13060260");
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			if (!(rsp.getBody().indexOf("\"success\":true") >= 0)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean sendEmail(String emailaddress, String code) {

		try {
//			HtmlEmail email = new HtmlEmail();
//			email.setHostName("smtp.sina.com");
//			email.setCharset("UTF-8");
//			email.addTo(emailaddress);// �ռ���ַ
//
//			email.setFrom("hcwswjf@sina.com", "ACATʵ����");
//
//			email.setAuthentication("hcwswjf@sina.com", "123456789wjf");
//
//			email.setSubject("ACATʵ������ܰ���ѣ�");
//			email.setMsg("��֤����:" + code);
//
//			email.send();
//			return true;
			
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.host", "smtp.sina.com");
			props.setProperty("mail.smtp.auth", "true");
			Session session = Session.getDefaultInstance(props);
			MimeMessage message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress("hcwswjf@sina.com"));
			message.addRecipients(Message.RecipientType.TO, emailaddress);
			message.setSubject("ACATʵ������ܰ���ѣ�");
			
			message.setText("��֤����:" + code);
			message.saveChanges();
			
			Transport ts = session.getTransport();
			ts.connect("hcwswjf@sina.com", "123456789wjf");
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
}
