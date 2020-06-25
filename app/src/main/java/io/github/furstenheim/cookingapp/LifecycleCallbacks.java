package io.github.furstenheim.cookingapp;

public interface LifecycleCallbacks {
    public void onStart();
    public void onResume();
    public void onPause();
    public void onStop();
    public void onDestroy();
}
