package cz.muni.pv239.marek.cv2.api;

import cz.muni.pv239.marek.cv2.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by marek on 02.03.17.
 */

public interface GitHubService {
    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);
}