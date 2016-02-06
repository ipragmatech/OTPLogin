package ipragmatech.com.mobilelogin.activity;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by narender on 30/1/16.
 */
public class MobileLoginApplication {
    public static volatile Context mMainContext;

    public static Context getStaticContext()
    {
        return MobileLoginApplication.mMainContext;
    }
}
