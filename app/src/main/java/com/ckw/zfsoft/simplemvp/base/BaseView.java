package com.ckw.zfsoft.simplemvp.base;

import android.app.Activity;

/**
 * Created by ckw
 * on 2017/11/29.
 */

public interface BaseView{

    void onError(String e);

    void setPresenter();

    Activity getCurrentActivity();

}
