package com.lide.app.ui.outbound.createOrder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.lide.app.R;
import com.lide.app.adapter.OutBoundOrderAdapter;
import com.lide.app.config.Configs;
import com.lide.app.persistence.widget.view.SwipeListView;
import com.lide.app.presenter.outbound.ControlOBOrderByCreatePresenter;
import com.lide.app.ui.FragmentBase;
import com.lide.app.ui.SearchActivity;
import com.lide.app.ui.VInterface.IDataFragmentView;
import com.lubin.bean.OutBoundOrder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* 生成出库单
* */
public class CreateOutBoundOrderFragment extends FragmentBase implements IDataFragmentView<List<OutBoundOrder>> {

    private List<OutBoundOrder> mOrders = new ArrayList<>();
    private View mView;

    private OutBoundOrderAdapter mAdapter;
    private ControlOBOrderByCreatePresenter mPresenter;
    private SwipeListView swipeListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_create_out_bound_order, container, false);
        ButterKnife.bind(this, mView);
        swipeListView = ((SwipeListView) mView.findViewById(R.id.lv_contain_out_bound_case));
        mPresenter = new ControlOBOrderByCreatePresenter(this);
        initView();
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadAllOutBoundOrder();
    }

    private void initView() {
        mAdapter = new OutBoundOrderAdapter(getActivity(), mOrders, mPresenter);
        swipeListView.setAdapter(mAdapter);
        swipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ScanOBOrderByCreateActivity.class);
                intent.putExtra(Configs.OutBoundOrderId, mOrders.get(position).getId());
                startAnimActivity(intent);
            }
        });
    }

    @OnClick(R.id.btn_create_out_bound_case)
    public void onClick() {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        intent.putExtra("Flag", Configs.SearchWarehousesFragment);
        startAnimActivity(intent);
    }

    @Override
    public void ShowData(List<OutBoundOrder> outBoundOrders) {
        List<OutBoundOrder> outBoundOrderList = new ArrayList<>();
        for (Iterator<OutBoundOrder> iterator = outBoundOrders.iterator(); iterator.hasNext(); ) {
            OutBoundOrder outBoundOrder = iterator.next();
            if (outBoundOrder.getIsCreate()) {
                outBoundOrderList.add(outBoundOrder);
            }
        }
        mAdapter.addAll(outBoundOrderList);
    }

    @Override
    public void ShowToast(String text) {
        if (text.equals("审核成功")) {
            mAdapter.notifyDataSetChanged();
        }
    }
}
