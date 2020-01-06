package com.bawei.zhangjiafu1707b20200106.model;


import android.util.Log;

import com.bawei.zhangjiafu1707b20200106.Servier.MySerice;
import com.bawei.zhangjiafu1707b20200106.contract.Contract;
import com.bawei.zhangjiafu1707b20200106.entity.Left_Entity;
import com.bawei.zhangjiafu1707b20200106.entity.Rigth_Entity;
import com.bawei.zhangjiafu1707b20200106.utlis.NetUtlis;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: 张家辅
 * @date: 2020/01/06
 */
public class Model implements Contract.IModel {

    @Override
    public void Leftsuccess(CallBack callBack) {
        NetUtlis.getInstance().create(MySerice.class)
                .getLeft()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Left_Entity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Left_Entity left_entity) {
                     callBack.success(left_entity);
                    }

                    @Override
                    public void onError(Throwable e) {
                    callBack.Error(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void Rigthsuccess(Map<String, String> map, CallBack callBack) {
      NetUtlis.getInstance().create(MySerice.class)
              .getRigrth(map)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Observer<Rigth_Entity>() {
                  @Override
                  public void onSubscribe(Disposable d) {

                  }

                  @Override
                  public void onNext(Rigth_Entity rigth_entity) {
                      callBack.success(rigth_entity);
                  }

                  @Override
                  public void onError(Throwable e) {
                   callBack.Error(e);
                  }

                  @Override
                  public void onComplete() {

                  }
              });
    }

    @Override
    public void Error(Throwable throwable) {

    }
}
