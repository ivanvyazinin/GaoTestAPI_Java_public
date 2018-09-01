package main.java.entities.contentCloud.blocks.practice;

import main.java.entities.contentCloud.blocks.ReusableBlock;

public class CommonPracticeBlock extends ReusableBlock {
    public Boolean showCorrectAnswer;
    public int attempts;
    public Scoring scoring;

    public static class Scoring{
        public String mode;
        public int points;

        public Scoring(){
            this.mode = "Score entire block";
            this.points = 1;
        }

        public Scoring(int points){
            this.mode = "Score entire block";
            this.points = points;
        }
    }
}
