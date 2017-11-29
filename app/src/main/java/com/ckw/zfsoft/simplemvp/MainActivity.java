package com.ckw.zfsoft.simplemvp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ckw.zfsoft.simplemvp.example.SearchBookContract;
import com.ckw.zfsoft.simplemvp.example.SearchBookPresenterImpl;
import com.ckw.zfsoft.simplemvp.repository.NearbyPerson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchBookContract.SearchBookView, View.OnClickListener {

    private SearchBookPresenterImpl mPresenter;

    private TextView mShow;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setPresenter();
        initView();
    }

    private void initView() {
        mShow = findViewById(R.id.tv_show);
        mButton = findViewById(R.id.btn_click);
        mButton.setOnClickListener(this);
    }


    @Override
    public void onError(String e) {
        mShow.setText(e);
    }

    @Override
    public void setPresenter() {
        mPresenter = new SearchBookPresenterImpl(this);
        mPresenter.getContext(this);
    }

    @Override
    public Activity getCurrentActivity() {
        return this;
    }

    @Override
    public void onClick(View v) {
        mPresenter.getSearchBooks("255",null,null,"男");
    }

    @Override
    public void showNearbyListResult(List<NearbyPerson> list) {
        mShow.setText(list.toString());
    }
}
