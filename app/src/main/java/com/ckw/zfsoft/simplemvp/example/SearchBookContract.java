package com.ckw.zfsoft.simplemvp.example;

import com.ckw.zfsoft.simplemvp.base.BasePresenter;
import com.ckw.zfsoft.simplemvp.base.BaseView;
import com.ckw.zfsoft.simplemvp.repository.NearbyPerson;

import java.util.List;

/**
 * Created by ckw
 * on 2017/11/29.
 */

public interface SearchBookContract {

    interface SearchBookView extends BaseView{
        void showNearbyListResult(List<NearbyPerson> list);
    }

    interface SearchBookPresenter extends BasePresenter{
        void getSearchBooks(String name,String longitude,String latitude,String sex);
    }

}
