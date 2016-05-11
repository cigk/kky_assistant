package com.kuaikuaiyu.assistant.ui.income.qrcode;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
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
     * 保存并且插入图片到系统相册中
     *
     * @param bitmap
     * @return
     */
    public String storeImage(Bitmap bitmap) {
        // 保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "assistant_store");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "assistant_qrcode" + ".jpg";
        File file = new File(appDir, fileName);
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

    synchronized public void saveView(Context context, View view) {
        Observable.just(storeImage(createViewBitmap(view))).compose(SchedulersCompat
                .applyIoSchedulers()).subscribe(new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                // 通知图库更新
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse
                        ("file://" + s)));
                UIUtil.showLongToast("保存成功: " + s);
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
