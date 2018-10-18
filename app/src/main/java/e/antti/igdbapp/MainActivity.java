package e.antti.igdbapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.igdb.api_android_java.callback.OnSuccessCallback;
import com.igdb.api_android_java.wrapper.Endpoint;
import com.igdb.api_android_java.wrapper.IGDBWrapper;
import com.igdb.api_android_java.wrapper.Parameters;
import com.igdb.api_android_java.wrapper.Version;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchEngine.OnsearchCompleteInterface {

    Button button;
    Spinner spinner;
    EditText keywordEdit;
    SearchEngine searchEngine;

    private String rating;
    private String ratingCount;
    private String ag_rating;
    private String ag_rating_count;

    private static final String TAG_NAME = "name";
    private static final String TAG_ID = "id";
    private static final String TAG_RATING = "rating";
    private static final String TAG_RATING_COUNT = "rating_count";
    private static final String TAG_AGGREGATED_RATING = "aggregated_rating";
    private static final String TAG_AGGREGATED_RATING_COUNT = "aggregated_rating_count";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        spinner = findViewById(R.id.Endpoint_spinner);
        keywordEdit = findViewById(R.id.edit_keyword);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.endpoint_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void callSearch(View view) {
        searchEngine = new SearchEngine();
        String endpoint = spinner.getSelectedItem().toString().toUpperCase();
        String keyword = keywordEdit.getText().toString();
        searchEngine.setContext(this);
        searchEngine.setEndpoint(endpoint);
        searchEngine.setParams(keyword);
        searchEngine.setListener(this);
        searchEngine.start();

    }

    @Override
    public void onSearchComplete(JSONArray data) throws JSONException {
        //Log.d("JPARSE", "onSearchComplete: " + data.toString());

        for (int i = 0; i < data.length(); i++) {

            JSONObject c = data.getJSONObject(i);
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
            Log.d("JPARSE", "JPARSE NAME: " + name);
            Log.d("JPARSE", "JPARSE SCORE: " + rating);
            Log.d("JPARSE", "JPARSE RATING COUNT: " + ratingCount);
            Log.d("JPARSE", "JPARSE CRITIC RATING: "+ ag_rating);
            Log.d("JPARSE", "JPARSE CRITIC RATING COUNT: "+ ag_rating_count);

            ArrayList<HashMap<String, String>> resultList = new ArrayList<>();

            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();

            // adding each child node to HashMap key => value
            map.put(TAG_NAME, name);
            map.put(TAG_RATING, rating);
            map.put(TAG_RATING_COUNT, ratingCount);
            map.put(TAG_AGGREGATED_RATING, ag_rating);
            map.put(TAG_AGGREGATED_RATING_COUNT, ag_rating_count);

            //adding HashList to ArrayList
            resultList.add(map);




            Log.d("JPARSEE", "onSearchComplete: "+ String.valueOf(resultList));


        }

        Game game = new Game("ass odyssey");
        Game game1 = new Game("path of grind");
        Game game2 = new Game("generic battle Royale");
        ArrayList<Game> gameArrayList = new ArrayList<>();
        gameArrayList.add(game);
        gameArrayList.add(game1);
        gameArrayList.add(game2);
        Log.d("listActivity", "onSearchComplete: soon to listactivity");

        GameWrapper gameWrapper = new GameWrapper(gameArrayList);
        Intent intent = new Intent(this,ListActivity.class);
        intent.putExtra("game",gameWrapper );


        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}

class GameWrapper implements Serializable{

    private static final long serialVersionUID = 1L;//wat
    private ArrayList<Game> games;

    GameWrapper( ArrayList<Game> games){
        this.games = games;
    }

    public ArrayList<Game> getItemDetails() {
        return games;
    }

}

class Game implements Serializable{

    private static final long serialVersionUID = 1L;//wat
    private String name;

    Game(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
