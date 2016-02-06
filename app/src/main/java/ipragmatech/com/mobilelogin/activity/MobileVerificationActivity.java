package ipragmatech.com.mobilelogin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.joanzapata.android.asyncservice.api.annotation.InjectService;
import com.joanzapata.android.asyncservice.api.annotation.OnMessage;
import com.joanzapata.android.asyncservice.api.internal.AsyncService;
import com.mobsandgeeks.saripaar.QuickRule;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.rengwuxian.materialedittext.MaterialEditText;

import ipragmatech.com.mobilelogin.MainActivity;
import ipragmatech.com.mobilelogin.R;
import ipragmatech.com.mobilelogin.request.BaseRequestObject;
import ipragmatech.com.mobilelogin.response.MobileVerificationResponse;
import ipragmatech.com.mobilelogin.service.MobileService;

/**
 * Created by narender on 30/1/16.
 */
public class MobileVerificationActivity extends MainActivity implements Validator.ValidationListener {

    public static final String MANDATORY ="This field is mandatory." ;
    public static final String PHONE_CODE_1 = "+91";
    public static final String PHONE_CODE_2 = "0";
    
    @NotEmpty(trim = true,message = MANDATORY)
    private MaterialEditText codeTextField;

    @NotEmpty(trim = true,message = MANDATORY)
    private MaterialEditText phoneNoTextField;

    private CheckBox acceptBox;

    private Validator validator;

    String mobileNo;
    TextView termsCondition;
    private Button nextButton;


    @InjectService
    public static MobileService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_signup);
        AsyncService.inject(this);
        initUI();
    }

    private void initUI() {
        validator = new Validator(this);
        validator.setValidationListener(this);
        codeTextField=(MaterialEditText) findViewById(R.id.signup_txt_code);
        phoneNoTextField=(MaterialEditText) findViewById(R.id.signup_txt_number);
        termsCondition = (TextView) findViewById(R.id.signup_term_condition);
        nextButton = (Button) findViewById(R.id.btn_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    validator.validate();
                } catch (IllegalStateException e) {
                }
            }
        });
        termsCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callLinkActivity();
            }
        });
        acceptBox=(CheckBox)findViewById(R.id.checkbox);
        validator.put(phoneNoTextField, new QuickRule<MaterialEditText>() {
            @Override
            public boolean isValid(MaterialEditText editText) {
                String phoneno = editText.getText().toString();
                if (phoneno.contains(PHONE_CODE_1) || phoneno.startsWith(PHONE_CODE_2)) {
                    return false;
                } else {
                    return phoneno.length() == 10;
                }
            }

            @Override
            public String getMessage(Context context) {
                return getString(R.string.mobile_no_error);
            }
        });

    }


    protected void callLinkActivity() {
        // add your class to provide terms and conditions
    }


    // validation methods

    @Override
    public void onValidationSucceeded() {
        mobileNo= (codeTextField.getText().toString().concat(phoneNoTextField.getText().toString()));
        if(acceptBox.isChecked()) {
            callService();
        }else{
            Toast.makeText(this, getString(R.string.accept_terms_condition), Toast.LENGTH_LONG).show();
        }
    }



    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            // Display error messages ;)
            if (view instanceof MaterialEditText) {
                ((MaterialEditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }


    protected void callService() {
        BaseRequestObject requestObject=new BaseRequestObject();
        requestObject.setMobileNo(mobileNo);
        service.getVerificationResponse(1, requestObject);
    }

    @OnMessage
    public  void OnResponseFetched(MobileVerificationResponse response)
    {
        if(response!=null && response.getStatus())
        {
            callMobileSignup();
        }else{
            Toast.makeText(this,response.getMessage(),Toast.LENGTH_LONG).show();
        }
    }



    protected void callMobileSignup() {
        Intent intent = new Intent(this, MobileSignupActivity.class);
        intent.putExtra("mobile_no", mobileNo);
        intent.putExtra("fromSetting",false);
        startActivity(intent);
    }




}
