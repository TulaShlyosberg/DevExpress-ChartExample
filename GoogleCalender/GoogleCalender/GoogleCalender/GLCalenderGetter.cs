using Google.Apis.Auth.OAuth2;
using Google.Apis.Calendar.v3;
using Google.Apis.Calendar.v3.Data;
using Google.Apis.Services;
using Google.Apis.Util.Store;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Collections.ObjectModel;
using System.Reflection;

namespace GoogleCalender
{
    public class GoogleUserAppointment {
        public string Summary { get; set; }
        public DateTime StartTime { get; set; }
        public DateTime EndTime { get; set; }
    }

    class GLCalenderGetter
    {
        private EventsResource.ListRequest request;

        public GLCalenderGetter() {
            var client = new HttpClient();
            var authDictionary = new Dictionary<string, string>() {
                {"response_type", "code"},
                {"client_id", "435208680463-mtp4umgqv8n15ak2v6d3690q1isd1cb9.apps.googleusercontent.com"},
                {"redirect_uri", "http://localhost:8080"},
                {"prompt", "consent"},
                {"scope", "https://www.googleapis.com/auth/calendar.readonly"},
                {"state", "1"},
                {"login_hint", "noskov.fa@phystech.edu"},
            };
            var content = new FormUrlEncodedContent(authDictionary);
            var str = client.PostAsync(new Uri(@"https://accounts.google.com/o/oauth2/auth"), content).Result.Content.ReadAsStringAsync().Result;
            string code = string.Empty;
        }

        public ObservableCollection<GoogleUserAppointment> GetData()
        {
            //request settings
            request.TimeMin = DateTime.MinValue;
            request.ShowDeleted = false;
            request.SingleEvents = true;
            request.OrderBy = EventsResource.ListRequest.OrderByEnum.StartTime;

            //request handler
            Events events = request.Execute();
            ObservableCollection<GoogleUserAppointment> Result = new ObservableCollection<GoogleUserAppointment>();
            if (events.Items != null && events.Items.Count > 0)
            {
                foreach (var eventItem in events.Items)
                {
                    Result.Add(new GoogleUserAppointment() {
                        Summary = eventItem.Summary.ToString(),
                        StartTime = (DateTime)eventItem.Start.DateTime,
                        EndTime = (DateTime)eventItem.End.DateTime
                    });
                }
            }
            return Result;
        }
    }
}
