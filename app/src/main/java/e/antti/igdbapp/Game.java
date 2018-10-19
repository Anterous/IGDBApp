package e.antti.igdbapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {

    private static final long serialVersionUID = 1L;//wat
    private String gName;
    private String uScore;
    private String uScoreCount;
    private String score;
    private String scoreCount;
    private String url;

    public Game(String gameName, String userScore, String userScoreC, String score, String scoreCount, String url){

        this.gName = gameName;
        this.uScore = userScore;
        this.uScoreCount = userScoreC;
        this.score = score;
        this.scoreCount = scoreCount;
        this.url = url;

    }
//    public Game(Parcel in){
//        gName = in.readString();
//        uScore = in.readString();
//        uScoreCount = in.readString();
//        score = in.readString();
//        scoreCount = in.readString();
//
//    }
    public String getgName(){
        return this.gName;
    }
    public String getuScore(){
        return this.uScore;
    }
    public String getuScoreCount(){
        return this.uScoreCount;
    }
    public String getScore(){
        return this.score;
    }
    public String getScoreCount(){
        return this.scoreCount;
    }
    public String getUrl(){return this.url;}

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeString(gName);
//        parcel.writeString(uScore);
//        parcel.writeString(uScoreCount);
//        parcel.writeString(score);
//        parcel.writeString(scoreCount);
//
//    }
//    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>()
//    {
//        public Game createFromParcel(Parcel in)
//        {
//            return new Game(in);
//        }
//        public Game[] newArray(int size)
//        {
//            return new Game[size];
//        }
//    };

}
class GameWrapper implements Serializable{

    private static final long serialVersionUID = 1L;//wat
    private ArrayList<Game> games;

    GameWrapper( ArrayList<Game> games){
        this.games = games;
    }

    public ArrayList<Game> getItemDetails() {
        return games;
    }

}
