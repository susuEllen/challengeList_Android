package com.example.ellenwong.challengelist;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by ellenwong on 1/25/15.
 * This is a parse object class, item created is meant to be saved to parse cloud
 */
@ParseClassName("Challenge")
public class ChallengeListItem extends ParseObject{
    //Field includes: name, description, metric, imageurl, duration, start, goal

    public String getName() {
        return getString("name");
    }

    public void setName(String value) {
        put("name", value);
    }

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String value) {
        put("description", value);
    }

    public String getMetric() {
        return getString("metric");
    }

    public void setMetric(String value) {
        put("metric", value);
    }

    public int getDuration() {
        return getInt("duration");
    }

    public void setDuration(int value) {
        put("duration", value);
    }

    public String getImageURL() {
        return getString("imageurl");
    }

    public void setImageURL(String value) {
        put("imageurl", value);
    }


    public int getStart() {
        return getInt("start");
    }

    public void setStart(int value) {
        put("start", value);
    }

    public int getGoal() {
        return getInt("goal");
    }

    public void setGoal(int value) {
        put("goal", value);
    }

    public static ParseQuery<ChallengeListItem> getQuery() {
        return ParseQuery.getQuery(ChallengeListItem.class);
    }
}
