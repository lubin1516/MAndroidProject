package com.lide.app.presenter.detection;

import com.google.gson.internal.LinkedTreeMap;
import com.lide.app.bean.JsonToBean.BaseContainerBean;
import com.lide.app.listener.OnLoadFinishListener;
import com.lide.app.model.MInterface.IQueryModel;
import com.lide.app.model.QueryModelImpl;
import com.lide.app.persistence.util.FormatUtils;
import com.lide.app.ui.VInterface.IDataFragmentView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by lubin on 2016/12/5.
 */

public class DetectPresenter {

    IDataFragmentView view;
    IQueryModel queryModel;
    List<String> data;
    List<LinkedTreeMap> container;
    public DetectPresenter(IDataFragmentView view) {
        this.view = view;
        queryModel = new QueryModelImpl();
    }

    /***
     * 检测epc集合中数据，是否存在后端数据库
     * @param data 标签集合
     */
    public synchronized void searchEpcList(final List<String> data) {
        this.data = data;
        container = new ArrayList<>();
        view.startProgressDialog("检测中...");
        try {
            installData(0);
        } catch (Exception e) {
            view.showDialog(e.toString());
        }
    }

    private void installData(int num) throws Exception {
        List<String> mData = new LinkedList<>();
        for (int i = num; i < data.size(); i++) {
            mData.add(data.get(i));
            if (mData.size() == 1000) {
                List<String> list = new ArrayList<>();
                list.addAll(mData);
                startSearch(list, i + 1, false);
                mData.clear();
                break;
            } else if (i + 1 == data.size()) {
                List<String> list = new ArrayList<>();
                list.addAll(mData);
                startSearch(list, i + 1, true);
                mData.clear();
                break;
            }
        }
    }

    private void startSearch(final List<String> data, final int i, final boolean flag) throws Exception {
        queryModel.queryEpcList(data, new OnLoadFinishListener() {
            @Override
            public void OnLoadFinish(Map<String, String> map) {
                if (map.get("statusCode").equals("200")) {
                    BaseContainerBean result = null;
                    try {
                        result = FormatUtils.resultToBean(map.get("result"), BaseContainerBean.class);
                        container.addAll(result.data);
                        if (flag) {
                            view.stopProgressDialog(null);
                            view.ShowData(container);
                        } else {
                            installData(i);
                        }
                    } catch (Exception e) {
                        view.showDialog(e.toString());
                    }
                } else {
                    view.showDialog(map.get("result"));
                }
            }
        });
    }
}
