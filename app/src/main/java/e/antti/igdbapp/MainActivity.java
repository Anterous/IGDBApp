package e.antti.igdbapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.igdb.api_android_java.callback.OnSuccessCallback;
import com.igdb.api_android_java.wrapper.Endpoint;
import com.igdb.api_android_java.wrapper.IGDBWrapper;
import com.igdb.api_android_java.wrapper.Parameters;
import com.igdb.api_android_java.wrapper.Version;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements SearchEngine.OnsearchCompleteInterface {

    Button button;
    TextView textView;
    Spinner spinner;
    SearchEngine searchEngine;


    private static final String TAG_NAME = "name";
    private static final String TAG_ID = "id";
    private static final String TAG_RATING = "rating";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final IGDBWrapper wrapper = new IGDBWrapper(this,"7e15a0f6c16e9c907f4063d2624d8fc4", Version.STANDARD, false);

        spinner = findViewById(R.id.Endpoint_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.endpoint_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void callSearch(View view) {
        searchEngine = new SearchEngine();
        String endpoint = spinner.getSelectedItem().toString().toUpperCase();
        //Log.d("JPARSE", "callsearch:" + endpoint);
        searchEngine.setContext(this);
        searchEngine.setEndpoint(endpoint);
        //searchEngine.setParams(endpoint);
        //searchEngine.setListener(this);
        searchEngine.start();

    }

    @Override
    public void onSearchComplete(JSONObject data) {
        Log.d("JPARSE", "onSearchComplete: ");
    }
}
