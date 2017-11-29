package com.ckw.zfsoft.simplemvp.netloader;

import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * 进度条辅助类
 */

public class ProgressHelp {
    private static KProgressHUD mProgress;

    public static void showProgress(Context context){
        if (mProgress == null) {
            mProgress = KProgressHUD.create(context)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .show();
        } else {
            mProgress.show();
        }
    }

    public static void dismissProgress(){
        if (mProgress!=null){
            mProgress.dismiss();
        }
        mProgress=null;
    }
}
