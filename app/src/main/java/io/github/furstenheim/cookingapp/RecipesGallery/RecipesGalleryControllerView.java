package io.github.furstenheim.cookingapp.RecipesGallery;

import java.lang.ref.WeakReference;

import io.github.furstenheim.cookingapp.ControllerView;
import io.github.furstenheim.store.Action;
import io.github.furstenheim.store.State;
import io.github.furstenheim.store.Store;
import io.github.furstenheim.store.ThreadExecutor;
import io.github.furstenheim.store.ThreadExecutorService;

public class RecipesGalleryControllerView extends ControllerView {
    private WeakReference<RecipesGalleryViewCallback> callback;
    RecipesGalleryControllerView(Store store,
            ThreadExecutor mainThread,
            WeakReference<RecipesGalleryViewCallback> callback) {
        super(store, mainThread);
        this.callback = callback;
    }

    void addHTMLRecipe(String recipe) {
        store.dispatch(new Action.CreateRecipeFromHTML(recipe));
    }
    @Override
    public void handleState(State state) {

    }
}
