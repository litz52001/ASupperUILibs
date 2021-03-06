package com.alibs.Tools;

public final class Log {
	
	/**
	 * 调试开关
	 */
	public static boolean mDebug = false;

	public static String makeTag(Class cls){
		return "Push_"+cls.getSimpleName();
	}
	
	public static void init(final boolean debug) {
		mDebug = debug;
	}
	
	public static void e(final String tag,final String msg){
		if (mDebug) {
			android.util.Log.e(tag, msg);
		}
	}
	
	public static void e(final String tag,final String msg,final Throwable tr){
		if (mDebug) {
			android.util.Log.e(tag, msg,tr);
		}
	}
	
	public static void w(final String tag,final String msg){
		if (mDebug) {
			android.util.Log.w(tag, msg);
		}
	}
	
	public static void w(final String tag,final String msg,final Throwable tr){
		if (mDebug) {
			android.util.Log.w(tag, msg,tr);
		}
	}
	
	public static void d(final String tag,final String msg){
		if (mDebug) {
			android.util.Log.d(tag, msg);
		}
	}
	
	public static void d(final String tag,final String msg,final Throwable tr){
		if (mDebug) {
			android.util.Log.d(tag, msg,tr);
		}
	}
	
	public static void v(final String tag,final String msg){
		if (mDebug) {
			android.util.Log.v(tag, msg);
		}
	}
	
	public static void v(final String tag,final String msg,final Throwable tr){
		if (mDebug) {
			android.util.Log.v(tag, msg,tr);
		}
	}
	
	public static void i(final String tag,final String msg){
		if (mDebug) {
			android.util.Log.i(tag, msg);
		}
	}
	
	public static void i(final String tag,final String msg,final Throwable tr){
		if (mDebug) {
			android.util.Log.i(tag, msg,tr);
		}
	}
}
