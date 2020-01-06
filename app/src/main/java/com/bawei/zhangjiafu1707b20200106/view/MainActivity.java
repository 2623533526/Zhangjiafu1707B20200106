package com.bawei.zhangjiafu1707b20200106.view;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;
import com.bawei.zhangjiafu1707b20200106.R;
import com.bawei.zhangjiafu1707b20200106.adapter.Lefta_Adapter;
import com.bawei.zhangjiafu1707b20200106.adapter.Rigth_Adapter;
import com.bawei.zhangjiafu1707b20200106.base.BaseAcitvity;
import com.bawei.zhangjiafu1707b20200106.entity.ClsEntity;
import com.bawei.zhangjiafu1707b20200106.entity.Left_Entity;
import com.bawei.zhangjiafu1707b20200106.entity.Rigth_Entity;
import com.bawei.zhangjiafu1707b20200106.greendao.ClsEntityDao;
import com.bawei.zhangjiafu1707b20200106.greendao.DaoMaster;
import com.bawei.zhangjiafu1707b20200106.greendao.DaoSession;
import com.bawei.zhangjiafu1707b20200106.presenter.Presenter;
import com.bawei.zhangjiafu1707b20200106.utlis.NetUtlis;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseAcitvity<Presenter> {
     @BindView(R.id.left_rv)
    RecyclerView left_rv;
    @BindView(R.id.rigth_rv)
    RecyclerView rigth_rv;
    private Gson gson;
    private ClsEntityDao clsEntityDao;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected void initData() {
      presenter.Leftsuccess();
      if(!NetUtlis.getInstance().isConnect()){
          List<ClsEntity> list = clsEntityDao.queryBuilder().list();
          ClsEntity clsEntity = list.get(0);
          String json = clsEntity.getJson();
          Log.i("zzzzzzzzzz",json);
          Left_Entity left_entity = gson.fromJson(json, Left_Entity.class);
          List<String> category = left_entity.getCategory();
          Lefta_Adapter lefta_adapter = new Lefta_Adapter(MainActivity.this, category);
          lefta_adapter.setLeftCallBack(new Lefta_Adapter.leftCallBack() {
              @Override
              public void success(String name) {
                  Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                  EventBus.getDefault().post(name);
              }
          });
          left_rv.setAdapter(lefta_adapter);
          return;
      }
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        gson = new Gson();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MainActivity.this, "zhang.db");
        SQLiteDatabase writableDatabase = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(writableDatabase);
        DaoSession daoSession = daoMaster.newSession();
        clsEntityDao = daoSession.getClsEntityDao();
        left_rv.setLayoutManager(new StaggeredGridLayoutManager(1,RecyclerView.VERTICAL));
        rigth_rv.setLayoutManager(new StaggeredGridLayoutManager(2,RecyclerView.VERTICAL));
    }

    @Override
    protected int layoutid() {
        return R.layout.activity_main;
    }

    @Override
    public void Success(Object object) {
        if(object instanceof Left_Entity){
            Left_Entity left_entity=(Left_Entity)object;
            if (NetUtlis.getInstance().isConnect()){
                String s = gson.toJson(left_entity);
                Log.i("json",s);
                ClsEntity clsEntity = new ClsEntity();
                clsEntity.setJson(s);
                clsEntityDao.insert(clsEntity);

            }
            List<String> category = left_entity.getCategory();
            Lefta_Adapter lefta_adapter = new Lefta_Adapter(MainActivity.this, category);
            lefta_adapter.setLeftCallBack(new Lefta_Adapter.leftCallBack() {
                @Override
                public void success(String name) {
                    Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(name);
                }
            });
            left_rv.setAdapter(lefta_adapter);
        }else if(object instanceof Rigth_Entity){
            Rigth_Entity rigth_entity=(Rigth_Entity)object;
            List<Rigth_Entity.DataBean> data = rigth_entity.getData();
            Rigth_Adapter rigth_adapter = new Rigth_Adapter(MainActivity.this, data);
            rigth_rv.setAdapter(rigth_adapter);
        }

    }
     @Subscribe(threadMode = ThreadMode.MAIN)
     public void getname(String name){
         Log.i("EVENTBUS",name);
         HashMap<String, String> map = new HashMap<>();
         map.put("category",name);
         presenter.Rigthsuccess(map);
     }
    @Override
    public void Rrror(Throwable throwable) {

    }
}
