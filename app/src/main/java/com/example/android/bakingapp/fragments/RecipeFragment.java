package com.example.android.bakingapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.Adapters.RecipeAdapter;
import com.example.android.bakingapp.IngredientsActivity;
import com.example.android.bakingapp.Interfaces.RecipeItemClickListener;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.models.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipeFragment extends Fragment {

    private static final String INTENT_KEY = "recipe";

    private OnFragmentInteractionListener mListener;
    private Context mContext;

    @BindView(R.id.recipe_recycler_view)
    RecyclerView recipeRecyclerView;


    public RecipeFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final Context context = getContext();

        View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);
        ButterKnife.bind(this, rootView);

        recipeRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recipeRecyclerView.setLayoutManager(linearLayoutManager);

        List<Recipe> recipeList = Recipe.getRecipesList(context);

        RecipeAdapter recipeAdapter = new RecipeAdapter(recipeList, new RecipeItemClickListener() {
            @Override
            public void onRecipeClick(Recipe recipe) {
                Intent intent = new Intent(context, IngredientsActivity.class);
                intent.putExtra(INTENT_KEY, recipe);
                startActivity(intent);
            }
        });

        recipeRecyclerView.setAdapter(recipeAdapter);

        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
