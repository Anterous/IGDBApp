package e.antti.igdbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

    private String gName;
    private String uScore;
    private String uScoreCount;
    private String cScore;
    private String cScoreCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gameName = (TextView) findViewById(R.id.gameName);
        userScore = (TextView) findViewById(R.id.userScore);
        userScoreCount = (TextView) findViewById(R.id.userScoreCount);
        criticScore = (TextView) findViewById(R.id.criticScore);
        criticScoreCount = (TextView) findViewById(R.id.criticScoreCount);

        Intent intent = getIntent();
        gName = intent.getStringExtra(TAG_NAME);
        uScore = intent.getStringExtra(TAG_RATING);
        uScoreCount = intent.getStringExtra(TAG_RATING_COUNT);
        cScore = intent.getStringExtra(TAG_AGGREGATED_RATING);
        cScoreCount = intent.getStringExtra(TAG_AGGREGATED_RATING_COUNT);

        gameName.append(gName);
        userScore.append(uScore);
        userScoreCount.append(uScoreCount);
        criticScore.append(cScore);
        criticScoreCount.append(cScoreCount);


    }
}
