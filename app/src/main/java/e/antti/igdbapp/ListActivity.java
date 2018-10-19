package e.antti.igdbapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
                String argument1 = ((TextView) view.findViewById(R.id.argument1)).getText().toString();
                String argument2 = ((TextView) view.findViewById(R.id.argument2)).getText().toString();
                String argument3 = ((TextView) view.findViewById(R.id.argument3)).getText().toString();
                String argument4 = ((TextView) view.findViewById(R.id.argument4)).getText().toString();

                // Starting new intent
                Intent in = new Intent(getApplicationContext(), MainActivity.class);//TODO change activity 2 singleitemactivity lol
                in.putExtra(TAG_NAME, name);
                in.putExtra(TAG_RATING, argument1);
                in.putExtra(TAG_RATING_COUNT, argument2);
                in.putExtra(TAG_AGGREGATED_RATING, argument3);
                in.putExtra(TAG_AGGREGATED_RATING_COUNT, argument4);

                startActivity(in);

            }
        });


        GameWrapper gameWrapper = (GameWrapper) getIntent().getSerializableExtra("game");

        ArrayList<Game> gameArrayList = gameWrapper.getItemDetails();


        for (int i =0; i < gameArrayList.size(); i++) {
            Game game = gameArrayList.get(i);
            HashMap<String,String> map = new HashMap<>();
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
            data.add(map);
        }
        ListAdapter adapter = new SimpleAdapter(this,
                data,
                R.layout.list_item,
                new String[]{"name","food"},
                new int[]{R.id.name,R.id.argument1 });

        listView.setAdapter(adapter);

    }
}
