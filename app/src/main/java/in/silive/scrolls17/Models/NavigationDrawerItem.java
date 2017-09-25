package in.silive.scrolls17.Models;

/**
 * Created by akriti on 20/8/16.
 */
public class NavigationDrawerItem {
    private boolean showNotify;
    private String title;
    private Integer image;


    public NavigationDrawerItem() {

    }

    public NavigationDrawerItem(boolean showNotify, String title) {
        this.showNotify = showNotify;
        this.title = title;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
