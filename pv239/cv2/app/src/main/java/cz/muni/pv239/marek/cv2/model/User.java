package cz.muni.pv239.marek.cv2.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marek on 02.03.17.
 */

public class User {

    @SerializedName("avatar_url")
    private String avatarUrl;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
