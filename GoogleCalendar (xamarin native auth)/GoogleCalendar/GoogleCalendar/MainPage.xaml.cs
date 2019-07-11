using GoogleCalendar.Models;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace GoogleCalendar {
    // Learn more about making custom code visible in the Xamarin.Forms previewer
    // by visiting https://aka.ms/xamarinforms-previewer
    [DesignTimeVisible(true)]
    public partial class MainPage : ContentPage {
        readonly ReceptionDeskData viewModel = new ReceptionDeskData();

        public MainPage() {
            InitializeComponent();
            BindingContext = viewModel;
        }

        public void Import(object sender, EventArgs e) {
            /*GLCalendarGetter GLGetter = new GLCalendarGetter("noskov.fa@phystech.edu");
            GLGetter.AuthAndGetEvents(viewModel.GoogleUserAppointments);
            var authorizationForm = new HtmlWebViewSource {
                Html = GLGetter.AuthAndGetEvents(viewModel.GoogleUserAppointments)
            };
            var browser = new WebView {
                Source = authorizationForm
            };
            Content = browser;*/
            IGoogleCalendarEventsGetter glGetter = DependencyService.Get<IGoogleCalendarEventsGetter>();
            glGetter.FillCollection(viewModel.GoogleUserAppointments);
        }
    }
}
