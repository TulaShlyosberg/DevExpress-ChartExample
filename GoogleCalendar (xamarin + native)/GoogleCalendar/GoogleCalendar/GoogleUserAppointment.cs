using System;
using System.Collections.Generic;
using System.Text;

namespace GoogleCalendar {
    public class GoogleUserAppointment {
        public string Id { get; set; }
        public string Summary { get; set; }
        public DateTime StartTime { get; set; }
        public DateTime EndTime { get; set; }
    }
}
