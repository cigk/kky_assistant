# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Android\adt\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}


# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\adt\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keepattributes InnerClasses
-keep class **.R$* {
    <fields>;
}


-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-ignorewarnings

-keepattributes SourceFile,LineNumberTable
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}



## amapLocation
-keep   class com.amap.api.location.**{*;}
-keep   class com.aps.**{*;}
-dontwarn com.amap.api.**
-dontwarn com.aps.**
## amapLocation end

## getui
-dontwarn com.igexin.**
-keep class com.igexin.**{*;}
## getui  end

## umeng
-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}
-keep public class com.kuaikuaiyu.user.R$*{
public static final int *;
}
-keep public class com.umeng.fb.ui.ThreadView {
}
-keep public class * extends com.umeng.**
-keep class com.umeng.** { *; }
## umeng end

## butterknife begin
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
## butterknife end

## domain
-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.kuaikuaiyu.merchant.domain.**{ *; }
##

## eventbus begin
-keepclassmembers class ** {
    public void onEvent*(**);
}
## eventbus end

## APM begin
-dontwarn org.apache.commons.**
-keep class org.apache.http.impl.client.**
-dontwarn org.apache.commons.**
-keep class com.blueware.** { *; }
-dontwarn com.blueware.**
-keepattributes Exceptions, Signature, InnerClasses
## APM end

#FMAgent同盾反欺诈 begin
-dontwarn android.os.**
-dontwarn com.android.internal.**
-keepclasseswithmembers class cn.fraudmetrix.android.**
#FMAgent同盾反欺诈 end

## webJsInterface begin
-keepclassmembers class com.kuaikuaiyu.merchant.ui.view.webview.AndroidJavaScript {
   public *;
}
## webJsInterface  end

##友盟分享 begin
-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**


-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep public interface com.facebook.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**

-keep public class com.umeng.socialize.* {*;}
-keep public class javax.**
-keep public class android.webkit.**

-keep class com.facebook.**
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**

-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}

-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}

-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}

-keep public class [your_pkg].R$*{
    public static final int *;
}
##友盟分享 end

## 美洽  begin
-keepattributes InnerClasses
-keep class **.R$* {
    <fields>;
}
## 美洽  end

## Retrofit  begin
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
## Retrofit  end

## okhttp  begin
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }
## okhttp  end

## rxjava  begin
-dontwarn rx.**
-keep class rx.** { *; }
## rxjava  end

## dagger  begin
-dontwarn dagger.**
-keep class dagger.** { *; }
## rxjava  end

-dontnote **
