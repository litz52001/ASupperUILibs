package com.alibs.App;


import android.app.Application;

public class CrashApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(getApplicationContext());
	}
}

