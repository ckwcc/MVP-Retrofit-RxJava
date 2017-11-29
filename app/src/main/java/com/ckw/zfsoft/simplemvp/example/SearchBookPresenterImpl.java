package com.ckw.zfsoft.simplemvp.example;

import android.content.Context;

import com.ckw.zfsoft.simplemvp.netloader.BaseObserver;
import com.ckw.zfsoft.simplemvp.netloader.AppNetLoader;
import com.ckw.zfsoft.simplemvp.repository.NearbyPerson;

import java.util.List;

/**
 * Created by ckw
 * on 2017/11/29.
 */

public class SearchBookPresenterImpl implements SearchBookContract.SearchBookPresenter {

    private SearchBookContract.SearchBookView mSearchBookView;
    private Context mContext;

    public SearchBookPresenterImpl(SearchBookContract.SearchBookView mSearchBookView) {
        this.mSearchBookView = mSearchBookView;
    }

    @Override
    public void getSearchBooks(String name,String longitude,String latitude,String sex) {
        AppNetLoader.getInstance().async(AppNetLoader.getInstance().getService().getNearbyPersonList(name, longitude, latitude, sex),
                new BaseObserver<List<NearbyPerson>>() {
                    @Override
                    protected void setNeedContext() {
                        setContext(mContext);
                    }

                    @Override
                    protected void _onNext(List<NearbyPerson> list) {
                        mSearchBookView.showNearbyListResult(list);
                    }

                    @Override
                    protected void _onError(Throwable e) {
                        mSearchBookView.onError(e.toString());
                    }
                });


    }

    @Override
    public void getContext(Context context) {
        mContext = context;
    }
}
