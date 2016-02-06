package ipragmatech.com.mobilelogin.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
/**
 * Created by narender on 30/1/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "user",
        "message",
        "count",
        "userId"
})
public class MobileVerificationResponse implements Serializable {
    @JsonProperty("status")
    private Boolean status;
    @JsonProperty("user")
    private User user;
    @JsonProperty("message")
    private String message;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("userId")
    private Integer userId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The status
     */
    @JsonProperty("status")
    public Boolean getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    @JsonProperty("status")
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The user
     */
    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }
    /**
     *
     * @return
     * The message
     */
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The count
     */
    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    /**
     *
     * @return
     * The userId
     */
    @JsonProperty("userId")
    public Integer getUserId() {
        return userId;
    }

    /**
     *
     * @param count
     * The count
     */
    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     *
     * @param userId
     * The userId
     */
    @JsonProperty("userId")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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
        return "MobileVerificationResponse{" +
                "status=" + status +
                ", user=" + user +
                ", message='" + message + '\'' +
                ", userId='" + userId + '\'' +
                ", count=" + count +
                '}';
    }
}

