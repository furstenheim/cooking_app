package io.github.furstenheim.cookingapp.RecipesGallery;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import java.lang.ref.WeakReference;

import io.github.furstenheim.cookingapp.AppStore;
import io.github.furstenheim.cookingapp.ControllerView;
import io.github.furstenheim.cookingapp.MainThread;
import io.github.furstenheim.cookingapp.R;
import io.github.furstenheim.cookingapp.ViewActivity;
import io.github.furstenheim.store.ThreadExecutor;

public class RecipesGalleryActivity extends ViewActivity<RecipesGalleryControllerView> implements RecipesGalleryViewCallback {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_gallery);
        setupControllerView();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        RecipesGalleryActivity thiz = this;
        fab.setOnClickListener(new View.OnClickListener() {
            // TODO save recipe
            @Override
            public void onClick(View view) {
                /*NavHostFragment finalHost = NavHostFragment.create(R.navigation.mobile_navigation);*/
                NavController navController1 = Navigation
                        .findNavController(thiz, R.id.nav_host_fragment);
                navController1.navigate(R.id.nav_home);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.recipes_gallery, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
               || super.onSupportNavigateUp();
    }

    @Override
    public void setupControllerView() {
        RecipesGalleryControllerView controllerView = new RecipesGalleryControllerView(AppStore.AppStore(),
                                                                         new MainThread(new WeakReference(this)), new WeakReference<RecipesGalleryViewCallback>(this));
        registerControllerViewForLifecycle(controllerView);
    }
}
