package e.antti.igdbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends android.app.ListActivity {

    ListView listView;

    ArrayList<HashMap<String,String>> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = getListView();

        ListAdapter adapter = new SimpleAdapter(this,
                data,
                R.layout.list_item,
                new String[]{"name","food"},
                new int[]{R.id.name,R.id.food });

        listView.setAdapter(adapter);

    }
}
