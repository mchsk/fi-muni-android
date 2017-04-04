package cz.muni.pv239.marek.cv5.api;

import java.util.List;


import cz.muni.pv239.marek.cv5.model.User;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by marek on 02.03.17.
 */

public interface GitHubService {

    // https://developer.github.com/v3/activity/watching/
    @GET("repos/{username}/{reponame}/subscribers")
    Observable<Response<List<User>>> getWatcherList(@Path("username") String username, @Path("reponame") String reponame);

}