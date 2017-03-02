package cz.muni.pv239.marek.cv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;


import cz.muni.pv239.marek.cv2.api.GitHubApi;
import cz.muni.pv239.marek.cv2.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private GitHubApi mGitHubApi = new GitHubApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Call<User> userCall = mGitHubApi.getService().getUser("octocat");
        userCall.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                // get the model from the response (deserialized http response)
                User user = response.body();

                // set the image into the imagevies
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                Glide.with(getApplicationContext()).load(user.getAvatarUrl()).into(imageView);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
