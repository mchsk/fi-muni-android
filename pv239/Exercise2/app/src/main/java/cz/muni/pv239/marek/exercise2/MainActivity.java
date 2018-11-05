package cz.muni.pv239.marek.exercise2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;


import cz.muni.pv239.marek.exercise2.api.GitHubApi;
import cz.muni.pv239.marek.exercise2.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private GitHubApi mGitHubApi = new GitHubApi();
    private Button mListViewActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareUi();
        loadGithubUsernameIcon("octocat");
    }

    private void prepareUi() {
        mListViewActivityButton = (Button) findViewById(R.id.listViewActivityButton);
        mListViewActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ListViewActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadGithubUsernameIcon(String username) {
        Call<User> userCall = mGitHubApi.getService().getUser(username);
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
