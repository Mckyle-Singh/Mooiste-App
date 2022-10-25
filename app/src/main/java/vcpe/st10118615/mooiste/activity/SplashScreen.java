package vcpe.st10118615.mooiste.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import vcpe.st10118615.mooiste.R;
import vcpe.st10118615.mooiste.util.Utils;

import io.paperdb.Paper;

public class SplashScreen extends AppCompatActivity {
    private SharedPreferences prefs;
    private boolean firstStart;
    private boolean isGoToGps = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Paper.init(SplashScreen.this);
        final String temp = Paper.book().read("active");
        Utils.statusBarColor(SplashScreen.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(temp!=null){
                    if(temp.equals("user")){
                        startActivity(new Intent(SplashScreen.this,MainActivity.class));
                        finish();
                    }
//                    if(temp.equals("admin")){
//                        startActivity(new Intent(SplashScreen.this, AdminHome.class));
//                        finish();
//                    }
                }
                else{
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    finish();
                }
            }
        },1000);
    }
}
