package com.ezt.eztproject;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * 初始化
 */
public class MyApplication extends Application {

	private static final Object sLock = new Object();

	private static final String TAG = "MyApplication";

	private final List<Activity> activityList = new LinkedList<Activity>();

	private static MyApplication mInstance;

	public String imei = ""; // 用户手机唯一标识

	public static WeakHashMap<String, Object> dataHolder = new WeakHashMap<String, Object>();

	public static synchronized MyApplication getInstance() {
		return mInstance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		if (mInstance == null) {
			mInstance = this;
		}
	}

	/**
	 * 添加Activity到容器中
	 * 
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		if (activityList != null && !activityList.contains(activity)) // activityList.contains(activity)-->!activityList.contains(activity)
																		// 去掉return
																		// lxh
			activityList.add(activity);
	}

	/**
	 * 释放已经销毁的activity
	 * 
	 * @author lxh
	 * @param activity
	 */
	public void removeActivity(Activity activity) {
		if (activityList != null && activityList.contains(activity))
			activityList.remove(activity);
	}

	/**
	 * 遍历所有Activity并finish
	 */
	public void exit() {

		Log.i(TAG, "exit---");
		for (Activity activity : activityList) {
			if (activity != null) {
				if (!activity.isFinishing()) {
					activity.finish();
					// android.os.Process.killProcess(android.os.Process.myPid());
				}

				activity = null;
			}
			activityList.remove(activity);
		}
		android.os.Process.killProcess(android.os.Process.myPid());

	}

	private String getAppName(int pID) {
		String processName = null;
		ActivityManager am = (ActivityManager) this
				.getSystemService(ACTIVITY_SERVICE);
		List l = am.getRunningAppProcesses();
		Iterator i = l.iterator();
		PackageManager pm = this.getPackageManager();
		while (i.hasNext()) {
			ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i
					.next());
			try {
				if (info.pid == pID) {
					CharSequence c = pm.getApplicationLabel(pm
							.getApplicationInfo(info.processName,
									PackageManager.GET_META_DATA));
					// Log.d("Process", "Id: "+ info.pid +" ProcessName: "+
					// info.processName +"  Label: "+c.toString());
					// processName = c.toString();
					processName = info.processName;
					return processName;
				}
			} catch (Exception e) {
				// Log.d("Process", "Error>> :"+ e.toString());
			}
		}
		return processName;
	}
}
