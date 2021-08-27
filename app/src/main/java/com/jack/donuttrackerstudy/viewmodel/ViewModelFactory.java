package com.jack.donuttrackerstudy.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.jack.donuttrackerstudy.storage.DonutDao;

public class ViewModelFactory implements ViewModelProvider.Factory {
    DonutDao donutDao;

    public ViewModelFactory(DonutDao donutDao) {
        this.donutDao = donutDao;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DonutListViewModel.class)) {
            return (T)new DonutListViewModel(donutDao);
        }
        if (modelClass.isAssignableFrom(DonutEntryViewModel.class)) {
            return (T)new DonutEntryViewModel(donutDao);
        }
        return null;
    }
}
