package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.fragments.RecipeDetailFragment;
import com.example.android.bakingapp.models.RecipeStep;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailFragment.OnDetailFragmentInteractionListener {

    private static final String INTENT_KEY_RECIPE_STEPS = "recipe_steps";
    private static final String INTENT_KEY_RECIPE_STEP_POSITION = "recipe_step_position";

    private List<RecipeStep> recipeStepList;
    private RecipeDetailFragment recipeDetailFragment;
    private int stepPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Intent intentCalled = getIntent();
        if (intentCalled != null) {
            if (intentCalled.getBundleExtra("extra") != null) {
                Bundle bundle = intentCalled.getBundleExtra("extra");
                recipeStepList = (ArrayList<RecipeStep>)bundle.getSerializable(INTENT_KEY_RECIPE_STEPS);
                stepPosition = bundle.getInt(INTENT_KEY_RECIPE_STEP_POSITION);
            }
        }


        recipeDetailFragment = new RecipeDetailFragment();
        recipeDetailFragment.setRecipeStepList(recipeStepList);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.detail_recipe_container, recipeDetailFragment)
                .commit();

    }


    @Override
    public void onDetailFragmentInteraction() {
        recipeDetailFragment.refreshToPosition(stepPosition);
    }
}
