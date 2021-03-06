package com.lide.app.presenter.findProduct;

import com.lide.app.bean.Container.FindEpcMessageForSku;
import com.lide.app.bean.JsonToBean.BaseContainerBean;
import com.lide.app.listener.OnFinishListener;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.model.MInterface.IQueryModel;
import com.lide.app.model.QueryModelImpl;
import com.lide.app.persistence.util.FormatUtils;
import com.lide.app.presenter.PInterface.IFindProductForSkuPresenter;
import com.lide.app.service.IScanService;
import com.lide.app.service.ScanServiceControl;
import com.lide.app.ui.VInterface.IDataFragmentView;

import java.text.Format;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by lubin on 2016/11/3.
 */

public class FindProductForSku {

    private final IQueryModel searchModel;
    IDataFragmentView view;

    public FindProductForSku(IDataFragmentView view) {
        this.view = view;
        searchModel = new QueryModelImpl();
    }

    /***
     * 搜索绑定这个条码的epc
     * @param barcode 条码
     */
    public void searchDetailEpcList(String barcode) {
        view.startProgressDialog("下载中...");
        searchModel.queryEpcListOfBarcode(barcode, new OnLoadFinishListener() {
            @Override
            public void OnLoadFinish(Map<String, String> map) {
                if (map.get("statusCode").equals("200")) {
                    BaseContainerBean result ;
                    try {
                        result = FormatUtils.resultToBean(map.get("result"), BaseContainerBean.class);
                        view.ShowData(result.data);
                    } catch (Exception e) {
                        view.showDialog(e.toString());
                    }
                } else {
                    view.showDialog(map.get("result"));
                }
                view.stopProgressDialog(null);
            }
        });
    }

}
