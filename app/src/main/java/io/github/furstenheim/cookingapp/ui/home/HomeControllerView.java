package io.github.furstenheim.cookingapp.ui.home;

import java.util.concurrent.ThreadPoolExecutor;

import io.github.furstenheim.cookingapp.ControllerView;
import io.github.furstenheim.store.State;
import io.github.furstenheim.store.Store;
import io.github.furstenheim.store.ThreadExecutorService;

public class HomeControllerView extends ControllerView {
    public HomeControllerView(Store store,
            ThreadExecutorService mainThread) {
        super(store, mainThread);
    }

    @Override
    public void handleState(State state) {
        // TODO
    }
}
