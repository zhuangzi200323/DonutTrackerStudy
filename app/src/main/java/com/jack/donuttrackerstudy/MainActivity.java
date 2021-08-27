package com.jack.donuttrackerstudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.jack.donuttrackerstudy.databinding.ActivityMainBinding;
import com.jack.donuttrackerstudy.setup.SelectionViewModel;
import com.jack.donuttrackerstudy.setup.SelectionViewModelFactory;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        navController = Navigation.findNavController(findViewById(R.id.nav_host_fragment));
        NavigationUI.setupActionBarWithNavController(this, navController);

        setupBottomNavMenu(navController);
        setupNavigationMenu(navController);

        SelectionViewModel selectionViewModel = new SelectionViewModelFactory(UserPreferencesRepository.Companion.getInstance(this))
                .create(SelectionViewModel.class);

        selectionViewModel.checkCoffeeTrackerEnabled().observe(this, selection-> {
            setupMenu(selection);
        });


        Notifier.INSTANCE.init(this);
    }

    private void setupMenu(UserPreferencesRepository.Selection selection) {
        BottomNavigationView bottomNav = (BottomNavigationView)findViewById(R.id.bottom_nav_view);
        if (bottomNav != null) {
            if (selection == UserPreferencesRepository.Selection.DONUT_AND_COFFEE) {
                bottomNav.setVisibility(View.VISIBLE);
            } else {
                bottomNav.setVisibility(View.GONE);
            }
        }
    }

    private void setupNavigationMenu(NavController navController){
        NavigationView sideNavView = (NavigationView)findViewById(R.id.nav_view);
        if (sideNavView != null) {
            NavigationUI.setupWithNavController(sideNavView, navController);
        }
    }

    private void setupBottomNavMenu(NavController navController){
        BottomNavigationView bottomNav = (BottomNavigationView)findViewById(R.id.bottom_nav_view);
        if (bottomNav != null) {
            NavigationUI.setupWithNavController(bottomNav, navController);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //super.onOptionsItemSelected(item);

        return NavigationUI.onNavDestinationSelected(item, navController);

    }
}