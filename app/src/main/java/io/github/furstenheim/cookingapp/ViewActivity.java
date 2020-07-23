package io.github.furstenheim.cookingapp;

import androidx.appcompat.app.AppCompatActivity;

public abstract class ViewActivity<T extends ControllerView> extends AppCompatActivity {
    protected T controllerView;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        controllerView.onDestroy();
    }


    @Override
    protected void onPause() {
        super.onPause();
        controllerView.onPause();
    }
    @Override
    protected void onStart() {
        super.onStart();
        controllerView.onStart();
    }
    @Override
    protected void onStop() {
        super.onStop();
        controllerView.onStop();
    }

    protected void registerControllerViewForLifecycle (T controllerView) {
        this.controllerView = controllerView;
    }
    public abstract void setupControllerView();
}
