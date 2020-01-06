package com.bawei.zhangjiafu1707b20200106.presenter;

import com.bawei.zhangjiafu1707b20200106.contract.Contract;
import com.bawei.zhangjiafu1707b20200106.model.Model;
import com.bawei.zhangjiafu1707b20200106.mvp.BasePresenter;

import java.util.Map;

/**
 * @author: 张家辅
 * @date: 2020/01/06
 */
public class Presenter extends BasePresenter<Model, Contract.IView> implements Contract.IPresenter{
    @Override
    protected Model initModel() {
        return new Model();
    }

    @Override
    public void Leftsuccess() {
       model.Leftsuccess(new Contract.IModel.CallBack() {
           @Override
           public void success(Object object) {
               getView().Success(object);
           }

           @Override
           public void Error(Throwable throwable) {
            getView().Rrror(throwable);
           }
       });
    }

    @Override
    public void Rigthsuccess(Map<String, String> map) {
        model.Rigthsuccess(map, new Contract.IModel.CallBack() {
            @Override
            public void success(Object object) {
                getView().Success(object);
            }

            @Override
            public void Error(Throwable throwable) {
             getView().Rrror(throwable);
            }
        });

    }

    @Override
    public void Error(Throwable throwable) {

    }
}
