using System;
using System.Collections.ObjectModel;
using System.Text;

namespace GoogleCalendar {
    public interface IGoogleCalendarEventsGetter {
        void FillCollection(ObservableCollection<GoogleUserAppointment> currentCollection);
    }
}
