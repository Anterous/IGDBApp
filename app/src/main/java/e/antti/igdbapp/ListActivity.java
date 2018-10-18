package e.antti.igdbapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListActivity extends android.app.ListActivity {

    ListView listView;

    ArrayList<? extends HashMap<String, String>> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = getListView();

        ListAdapter adapter = new SimpleAdapter(this,
                data,
                R.layout.list_item,
                new String[]{"NIMI","food"},
                new int[]{R.id.name,R.id.argument1 });

        listView.setAdapter(adapter);

    }
}
