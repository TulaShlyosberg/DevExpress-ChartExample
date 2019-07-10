package com.example.glcalendarandroid;

import android.Manifest;
import android.annotation.SuppressLint;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.api.services.calendar.Calendar;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    private View mContentView;
    private Calendar client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestScopes(new Scope("https://www.googleapis.com/auth/calendar.events.readonly"),
                        new Scope("https://www.googleapis.com/auth/userinfo.profile"),
                        new Scope("https://www.googleapis.com/auth/userinfo.email"))
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account != null){
            Log.w("!!!", account.getEmail());
            GetData();
        } else {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            account = completedTask.getResult(ApiException.class);
            GetData();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Authorization trouble", "signInResult:failed code=" + e.getMessage());
        }
    }

    public static final String[] CALENDAR_PROJECTION = new String[] {
            CalendarContract.Calendars._ID,                           // 0
    };

    // The indices for the projection array above.
    private static final int PROJECTION_ID_INDEX = 0;

    public static final String[] EVENT_PROJECTION = new String[] {
            CalendarContract.Events._ID,
            CalendarContract.Events.CALENDAR_ID,
            CalendarContract.Events.DTSTART,
            CalendarContract.Events.DTEND,
            CalendarContract.Events.TITLE,
            CalendarContract.Events.DESCRIPTION,
    };

    private  static  final int PROJECTION_EVENT_ID = 0;
    private  static  final int PROJECTION_CALENDAR_ID = 1;
    private  static  final int PROJECTION_EVENT_DTSTART = 2;
    private  static  final int PROJECTION_EVENT_DTEND = 3;
    private  static  final int PROJECTION_EVENT_TITLE = 4;
    private  static  final int PROJECTION_EVENT_DESCRIPTION = 5;

    private void GetData() {
        //Should be rewrited such as https://developer.android.com/training/permissions/requesting#java
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_CALENDAR},
                200);
        Cursor cursorCalendar = null;
        ContentResolver crCalendar = getContentResolver();
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";
        String[] selectionArgs = new String[] {account.getEmail(), "com.google",
                account.getEmail()};
// Submit the query and get a Cursor object back.
        cursorCalendar = crCalendar.query(uri, CALENDAR_PROJECTION, selection, selectionArgs, null);

        while (cursorCalendar.moveToNext()) {
            String calID = null;

            calID = cursorCalendar.getString(PROJECTION_ID_INDEX);

            //Get events from each calendar
            Cursor cursorEvents = null;
            ContentResolver crEvents = getContentResolver();
            Uri uriEvents = CalendarContract.Events.CONTENT_URI;
            String selectionEvents = "(" + CalendarContract.Events.CALENDAR_ID + "=?)";
            String[] selectionEventsArgs = new String[] { calID };
            cursorEvents = crEvents.query(uriEvents, EVENT_PROJECTION, selectionEvents, selectionEventsArgs, null);

            while (cursorEvents.moveToNext()) {
                GoogleUserAppointment newAppointment = new GoogleUserAppointment(
                        cursorEvents.getString(PROJECTION_EVENT_ID),
                        cursorEvents.getString(PROJECTION_EVENT_TITLE),
                        cursorEvents.getLong(PROJECTION_EVENT_DTSTART),
                        cursorEvents.getLong(PROJECTION_EVENT_DTEND)
                );
                //do something into collection
            }
        }


    }


    private int RC_SIGN_IN = 200;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInAccount account;


}
