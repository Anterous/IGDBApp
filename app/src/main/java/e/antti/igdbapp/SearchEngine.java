package e.antti.igdbapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.igdb.api_android_java.callback.OnSuccessCallback;
import com.igdb.api_android_java.wrapper.Endpoint;
import com.igdb.api_android_java.wrapper.IGDBWrapper;
import com.igdb.api_android_java.wrapper.Parameters;
import com.igdb.api_android_java.wrapper.Version;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class SearchEngine extends Thread{

    private Context mContext;
    public SearchEngine searchEngine;
    private String query;
    private String endpoint;

    private String rating;
    private String ratingCount;
    private String ag_rating;
    private String ag_rating_count;
    private String pic_url;

    ArrayList<Game> gameArrayList;

    private static final String TAG_NAME = "name";
    private static final String TAG_PICTURE = "cover";
    private static final String TAG_RATING = "rating";
    private static final String TAG_RATING_COUNT = "rating_count";
    private static final String TAG_AGGREGATED_RATING = "aggregated_rating";
    private static final String TAG_AGGREGATED_RATING_COUNT = "aggregated_rating_count";

    OnsearchCompleteInterface onSearchComplete;

    public void setListener(OnsearchCompleteInterface listener){ this.onSearchComplete = listener; }

    public void setParams(String keyword) { this.query = keyword; }

    public String getParams(){ return query; }

    public void setEndpoint(String end) { this.endpoint = end; }

    public String getEndpoint(){
        return endpoint;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void run() {
        mContext = getContext();
        endpoint = getEndpoint();
        query = getParams();
        IGDBWrapper wrapper = new IGDBWrapper(mContext,"7e15a0f6c16e9c907f4063d2624d8fc4", Version.STANDARD, false);
        Parameters params = new Parameters()
                .addSearch(query)
                .addFields("*")
                .addOrder("popularity:desc");
        searchEngine = this;

        Log.d("JPARSE", "run: "+ endpoint);
        wrapper.search(Endpoint.valueOf(endpoint), params, new OnSuccessCallback() {
            @Override
            public void onError(@NotNull VolleyError volleyError) {
                volleyError.printStackTrace();
            }

            @Override
            public void onSuccess(JSONArray result) {
                try {

                    gameArrayList = new ArrayList<>();
                    for (int i = 0; i < result.length(); i++) {

                        JSONObject c = result.getJSONObject(i);
                        //Log.d("JPARSE", "onSuccess: " + c.toString());
                        // Storing each json item in variable
                        String name = c.getString(TAG_NAME);
                        //String id = c.getString(TAG_ID);
                        rating = "";
                        ratingCount = "";
                        ag_rating = "";
                        ag_rating_count = "";
                        if (c.has(TAG_RATING)) {
                            rating = c.getString(TAG_RATING);
                        }
                        if (c.has(TAG_RATING_COUNT)) {
                            ratingCount = c.getString(TAG_RATING_COUNT);
                        }
                        if (c.has(TAG_AGGREGATED_RATING)) {
                            ag_rating = c.getString(TAG_AGGREGATED_RATING);
                        }
                        if (c.has(TAG_AGGREGATED_RATING_COUNT)) {
                            ag_rating_count = c.getString(TAG_AGGREGATED_RATING_COUNT);
                        }
                        if (c.has(TAG_PICTURE)) {
                            pic_url = c.getString(TAG_PICTURE);
                        }
                        Log.d("JPARSE", "JPARSE NAME: " + name);
                        Log.d("JPARSE", "JPARSE SCORE: " + rating);
                        Log.d("JPARSE", "JPARSE RATING COUNT: " + ratingCount);
                        Log.d("JPARSE", "JPARSE CRITIC RATING: "+ ag_rating);
                        Log.d("JPARSE", "JPARSE CRITIC RATING COUNT: "+ ag_rating_count);
                        Log.d("JPARSE", "PIC URL: " + pic_url);

                        Game game = new Game(name, rating, ratingCount, ag_rating, ag_rating_count);
                        gameArrayList.add(game);

                    }

                    GameWrapper gameWrapper = new GameWrapper(gameArrayList);
                    searchEngine.onSearchComplete.onSearchComplete(gameWrapper);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public interface OnsearchCompleteInterface{
        void onSearchComplete(GameWrapper gameWrapper);
    }

}

