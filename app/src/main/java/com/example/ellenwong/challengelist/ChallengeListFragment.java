package com.example.ellenwong.challengelist;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ellenwong on 1/20/15.
 */

/**
 * A placeholder fragment containing a simple view.
 */
public class ChallengeListFragment extends Fragment {

    private static String TAG = "ChallengeListFragment";
    private static TextView testView = null;
    private static String jsonStr = "";
    private static ArrayAdapter<String> mChallengeListAdapter = null;
    private static ArrayList<String> testDataArrayList = null;

    public ChallengeListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // test button, remove later!
        Button testButton = (Button) rootView.findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testButtonClicked();
            }
        });
        // end test button

        Button addButton = (Button) rootView.findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButtonClicked();
            }
        });

        String [] testData = {"This", "is", "to", "test", "list"};
        testDataArrayList = new ArrayList<String>(Arrays.asList(testData));

        // Initialize an array adapter to display content in ListView
        mChallengeListAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_main,
                R.id.list_item_main_textview,
                testDataArrayList);

        // set the adapter to the ListView that we will populate with the data
        ListView listView = (ListView) rootView.findViewById(R.id.listView_main);
        listView.setAdapter(mChallengeListAdapter);

        return rootView;
    }

    // Onclick method for testButton
    void testButtonClicked() {
        //new FetchServerDataTask().execute();
        fetchChallengesFromParse();

    }

    void addButtonClicked() {
        Intent intent = new Intent(this.getActivity(), AddItemActivity.class);
        // add flags if needed to the intent
        startActivity(intent);
    }

    // Use Parse Query API to get challenges from Parse cloud
    private void fetchChallengesFromParse() {
        ParseQuery<ChallengeListItem> challenges = ChallengeListItem.getQuery();
        challenges.findInBackground(new FindCallback<ChallengeListItem>() {
            @Override
            public void done(List<ChallengeListItem> challengeListItems, ParseException e) {
                mChallengeListAdapter.clear();
                for (ChallengeListItem challenge: challengeListItems) {
                    mChallengeListAdapter.add(challenge.getName());
                }
            }
        });
    }


            /**
             * Created by ellenwong on 1/16/15.
             * Make it static so it doesn't have access to any member variables of the outer class
             * A temp lightweight network class to do calls to server using AsyncTask
             * Need implementing a more complex class to handle larger data load using
             * Runnables and concurrent class
             *
             */
            private class FetchServerDataTask extends AsyncTask<String, String, String> {

                @Override
                protected String doInBackground(String... params) {

                    ServerConnectionHelper connectionHelper = new ServerConnectionHelper();
                    jsonStr = connectionHelper.sendAServerConnection();  // modify to call parse cloud
                   return jsonStr;
                }

                @Override
                protected void onPostExecute(String jsonStr) {
                    Log.d(TAG, "jsonStr = " + jsonStr);

                    mChallengeListAdapter.clear();

                    ListDataParser parser = new ListDataParser();
                    try {
                        testDataArrayList = parser.getChallengeListFromJSON(jsonStr);
                    } catch(JSONException e) {
                        Log.e(TAG, "onPostExecute exception: " + e.toString());
                    }

                    for (String challenge: testDataArrayList) {
                        mChallengeListAdapter.add(challenge);
                    }

                }

            }
}
