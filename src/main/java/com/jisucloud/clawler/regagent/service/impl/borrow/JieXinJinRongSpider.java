package com.jisucloud.clawler.regagent.service.impl.borrow;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;


import com.jisucloud.clawler.regagent.interfaces.PapaSpider;
import com.jisucloud.clawler.regagent.interfaces.PapaSpiderConfig;

import java.util.Map;



@Slf4j
@PapaSpiderConfig(
		home = "homecreditcfc.com", 
		message = "捷信消费金融有限公司于2010年11月10日成立。作为银监会批准设立的首批四家试点消费金融公司之一，捷信消费金融有限公司成为中国首家且唯一一家外商独资的消费金融公司。", 
		platform = "homecreditcfc", 
		platformName = "捷信金融", 
		tags = { "P2P", "消费分期" , "借贷" }, 
		testTelephones = { "15985268904", "18212345678" })
public class JieXinJinRongSpider extends PapaSpider {

	
	
	public boolean checkTelephone(String account) {
		try {
			String url = "https://lm.homecredit.cn/15.0/login-module/v2/getUserDeviceInfoByPhoneDeviceId";
			FormBody formBody = new FormBody
	                .Builder()
	                .add("phoneNumber", account)
	                .add("deviceId", "867368032072541")
	                .build();
			Request request = new Request.Builder().url(url)
					.addHeader("User-Agent", "okhttp/3.9.1")
					.addHeader("Authorization", "Basic ZjN3UlBXS09SbTRXSERvMzpPWGZjYldGRXpPUTFuMHlv")
					.addHeader("appVersion", "26.2.0")
					.addHeader("deviceId", "867368032072541")
					.addHeader("platform", "Android")
					.addHeader("Host", "lm.homecredit.cn")
					.post(formBody)
					.build();
			Response response = okHttpClient.newCall(request).execute();
			JSONObject result = JSON.parseObject(response.body().string());
			return result.getJSONObject("value").getBooleanValue("userRegistered");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkEmail(String account) {
		return false;
	}

	@Override
	public Map<String, String> getFields() {
		return null;
	}

}
