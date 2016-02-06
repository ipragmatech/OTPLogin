package ipragmatech.com.mobilelogin.request;

/**
 * Created by narender on 30/1/16.
 */
public class MobileSignupRequestObject extends BaseRequestObject {

    String otp;
    String deviceId;
    String deviceType;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviveType) {
        this.deviceType = deviveType;
    }

    public String getOtp() {
        return otp;
    }
    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

}
