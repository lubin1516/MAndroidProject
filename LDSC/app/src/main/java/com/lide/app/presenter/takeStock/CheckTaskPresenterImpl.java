package com.lide.app.presenter.takeStock;

import android.text.TextUtils;

import com.lide.app.R;
import com.lide.app.model.CheckTaskModelImpl;
import com.lide.app.model.MInterface.ICheckTaskModel;
import com.lide.app.presenter.PInterface.ICheckTaskPresenter;
import com.lide.app.ui.VInterface.ICheckTaskView;

public class CheckTaskPresenterImpl implements ICheckTaskPresenter {
    ICheckTaskView view;
    ICheckTaskModel model;

    public CheckTaskPresenterImpl(ICheckTaskView view) {
        this.view = view;
        this.model = new CheckTaskModelImpl();
    }

    @Override
    public void addCheckTask(String taskName, int mode) {
        if (!TextUtils.isEmpty(taskName)) {
            if (model.addCheckTask(taskName, mode)) {
                getAllCheckTask();
                view.jumpToTakeStock();
            } else {
                view.showResult(R.string.add_check_task_fail);
            }
        } else {
            view.showResult(R.string.empty_data);
        }
    }

    @Override
    public void deleteCheckTask(String taskName) {

        if (model.deleteCheckTask(taskName)) {
            getAllCheckTask();
            view.showResult(R.string.delete_check_task_succeed);
        } else {
            view.showResult(R.string.delete_check_task_fail);
        }
    }

    public void deleteCheckTaskByCodeId(String codeId){
        if (model.deleteCheckTaskCodeId(codeId)) {
            getAllCheckTask();
            view.showResult(R.string.delete_check_task_succeed);
        } else {
            view.showResult(R.string.delete_check_task_fail);
        }
    }

    @Override
    public void updateCheckTaskName(String InvalidName, String newName) {
        if (!TextUtils.isEmpty(newName)) {
            if (model.updateCheckTaskName(InvalidName, newName)) {
                getAllCheckTask();
                view.showResult(R.string.update_check_task_succeed);
            } else {

                view.showResult(R.string.update_check_task_fail);
            }
        } else {
            view.showResult(R.string.empty_data);
        }
    }

    @Override
    public void detailSearch(String keyword) {
        if (keyword != null) {
            view.showCheckTaskList(model.detailSearch(keyword));
        }
    }

    @Override
    public void getAllCheckTask() {
        view.showCheckTaskList(model.getAllCheckTask());
    }

}
