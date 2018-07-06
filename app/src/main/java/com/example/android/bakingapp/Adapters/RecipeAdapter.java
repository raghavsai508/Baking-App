package com.example.android.bakingapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.models.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<Recipe> mRecipeList;

    public RecipeAdapter(List<Recipe> recipes) {
        mRecipeList = recipes;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_recipe_name)
        TextView textViewRecipeName;

        @BindView(R.id.iv_recipe)
        ImageView imageViewRecipe;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View mView = layoutInflater.inflate(R.layout.recipe_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = mRecipeList.get(position);
        holder.textViewRecipeName.setText(recipe.getmRecipeName());
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }
}
