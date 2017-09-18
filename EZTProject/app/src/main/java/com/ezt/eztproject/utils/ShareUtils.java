package com.ezt.eztproject.utils;

import android.app.Activity;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;

public class ShareUtils {

	public void share(final Activity activity, String title, String ImageUrl,
					  final String text, final String targetUrl,
					  PlatformActionListener callBack) {
		OnekeyShare oks = new OnekeyShare();
		oks.setSilent(false);
		// oks.setPlatform(platformToShare); //指定平台
		// ShareSDK快捷分享提供两个界面第一个是九宫格 CLASSIC 第二个是SKYBLUE
		oks.setTheme(OnekeyShareTheme.CLASSIC);
		// 令编辑页面显示为Dialog模式
		oks.setDialogMode();
		// 在自动授权时可以禁用SSO方式
		oks.disableSSOWhenAuthorize();
		oks.setTitle(title);
		oks.setTitleUrl(targetUrl);
		oks.setText(text);
		oks.setImageUrl(ImageUrl);
		oks.setUrl(targetUrl); // 微信不绕过审核分享链接
		oks.setVenueName("ShareSDK");
		oks.setVenueDescription("This is a beautiful place!");
		oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {

			@Override
			public void onShare(Platform platform,
								cn.sharesdk.framework.Platform.ShareParams paramsToShare) {
				if ("SinaWeibo".equals(platform.getName())) {
					paramsToShare.setUrl(null);
					paramsToShare.setText(text + targetUrl);
				}
			}
		});
		oks.setCallback(callBack);
		// 将快捷分享的操作结果将通过OneKeyShareCallback回调
		// oks.setCallback(new OneKeyShareCallback());
		// 去自定义不同平台的字段内容
		// oks.setShareContentCustomizeCallback(new
		// ShareContentCustomizeDemo());
		// // 在九宫格设置自定义的图标
		// Bitmap logo = BitmapFactory.decodeResource(context.getResources(),
		// R.drawable.ic_launcher);
		// String label = "ShareSDK";
		// OnClickListener listener = new OnClickListener() {
		// public void onClick(View v) {
		//
		// }
		// };
		// 为EditPage设置一个背景的View
		// oks.setEditPageBackground(getPage());
		// 隐藏九宫格中的新浪微博
		// oks.addHiddenPlatform(SinaWeibo.NAME);

		// 启动分享
		oks.show(activity);
	}

	public void shareWithImagePath(final Activity activity, String title,
								   String imgPath, final String text, final String targetUrl,
								   PlatformActionListener callBack) {
		OnekeyShare oks = new OnekeyShare();

		oks.setSilent(false);
		// oks.setPlatform(platformToShare); //指定平台
		// ShareSDK快捷分享提供两个界面第一个是九宫格 CLASSIC 第二个是SKYBLUE
		oks.setTheme(OnekeyShareTheme.CLASSIC);
		// 令编辑页面显示为Dialog模式
		oks.setDialogMode();
		// 在自动授权时可以禁用SSO方式
		oks.disableSSOWhenAuthorize();
		oks.setTitle(title);
		oks.setTitleUrl(targetUrl);
		oks.setText(text);
		oks.setImagePath(imgPath);
		oks.setUrl(targetUrl); // 微信不绕过审核分享链接
		oks.setVenueName("ShareSDK");
		oks.setVenueDescription("This is a beautiful place!");
		oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {

			@Override
			public void onShare(Platform platform,
								cn.sharesdk.framework.Platform.ShareParams paramsToShare) {
				if ("SinaWeibo".equals(platform.getName())) {
					paramsToShare.setUrl(null);
					paramsToShare.setText(text + targetUrl);
				}
			}
		});
		oks.setCallback(callBack);
		oks.show(activity);
	}
}
