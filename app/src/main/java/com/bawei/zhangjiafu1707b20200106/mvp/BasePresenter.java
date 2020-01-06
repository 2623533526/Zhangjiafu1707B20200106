package com.bawei.zhangjiafu1707b20200106.mvp;

import java.lang.ref.WeakReference;

/**
 * @author: 张家辅
 * @date: 2020/01/06
 */
public abstract class BasePresenter<M extends IBaseModel,V extends IBaseView> {
  public   M model;
  private WeakReference<V> weakReference;

    public BasePresenter() {
        model=initModel();
    }
    public void attAch(V v){
         weakReference=new WeakReference<V>(v);
    }
    public void detAch(){
            weakReference.clear();

    }
    public V getView(){
      return   weakReference.get();
    }

    protected abstract M initModel();
}
