package e.antti.igdbapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListActivity extends android.app.ListActivity {

    ListView listView;

    ArrayList<HashMap<String,String>> data = new ArrayList<>();

    private static final String TAG_NAME = "name";
    private static final String TAG_ID = "id";
    private static final String TAG_RATING = "rating";
    private static final String TAG_RATING_COUNT = "rating_count";
    private static final String TAG_AGGREGATED_RATING = "aggregated_rating";
    private static final String TAG_AGGREGATED_RATING_COUNT = "aggregated_rating_count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = getListView();

        GameWrapper gameWrapper = (GameWrapper) getIntent().getSerializableExtra("game");

        ArrayList<Game> gameArrayList = gameWrapper.getItemDetails();
        HashMap<String,String> map = new HashMap<>();

        for (int i =0; i < gameArrayList.size(); i++) {
            Game game = gameArrayList.get(i);
            Log.d("listActivity", game.getgName());
            Log.d("listActivity", game.getuScore());
            Log.d("listActivity", game.getuScoreCount());
            Log.d("listActivity", game.getScore());
            Log.d("listActivity", game.getScoreCount());


            map.put(TAG_NAME, game.getgName());
            map.put(TAG_RATING, game.getuScore());
            map.put(TAG_RATING_COUNT, game.getScoreCount());
            map.put(TAG_AGGREGATED_RATING, game.getScore());
            map.put(TAG_AGGREGATED_RATING_COUNT, game.getScoreCount());
        }


//        hashMap.put("name",gameArrayList.get(0).getName());
//        hashMap.put("food",gameArrayList.get(1).getName());
        data.add(map);

        ListAdapter adapter = new SimpleAdapter(this,
                data,
                R.layout.list_item,
                new String[]{"name","food"},
                new int[]{R.id.name,R.id.argument1 });

        listView.setAdapter(adapter);

    }
}
