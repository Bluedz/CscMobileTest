package com.founder.fix.mobile.push;


import java.util.Arrays;
import java.util.List;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class JPushUtil {

	// 极光推送授权号
	private static String masterSecret = "20338eba34f9a39b9206d63f";
	private static String appKey = "fc9097f14e508a317c8f455c";

	public static void main(String[] args) {
		List<String> regIds = Arrays.asList("140fe1da9ea811004f8");//设备唯一值
		String title = "推送测试Title", content = "推送测试内容，这个内容可以写很多，然后看看极光推送的效果，^_^";		
		
		Builder pushPayBuilder = getNotifyPayBuilder(title, content);
		pushPayBuilder.setAudience(Audience.registrationId(regIds));//设置通知对象为特定设备
		//pushPayBuilder.setAudience(Audience.all())//全部设备
		PushPayload pushPayload = pushPayBuilder.build();
		PushResult result = sendPushMsg(pushPayload);//
		System.out.println(result);
	}

	/**
	 * 发送通知
	 * @param pushPayload 消息对象
	 * @return
	 */
	public static PushResult sendPushMsg(PushPayload pushPayload) {
		PushClient client = new PushClient(masterSecret, appKey);
		PushResult result = null;
		try {
			result = client.sendPush(pushPayload);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
			System.out.println("HTTP Status: " + e.getStatus());
			System.out.println("Error Code: " + e.getErrorCode());
			System.out.println("Error Message: " + e.getErrorMessage());
		}
		return result;
	}

	/**
	 * 推送通知对象<br>
	 * 在通知栏显示信息内容
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 * @return
	 */
	public static Builder getNotifyPayBuilder(String title, String content) {
		return PushPayload.newBuilder().setPlatform(Platform.all())// 所有的平台
				.setNotification(Notification.android(content, title, null));// 通知栏，android才有title，其他都只有content
	}
	
	/**
	 * 推送消息对象 <br>
	 * 通知栏不显示收到的信息内容
	 * @param content 内容
	 * @return
	 */
	public static Builder getMsgPushPayBuilder(String content){
		return PushPayload.newBuilder().setPlatform(Platform.all())//所有的平台
				.setAudience(Audience.all())//所有设备
				.setMessage(Message.content(content));//消息内容
				
	}
}