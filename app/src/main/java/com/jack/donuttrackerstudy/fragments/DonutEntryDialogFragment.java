package com.jack.donuttrackerstudy.fragments;

import android.app.PendingIntent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jack.donuttrackerstudy.Notifier;
import com.jack.donuttrackerstudy.R;
import com.jack.donuttrackerstudy.viewmodel.DonutEntryViewModel;
import com.jack.donuttrackerstudy.viewmodel.ViewModelFactory;
import com.jack.donuttrackerstudy.databinding.DonutEntryDialogBinding;
import com.jack.donuttrackerstudy.model.Donut;
import com.jack.donuttrackerstudy.storage.DonutDao;
import com.jack.donuttrackerstudy.storage.SnackDatabase;

public class DonutEntryDialogFragment extends BottomSheetDialogFragment {
    DonutEntryDialogBinding binding;
    DonutEntryViewModel donutEntryViewModel;
    Donut donut = null;

    private enum EditingState {
        NEW_DONUT, EXISTING_DONUT;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DonutEntryDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DonutDao donutDao = SnackDatabase.getDatabase(requireContext()).donutDao();
        donutEntryViewModel = new ViewModelProvider(this, new ViewModelFactory(donutDao)).get(DonutEntryViewModel.class);

        Bundle arguments = getArguments();
        if (arguments != null) {
            DonutEntryDialogFragmentArgs donutEntryDialogFragmentArgs = DonutEntryDialogFragmentArgs.fromBundle(arguments);
            long itemId = donutEntryDialogFragmentArgs.getItemId();
            EditingState editingState;
            if (itemId > 0) {
                editingState = EditingState.EXISTING_DONUT;
            } else {
                editingState = EditingState.NEW_DONUT;
            }

            // If we arrived here with an itemId of >= 0, then we are editing an existing item
            if (editingState == EditingState.EXISTING_DONUT) {
                // Request to edit an existing item, whose id was passed in as an argument.
                // Retrieve that item and populate the UI with its details
                donutEntryViewModel.get(itemId).observe(getViewLifecycleOwner(), donutItem -> {
                    binding.name.setText(donutItem.name);
                    binding.description.setText(donutItem.description);
                    binding.ratingBar.setRating(donutItem.rating);
                    donut = donutItem;
                });
            }
        }

        binding.doneButton.setOnClickListener(v-> {
            long actualId = donutEntryViewModel.addData(donut != null ? donut.id : 0, binding.name.getText().toString(),
                    binding.description.getText().toString(), binding.ratingBar.getRating());
//            Bundle bundle = new DonutEntryDialogFragmentArgs.Builder().setItemId(actualId).build().toBundle();
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            PendingIntent pendingIntent = navController
                    .createDeepLink()
                    .setDestination(R.id.donutEntryDialogFragment)
                    .setArguments(arguments)
                    .createPendingIntent();
            Notifier.INSTANCE.postNotification(actualId, requireContext(), pendingIntent);
            dismiss();
        });

        binding.cancelButton.setOnClickListener(v-> {
            dismiss();
        });
    }
}
