package com.kuaikuaiyu.assistant.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;

import com.kuaikuaiyu.assistant.BuildConfig;
import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import java.io.File;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:    Retrofit工具类
 */
public class NetUtil {

    private static OkHttpClient okHttpClient = createOkHttpClient();
    private static Retrofit serverRetrofit = createServerRetrofit();
    private static Retrofit passRetrofit = createPassRetrofit();

    /**
     * 创建OkHttp
     *
     * @return
     */
    private static OkHttpClient createOkHttpClient() {
//        SSLSocketFactory socketFactory = getSocketFactory();
        File cacheFile = new File(UIUtil.getContext().getCacheDir(), "okhttp_cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb缓存
        CacheControlInterceptor cacheControlInterceptor = new CacheControlInterceptor();
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(cacheControlInterceptor)
                .cache(cache)
                .followRedirects(true);
        if (BuildConfig.DEBUG_MODE) {
            HttpLoggingInterceptor bodyInterceptor = new HttpLoggingInterceptor();
            bodyInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            return builder.addInterceptor(bodyInterceptor).build();
        }
//        if (null != socketFactory) {
//            return builder.sslSocketFactory(socketFactory).addInterceptor(interceptor).build();
//        }
        return builder.build();
    }

    /**
     * 创建SSLSocketFactory
     *
     * @return
     */
    @Nullable
    private static SSLSocketFactory getSocketFactory() {
        try {
            // j2se要使用SUNX509
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("X509");
            // 使用p12格式的证书，也支持其他类型的，具体请看KeyStore的API
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            // InputStream keyInput = new FileInputStream(pKeyFile);
            keyStore.load(UIUtil.getResources().openRawResource(R.raw.keystore), "kuaikuaiyu888"
                    .toCharArray());

            keyManagerFactory.init(keyStore, "kuaikuaiyu888".toCharArray());

            KeyStore trustStore = KeyStore.getInstance("BKS");
            trustStore.load(UIUtil.getResources().openRawResource(R.raw.certstore), "kuaikuaiyu888"
                    .toCharArray());

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trustStore);

            SSLContext context = SSLContext.getInstance("TLS");
            // init的第一个参数是验证客户端证书，第二个参数验证CA证书，由于私签的CA，所有绕过证书安全认证
//            context.init(keyManagerFactory.getKeyManagers(), new TrustManager[]{new DefaultTrustManager()}, null);
            context.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
            return context.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建Server服务器对应的Retrofit
     *
     * @return
     */
    private static Retrofit createServerRetrofit() {
        return createRetrofit(AppConfig.PROTOCAL_HTTPS + BuildConfig.SERVER_URL);
    }


    /**
     * 创建Pass服务器对应的Retrofit
     *
     * @return
     */
    private static Retrofit createPassRetrofit() {
        return createRetrofit(AppConfig.PROTOCAL_HTTPS + BuildConfig.PASS_SERVRE);
    }

    /**
     * 创建Test服务器对应的Retrofit
     *
     * @return
     */
    private static Retrofit createTestRetrofit() {
        return createRetrofit("http://192.168.1.101/");
    }

    /**
     * 创建retrofit实例
     */
    private static Retrofit createRetrofit(String baseurl) {
        return new Retrofit.Builder().baseUrl(baseurl).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory
                        (RxJavaCallAdapterFactory.create()).build();
    }

    /**
     * 创建Server服务器指定的api接口的实现
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T create(Class<T> clazz) {
        return serverRetrofit.create(clazz);
    }

    /**
     * 创建Pass服务器指定的api接口的实现
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T createForPass(Class<T> clazz) {
        return passRetrofit.create(clazz);
    }


    /**
     * For Test
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T createForTest(Class<T> clazz) {
        return createTestRetrofit().create(clazz);
    }

    /**
     * 检查是否有网络
     *
     * @param context
     * @return
     */
    public static boolean isNetAvailable(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null)
                return mNetworkInfo.isAvailable();
        }
        return false;
    }
}
