package com.example.zhihudiary.utils;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class DownloadUtils {
	public static void loadData(String url){
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String jsonDataString = responseInfo.result;
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO 自动生成的方法存根
				
			}
		});
	} 

}
