package nyc.c4q.akashaarcher.group3memestudio.Model;

import android.support.annotation.DrawableRes;

/**
 * Created by akashaarcher on 1/9/17.
 */

public class Thumbnails {

    private Integer image;
    private String title;
    private int isActiveFilter = -1;

    public Thumbnails(@DrawableRes Integer resource, String title) {
        this.image = resource;
        this.title = title;
    }

    public Integer getImageResource() {
        return image;
    }

    public void setImageResource(Integer image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsActiveFilter() {
        return isActiveFilter;
    }

    public void setIsActiveFilter(int isActiveFilter) {
        this.isActiveFilter = isActiveFilter;
    }
}
