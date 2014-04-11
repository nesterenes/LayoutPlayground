package com.oneupapplications.layoutplayground;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.oneupapplications.layoutplayground.model.article;
import com.oneupapplications.layoutplayground.utility.ImageLoader;
import com.oneupapplications.layoutplayground.utility.LazyAdapter;

import java.util.ArrayList;
import java.util.Arrays;


public class ArtListFragment  extends ListFragment {

    public static final String ARG_ITEM_Key = "ArticleKey";

    public ArrayList<article> ArticlesArray = new ArrayList<article>();

    // Mandatory empty constructor for the fragment manager to instantiate
    public ArtListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if(ArticlesArray.size() < 1 && savedInstanceState == null){
            ArticlesArray =  getStaticArticles();
        } else if (savedInstanceState.containsKey(ARG_ITEM_Key)) {
            //String tempContent = getArguments().getString(ARG_ITEM_Key);
            //ArticlesArray = new ArrayList<article>(Arrays.asList(new Gson().fromJson(tempContent, article[].class)));
            ArticlesArray =  getStaticArticles();
        }

        setListAdapter(new LazyAdapter(getActivity(), ArticlesArray));
    } // END onCreate

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null) {

        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (ArticlesArray.size() > 1) {
            //String myJsonArticle = new Gson().toJson(ArticlesArray, article.class);
            // Serialize and persist the activated item position.
            //outState.putString("ArticleKey", myJsonArticle);
            outState.putString("ArticleKey", "11");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //Toast mToast = Toast.makeText(getActivity(), Long.toString(id), Toast.LENGTH_SHORT);
        //mToast.show();

        Object o = l.getAdapter().getItem(position);
        article fullObject = (article)o;

        //Toast mToast = Toast.makeText(getActivity(), fullObject.getPostTitle(), Toast.LENGTH_SHORT);
        //mToast.show();

        onArtSelChngListener listener = (onArtSelChngListener)getActivity();
        listener.onArtSelChanged(fullObject);


    } // END onListItemClick

    private ArrayList<article> getStaticArticles() {

        ArrayList<article> rtn = new ArrayList<article>();

        article art1 = new article();
        art1.setId(101);
        art1.setTags("tags tags 1");
        art1.setTags("tags tags 1");
        art1.setPredictionYear("2015");
        art1.setPostTitle("Headline 1 - Lorem ipsum dolor sit amet, consectetur adipisicing");
        art1.setPostBody("Body Copy 1 - 12pt - Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        art1.setImageUrl("http://api.androidhive.info/music/images/adele.png");
        art1.setLikeliness("low");

        article art2 = new article();
        art2.setId(202);
        art2.setTags("tags 222");
        art2.setPredictionYear("2015");
        art2.setPostTitle("Headline 2 - Lorem ipsum dolor sit amet, consectetur adipisicing");
        art2.setPostBody("Body Copy 2 - 12pt - Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        art2.setImageUrl("http://api.androidhive.info/music/images/eminem.png");
        art2.setLikeliness("high");

        // adding HashList to ArrayList
        rtn.add(art1);
        rtn.add(art2);

        return rtn;
    }  //END ArrayList<article> getStaticArticles()

}
