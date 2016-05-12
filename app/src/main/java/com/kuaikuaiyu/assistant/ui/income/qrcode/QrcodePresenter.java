package com.kuaikuaiyu.assistant.ui.income.qrcode;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;

import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.service.IncomeService;
import com.kuaikuaiyu.assistant.rx.SchedulersCompat;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/10 14:50
 * desc:
 */
public class QrcodePresenter implements BasePresenter {
    private IncomeService service;
    private QrcodeView mView;

    private static final String IMAGE_PREFIX = "qrcode_";
    private static final String IMAGE_SUFFIX = ".jpg";
    private static final String IMAGE_STORE_DIR = "assistant_store";

    @Inject
    public QrcodePresenter(IncomeService service, QrcodeView view) {
        this.service = service;
        mView = view;
    }

    /**
     * 将布局保存为图片
     *
     * @param v
     * @return
     */
    public Bitmap createViewBitmap(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    /**
     * 清空指定文件夹的指定图片
     * 需要获取[WRITE_EXTERNAL_STORAGE]权限
     *
     * @param dirPath
     */
    private void clearQrcodeImages(String dirPath, String prefix) {
        File dir = new File(dirPath);
        for (File f : dir.listFiles()) {
            if (f.getName().startsWith(prefix))
                f.delete();
        }
    }

    /**
     * 保存图片
     * 需要获取[WRITE_EXTERNAL_STORAGE]权限
     *
     * @param bitmap
     * @return
     */
    private String storeImage(Bitmap bitmap) {
        File storeDir = new File(Environment.getExternalStorageDirectory(), IMAGE_STORE_DIR);
        if (!storeDir.exists()) {
            storeDir.mkdir();
        }

        //清空原来图片
        clearQrcodeImages(storeDir.getAbsolutePath(), IMAGE_PREFIX);

        String fileName = IMAGE_PREFIX + System.currentTimeMillis() + IMAGE_SUFFIX;
        File file = new File(storeDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return file.getAbsolutePath();
    }

    /**
     * 异步保存view为图片 刷新图库
     * 需要获取[WRITE_EXTERNAL_STORAGE]权限
     *
     * @param context
     * @param view
     */
    synchronized public void saveView(Context context, View view) {
        Observable.just(storeImage(createViewBitmap(view))).compose(SchedulersCompat
                .applyIoSchedulers()).subscribe(new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                if (!TextUtils.isEmpty(s)) {
                    UIUtil.showLongToast("保存成功: " + s);
                    // 通知图库更新
                    context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri
                            .parse("file://" + s)));
                } else {
                    UIUtil.showToast("保存失败 请重试");
                }
            }

            @Override
            public void onError(Throwable e) {
                UIUtil.showToast("保存失败 请重试");
            }

            @Override
            public void onCompleted() {
            }
        });
    }

    @Override
    public void clean() {

    }
}
