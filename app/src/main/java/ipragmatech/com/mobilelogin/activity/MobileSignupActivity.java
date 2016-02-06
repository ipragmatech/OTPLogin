package ipragmatech.com.mobilelogin.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.joanzapata.android.asyncservice.api.annotation.InjectService;
import com.joanzapata.android.asyncservice.api.annotation.OnMessage;
import com.joanzapata.android.asyncservice.api.internal.AsyncService;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;

import ipragmatech.com.mobilelogin.MainActivity;
import ipragmatech.com.mobilelogin.R;
import ipragmatech.com.mobilelogin.request.BaseRequestObject;
import ipragmatech.com.mobilelogin.request.MobileSignupRequestObject;
import ipragmatech.com.mobilelogin.response.MobileVerificationResponse;
import ipragmatech.com.mobilelogin.response.PostDataResponse;
import ipragmatech.com.mobilelogin.response.User;
import ipragmatech.com.mobilelogin.service.MobileService;
import ipragmatech.com.mobilelogin.service.SettingMobileService;

/**
 * Created by narender on 30/1/16.
 */
public class MobileSignupActivity extends MainActivity implements Validator.ValidationListener{

    @NotEmpty
    private MaterialEditText otpTextField;

    @InjectService
    public static MobileService service;


    @InjectService
    public static SettingMobileService settingMobileService;

    private Validator validator;

    String mobileNo;
    Boolean resendOtpService;

    Boolean fromSetting;
    public static final int SETTING_REQ_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AsyncService.inject(this);
        setContentView(R.layout.activity_second_signup);
        mobileNo=getIntent().getExtras().getString("mobile_no");
        fromSetting = getIntent().getExtras().getBoolean("fromSetting");
        initUI();
    }

    private void initUI() {

        validator = new Validator(this);
        validator.setValidationListener(this);

        otpTextField = (MaterialEditText) findViewById(R.id.signup_txt_code);
        Button otpResendBtn = (Button) findViewById(R.id.signup_resend_btn_mobile);
        otpResendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertConfimationDialog();

            }
        });

        Button done = (Button) findViewById(R.id.signup_done_btn_mobile);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });

    }


    private void alertConfimationDialog(){
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.alert))
                .setMessage(String.format(getResources().getString(R.string.otp_confirm)))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        callResendOtpService();
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(R.drawable.ic_alert_blue)
                .show();

    }

    @Override
    public void onValidationSucceeded() {
        if(fromSetting){
            callChangeMobileOtpService();
        }else callService();
    }


    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view instanceof MaterialEditText) {
                ((MaterialEditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    //--------- settings service method ---------//

    protected void callChangeMobileOtpService() {
        MobileSignupRequestObject requestObject=new MobileSignupRequestObject();
        requestObject.setMobileNo(mobileNo);
        requestObject.setOtp(otpTextField.getText().toString());
        settingMobileService.getMobileChangeOtpResponse(1, requestObject);
    }

    @OnMessage
    public  void OnChangeNumberResposeFetched(PostDataResponse response)
    {
        if(response!=null&& response.getStatus()!=null &&response.getStatus())
        {
            User user = getUserObjectFromPrefrences();
            user.setPhone(mobileNo);
            backToSettingActivity();
        }else{
            Toast.makeText(this,response.getMessage(),Toast.LENGTH_LONG).show();
        }
    }


    private void backToSettingActivity() {
        Intent intent=new Intent();
        setResult(SETTING_REQ_CODE, intent);
        finish();//finishing activity
    }

    // -------- service method -------- //

    protected void callResendOtpService() {
        resendOtpService = true;
        BaseRequestObject requestObject=new BaseRequestObject();
        requestObject.setMobileNo(mobileNo);
        service.getVerificationResponse(1, requestObject);
    }

    protected void callService() {
        resendOtpService = false;
        MobileSignupRequestObject requestObject=new MobileSignupRequestObject();
        requestObject.setMobileNo(mobileNo);
        requestObject.setOtp(otpTextField.getText().toString());
        requestObject.setDeviceId(getDeviceIdFromPrefrences());
        requestObject.setDeviceType("android");
        service.getMobileSignupResponse(1, requestObject);
    }


    @OnMessage
    public  void OnResposeFetched(MobileVerificationResponse response)
    {
        if(response!=null && response.getStatus()!=null &&response.getStatus())
        {
            if (resendOtpService) otpResendResponse(response);
            else signupResponse(response);
        }else{
            Toast.makeText(this,response.getMessage(),Toast.LENGTH_LONG).show();
        }
    }


    private void otpResendResponse(MobileVerificationResponse response){
        Toast.makeText(this,response.getMessage(),Toast.LENGTH_LONG).show();
    }

    private void signupResponse(MobileVerificationResponse response){
        if(response.getUser() != null){
            Toast.makeText(this, "otp  done successfully", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "otp done successfully", Toast.LENGTH_LONG).show();
        }
    }
}
