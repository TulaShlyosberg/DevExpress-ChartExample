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


namespace GLConsole_HTTP {
    class Program {

        const string scope = "https://www.googleapis.com/auth/calendar.readonly";
        const string endPoint = "https://www.googleapis.com/calendar/v3/calendars/events?key=AIzaSyAMOgJJuWuaAgG9H450T5R3pV8BDCA8re8";
        const string clientId = "819713777853-7ggs28apgk8vg8vum0o9ouot8743jttc.apps.googleusercontent.com";
        const string clientSecrets = "04Q223hyv-SEfAQdvWmDU4Ws";


        static void Main(string[] args) {
            var client = new HttpClient();

            //Create authorization request
            var authDictionary = new Dictionary<string, string>() {
                {"response_type", "code"},
                {"client_id", clientId},
                {"redirect_uri", "http://localhost:8080"},
                {"prompt", "consent"},
                {"scope", scope},
                {"state", "1"},
            };
            var content = new FormUrlEncodedContent(authDictionary);
            var str = client.PostAsync(new Uri(@"https://accounts.google.com/o/oauth2/v2/auth"), content).Result.Content.ReadAsStringAsync().Result;

            //Write answer into file
            StreamWriter sw = new StreamWriter("answer.html", false, Encoding.Default);
            sw.WriteLine(str);

            string code = string.Empty;

            //Wait while browser get authorization code
            using (var httpListener = new HttpListener()) {
                httpListener.Prefixes.Add("http://localhost:8080/");

                httpListener.Start();

                while (true) {

                    var context = httpListener.GetContext();
                    var request = context.Request;
                    var result = request.QueryString;
                    if (result["state"] == "1") {
                        code = result["code"];
                        context.Response.StatusCode = 200;
                        context.Response.Close();
                        break;
                    }
                }
            }

            //Create token request
            var accessDictionary = new Dictionary<string, string>() {
                {"client_id", clientId},
                {"client_secret", clientSecrets},
                {"grant_type", "authorization_code"},
                {"redirect_uri", "http://localhost:8080"},
                {"code", code}
            };

            content = new FormUrlEncodedContent(accessDictionary);
            var tokenString = client.PostAsync(new Uri(@"https://accounts.google.com/o/oauth2/token"), content).Result.Content.ReadAsStringAsync().Result;
            Console.WriteLine(tokenString);
            string accessToken = JObject.Parse(tokenString)["access_token"].ToString();
            string refreshToken = JObject.Parse(tokenString)["refresh_token"].ToString();

            var refreshDictionary = new Dictionary<string, string>() {
                { "grant_type", "refresh_token"},
                { "refresh_token", refreshToken},
                { "client_id", clientId},
                { "client_secret", clientSecrets}
            };

            content = new FormUrlEncodedContent(refreshDictionary);
            tokenString = client.PostAsync(new Uri(@"https://accounts.google.com/o/oauth2/token"), content).Result.Content.ReadAsStringAsync().Result;
            accessToken = JObject.Parse(tokenString)["access_token"].ToString();

            Console.WriteLine(accessToken);

            //Get Calendar html
            client.DefaultRequestHeaders.Add("Authorization", "Bearer " + accessToken);
            var calendarHTML = client.GetAsync(new Uri(endPoint)).Result.Content.ReadAsStringAsync().Result;
            Console.WriteLine(calendarHTML);
            StreamWriter calendarStreamWriter = new StreamWriter("calendar.html", false, Encoding.Default);
            calendarStreamWriter.WriteLine(calendarHTML);
        } 
    }
}
