package cz.muni.pv239.marek.cv2;

/**
 * Created by marek on 03.03.17.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cz.muni.pv239.marek.cv2.model.User;

public class UserAdapter extends ArrayAdapter<User>{

    public UserAdapter(Activity context,
                       List<User> watcherList) {
        super(context, R.layout.list_user_item, watcherList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the watcher's object by id
        User watcher = getItem(position);

        // if the view is being reused
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_user_item, parent, false);
        }

        // get the views i want to update
        TextView loginTextView = (TextView) convertView.findViewById(R.id.loginTextView);
        ImageView avatarImageView = (ImageView) convertView.findViewById(R.id.avatarImageView);

        // update the views
        loginTextView.setText(watcher.getLogin());
        Glide.with(getContext()).load(watcher.getAvatarUrl()).into(avatarImageView);

        // return updated view, it will get rendered in your list
        return convertView;
    }
}
