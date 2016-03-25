package project.com.crystalpredictor;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

                                //Declaring the Variables for the items on the layout


    public static final String Tag= MainActivity.class.getSimpleName();
    TextView answerlabel1;
    Button answerButton;
    ImageView ballImage;
    SensorManager mSensorManager;
    Sensor mSensor;
    ShakeDetector mShakeDetector;



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                             //Accessing all the view object on the layout



        answerlabel1 = (TextView) findViewById(R.id.textView);
        answerButton = (Button) findViewById(R.id.button);
        ballImage = (ImageView) findViewById(R.id.imageView);

                            //calling the CrystalBall Method



        crystallBall();
        shakeDetector();
        ToastMessage();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        Log.d(Tag,"We are logging from the main activity");


    }

    @Override
    protected void  onResume()
    {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector,mSensor,SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mSensorManager.unregisterListener(mShakeDetector);
    }


                                    //Declaring the crystallBall Method



    public void crystallBall()
    {
        final CrystalBall mCrystalBall = new CrystalBall(); //initializing the CrystalBall object

        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(mCrystalBall);
            }
        });
    }


                                /*
                                //refactored the function of the onclick listener into method
                                */


    public void getAnswer(CrystalBall mCrystalBall) {
        String answer;
        answer = mCrystalBall.getAnswer();

        answerlabel1.setText(answer);

        AnimateCrystalBall();
        AnimateAnswer();
        Music();
    }

                                /*
                                //Declaring the Animate CrystalBall Method
                                */


    public void AnimateCrystalBall() {

        ballImage.setImageResource(R.drawable.ball_animation);
        AnimationDrawable ballAnimation  = (AnimationDrawable)ballImage.getDrawable();
        if(ballAnimation.isRunning())
        {

        }
        ballAnimation.start();
    }


                            /*
                            //Declaring the AnimateAnswer Method
                            */


    public void AnimateAnswer()
    {
        AlphaAnimation fadeAnimation = new AlphaAnimation(0,1);
        fadeAnimation.setDuration(1500);
        fadeAnimation.setFillAfter(false);

        answerlabel1.setAnimation(fadeAnimation);
    }


                            /*
                            //Declaring the MediaPlayer Method
                             */


    public void Music()
    {
        MediaPlayer player = MediaPlayer.create(this,R.raw.crystal_ball);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }



                        /*
                        //Declaring the shake detector method
                        */


    public void shakeDetector()
    {
        final CrystalBall mCrystalBall = new CrystalBall(); //initializing the CrystalBall object

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensor = (Sensor)mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                getAnswer(mCrystalBall);
            }
        });
    }



                                    /*
                                    Toast Message for the Screen At the center
                                     */

    public void ToastMessage()
    {
        String Message = "Welcome to the CrystalBall Predcitor";
        Toast mToast = Toast.makeText(this,Message,Toast.LENGTH_LONG);
        mToast.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
        mToast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://project.com.crystalpredictor/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://project.com.crystalpredictor/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
