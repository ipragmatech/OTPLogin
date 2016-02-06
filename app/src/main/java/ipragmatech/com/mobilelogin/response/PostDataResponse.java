package ipragmatech.com.mobilelogin.response;

import com.fasterxml.jackson.annotation.JsonInclude;

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
@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
        "status",
        "message"
})
public class PostDataResponse implements Serializable {

    @JsonProperty("status")
    private Boolean status;
    @JsonProperty("message")
    private String message;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    @JsonProperty("status")
    public Boolean getStatus() {
        return status;
    }


    @JsonProperty("status")
    public void setStatus(Boolean status) {
        this.status = status;
    }


    @JsonProperty("message")
    public String getMessage() {
        return message;
    }


    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
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
        return "PostDataResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
