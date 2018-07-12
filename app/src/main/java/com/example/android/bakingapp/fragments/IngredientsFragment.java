package com.example.android.bakingapp.fragments;


import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.Adapters.RecipeStepAdapter;
import com.example.android.bakingapp.Interfaces.RecipeStepItemClickListener;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.models.Ingredient;
import com.example.android.bakingapp.models.Recipe;
import com.example.android.bakingapp.widget.RecipeWidgetProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientsFragment extends Fragment {

    public interface IngFragmentRecipeStepClickListener {
        void recipeStepClickAt(int position);
    }


    private Recipe recipe;
    private IngFragmentRecipeStepClickListener ingFragmentRecipeStepClickListener;

    @BindView(R.id.recycler_view_recipe_steps)
    RecyclerView recyclerViewRecipeSteps;

    @BindView(R.id.tv_ingredients)
    TextView textViewIngredients;

    public IngredientsFragment() {
        // Required empty public constructor
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setIngFragmentRecipeStepClickListener(IngFragmentRecipeStepClickListener ingFragmentRecipeStepClickListener) {
        this.ingFragmentRecipeStepClickListener = ingFragmentRecipeStepClickListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context context = getContext();
        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);
        ButterKnife.bind(this, rootView);

        setupIngredients();
        setupRecyclerView(context);
        setupAppWidget();
        return rootView;
    }

    private void setupIngredients() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Ingredient ingredient: recipe.getmIngredients()) {
            stringBuilder.append(ingredient.getmIngredient()+" "+ingredient.getmMeasure()+" "+ingredient.getmQuantity()+"\n");
        }
        textViewIngredients.setText(stringBuilder);
    }

    private void setupRecyclerView(Context context) {
        recyclerViewRecipeSteps.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerViewRecipeSteps.setLayoutManager(linearLayoutManager);

        RecipeStepAdapter recipeStepAdapter = new RecipeStepAdapter(recipe.getmRecipeSteps(), new RecipeStepItemClickListener() {
            @Override
            public void onRecipeStepClick(int position) {
                ingFragmentRecipeStepClickListener.recipeStepClickAt(position);
            }
        });
        recyclerViewRecipeSteps.setAdapter(recipeStepAdapter);
    }

    private void setupAppWidget() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getContext());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(getContext(), RecipeWidgetProvider.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list_view);
        RecipeWidgetProvider.updateRecipeWidget(getContext(), appWidgetManager, recipe, appWidgetIds);
    }

}
