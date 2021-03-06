package com.jisucloud.clawler.regagent.service.impl.education;


import com.jisucloud.clawler.regagent.interfaces.PapaSpider;
import com.jisucloud.clawler.regagent.interfaces.PapaSpiderConfig;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

import java.util.HashMap;
import java.util.Map;



@Slf4j
@PapaSpiderConfig(
		home = "zikao365.com", 
		message = "自考365,自考网站,自考培训机构,连续多年获得全国十佳网络教育机构称号,常年提供自考专业辅导和自考本科辅导,开设有自考辅导班, 专家名师授课,提供24小时在线答疑。", 
		platform = "zikao365", 
		platformName = "自考365", 
		tags = { "自考","学习","教育" }, 
		testTelephones = { "15010645316", "18212345678" })
public class ZiKao365Spider extends PapaSpider {

	

	public boolean checkTelephone(String account) {
		try {
			String url = "http://member.zikao365.com/uc/api/ifMobileBound";
			FormBody formBody = new FormBody
	                .Builder()
	                .add("phone", account)
	                .add("time", "0.5205300494551759")
	                .build();
			Request request = new Request.Builder().url(url)
					.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:56.0) Gecko/20100101 Firefox/56.0")
					.addHeader("Host", "member.zikao365.com")
					.addHeader("Referer", "http://member.zikao365.com/uc/member/register.shtm?gotoUrl=&type=1")
					.post(formBody)
					.build();
			Response response = okHttpClient.newCall(request).execute();
			if (response.body().string().contains("user exist")) {
				return true;
			}
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
