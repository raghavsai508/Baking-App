package com.example.android.bakingapp.fragments;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.PlayerActivity;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.models.RecipeStep;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends Fragment {

    private static final String VIDEO_URL_KEY = "video_url";

    private List<RecipeStep> recipeStepList;
    private SimpleExoPlayer mExoPlayer;
    private OnDetailFragmentInteractionListener mListener;
    private RecipeStep recipeStep;
    private boolean mTwoPane;

    @BindView(R.id.exo_player_detail)
    SimpleExoPlayerView exoPlayerView;

    @BindView(R.id.exo_player_image_View)
    ImageView exoPlayerImageView;

    @BindView(R.id.tv_description)
    TextView textViewDescription;

    @BindView(R.id.btn_next)
    Button btnNext;

    @BindView(R.id.btn_previous)
    Button btnPrevious;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, rootView);

        showHidePlayerView();
        mListener.onDetailFragmentInteraction();
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDetailFragmentInteractionListener) {
            mListener = (OnDetailFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    private void showHidePlayerView() {
        if (mTwoPane) {
            exoPlayerImageView.setVisibility(View.INVISIBLE);
            exoPlayerView.setVisibility(View.VISIBLE);
        } else {
            exoPlayerImageView.setVisibility(View.VISIBLE);
            exoPlayerView.setVisibility(View.INVISIBLE);
        }
    }

    public void setRecipeStepList(List<RecipeStep> recipeStepList, boolean mTwoPane) {
        this.recipeStepList = recipeStepList;
        this.mTwoPane = mTwoPane;
    }

    public void refreshToPosition(int position) {
        recipeStep = recipeStepList.get(position);
        initializePlayer(Uri.parse(recipeStep.getmVideoURL()));
        textViewDescription.setText(recipeStep.getmDescription());
    }


    @OnClick(R.id.exo_player_image_View)
    public void showPlayerActivity(View view) {
        Intent intent = new Intent(getContext(), PlayerActivity.class);
        intent.putExtra(VIDEO_URL_KEY, recipeStep.getmVideoURL());
        startActivity(intent);
    }

    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            exoPlayerView.setPlayer(mExoPlayer);
        } else {
            mExoPlayer.stop();
        }
            // Prepare the MediaSource.
        String userAgent = Util.getUserAgent(getContext(), getString(R.string.app_name));
        MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
        mExoPlayer.prepare(mediaSource);
        mExoPlayer.setPlayWhenReady(true);
    }

    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }


    public interface OnDetailFragmentInteractionListener {
        void onDetailFragmentInteraction();
    }

}
