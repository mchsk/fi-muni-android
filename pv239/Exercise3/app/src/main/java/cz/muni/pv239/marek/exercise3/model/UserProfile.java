package cz.muni.pv239.marek.exercise3.model;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by marek on 11.03.17.
 */

public class UserProfile extends RealmObject {

    @Required
    private String name;

    @Required
    private String surname;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
