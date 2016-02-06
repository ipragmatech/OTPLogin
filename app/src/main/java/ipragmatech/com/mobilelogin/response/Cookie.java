package ipragmatech.com.mobilelogin.response;

/**
 * Created by narender on 30/1/16.
 */
public class Cookie {
    private final String phpSessionId;
    private final String lang;
    private final String locale;

    public Cookie(String phpSessionId, String lang, String locale) {
        this.phpSessionId = phpSessionId;
        this.lang = lang;
        this.locale = locale;
    }

    public String getPhpSessionId() {
        return phpSessionId;
    }

    public String getLang() {
        return lang;
    }

    public String getLocale() {
        return locale;
    }
}

