package com.lide.app.presenter.takeStock;

import com.lide.app.bean.JsonToBean.EPCDiff;
import com.lide.app.bean.JsonToBean.ProductDiff;
import com.lide.app.bean.JsonToBean.SkuDiff;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.model.QueryDiffModel;
import com.lide.app.presenter.PInterface.IQueryDiffPresenter;
import com.lide.app.ui.VInterface.IDataFragmentView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueryDiffPresenterImpl implements IQueryDiffPresenter {

    IDataFragmentView view;
    QueryDiffModel model;

    private List<ProductDiff.DataBean> product = new ArrayList<>();
    private List<SkuDiff.DataBean> sku = new ArrayList<>();
    private List<EPCDiff.DataBean> epcs = new ArrayList<>();

    public QueryDiffPresenterImpl(IDataFragmentView view) {
        this.view = view;
        this.model = new QueryDiffModel();
    }

    int currentProductPage = 1;


    /*
    * 根据款号查询差异
    * */
    @Override
    public void LoadProductDiff() {
        if (currentProductPage == -1) {
            view.showDialog("没有数据啦~");
            return;
        }
        view.startProgressDialog("下载中...");
        model.LoadProductDiff(null, currentProductPage, new OnLoadFinishListener() {
            @Override
            public void OnLoadFinish(Map<String, String> map) {
                if (map.get("statusCode").equals("200")) {
                    ProductDiff result = model.toProductDiff(map.get("result"));
                    if (result.isHasNext()) {
                        currentProductPage++;
                    } else {
                        currentProductPage = -1;
                    }
                    product.addAll(result.getData());
                    view.ShowData(queryProductDiff("ALL"));
                } else {
                    view.showDialog(map.get("result"));
                }
                view.stopProgressDialog(null);
            }
        });
    }


    /*
    * 根据SKU查询差异
    * */
    @Override
    public void LoadSkuDiff(final String productCode) {
        sku.clear();
        view.startProgressDialog("下载中...");
        model.LoadSkuDiff(null, productCode, new OnLoadFinishListener() {
            @Override
            public void OnLoadFinish(Map<String, String> map) {
                if (map.get("statusCode").equals("200")) {
                    SkuDiff result = model.toSkuDiff(map.get("result"));
                    sku.addAll(result.getData());
                    view.ShowData(querySkuDiff("ALL"));
                } else {
                    view.showDialog(map.get("result"));
                }
                view.stopProgressDialog(null);
            }
        });
    }

    int currentEpcPage = 1;
    String barcode = null;

    /*
    * 根据条码查询已盘点的EPC
    * */
    @Override
    public void LoadEpc(final String barcode) {
        if (this.barcode == null || !this.barcode.equals(barcode)) {
            this.barcode = barcode;
            currentEpcPage = 1;
            epcs.clear();
        }
        if (currentEpcPage == -1) {
            view.showDialog("没有数据啦~");
            return;
        }
        view.startProgressDialog("下载中...");
        model.LoadEPC(null, currentEpcPage, barcode, new OnLoadFinishListener() {
            @Override
            public void OnLoadFinish(Map<String, String> map) {
                if (map.get("statusCode").equals("200")) {
                    EPCDiff result = model.toEPC(map.get("result"));
                    if (result.isHasNext()) {
                        currentEpcPage++;
                    } else {
                        currentEpcPage = -1;
                    }
                    epcs.addAll(result.getData());
                    view.ShowData(epcs);
                } else {
                    view.showDialog(map.get("result"));
                }
                view.stopProgressDialog(null);
            }
        });
    }

    @Override
    public void LoadEpc(final String barcode, boolean flag) {
        epcs.clear();
        currentEpcPage = 1;
        LoadEpc(barcode);
    }

    @Override
    public void QueryProductDiff(String type) {
        view.ShowData(queryProductDiff(type));
    }

    public void QuerySkuDiff(String type) {
        view.ShowData(querySkuDiff(type));
    }

    public void QueryEpc() {
        view.ShowData(epcs);
    }

    private List<ProductDiff.DataBean> queryProductDiff(String type) {
        List<ProductDiff.DataBean> datas = new ArrayList<>();
        for (ProductDiff.DataBean bean : product) {
            if (type.equals("ALL")) {
                datas.add(bean);
            } else if (type.equals("DIFF")) {
                if (bean.getDiffQty() != 0) {
                    datas.add(bean);
                }
            } else {
                if (bean.getDiffQty() == 0) {
                    datas.add(bean);
                }
            }
        }
        return datas;
    }


    private List<SkuDiff.DataBean> querySkuDiff(String type) {
        List<SkuDiff.DataBean> datas = new ArrayList<>();
        for (SkuDiff.DataBean bean : sku) {
            if (type.equals("ALL")) {
                datas.add(bean);
            } else if (type.equals("DIFF")) {
                if (bean.getDiffQty() != 0) {
                    datas.add(bean);
                }
            } else {
                if (bean.getDiffQty() == 0) {
                    datas.add(bean);
                }
            }
        }
        return datas;
    }

    /*
    * 根据款号查询差异
    * */
    public void queryDetailProduct(String product) {
        if (product.equals("")) {
            return;
        }
        view.startProgressDialog("下载中...");
        model.LoadProductDiff(product, 0, new OnLoadFinishListener() {
            @Override
            public void OnLoadFinish(Map<String, String> map) {
                if (map.get("statusCode").equals("200")) {
                    ProductDiff result = model.toProductDiff(map.get("result"));
                    view.ShowData(result.getData());
                } else {
                    view.showDialog(map.get("result"));
                }
                view.stopProgressDialog(null);
            }
        });
    }

    /*
    * 根据SKU查询差异
    * */
    public void queryDetailSku(String sku) {
        if (sku.equals("")) {
            return;
        }
        view.startProgressDialog("下载中...");
        model.LoadSkuDiff(sku, null, new OnLoadFinishListener() {
            @Override
            public void OnLoadFinish(Map<String, String> map) {
                if (map.get("statusCode").equals("200")) {
                    SkuDiff result = model.toSkuDiff(map.get("result"));
                    view.ShowData(result.getData());
                } else {
                    view.showDialog(map.get("result"));
                }
                view.stopProgressDialog(null);
            }
        });
    }

    //根据条码查询已盘点的EPC
    @Override
    public void queryDetailEpc(String epc) {
        if (epc.equals("")) {
            return;
        }
        view.startProgressDialog("下载中...");
        model.LoadEPC(epc, 0, null, new OnLoadFinishListener() {
            @Override
            public void OnLoadFinish(Map<String, String> map) {
                if (map.get("statusCode").equals("200")) {
                    EPCDiff result = model.toEPC(map.get("result"));
                    view.ShowData(result.getData());
                } else {
                    view.showDialog(map.get("result"));
                }
                view.stopProgressDialog(null);
            }
        });
    }

}
