package com.bawei.zhangjiafu1707b20200106.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bawei.zhangjiafu1707b20200106.contract.Contract;
import com.bawei.zhangjiafu1707b20200106.mvp.BasePresenter;

/**
 * @author: 张家辅
 * @date: 2020/01/06
 */
public abstract class BaseAcitvity<P extends BasePresenter> extends AppCompatActivity implements Contract.IView {
   public P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutid());
        presenter=initPresenter();
        presenter.attAch(this);
        initView();
        initData();
    }

    protected abstract P initPresenter();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int layoutid();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.detAch();
        }
    }
}
