package e.antti.igdbapp;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

    public class PicassoClient {

        public static void downloadImage(Context c,String url,ImageView img)
        {
            if(url != null && url.length()>0)
            {
                Picasso.get().load(url).into(img);
            }else
            {

            }
        }

    }

