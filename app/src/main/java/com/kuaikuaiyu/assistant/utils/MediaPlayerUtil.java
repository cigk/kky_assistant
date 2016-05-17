package com.kuaikuaiyu.assistant.utils;

import android.media.MediaPlayer;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2015/9/15
 * Desc:    MediaPlayer 工具类
 */
public class MediaPlayerUtil {

//    private static MediaPlayer player;

    public static void play(int resid) {
//        if (player != null) {
//            player.stop();
//            player.release();
//            player = null;
//        }
        MediaPlayer player = MediaPlayer.create(UIUtil.getContext(), resid);
//        player.setVolume(0.4f, 0.4f);
//        try {
//            player.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        player.start();

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
            }
        });
    }
}
