package com.example.zhihudiary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.AvoidXfermode.Mode;
import android.renderscript.Sampler.Value;

import com.example.zhihudiary.activity.MainActivity;

public class SpTools {
	public static void putString(Context context,String key,String value){
		SharedPreferences sharedPreferences = context.getSharedPreferences(MyContants.CACHEJSON,Context.MODE_PRIVATE );
		sharedPreferences.edit().putString(key, value).commit();
	}
	
	public static String getString(Context context,String key,String defaultValue){
		SharedPreferences sharedPreferences = context.getSharedPreferences(MyContants.CACHEJSON,Context.MODE_PRIVATE );
		return sharedPreferences.getString(key, defaultValue);
	}
}
