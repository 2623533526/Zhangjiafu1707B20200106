package com.bawei.zhangjiafu1707b20200106.Servier;

import com.bawei.zhangjiafu1707b20200106.api.Api;
import com.bawei.zhangjiafu1707b20200106.entity.Left_Entity;
import com.bawei.zhangjiafu1707b20200106.entity.Rigth_Entity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author: 张家辅
 * @date: 2020/01/06
 */
public interface MySerice {
    @GET(Api.LEFT_URL)
    Observable<Left_Entity> getLeft();
    @GET(Api.RIGTH_URL)
    Observable<Rigth_Entity> getRigrth(@QueryMap Map<String,String> map);
}
