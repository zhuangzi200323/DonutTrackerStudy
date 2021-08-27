package com.jack.donuttrackerstudy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.jack.donuttrackerstudy.adapter.DonutListAdapter;
import com.jack.donuttrackerstudy.viewmodel.DonutListViewModel;
import com.jack.donuttrackerstudy.MainActivity;
import com.jack.donuttrackerstudy.R;
import com.jack.donuttrackerstudy.viewmodel.ViewModelFactory;
import com.jack.donuttrackerstudy.databinding.DonutListBinding;
import com.jack.donuttrackerstudy.listener.ItemClickListener;
import com.jack.donuttrackerstudy.model.Donut;
import com.jack.donuttrackerstudy.storage.DonutDao;
import com.jack.donuttrackerstudy.storage.SnackDatabase;

public class DonutList extends Fragment implements ItemClickListener {
    private DonutListViewModel donutListViewModel;
    DonutListAdapter adapter;
    DonutListBinding binding;
    MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DonutListBinding.inflate(inflater);
        mainActivity = (MainActivity)requireActivity();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new DonutListAdapter(this);

        DonutDao donutDao = SnackDatabase.getDatabase(requireContext()).donutDao();
        donutListViewModel = new ViewModelProvider(this, new ViewModelFactory(donutDao)).get(DonutListViewModel.class);
        donutListViewModel.donuts().observe(getViewLifecycleOwner(), (items)-> {
            adapter.updateData(items);
        });

        binding.recyclerView.setAdapter(adapter);

        binding.fab.setOnClickListener((v)->{
            //mainActivity.switchDialogFragment();
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigate(DonutListDirections.actionDonutListToDonutEntryDialogFragment());
        });
    }

    @Override
    public void delete(Donut donut) {
        donutListViewModel.delete(donut);
    }

    @Override
    public void edit(Donut donut) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        DonutListDirections.ActionDonutListToDonutEntryDialogFragment fragment = DonutListDirections.actionDonutListToDonutEntryDialogFragment();
        fragment.setItemId(donut.id);
        navController.navigate(fragment);
    }
}
