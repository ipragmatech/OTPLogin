package ipragmatech.com.mobilelogin.utils;

/**
 * Created by narender on 30/1/16.
 */
public class PaginationAtrr {

    private Integer offset;

    private Integer pageId;

    public Integer getOffset() {
        return offset;
    }

    public Integer getPageId() {
        return pageId;
    }
    /*public void setOffset(Integer pageId){
        this.offset = ((pageId-1)* Constants.ITEM_PER_PAGE);
    }*/

    public void setPageId(Integer pageId) {
        this.pageId = pageId;

    }


}
