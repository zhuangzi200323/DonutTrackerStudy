package com.jack.donuttrackerstudy.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.jack.donuttrackerstudy.model.Donut;
import com.jack.donuttrackerstudy.storage.DonutDao;

import java.util.List;

public class DonutListViewModel extends ViewModel {
    DonutDao donutDao;

    public DonutListViewModel(DonutDao donutDao) {
        this.donutDao = donutDao;
    }

    public LiveData<List<Donut>> donuts() {
        return donutDao.getAll();
    }

    public void delete(Donut donut) {
        donutDao.delete(donut);
    }
}
