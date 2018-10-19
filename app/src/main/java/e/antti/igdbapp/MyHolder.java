package e.antti.igdbapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyHolder {

    public TextView nameTxt;
    public ImageView img;

    public MyHolder(View v) {

        nameTxt =  v.findViewById(R.id.name);
        img= v.findViewById(R.id.imageView);

    }
}
