package ipragmatech.com.mobilelogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ipragmatech.com.mobilelogin.activity.MobileVerificationActivity;
import ipragmatech.com.mobilelogin.response.User;

public class MainActivity extends AppCompatActivity {
    private Button signInMobileButton;
    private static final String PREF_USERID = "userId";
    private static final String PREF_USERNAME = "username";
    public static final String TAG = "MobileLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signInMobileButton = (Button)findViewById(R.id.login_btn_mobile);
        signInMobileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MobileVerificationActivity.class);
                startActivity(intent);
            }
        });
    }
    public String getDeviceIdFromPrefrences(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getString("deviceId", "")!=null){
            return preferences.getString("deviceId","");
        }else return null;
    }
    public User getUserObjectFromPrefrences(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getInt(PREF_USERID, 0) !=0){
            User userObject = new User();
            userObject.setUserId(preferences.getInt(PREF_USERID, 0));
            userObject.setUsername(preferences.getString(PREF_USERNAME, ""));
            return userObject;
        }
        return null;
    }
}
