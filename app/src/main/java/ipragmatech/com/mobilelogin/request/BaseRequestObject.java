package ipragmatech.com.mobilelogin.request;

import java.util.HashMap;
import java.util.Map;

import ipragmatech.com.mobilelogin.utils.Constants;
import ipragmatech.com.mobilelogin.utils.PaginationAtrr;

/**
 * Created by narender on 30/1/16.
 */
public class BaseRequestObject extends PaginationAtrr {
    public static final String API_KEY = "apiKey";
    public static final String SECRET_KEY = "secretKey";
    public static final String VERSION = "version";
    public  String userId;
    public  String userType;
    public String groupId;
    public  String forumId;
    public  String topicId;
    public  String postId;
    public  String blogId;
    public  String mobileNo;
    public String photoId;
    public String albumId;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getApiKey() {
        return Constants.API_KEY;
    }

    public String getSecretKey() {
        return Constants.SECRET_KEY;
    }

    public String getVersion() {
        return Constants.VERSION;
    }

    public String getServerUrl() {
        return Constants.BASE_URL;
    }

    public String getForumId() {
        return forumId;
    }

    public void setForumId(String forumId) {
        this.forumId = forumId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Map<String, String> initializeParams() {
        Map<String, String> queryParams = new HashMap<String, String>(15);
        queryParams.put(API_KEY, getApiKey());
        queryParams.put(SECRET_KEY, getSecretKey());
        queryParams.put(VERSION, getVersion());
        return queryParams;
    }

    public Map<String, Object> initializeObjectParams() {
        Map<String, Object> queryParams = new HashMap<String, Object>(15);
        queryParams.put(API_KEY, getApiKey());
        queryParams.put(SECRET_KEY, getSecretKey());
        queryParams.put(VERSION, getVersion());
        return queryParams;
    }
    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }
}

