package com.kuaikuaiyu.assistant.ui.common;

import android.content.Context;
import android.webkit.JavascriptInterface;

public class JavaScriptInterface {

    private Context mContext;

//    private final UMSocialService mController = UMServiceFactory.getUMSocialService(AppConfig.DESCRIPTOR);

    public JavaScriptInterface(Context context) {
        this.mContext = context;
    }

    /**
     * 名校贷下载接口
     */
//    @JavascriptInterface
//    public void downloadApp() {
//        Intent intent = new Intent();
//        intent.setAction("android.intent.action.VIEW");
//        Uri content_url = Uri
//                .parse("https://m.nonobank.com/appdown/downapp.html?type=mxd");
//        intent.setData(content_url);
//        mContext.startActivity(intent);
//    }

    /**
     * 跳转到红包页面接口
     */
//    @JavascriptInterface
//    public void jumpToRedPacketList() {
//        Intent intent = new Intent(mContext, MyRedPacketActivity.class);
//        mContext.startActivity(intent);
////        ((Activity) mContext).finish();
//    }

    /**
     * 邀请码分享接口
     *
     * @param title       分享标题
     * @param description 分享的描述
     * @param imageUrl    分享图片地址
     * @param linkUrl     分享链接
     */
    @JavascriptInterface
    public void shareAction(String title, String description, String imageUrl, String linkUrl) {
//        mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
//                SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA);
//        mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
//                SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE);
//        setShareContent(title, description, imageUrl, linkUrl);
//        mController.openShare((Activity) mContext, false);
    }

    @JavascriptInterface
    public void closeShopNotice() {//账户状态
//        ((ShopNoticeForceAgreeActivity) mContext).getAccountInfo();
    }

    /**
     * 根据不同的平台设置不同的分享内容</br>
     */
    private void setShareContent(String title, String description, String imageUrl, String linkUrl) {

//        // 配置新浪微博SSO
//        mController.getConfig().setSsoHandler(new SinaSsoHandler());
//        addQqPlatform();
//        addWxPlatform();
//
//        UMImage image = new UMImage(mContext, imageUrl);
//        image.setTargetUrl(imageUrl);
//        UMImage qqShareImage = new UMImage(mContext,
//                BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher));
//
//        // 设置微信分享的内容
//        WeiXinShareContent weixinContent = new WeiXinShareContent();
//        weixinContent.setShareContent(description);
//        weixinContent.setTitle(title);
//        weixinContent.setTargetUrl(linkUrl);
//        weixinContent.setShareMedia(image);
//        mController.setShareMedia(weixinContent);
//
//        // 设置朋友圈分享的内容
//        CircleShareContent circleMedia = new CircleShareContent();
//        circleMedia.setTitle(title);
//        circleMedia.setShareContent(description);
//        circleMedia.setShareMedia(image);
//        circleMedia.setTargetUrl(linkUrl);
//        mController.setShareMedia(circleMedia);
//
//        // 设置QQ空间分享内容
//        QZoneShareContent qzone = new QZoneShareContent();
//        qzone.setTitle(title);
//        qzone.setShareContent(description);
//        qzone.setShareMedia(qqShareImage);
////        qzone.setShareImage(image);
//        qzone.setTargetUrl(linkUrl);
//        mController.setShareMedia(qzone);
//
//        // 设置QQ分享内容
//        QQShareContent qqShareContent = new QQShareContent();
//        qqShareContent.setTitle(title);
//        qqShareContent.setShareContent(description);
//        qqShareContent.setShareMedia(image);
////        qqShareContent.setShareMedia(qqShareImage);
//        qqShareContent.setTargetUrl(linkUrl);
//        mController.setShareMedia(qqShareContent);
//
//        // 设置新浪微博分享内容
//        SinaShareContent sinaContent = new SinaShareContent();
//        sinaContent.setTitle(title);
//        sinaContent.setShareContent(description);
//        sinaContent.setShareImage(image);
//        sinaContent.setTargetUrl(linkUrl);
//        mController.setShareMedia(sinaContent);
    }

    /**
     * 添加微信平台
     */
    private void addWxPlatform() {
        String appId = "wxeaba9e7298410b12";
        String appSecret = "e7b035cff7a24515c5e7211f34ad0f86";

//        // 添加微信平台
//        UMWXHandler wxHandler = new UMWXHandler((WebViewActivity) mContext, appId, appSecret);
//        wxHandler.addToSocialSDK();
//
//        // 支持微信朋友圈
//        UMWXHandler wxCircleHandler = new UMWXHandler((WebViewActivity) mContext, appId, appSecret);
//        wxCircleHandler.setToCircle(true);
//        wxCircleHandler.addToSocialSDK();
    }

    /**
     * 添加QQ平台
     */
    private void addQqPlatform() {
        String appId = "1104746967";
        String appKey = "Vpn6zNFw36DyEy1S";

//        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler((WebViewActivity) mContext, appId, appKey);
//        qZoneSsoHandler.addToSocialSDK();
//
//        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler((WebViewActivity) mContext, appId, appKey);
//        qqSsoHandler.addToSocialSDK();
    }
}
