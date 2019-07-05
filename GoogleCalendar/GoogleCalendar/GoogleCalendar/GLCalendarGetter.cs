using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.Collections.ObjectModel;
using Xamarin.Forms;
using System.Net.Sockets;

namespace GoogleCalendar
{
    public class GoogleUserAppointment {
        public string Id { get; set; }
        public string Summary { get; set; }
        public DateTime StartTime { get; set; }
        public DateTime EndTime { get; set; }
    }

    class GLCalendarGetter
    {
        public GLCalendarGetter(string login) {
            endPoint = String.Format("https://www.googleapis.com/calendar/v3/calendars/{0}/events?key=AIzaSyAMOgJJuWuaAgG9H450T5R3pV8BDCA8re8", login);
            client = new HttpClient();
        }


        public void AuthAndGetEvents(ObservableCollection<GoogleUserAppointment> currentCollection) {
            //Create authorization request

            /*
            var authDictionary = new Dictionary<string, string>() {
                {"response_type", "code"},
                {"client_id", clientId},
                {"redirect_uri", localPort},
                {"prompt", "consent"},
                {"scope", scope},
                {"state", "1"},
            };
            var content = new FormUrlEncodedContent(authDictionary);
            var authorizationForm = client.PostAsync(new Uri(@"https://accounts.google.com/o/oauth2/v2/auth"), content).Result
                .Content.ReadAsStringAsync().Result;
               
            
            IPHostEntry ipHostInfo = Dns.GetHostEntry(Dns.GetHostName());
            IPAddress ipAddress = ipHostInfo.AddressList[0];
            IPEndPoint localEndPoint = new IPEndPoint(ipAddress, 11000);*/

            string authorizationForm = string.Empty;

            string authURL = String.Format("https://accounts.google.com/o/oauth2/v2/auth?response_type={0}" +
                "&client_id={1}&redirect_uri={2}&prompt={3}&scope={4}&state={5}",
                "code", clientId, localPort, "consent", scope, "1");



            Task.Factory.StartNew(() => {
                Device.OpenUri(new Uri(authURL));
                return;
            });

            //Create listener
            using (var httpListener = new HttpListener()) {
                httpListener.Prefixes.Add(localPort);
                httpListener.Start();

                while (true) {

                    var context = httpListener.GetContext();
                    var request = context.Request;
                    var result = request.QueryString;
                    if (result["state"] == "1") {
                        code = result["code"];
                        context.Response.StatusCode = 200;
                        context.Response.Close();
                        StoreData(currentCollection);
                        return;
                    }
                }
            }
        }


        private void StoreData(ObservableCollection<GoogleUserAppointment> currentCollection) {
            //Get access token
            var accessDictionary = new Dictionary<string, string>() {
                {"client_id", clientId},
                {"client_secret", clientSecrets},
                {"grant_type", "authorization_code"},
                {"redirect_uri", localPort},
                {"code", code}
            };

            var content = new FormUrlEncodedContent(accessDictionary);
            var tokenString = client.PostAsync(new Uri(@"https://accounts.google.com/o/oauth2/token"), content).Result.Content.ReadAsStringAsync().Result;
            string accessToken = JObject.Parse(tokenString)["access_token"].ToString();

            //Get and process data
            client.DefaultRequestHeaders.Add("Authorization", "Bearer " + accessToken);
            var calendarJSON = client.GetAsync(new Uri(endPoint)).Result.Content.ReadAsStringAsync().Result;
            foreach (var eventItem in JObject.Parse(calendarJSON)["items"]) {
                var newAppointement = new GoogleUserAppointment() {
                    Id = eventItem["id"].ToString(),
                    Summary = eventItem["summary"].ToString(),
                    StartTime = DateTime.Parse(eventItem["start"]["dateTime"].ToString()),
                    EndTime = DateTime.Parse(eventItem["end"]["dateTime"].ToString()),
                };
                currentCollection.Add(newAppointement);
            }
            return;
        }

        private HttpClient client = null;
        private const string scope = "https://www.googleapis.com/auth/calendar.events.readonly";
        private const string clientId = "819713777853-7ajlvaeh2ceoju3bt6nqie2k74nrvusl.apps.googleusercontent.com";
        private const string clientSecrets = "GB6JmwaZs03H2GYnSMXuSsZ4";
        private string endPoint;
        private string code = string.Empty;
        private string localPort = "http://127.0.0.1:8080/";
    }
}
