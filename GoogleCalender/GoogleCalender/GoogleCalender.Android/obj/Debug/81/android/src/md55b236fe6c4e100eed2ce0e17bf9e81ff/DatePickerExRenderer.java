package md55b236fe6c4e100eed2ce0e17bf9e81ff;


public class DatePickerExRenderer
	extends md51558244f76c53b6aeda52c8a337f2c37.DatePickerRenderer
	implements
		mono.android.IGCUserPeer
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"";
		mono.android.Runtime.register ("DevExpress.XamarinForms.Scheduler.Droid.Renderers.DatePickerExRenderer, DevExpress.XamarinForms.Scheduler.Android", DatePickerExRenderer.class, __md_methods);
	}


	public DatePickerExRenderer (android.content.Context p0, android.util.AttributeSet p1, int p2)
	{
		super (p0, p1, p2);
		if (getClass () == DatePickerExRenderer.class)
			mono.android.TypeManager.Activate ("DevExpress.XamarinForms.Scheduler.Droid.Renderers.DatePickerExRenderer, DevExpress.XamarinForms.Scheduler.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new java.lang.Object[] { p0, p1, p2 });
	}


	public DatePickerExRenderer (android.content.Context p0, android.util.AttributeSet p1)
	{
		super (p0, p1);
		if (getClass () == DatePickerExRenderer.class)
			mono.android.TypeManager.Activate ("DevExpress.XamarinForms.Scheduler.Droid.Renderers.DatePickerExRenderer, DevExpress.XamarinForms.Scheduler.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new java.lang.Object[] { p0, p1 });
	}


	public DatePickerExRenderer (android.content.Context p0)
	{
		super (p0);
		if (getClass () == DatePickerExRenderer.class)
			mono.android.TypeManager.Activate ("DevExpress.XamarinForms.Scheduler.Droid.Renderers.DatePickerExRenderer, DevExpress.XamarinForms.Scheduler.Android", "Android.Content.Context, Mono.Android", this, new java.lang.Object[] { p0 });
	}

	private java.util.ArrayList refList;
	public void monodroidAddReference (java.lang.Object obj)
	{
		if (refList == null)
			refList = new java.util.ArrayList ();
		refList.add (obj);
	}

	public void monodroidClearReferences ()
	{
		if (refList != null)
			refList.clear ();
	}
}
