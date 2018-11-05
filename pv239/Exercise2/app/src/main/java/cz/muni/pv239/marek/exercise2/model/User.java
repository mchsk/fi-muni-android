package cz.muni.pv239.marek.exercise2.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marek on 02.03.17.
 */

public class User {

    @SerializedName("avatar_url")
    private String avatarUrl;
    private String login;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
