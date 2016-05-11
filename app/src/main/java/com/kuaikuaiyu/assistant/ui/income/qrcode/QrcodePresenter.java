package com.kuaikuaiyu.assistant.ui.income.qrcode;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.service.IncomeService;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

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
     * @param context
     * @param bmp
     * @return
     */
    public String saveImage(Context context, Bitmap bmp) {
        // 保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "assistant_store");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "qrcode_" + System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

//        // 把文件插入到系统图库
        //        fixMediaDir();
        //        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bmp,
        //                null, null);

        // 通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse
                ("file://" + file.getAbsolutePath())));

        UIUtil.showToast("保存成功: " + file.getAbsolutePath());
        return file.getAbsolutePath();
    }

    private void fixMediaDir() {
        File sdcard = Environment.getExternalStorageDirectory();
        if (sdcard == null) {
            return;
        }
        File dcim = new File(sdcard, "DCIM");
        if (dcim == null) {
            return;
        }
        File camera = new File(dcim, "Camera");
        if (camera.exists()) {
            return;
        }
        camera.mkdir();
    }

    @Override
    public void clean() {

    }
}
