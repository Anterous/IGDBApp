package e.antti.igdbapp;

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
