package com.ckw.zfsoft.simplemvp.netloader;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ckw
 * on 2017/11/29.
 */

public class AppNetLoader<T> {
    private RetrofitService mApi;
    //默认超时时间
    private static final int DEFAULT_TIME = 10;
    private static Retrofit mRetrofit = null;
    private static AppNetLoader mNetLoader;
    private static OkHttpClient mOkHttpClient;

    private AppNetLoader(){
        mRetrofit=new Retrofit.Builder()
                .baseUrl(RetrofitService.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttp())
                .build();
        mApi=mRetrofit.create(RetrofitService.class);
    }


    /**
     * 设置okhttp
     * @return
     */
    private OkHttpClient getOkHttp() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                    .addInterceptor(new BaseInterceptor())
                    .connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIME,TimeUnit.SECONDS)
                    .build();
        }
        return mOkHttpClient;
    }

    /**
     * 获取实例
     * @return
     */
    public static synchronized AppNetLoader getInstance(){
        if (mNetLoader==null)
            synchronized (AppNetLoader.class){
                if (mNetLoader==null){
                    return new AppNetLoader();
                }
            }
        return mNetLoader;

    }

    /**
     * 发起网络请求
     * @param observable
     * @param sub
     */
    public void async(Observable<HttpResult<T>> observable, BaseObserver<HttpResult<T>> sub){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sub);
    }

    /**
     * 获取api对象
     * @return
     */
    public RetrofitService getService(){
        return mApi;
    }
}
