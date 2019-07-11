using System;
using System.Collections;

using Android.App;
using Android.Content.PM;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;
using Android.Content;
using System.Collections.Generic;
using GLCalendarGetter;
using Android;
using Android.Support.V4.App;

namespace GoogleCalendar.Droid
{
    [Activity(Label = "GoogleCalendar", Icon = "@mipmap/icon", Theme = "@style/MainTheme", MainLauncher = true, ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation)]
    public class MainActivity : global::Xamarin.Forms.Platform.Android.FormsAppCompatActivity
    {
        protected override void OnCreate(Bundle savedInstanceState)
        {
            TabLayoutResource = Resource.Layout.Tabbar;
            ToolbarResource = Resource.Layout.Toolbar;

            base.OnCreate(savedInstanceState);

            Xamarin.Essentials.Platform.Init(this, savedInstanceState);
            global::Xamarin.Forms.Forms.Init(this, savedInstanceState);
            LoadApplication(new App());
            String[] permissions = new String[] { Manifest.Permission.GetAccounts,
                Manifest.Permission.Internet, Manifest.Permission.ReadCalendar };
            ActivityCompat.RequestPermissions(this, permissions, 200);
        }
        public override void OnRequestPermissionsResult(int requestCode, string[] permissions, [GeneratedEnum] Android.Content.PM.Permission[] grantResults)
        {
            Xamarin.Essentials.Platform.OnRequestPermissionsResult(requestCode, permissions, grantResults);

            base.OnRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == 200) {
                SetGetter();
                Auth();
                GetEvents();
            }
        }

        protected override void OnActivityResult(int requestCode, Result resultCode, Intent data) {
            base.OnActivityResult(requestCode, resultCode, data);
            if (requestCode == RC_AUTH && resultCode == Result.Ok) {
                glGetter.FillCollection(data, newCollection);
            }
        }

        public void SetGetter() {
            glGetter = new GoogleCalendarGetter(this, ContentResolver);
            newCollection = new List<DataItem>();
        }

        public void Auth() {
            Intent data = glGetter.GetAuthIntent(client_id);
            StartActivityForResult(data, RC_AUTH);
        }

        public List<DataItem> GetEvents() {
            return newCollection;
        }

        private GoogleCalendarGetter glGetter;
        private string client_id = "682086401144-7chacpjcngqkoi8jq5rfcgj87o6jved2.apps.googleusercontent.com";
        private List<DataItem> newCollection;
        private int RC_AUTH = 200;
    }
}