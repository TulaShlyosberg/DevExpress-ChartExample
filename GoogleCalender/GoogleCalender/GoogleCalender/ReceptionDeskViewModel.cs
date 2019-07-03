using System;
using System.Collections.Generic;
using Xamarin.Forms;
using GoogleCalender.Models;
using System.ComponentModel;

namespace GoogleCalender.ViewModels
{
    public class ReceptionDeskViewModel : INotifyPropertyChanged
    {
        readonly ReceptionDeskData data;

        public event PropertyChangedEventHandler PropertyChanged;
        public DateTime StartDate { get { return ReceptionDeskData.BaseDate; } }
        public IReadOnlyList<GoogleUserAppointment> GoogleUserAppointments { get => data.GoogleUserAppointments; }

        public ReceptionDeskViewModel()
        {
            data = new ReceptionDeskData();
        }

        protected void RaisePropertyChanged(string name)
        {
            if (PropertyChanged != null)
                PropertyChanged(this, new PropertyChangedEventArgs(name));
        }
    }
}
