package com.oneupapplications.layoutplayground.ui;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.oneupapplications.layoutplayground.api.Controller.WsAPIController;
import com.oneupapplications.layoutplayground.model.Article;
import com.oneupapplications.layoutplayground.utility.LazyAdapter;
import com.oneupapplications.layoutplayground.utility.WSHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ArtListFragment  extends ListFragment {

    //TODO TESTING
    private ProgressDialog pDialog;
    // URL to get contacts JSON
    private static String url1 = "http://api.icndb.com/jokes/random?limitTo=[nerdy]";
    private static String url = "http://google2099.com/api/Article";
    private static String url_single = "http://google2099.com/api/Article?id=2";

    // JSON Node names
    private static final String TAG_VALUES = "value";
    private static final String TAG_ID = "id";
    //private static final String TAG_CATEGORY = "category";
    private static final String TAG_JOKE = "joke";

    // jokes JSONArray
    JSONArray jokes = null;

    // jokes JSONArray
    JSONArray articles_json = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> jokeList;

    private WsAPIController controller = null;

    private String TmpInput;

    //END TODO TESTING


    public static final String ARG_ITEM_Key = "ArticleKey";

    public ArrayList<Article> ArticlesArray = new ArrayList<Article>();

    // Mandatory empty constructor for the fragment manager to instantiate
    public ArtListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


        MakeVolleyball();

        if(ArticlesArray.size() < 1 && savedInstanceState == null){
            ArticlesArray =  getStaticArticles();
        } else if (savedInstanceState.containsKey(ARG_ITEM_Key)) {
            //String tempContent = getArguments().getString(ARG_ITEM_Key);
            //ArticlesArray = new ArrayList<article>(Arrays.asList(new Gson().fromJson(tempContent, article[].class)));
            ArticlesArray =  getStaticArticles();
        }

        setListAdapter(new LazyAdapter(getActivity(), ArticlesArray));

        new GetWSArticles().execute();

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
        Article fullObject = (Article)o;

        //Toast mToast = Toast.makeText(getActivity(), fullObject.getPostTitle(), Toast.LENGTH_SHORT);
        //mToast.show();

        onArtSelChngListener listener = (onArtSelChngListener)getActivity();
        listener.onArtSelChanged(fullObject);


    } // END onListItemClick

    private ArrayList<Article> getStaticArticles() {

        ArrayList<Article> rtn = new ArrayList<Article>();

        Article art1 = new Article();
        art1.setId(101);
        art1.setTags("tags tags 1");
        art1.setTags("tags tags 1");
        art1.setPredictionYear("2015");
        art1.setPostTitle("Headline 1 - Lorem ipsum dolor sit amet, consectetur adipisicing");
        art1.setPostBody("Body Copy 1 - 12pt - Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        art1.setImageUrl("http://api.androidhive.info/music/images/adele.png");
        art1.setLikeliness("low");

        Article art2 = new Article();
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




    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetWSArticles extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            WSHandler sh = new WSHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, WSHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {

//
//                    JSONObject jsonItem = jsonObj.getJSONObject(TAG_VALUES);
//
//                    String id = jsonItem.getString(TAG_ID);
//                    String joke = jsonItem.getString(TAG_JOKE);
//
//                    // tmp hashmap for single joke
//                    HashMap<String, String> jokeHash = new HashMap<String, String>();
//
//                    // adding each child node to HashMap key => value
//                    jokeHash.put(TAG_ID, id);
//                    jokeHash.put(TAG_JOKE, joke);
//
//                    // adding joke to joke list
//                    jokeList.add(jokeHash);
//
//
                    //JSONObject jsonObj = new JSONObject(jsonStr);

                    articles_json = new JSONArray(jsonStr);

                    List<Article> myList = new ArrayList<Article>();

                    for (int i = 0; i < articles_json.length(); i++) {

                        JSONObject jsonobj_tmp = articles_json.getJSONObject(i);

                        Article artTemp = new Article();

                        artTemp.setId(Integer.parseInt(jsonobj_tmp.getString("Id")));
                        artTemp.setPostTitle(jsonobj_tmp.getString("PostTitle"));
                        artTemp.setPostBody(jsonobj_tmp.getString("PostBody"));
                        artTemp.setLikeliness(jsonobj_tmp.getString("Likeliness"));
                        artTemp.setTags(jsonobj_tmp.getString("Tags"));
                        artTemp.setPredictionYear(jsonobj_tmp.getString("PredictionYear"));
                        artTemp.setImageUrl("http://api.androidhive.info/music/images/eminem.png");


                    }

                    // Getting JSON Array node
                    //jokes = jsonObj.getJSONArray(TAG_VALUES);

                    // looping through All Contacts
                    /*for (int i = 0; i < jokes.length(); i++) {
                        JSONObject c = jokes.getJSONObject(i);

                        String id = c.getString(TAG_ID);
                        //String category = c.getString(TAG_CATEGORY);
                        String joke = c.getString(TAG_JOKE);

                        // Phone node is JSON Object

                        // tmp hashmap for single contact
                        HashMap<String, String> jokeHash = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        jokeHash.put(TAG_ID, id);
                        jokeHash.put(TAG_JOKE, joke);

                        // adding contact to contact list
                        jokeList.add(jokeHash);
                    }*/
                } catch (Exception e) {
                    Log.d("tmpArticle", e.toString());
                }
            } else {
                Log.e("WSHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            //ListAdapter adapter = new SimpleAdapter(
            //        TestHandler.this, jokeList,
             //       R.layout.activity_test_listitem, new String[] { TAG_JOKE }, new int[] { R.id.joke });

            //setListAdapter(adapter);
        }

    }


    protected void MakeVolleyball() {



        controller = WsAPIController.getInstance(getActivity());

        //int id, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener
        controller.getArticleById(1, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {

                    Log.i("VolleyInfo", "");

                    String tmpString = jsonObject.getString("Id");

                    Toast mToast = Toast.makeText(getActivity(),tmpString, Toast.LENGTH_SHORT);
                    mToast.show();

                    //txtJoke.setText(jsonObject.getJSONObject("value").getString("joke"));

                } catch (Exception e) {
                    //txtJoke.setText(e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //txtJoke.setText(volleyError.getMessage());
                Log.i("VolleyInfo", volleyError.toString());
            }
        });


    }

}
