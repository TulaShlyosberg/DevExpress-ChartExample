using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using DevExpress.XamarinForms.Charts;

namespace ChartsExample
{
    // Learn more about making custom code visible in the Xamarin.Forms previewer
    // by visiting https://aka.ms/xamarinforms-previewer
    [DesignTimeVisible(true)]
    public partial class MainPage : ContentPage
    {
        public MainPage()
        {
            InitializeComponent();
        }
    }

    class LandAreaDataAdapter : BindableObject, IPieSeriesData, IChangeableSeriesData
    {
        public const string ItemsSourcePropertyName = "ItemsSource";
        public static readonly BindableProperty ItemsSourceProperty = BindableProperty.Create(
            propertyName: ItemsSourcePropertyName,
            returnType: typeof(IReadOnlyList<LandAreaItem>),
            declaringType: typeof(LandAreaDataAdapter),
            propertyChanged: OnItemsSourcePropertyChanged
        );

        public event DataChangedEventHandler DataChanged;
        public IReadOnlyList<LandAreaItem> ItemsSource
        {
            get => (IReadOnlyList<LandAreaItem>)GetValue(ItemsSourceProperty);
            set => SetValue(ItemsSourceProperty, value);
        }

        public int GetDataCount() => (ItemsSource == null) ? 0 : ItemsSource.Count;
        public object GetKey(int index) => ItemsSource[index];
        public string GetLabel(int index) => ItemsSource[index].CountryName;
        public double GetValue(int index) => ItemsSource[index].Area;

        static void OnItemsSourcePropertyChanged(BindableObject bindable, object oldValue, object newValue)
        {
            if (!(bindable is LandAreaDataAdapter adapter)) return;
            DataChangedEventHandler handler = adapter.DataChanged;
            if (handler != null)
            {
                handler.Invoke(adapter, DataChangedEventArgs.Reset());
            }
        }
    }
}
