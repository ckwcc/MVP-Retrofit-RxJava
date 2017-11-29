package com.ckw.zfsoft.simplemvp.netloader;

import android.app.Activity;
import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 在此处封装了发起网络请求时的进度条
 * 提供了不显示的方法
 * Created by funnyzhao on 2017/8/23.
 */

public abstract class BaseObserver<T> implements Observer<T>{
    private Context mContext;
    //默认需要进度条
    private boolean mShowTag=true;
    /**
     * 必须实现的方法，用来获取context
     * @return
     */
    protected abstract void setNeedContext();
    protected void setContext(Context context){
        mContext=context;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        //默认需要进度条
        setProgress(mShowTag);
        setNeedContext();
        if (mShowTag && !((Activity)mContext).isFinishing()){
            ProgressHelp.showProgress(mContext);
        }
    }

    @Override
    public void onNext(@NonNull T t) {
        if (mShowTag && !((Activity)mContext).isFinishing()){
            ProgressHelp.dismissProgress();
        }
        _onNext(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (mShowTag && !((Activity)mContext).isFinishing()){
            ProgressHelp.dismissProgress();
        }
        _onError(e);
    }

    @Override
    public void onComplete() {
    }
    protected abstract void _onNext(@NonNull T t);
    protected abstract void _onError(@NonNull Throwable e);

    /**
     * 如果不需要Progress重写此方法
     * @param isShow
     */
    protected  void setProgress(boolean isShow){
        mShowTag=isShow;
    }
}
