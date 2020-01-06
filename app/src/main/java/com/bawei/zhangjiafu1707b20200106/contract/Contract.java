package com.bawei.zhangjiafu1707b20200106.contract;

import com.bawei.zhangjiafu1707b20200106.mvp.IBaseModel;
import com.bawei.zhangjiafu1707b20200106.mvp.IBaseView;

import java.util.Map;

/**
 * @author: 张家辅
 * @date: 2020/01/06
 */
public class Contract {
  public   interface IView extends IBaseView {
        public void Success(Object object);
        public void Rrror(Throwable throwable);
    }
    public interface IPresenter{
        public void Leftsuccess();
        public void Rigthsuccess(Map<String,String> map);
        public void Error(Throwable throwable);
    }
    public interface IModel extends IBaseModel {
        public void Leftsuccess(CallBack callBack);
        public void Rigthsuccess(Map<String,String> map,CallBack callBack);
        public void Error(Throwable throwable);
        interface CallBack{
            void success(Object object);
            void Error(Throwable throwable);
        }
    }
}
