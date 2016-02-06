package ipragmatech.com.mobilelogin.response;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by narender on 30/1/16.
 */
@JsonPropertyOrder({
        "userId",
        "username",
        "userToken",
        "phone"

})
public class User implements Serializable {

    @JsonProperty("userId")
    private Integer userId;

    @JsonProperty("username")
    private String username;
    @JsonProperty("userToken")
    private String userToken;
    @JsonProperty("phone")
    private String phone;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    @JsonProperty("userId")
    public Integer getUserId() {
        return userId;
    }


    @JsonProperty("userId")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }






    @JsonProperty("username")
    public String getUsername() {
        return username;
    }


    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }


    @JsonProperty("userToken")
    public String getUserToken() {
        return userToken;
    }


    @JsonProperty("userToken")
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    @JsonProperty("phone")
    public void setPhone(String phone){this.phone = phone;}


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return username;
    }

    public String getFriendName(){
        return username;
    }
}

