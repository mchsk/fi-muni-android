package cz.muni.pv239.marek.cv2.api;

import java.util.List;

import cz.muni.pv239.marek.cv2.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by marek on 02.03.17.
 */

public interface GitHubService {

    // https://developer.github.com/v3/users/
    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);

    // https://developer.github.com/v3/activity/watching/
    @GET("repos/{username}/{reponame}/subscribers")
    Call<List<User>> getSubscriberList(@Path("username") String username, @Path("reponame") String reponame);

}