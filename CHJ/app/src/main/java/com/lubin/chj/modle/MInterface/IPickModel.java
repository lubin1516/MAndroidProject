package com.lubin.chj.modle.MInterface;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.PcInfo;
import com.lubin.chj.bean.jsonToBean.QueryPCByCKpzhReturn;
import com.lubin.chj.bean.jsonToBean.QueryPcReturn;

import java.util.List;
import java.util.Map;

/**
 * @author DaiJiCheng
 * @time 2016/9/22  16:26
 * @desc ${TODD}
 */
public interface IPickModel {
    //凭证查询
    void queryPCByCkpzh(Map<String, Object> map, OnNetReqFinishListener listener);

    Map<String, Object> getHashMapForPZH(String pzbh);

    List<QueryPCByCKpzhReturn.ListBean> getQueryPcBypzhListBean();

    //直接查询
    void queryPc(Map<String, Object> map, OnNetReqFinishListener listener);

    Map<String, Object> getHashMap(String pchs);
    Map<String, Object> getHashMapForGw(String gwbh);
    Map<String, Object> getHashMapForQybh(String qybh);
    List<QueryPcReturn.ListBean> getQueryPcListBean();

    //拣货
    void fetchPC(Map<String, Object> map, OnNetReqFinishListener listener);

    Map<String, Object> getHashMapFechPc(List<PcInfo> list, String ckpzh);


    //未取货原因
    void noFetching(Map<String, Object> map, OnNetReqFinishListener listener);

    Map<String, Object> getHashMapNofetching(List<PcInfo> list, String ckpzh, String wqhyy);

    //获取单列表
    void GetMyPzhList(String pzlx, String orderType, final OnNetReqFinishListener listener);

    //获取单明细
    void GetPzhDetailResult(String pzlx, String pzh, final OnNetReqFinishListener listener);

}
