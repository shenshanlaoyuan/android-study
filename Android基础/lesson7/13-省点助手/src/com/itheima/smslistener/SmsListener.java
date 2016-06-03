package com.itheima.smslistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

public class SmsListener extends BroadcastReceiver {

	
	//成功的监听到 用户 收到了 短消息 
	// 那么短消息的内容是什么呢 ?
	
	// 工业标准 -- pdu 
	@Override
	public void onReceive(Context context, Intent intent) {
		
		System.out.println("接收 到了 短信了 ");
		
		Object[] objs = (Object[]) intent.getExtras().get("pdus");
		
		for (Object obj : objs) {
			
			SmsMessage message = SmsMessage.createFromPdu((byte[]) obj);
			
			// message 就是一个短消息 实体数据 
			
			String messageBody = message.getMessageBody();
			//发送者  ...
			String sender = message.getOriginatingAddress();
			
			if("1387655".equals(sender)){
				abortBroadcast();
			}
			System.out.println("messageBody 短信内容 : " + messageBody);
			System.out.println("sender  发送人  : " + sender);
		}
		
	}
	
	
	
	/* 
	www.grepcode.com  ---搜寻 android.provider.Telephony.SMS_RECEIVED  --- 找到 Telephony 类 , 有如下的方法, 这是现成 的
	告诉我们如何去解析 获得一个短消息 
	
	
	 * public static final SmsMessage[] getMessagesFromIntent(
             Intent intent) {
         Object[] messages = (Object[]) intent.getSerializableExtra("pdus");
         byte[][] pduObjs = new byte[messages.length][];

         for (int i = 0; i < messages.length; i++) {
             pduObjs[i] = (byte[]) messages[i];
         }
         byte[][] pdus = new byte[pduObjs.length][];
         int pduCount = pdus.length;
         SmsMessage[] msgs = new SmsMessage[pduCount];
         for (int i = 0; i < pduCount; i++) {
             pdus[i] = pduObjs[i];
             msgs[i] = SmsMessage.createFromPdu(pdus[i]);
         }
         return msgs;
     }*/

}
