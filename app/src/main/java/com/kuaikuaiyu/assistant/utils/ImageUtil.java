package com.kuaikuaiyu.assistant.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.ImageView;

import com.kuaikuaiyu.assistant.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

public class ImageUtil {
    private static DisplayImageOptions optionRect;
    private static DisplayImageOptions optionRound;

    /**
     * 加载图片
     *
     * @param uri
     * @param listener
     */
    public static void loadImg(String uri, ImageLoadingListener listener) {
        ImageLoader.getInstance().loadImage(uri, listener);
    }

    /**
     * 显示长方形图片
     *
     * @param uri
     * @param imageView
     */
    public static void displayRect(String uri, ImageView imageView) {
        ImageLoader.getInstance().displayImage(uri, imageView, getOptionRect());
    }

    /**
     * 显示长方形图片 添加图片加载监听
     *
     * @param uri
     * @param imageView
     * @param listener
     */
    public static void displayRect(String uri, ImageView imageView, ImageLoadingListener listener) {
        ImageLoader.getInstance().displayImage(uri, imageView, getOptionRect(), listener);
    }

    /**
     * 显示原型图片，圆角默认200，可以自行修改
     *
     * @param uri
     * @param imageView
     */
    public static void displayRound(String uri, ImageView imageView) {
        ImageLoader.getInstance().displayImage(uri, imageView, getOptionRound());
    }

    /**
     * 显示原型图片，圆角默认200，可以自行修改 添加图片加载监听
     *
     * @param uri
     * @param imageView
     */
    public static void displayRound(String uri, ImageView imageView, ImageLoadingListener listener) {
        ImageLoader.getInstance().displayImage(uri, imageView, getOptionRound(), listener);
    }

    private static DisplayImageOptions getOptionRect() {
        if (optionRect == null) {
            optionRect = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.default_img)
                    .showImageOnFail(R.mipmap.default_img)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.ARGB_8888)
                    .build();
        }

        return optionRect;
    }

    private static DisplayImageOptions getOptionRound() {
        if (optionRound == null) {
            optionRound = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.default_img)
                    .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                    .showImageOnFail(R.mipmap.default_img)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.ARGB_8888)
                    .displayer(new RoundedBitmapDisplayer(200))
                    .build();
        }
        return optionRound;
    }

    /**
     * 初始化ImageLoader
     */
    public static void initImageLoader(Context context) {
        String filePath = Environment.getExternalStorageDirectory() + "/Android/data/"
                + context.getPackageName() + "/cache/";

        File cacheDir = StorageUtils.getOwnCacheDirectory(context, filePath);// 获取到缓存的目录地址
        // 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                // .memoryCacheExtraOptions(480, 800) // max width, max
                // height，即保存的每个缓存文件的最大长宽
                // .discCacheExtraOptions(480, 800, CompressFormat.JPEG,
                // 75, null) // Can slow ImageLoader, use it carefully
                // (Better don't use it)设置缓存的详细信息，最好不要设置这个
                .threadPoolSize(3)
                // 线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                // .memoryCache(new WeakMemoryCache())
                // .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024
                // * 1024)) // You can pass your own memory cache
                // implementation你可以通过自己的内存缓存实现
                .memoryCacheSize(5 * 1024 * 1024)
                // /.discCacheSize(50 * 1024 * 1024)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                // 将保存的时候的URI名称用MD5
                // 加密
                // .discCacheFileNameGenerator(new
                // HashCodeFileNameGenerator())//将保存的时候的URI名称用HASHCODE加密
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                // .discCacheFileCount(100) //缓存的File数量
                .diskCache(new UnlimitedDiskCache(cacheDir))
                // 自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                // connectTimeout
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000))
                // readTimeout(30)// 超时时间
                // .writeDebugLogs() // Remove for release app

                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);// 全局初始化此配置
    }

}
