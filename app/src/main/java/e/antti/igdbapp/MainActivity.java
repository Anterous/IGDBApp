package e.antti.igdbapp;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.igdb.api_android_java.callback.OnSuccessCallback;
import com.igdb.api_android_java.wrapper.Endpoint;
import com.igdb.api_android_java.wrapper.IGDBWrapper;
import com.igdb.api_android_java.wrapper.Parameters;
import com.igdb.api_android_java.wrapper.Version;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements SearchEngine.OnsearchCompleteInterface {

    Button button;
    Spinner spinner;
    EditText keywordEdit;
    SearchEngine searchEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.Endpoint_spinner);
        keywordEdit = findViewById(R.id.edit_keyword);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.endpoint_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ImageView imageView = findViewById(R.id.image);
        Picasso.get().load("https://lh3.googleusercontent.com/16TTxwHqp3GMQhsYketfNeCBYF0x3HIMltZlsq_CpFPfOCv2lV1RSRBYjkVfTJIkJA").resize(1000,800).into(imageView);
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
    public void onSearchComplete(GameWrapper gameWrapper) {

        Intent intent = new Intent(this,ListActivity.class);
        intent.putExtra("game",gameWrapper );

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}




