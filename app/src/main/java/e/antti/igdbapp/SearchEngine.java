package e.antti.igdbapp;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.VolleyError;
import com.igdb.api_android_java.callback.OnSuccessCallback;
import com.igdb.api_android_java.wrapper.Endpoint;
import com.igdb.api_android_java.wrapper.IGDBWrapper;
import com.igdb.api_android_java.wrapper.Parameters;
import com.igdb.api_android_java.wrapper.Version;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;


public class SearchEngine extends Thread{

    private Context mContext;
    private JSONObject c;
    private String query;
    private  String endpoint;
    private String rating;
    OnsearchCompleteInterface onSearchComplete;
    private static final String TAG_NAME = "name";
    private static final String TAG_ID = "id";
    private static final String TAG_RATING = "rating";

    public void setListener(OnsearchCompleteInterface listener){
        this.onSearchComplete = listener;
    }

    public void setParams(String params) {
        this.query = params;
    }
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
        IGDBWrapper wrapper = new IGDBWrapper(mContext,"7e15a0f6c16e9c907f4063d2624d8fc4", Version.STANDARD, false);
        Parameters params = new Parameters()
                .addSearch("spyro")
                .addFields("rating,name")
                .addOrder("published_at:desc");

        Log.d("JPARSE", "run: "+ endpoint);
        wrapper.search(Endpoint.valueOf(endpoint), params, new OnSuccessCallback(){
            @Override
            public void onError(@NotNull VolleyError volleyError) {
                    volleyError.printStackTrace();
            }

            @Override
            public void onSuccess(JSONArray result) {
                try{
                    for (int i= 0; i < result.length();i++) {

                         c = result.getJSONObject(i);
                         //Log.d("JPARSE", "onSuccess: " + c.toString());
                        // Storing each json item in variable
                        String name = c.getString(TAG_NAME);
                           //String id = c.getString(TAG_ID);
                        rating = "";
                           Log.d("JPARSE", "JPARSE NAME: " + name);
                           //Log.d("JPARSE", "JPARSE ID: " + id);
                           Log.d("JPARSE", "JPARSE SCORE: " + rating);


                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        //this.onSearchComplete.onSearchComplete(c);
    }

    public interface OnsearchCompleteInterface{
        void onSearchComplete(JSONObject data);
    }
}
