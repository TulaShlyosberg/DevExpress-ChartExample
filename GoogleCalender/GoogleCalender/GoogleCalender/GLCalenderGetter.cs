using Google.Apis.Auth.OAuth2;
using Google.Apis.Calendar.v3;
using Google.Apis.Calendar.v3.Data;
using Google.Apis.Services;
using Google.Apis.Util.Store;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading;
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
            string[] Scopes = { CalendarService.Scope.CalendarReadonly };

            UserCredential credential = null;
            Assembly assembly = Assembly.GetExecutingAssembly();
            using (Stream stream = assembly.GetManifestResourceStream("GoogleCalender.credentials.json"))
            using (StreamReader reader = new StreamReader(stream)) {
            //using (var stream =
            //    new FileStream("credentials.json", FileMode.Open, FileAccess.Read))
            //{
                // The file token.json stores the user's access and refresh tokens, and is created
                // automatically when the authorization flow completes for the first time.
                string credPath = "token.json";
                try {
                    credential = GoogleWebAuthorizationBroker.AuthorizeAsync(
                        GoogleClientSecrets.Load(stream).Secrets,
                        Scopes,
                        "user",
                        CancellationToken.None).Result;
                }catch (Exception e) {

                }

                // Create Google Calendar API service.
                var service = new CalendarService(new BaseClientService.Initializer()
                {
                    HttpClientInitializer = credential,
                    ApplicationName = "DevExpress Scheduler",
                });

                // Define parameters of request.
                request = service.Events.List("primary");
                request.TimeMin = DateTime.MinValue;
                request.ShowDeleted = false;
                request.SingleEvents = true;
                request.OrderBy = EventsResource.ListRequest.OrderByEnum.StartTime;
            }
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
