using System;
using System.Collections.ObjectModel;
using Xamarin.Forms;

namespace GoogleCalendar.Models
{
    public class ReceptionDeskData
    {
        public static DateTime BaseDate = DateTime.Today;
        public ObservableCollection<GoogleUserAppointment> GoogleUserAppointments { get; private set; }

        public ReceptionDeskData()
        {
        }
    }
}