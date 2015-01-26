package com.example.ellenwong.challengelist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class MainActivity extends ActionBarActivity {

    private static String TAG = "MainActivity";
    private static final String APP_ID_PARSE = "A6AWoEb3MwcoCdYOLyAkT4IcRPCsEz17zkh9LSBR";
    private static final String CLIENT_ID_PARSE = "R0XTcA7Np8LclTrvTLS9rOsQlvq2kkOMwxARj1i9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerParseObjects();
        Parse.initialize(this, APP_ID_PARSE, CLIENT_ID_PARSE);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ChallengeListFragment())
                    .commit();
        }

        // try to create a test Parse Object, REMOVE LATER
        //testCreatingTestChallengeListObject();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void testCreateParseUser() {
        // try out creating user with standard code
        //testCreateParseUser();

        ParseUser user = new ParseUser();
        user.setUsername("my name Ellen");
        user.setPassword("my pass Ellen");
        user.setEmail("email@example.com");

// other fields can be set just like with ParseObject
        user.put("phone", "650-555-0000");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {

                } else {

                }
            }
        });


    }
    private void testCreateParseObject() {
        ParseObject testObject = new ParseObject("TestObjectEllen");
        testObject.put("What", "is");
        testObject.saveInBackground();
    }

    private void testCreatingTestChallengeListObject() {
        ChallengeListItem item = new ChallengeListItem();
        item.setName("Yoga");
        item.setDescription("Do Yoga for 30 Days");
        item.setGoal(30);
        item.setStart(0);
        item.setImageURL("http://www.lanzarotesurf.com/wp-content/uploads/2014/11/yoga-pose.jpg");
        item.setMetric("day");
        item.saveInBackground();
    }

    private void registerParseObjects() {
        ParseObject.registerSubclass(ChallengeListItem.class);
    }
}
