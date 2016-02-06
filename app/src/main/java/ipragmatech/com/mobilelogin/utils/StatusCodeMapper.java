package ipragmatech.com.mobilelogin.utils;

import android.util.Log;

import com.joanzapata.android.asyncservice.api.ErrorMapper;

import ipragmatech.com.mobilelogin.MainActivity;

/**
 * Created by narender on 30/1/16.
 */
public class StatusCodeMapper implements ErrorMapper {

    @Override
    public int mapError(Throwable throwable) {
        Log.e(MainActivity.TAG, "Handing exception in mapper " + throwable.getMessage());
        Log.e(MainActivity.TAG, "Handing  " + throwable);

        if(throwable.getMessage() == null || throwable.getMessage().contains("Failed to allocate")) {
            Log.e(MainActivity.TAG, "Return code  " + "504");
            return  504;
        }else if (throwable.getMessage().contains("Unable to resolve host")) {
            return 0;
        } else {
            return 500;
        }

    }
}


