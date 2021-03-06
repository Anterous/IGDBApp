package e.antti.igdbapp;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Game> Games;
    LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList<Game> games) {
        this.c = c;
        this.Games = games;
    }

    @Override
    public int getCount() {
        return Games.size();
    }

    @Override
    public Object getItem(int position) {
        return Games.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null)
        {
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.list_item,parent,false);

        }

        //BIND DATA
        MyHolder holder=new MyHolder(convertView);
        holder.nameTxt.setText(Games.get(position).getgName());
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("images.igdb.com")
                .appendPath("igdb")
                .appendPath("image")
                .appendPath("upload")
                .appendPath("t_thumb")
                .appendPath(Games.get(position).getUrl()+".png");

        String myUrl = builder.build().toString();
       // PicassoClient.downloadImage(c,Games.get(position).getUrl(),holder.img);
        Picasso.get().load(myUrl).into(holder.img);
        return convertView;
    }
}
