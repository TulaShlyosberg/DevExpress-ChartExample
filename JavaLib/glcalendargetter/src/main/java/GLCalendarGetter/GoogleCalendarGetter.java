package GLCalendarGetter;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class GoogleCalendarGetter {

    @RequiresApi(api = Build.VERSION_CODES.M)
    public GoogleCalendarGetter(Activity activity, ContentResolver crCalendar) {
        /*if (activity.checkSelfPermission(Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_CALENDAR},0);
        }*/
        this.activity = activity;
        this.crCalendar = crCalendar;
    }

    public Intent GetAuthIntent(String client_id) {
        GoogleSignInClient mGoogleSignInClient;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(client_id)
                .requestScopes(new Scope("https://www.googleapis.com/auth/calendar.events.readonly"),
                        new Scope("https://www.googleapis.com/auth/userinfo.profile"),
                        new Scope("https://www.googleapis.com/auth/userinfo.email"))
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
        return mGoogleSignInClient.getSignInIntent();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void FillCollection(Intent data, List<DataItem> currentCollection) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        handleSignInResult(task, currentCollection);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask, List<DataItem> currentCollection) {
        try {
            account = completedTask.getResult(ApiException.class);
            FillCollection(currentCollection);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void FillCollection(List<DataItem> currentCollection) {
        //Fields required
        String[] CALENDAR_PROJECTION = new String[]{
                CalendarContract.Calendars._ID,                           // 0
        };

        int PROJECTION_ID_INDEX = 0;

        String[] EVENT_PROJECTION = new String[]{
                CalendarContract.Events._ID,
                CalendarContract.Events.CALENDAR_ID,
                CalendarContract.Events.DTSTART,
                CalendarContract.Events.DTEND,
                CalendarContract.Events.TITLE,
                CalendarContract.Events.DESCRIPTION,
        };

        int PROJECTION_EVENT_ID = 0;
        int PROJECTION_CALENDAR_ID = 1;
        int PROJECTION_EVENT_DTSTART = 2;
        int PROJECTION_EVENT_DTEND = 3;
        int PROJECTION_EVENT_TITLE = 4;
        int PROJECTION_EVENT_DESCRIPTION = 5;


        //Calendar query setup
        Cursor cursorCalendar = null;
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";
        String[] selectionArgs = new String[]{account.getEmail(), "com.google", account.getEmail()};
        cursorCalendar = crCalendar.query(uri, CALENDAR_PROJECTION, selection, selectionArgs, null);

        //Calendar processing
        while (cursorCalendar.moveToNext()) {
            String calendarID = null;

            calendarID = cursorCalendar.getString(PROJECTION_ID_INDEX);

            //Get events from each calendar
            Cursor cursorEvents = null;
            Uri uriEvents = CalendarContract.Events.CONTENT_URI;
            String selectionEvents = "(" + CalendarContract.Events.CALENDAR_ID + "=?)";
            String[] selectionEventsArgs = new String[]{calendarID};
            if (activity.checkSelfPermission(Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                return;
            }
            cursorEvents = crCalendar.query(uriEvents, EVENT_PROJECTION, selectionEvents, selectionEventsArgs, null);

            while (cursorEvents.moveToNext()) {
                DataItem newAppointment = new DataItem(
                        cursorEvents.getString(PROJECTION_EVENT_ID),
                        cursorEvents.getString(PROJECTION_EVENT_TITLE),
                        cursorEvents.getLong(PROJECTION_EVENT_DTSTART),
                        cursorEvents.getLong(PROJECTION_EVENT_DTEND)
                );
                currentCollection.add(newAppointment);
            }
        }

    }

    private GoogleSignInAccount account;
    private ContentResolver crCalendar;
    private Activity activity;
}

