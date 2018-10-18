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


public class SearchEngine extends Thread{

    private Context mContext;
    private JSONObject c;
    public SearchEngine searchEngine;
    private String query;
    private String endpoint;
    private String rating;
    private String ratingCount;
    public JSONArray DATA;

    OnsearchCompleteInterface onSearchComplete;


    public void setListener(OnsearchCompleteInterface listener){
        this.onSearchComplete = listener;
    }

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
                    searchEngine.onSearchComplete.onSearchComplete(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });
    }


    public interface OnsearchCompleteInterface{
        void onSearchComplete(JSONArray data) throws JSONException;
    }

}

