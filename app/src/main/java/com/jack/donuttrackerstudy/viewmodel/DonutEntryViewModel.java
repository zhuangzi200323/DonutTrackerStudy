package com.jack.donuttrackerstudy.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jack.donuttrackerstudy.model.Donut;
import com.jack.donuttrackerstudy.storage.DonutDao;

public class DonutEntryViewModel extends ViewModel {
    private LiveData<Donut> donutLiveData;
    DonutDao donutDao;

    public DonutEntryViewModel(DonutDao donutDao) {
        this.donutDao = donutDao;
    }

    public LiveData<Donut> get(long id) {
        if (donutLiveData == null) {
            donutLiveData = new MutableLiveData<>(donutDao.get(id));
        }
        return donutLiveData;
    }

    public long addData(long id, String name, String description, float rating) {
        Donut donut = new Donut(id, name, description, rating);
        long actualId = id;

        if (id > 0) {
            update(donut);
        } else {
            actualId = insert(donut);
        }
        return actualId;
    }

    public long insert(Donut donut) {
        return donutDao.insert(donut);
    }

    private void update(Donut donut) {
        donutDao.update(donut);
    }
}
