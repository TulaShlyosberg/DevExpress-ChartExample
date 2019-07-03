package md527e0643a2f3eb2135b59727a6a0e7d4e;


public class SingleItemTemplatedViewProvider
	extends md527e0643a2f3eb2135b59727a6a0e7d4e.TemplatedViewProvider
	implements
		mono.android.IGCUserPeer
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"";
		mono.android.Runtime.register ("DevExpress.XamarinForms.Scheduler.Droid.Internal.SingleItemTemplatedViewProvider, DevExpress.XamarinForms.Scheduler.Android", SingleItemTemplatedViewProvider.class, __md_methods);
	}


	public SingleItemTemplatedViewProvider ()
	{
		super ();
		if (getClass () == SingleItemTemplatedViewProvider.class)
			mono.android.TypeManager.Activate ("DevExpress.XamarinForms.Scheduler.Droid.Internal.SingleItemTemplatedViewProvider, DevExpress.XamarinForms.Scheduler.Android", "", this, new java.lang.Object[] {  });
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
