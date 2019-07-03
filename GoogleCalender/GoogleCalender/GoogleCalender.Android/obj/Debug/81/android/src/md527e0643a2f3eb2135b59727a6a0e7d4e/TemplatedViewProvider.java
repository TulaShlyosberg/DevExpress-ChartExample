package md527e0643a2f3eb2135b59727a6a0e7d4e;


public class TemplatedViewProvider
	extends java.lang.Object
	implements
		mono.android.IGCUserPeer,
		com.devexpress.scheduler.providers.ViewProvider
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"n_getItemsCount:()I:GetGetItemsCountHandler:DevExpress.XamarinAndroid.Scheduler.IViewProviderInvoker, DevExpress.Xamarin.Android.Scheduler\n" +
			"n_getOwnerPanel:()Lcom/devexpress/scheduler/panels/ManagedLayoutViewGroup;:GetGetOwnerPanelHandler:DevExpress.XamarinAndroid.Scheduler.IViewProviderInvoker, DevExpress.Xamarin.Android.Scheduler\n" +
			"n_setOwnerPanel:(Lcom/devexpress/scheduler/panels/ManagedLayoutViewGroup;)V:GetSetOwnerPanel_Lcom_devexpress_scheduler_panels_ManagedLayoutViewGroup_Handler:DevExpress.XamarinAndroid.Scheduler.IViewProviderInvoker, DevExpress.Xamarin.Android.Scheduler\n" +
			"n_createNewView:(I)Landroid/view/View;:GetCreateNewView_IHandler:DevExpress.XamarinAndroid.Scheduler.IViewProviderInvoker, DevExpress.Xamarin.Android.Scheduler\n" +
			"n_enableView:(Landroid/view/View;)V:GetEnableView_Landroid_view_View_Handler:DevExpress.XamarinAndroid.Scheduler.IViewProviderInvoker, DevExpress.Xamarin.Android.Scheduler\n" +
			"n_getStubColor:(I)I:GetGetStubColor_IHandler:DevExpress.XamarinAndroid.Scheduler.IViewProviderInvoker, DevExpress.Xamarin.Android.Scheduler\n" +
			"n_recycle:()V:GetRecycleHandler:DevExpress.XamarinAndroid.Scheduler.IViewProviderInvoker, DevExpress.Xamarin.Android.Scheduler\n" +
			"n_recycleView:(Landroid/view/View;)V:GetRecycleView_Landroid_view_View_Handler:DevExpress.XamarinAndroid.Scheduler.IViewProviderInvoker, DevExpress.Xamarin.Android.Scheduler\n" +
			"n_requestView:(I)Landroid/view/View;:GetRequestView_IHandler:DevExpress.XamarinAndroid.Scheduler.IViewProviderInvoker, DevExpress.Xamarin.Android.Scheduler\n" +
			"n_setViewLoader:(Lcom/devexpress/scheduler/providers/ViewLoader;)V:GetSetViewLoader_Lcom_devexpress_scheduler_providers_ViewLoader_Handler:DevExpress.XamarinAndroid.Scheduler.IViewProviderInvoker, DevExpress.Xamarin.Android.Scheduler\n" +
			"";
		mono.android.Runtime.register ("DevExpress.XamarinForms.Scheduler.Droid.Internal.TemplatedViewProvider, DevExpress.XamarinForms.Scheduler.Android", TemplatedViewProvider.class, __md_methods);
	}


	public TemplatedViewProvider ()
	{
		super ();
		if (getClass () == TemplatedViewProvider.class)
			mono.android.TypeManager.Activate ("DevExpress.XamarinForms.Scheduler.Droid.Internal.TemplatedViewProvider, DevExpress.XamarinForms.Scheduler.Android", "", this, new java.lang.Object[] {  });
	}


	public int getItemsCount ()
	{
		return n_getItemsCount ();
	}

	private native int n_getItemsCount ();


	public com.devexpress.scheduler.panels.ManagedLayoutViewGroup getOwnerPanel ()
	{
		return n_getOwnerPanel ();
	}

	private native com.devexpress.scheduler.panels.ManagedLayoutViewGroup n_getOwnerPanel ();


	public void setOwnerPanel (com.devexpress.scheduler.panels.ManagedLayoutViewGroup p0)
	{
		n_setOwnerPanel (p0);
	}

	private native void n_setOwnerPanel (com.devexpress.scheduler.panels.ManagedLayoutViewGroup p0);


	public android.view.View createNewView (int p0)
	{
		return n_createNewView (p0);
	}

	private native android.view.View n_createNewView (int p0);


	public void enableView (android.view.View p0)
	{
		n_enableView (p0);
	}

	private native void n_enableView (android.view.View p0);


	public int getStubColor (int p0)
	{
		return n_getStubColor (p0);
	}

	private native int n_getStubColor (int p0);


	public void recycle ()
	{
		n_recycle ();
	}

	private native void n_recycle ();


	public void recycleView (android.view.View p0)
	{
		n_recycleView (p0);
	}

	private native void n_recycleView (android.view.View p0);


	public android.view.View requestView (int p0)
	{
		return n_requestView (p0);
	}

	private native android.view.View n_requestView (int p0);


	public void setViewLoader (com.devexpress.scheduler.providers.ViewLoader p0)
	{
		n_setViewLoader (p0);
	}

	private native void n_setViewLoader (com.devexpress.scheduler.providers.ViewLoader p0);

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
