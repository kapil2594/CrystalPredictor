package project.com.crystalpredictor;

import java.util.Random;

/**
 * Created by redskull on 3/12/2016.
 */
public class CrystalBall {
    public    String[] answers = {"It is certain",
            "It is decidedly so",
            "All sign say Yes",
            "The stars are not aligned",
            "My reply is No",
            "It is Doubtful",
            "Better not Tell you Now",
            "Concentrate and ask again",
            "Unable to answer Now"
    };
    String answer;
    public String getAnswer()
    {
        Random mRandom = new Random();
        int randomNumber = mRandom.nextInt(answers.length);
        answer  = answers[randomNumber];
        return answer;
    }
}
