package com.oneupapplications.layoutplayground;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;

import com.oneupapplications.layoutplayground.model.article;

import com.oneupapplications.layoutplayground.utility.ImageLoader;

public class ArtDetailFragment extends Fragment {

    public static final String ARG_ITEM_Key = "ArticleKey";

    private article mArticle;

    public ImageLoader imageLoader;

    public ArtDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_Key)) {
            String tempContent = getArguments().getString(ARG_ITEM_Key);
            mArticle = new Gson().fromJson(tempContent, article.class);
            imageLoader = new ImageLoader(this.getActivity());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.article_row, container, false);

        // Show the dummy content as text in a TextView.
        if (mArticle != null) {
            ((TextView) rootView.findViewById(R.id.post_title)).setText(mArticle.getPostTitle());
            ((TextView) rootView.findViewById(R.id.post_body)).setText(mArticle.getPostBody());
            ((TextView) rootView.findViewById(R.id.tag)).setText(mArticle.getTags() + "  |  " + mArticle.getPredictionYear());

            ImageView post_image=(ImageView)rootView.findViewById(R.id.post_image);
            imageLoader.DisplayImage(mArticle.getImageUrl(), post_image);
            ((TextView) rootView.findViewById(R.id.likelihood)).setText(mArticle.getLikeliness());

        }

        return rootView;
    }
}
