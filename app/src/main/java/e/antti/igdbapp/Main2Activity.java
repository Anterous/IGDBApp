package e.antti.igdbapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG_NAME = "name";
    private static final String TAG_ID = "id";
    private static final String TAG_RATING = "rating";
    private static final String TAG_RATING_COUNT = "rating_count";
    private static final String TAG_AGGREGATED_RATING = "aggregated_rating";
    private static final String TAG_AGGREGATED_RATING_COUNT = "aggregated_rating_count";

    TextView gameName;
    TextView userScore;
    TextView userScoreCount;
    TextView criticScore;
    TextView criticScoreCount;
    TextView gameSite;

//    private String gName;
//    private String uScore;
//    private String uScoreCount;
//    private String cScore;
//    private String cScoreCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gameName = (TextView) findViewById(R.id.gameName);
        userScore = (TextView) findViewById(R.id.userScore);
        userScoreCount = (TextView) findViewById(R.id.userScoreCount);
        criticScore = (TextView) findViewById(R.id.criticScore);
        criticScoreCount = (TextView) findViewById(R.id.criticScoreCount);
        gameSite = (TextView) findViewById(R.id.siteURL);
        ImageView imageView = findViewById(R.id.image);

        Intent intent = getIntent();
        GameWrapper gameWrapper = (GameWrapper) getIntent().getSerializableExtra("game");
        long id = intent.getLongExtra(TAG_ID, 0);

        ArrayList<Game> gameArrayList = gameWrapper.getItemDetails();

        Game game = gameArrayList.get((int) id);

        gameName.append(game.getgName());
        userScore.append(game.getuScore());
        userScoreCount.append(game.getuScoreCount());
        criticScore.append(game.getScore());
        criticScoreCount.append(game.getScoreCount());
        gameSite.setText(game.getSiteURL());

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("images.igdb.com")
                .appendPath("igdb")
                .appendPath("image")
                .appendPath("upload")
                .appendPath("t_720p")
                .appendPath(game.getUrl()+".png");

        String myUrl = builder.build().toString();

        Picasso.get().load(myUrl).resize(360,640).into(imageView);


    }
}
