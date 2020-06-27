package io.github.furstenheim.cookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public abstract class ViewFragment<T extends ControllerView> extends Fragment {
    T controllerView;

    @Override
    public void onDestroy() {
        super.onDestroy();
        controllerView.onDestroy();
    }


    @Override
    public void onPause() {
        super.onPause();
        controllerView.onPause();
    }
    @Override
    public void onStart() {
        super.onStart();
        controllerView.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();
        controllerView.onStop();
    }

    protected void registerControllerViewForLifecycle (T controllerView) {
        this.controllerView = controllerView;
    }
    public abstract void setupControllerView();
}
