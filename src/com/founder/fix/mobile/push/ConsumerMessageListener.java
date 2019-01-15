package com.founder.fix.mobile.push;

import java.util.Arrays;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;

/**
 */
public class ConsumerMessageListener implements MessageListener {
	public void onMessage(Message message) {
		MapMessage mapMessage = (MapMessage) message;

		try {
			System.out.println("---------消息消费---------");
			String content;
			content = mapMessage.getString("description");
			Object assigneeList= mapMessage.getObject("assigneeList");
			System.out.println(content);
			System.out.println(assigneeList);
			JPushUtil jpush = new JPushUtil();
			
			List<String> regIds = Arrays.asList("140fe1da9ea811004f8");//设备唯一值
			String title = "推送测试Title";
//			content = "推送测试内容，这个内容可以写很多，然后看看极光推送的效果，^_^";		
			
			Builder pushPayBuilder = jpush.getNotifyPayBuilder(title, content);
			pushPayBuilder.setAudience(Audience.registrationId(regIds));//设置通知对象为特定设备
			//pushPayBuilder.setAudience(Audience.all())//全部设备
			PushPayload pushPayload = pushPayBuilder.build();
			PushResult result = jpush.sendPushMsg(pushPayload);//
			System.out.println(result.isResultOK());
			
		} catch (JMSException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}