package com.example.ellenwong.challengelist;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ellenwong on 1/19/15.
 */
public class ListDataParser {

    final static String TAG = "ListDataParser";
    final String CHALLENGE_LIST_KEY = "text";
    final String ERR_CODE_KEY = "err";
    final String ERR_CODE_FAILED = "0";
    final String ERR_CODE_SUCCESS = "1";

    public ArrayList<String> getChallengeListFromJSON(String challengeListJsonStr) throws JSONException{

        String errCode = "";
        ArrayList<String> challengeArrayList = new ArrayList<String>();

        JSONObject challengeListJSON = new JSONObject(challengeListJsonStr);

        // get err code
        errCode = challengeListJSON.getString(ERR_CODE_KEY);
        Log.d(TAG, "log errCode = " + errCode);

        if (!errCode.equals(ERR_CODE_SUCCESS)) {
            // if errCode is not success, return no list
            Log.e(TAG, "return empty arraylist, non success errCode = " + errCode);
            return new ArrayList<String>();
        }

        // get
        JSONArray challengeListJsonArray =  challengeListJSON.getJSONArray(CHALLENGE_LIST_KEY);

        if (challengeListJsonArray != null) {
            // parse the jsonArray to create an ArrayList
            for (int i = 0; i < challengeListJsonArray.length(); i++){
                challengeArrayList.add((challengeListJsonArray.get(i).toString()));
            }
        }

        return challengeArrayList;
    }
}

/*
*
*
* {
err: 1,
text: [
"Hello?",
"Fuck you!",
"Yes?",
"What do you want?"
]
}

*/