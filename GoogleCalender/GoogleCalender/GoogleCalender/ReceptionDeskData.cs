using System;
using System.Collections.ObjectModel;
using Xamarin.Forms;

namespace GoogleCalender.Models
{
    public class ReceptionDeskData
    {
        public static DateTime BaseDate = DateTime.Today;
        public ObservableCollection<GoogleUserAppointment> GoogleUserAppointments { get; private set; }

        public ReceptionDeskData()
        {
            GLCalenderGetter GLGetter = new GLCalenderGetter();
            GoogleUserAppointments = GLGetter.GetData();
        }
    }
}