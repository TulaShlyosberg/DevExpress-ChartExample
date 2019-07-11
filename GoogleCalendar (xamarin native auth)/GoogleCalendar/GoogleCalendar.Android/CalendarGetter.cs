using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Collections.ObjectModel;

using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using GLCalendarGetter;

[assembly: Xamarin.Forms.Dependency(typeof(GoogleCalendarGetter))]
[assembly: Xamarin.Forms.Dependency(typeof(DataItem))]


namespace GoogleCalendar.Droid {
    public class CalendarGetter : IGoogleCalendarEventsGetter {

        public void FillCollection(ObservableCollection<GoogleUserAppointment> currentCollection) {
            MainActivity mainActivity = (MainActivity)Application.Context;
            mainActivity.SetGetter();
            mainActivity.Auth();
            foreach(var eventItem in mainActivity.GetEvents()) {
                GoogleUserAppointment newAppointment = new GoogleUserAppointment() {
                    Id = eventItem.Id,
                    Summary = eventItem.Summary,
                    StartTime = new DateTime(eventItem.StartTime),
                    EndTime = new DateTime(eventItem.EndTime)
                };
                currentCollection.Add(newAppointment);
            }
        }
    }

    
}