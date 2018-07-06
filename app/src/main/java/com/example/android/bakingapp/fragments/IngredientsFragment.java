package com.example.android.bakingapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.Adapters.RecipeStepAdapter;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.models.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientsFragment extends Fragment {

    private Recipe recipe;

    @BindView(R.id.recycler_view_recipe_steps)
    RecyclerView recyclerViewRecipeSteps;

    public IngredientsFragment() {
        // Required empty public constructor
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context context = getContext();
        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);
        ButterKnife.bind(this, rootView);

        recyclerViewRecipeSteps.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerViewRecipeSteps.setLayoutManager(linearLayoutManager);

        RecipeStepAdapter recipeStepAdapter = new RecipeStepAdapter(recipe.getmRecipeSteps());
        recyclerViewRecipeSteps.setAdapter(recipeStepAdapter);

        return rootView;

    }

}
