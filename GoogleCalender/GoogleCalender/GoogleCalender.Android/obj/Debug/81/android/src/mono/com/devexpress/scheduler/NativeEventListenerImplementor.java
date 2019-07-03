package mono.com.devexpress.scheduler;


public class NativeEventListenerImplementor
	extends java.lang.Object
	implements
		mono.android.IGCUserPeer,
		com.devexpress.scheduler.NativeEventListener
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"n_dayViewTopRowIndexChanged:(D)V:GetDayViewTopRowIndexChanged_DHandler:DevExpress.XamarinAndroid.Scheduler.INativeEventListenerInvoker, DevExpress.Xamarin.Android.Scheduler\n" +
			"n_doubleTap:(Lcom/devexpress/scheduler/views/hittesting/SchedulerHitInfo;)V:GetDoubleTap_Lcom_devexpress_scheduler_views_hittesting_SchedulerHitInfo_Handler:DevExpress.XamarinAndroid.Scheduler.INativeEventListenerInvoker, DevExpress.Xamarin.Android.Scheduler\n" +
			"n_idle:()V:GetIdleHandler:DevExpress.XamarinAndroid.Scheduler.INativeEventListenerInvoker, DevExpress.Xamarin.Android.Scheduler\n" +
			"n_longPress:(Lcom/devexpress/scheduler/views/hittesting/SchedulerHitInfo;)V:GetLongPress_Lcom_devexpress_scheduler_views_hittesting_SchedulerHitInfo_Handler:DevExpress.XamarinAndroid.Scheduler.INativeEventListenerInvoker, DevExpress.Xamarin.Android.Scheduler\n" +
			"n_requestContainers:(I)[Lcom/devexpress/scheduler/viewInfos/containers/DayContainerViewInfo;:GetRequestContainers_IHandler:DevExpress.XamarinAndroid.Scheduler.INativeEventListenerInvoker, DevExpress.Xamarin.Android.Scheduler\n" +
			"n_requestWeekContainers:(I)[Lcom/devexpress/scheduler/viewInfos/containers/WeekContainerViewInfo;:GetRequestWeekContainers_IHandler:DevExpress.XamarinAndroid.Scheduler.INativeEventListenerInvoker, DevExpress.Xamarin.Android.Scheduler\n" +
			"n_tap:(Lcom/devexpress/scheduler/views/hittesting/SchedulerHitInfo;)V:GetTap_Lcom_devexpress_scheduler_views_hittesting_SchedulerHitInfo_Handler:DevExpress.XamarinAndroid.Scheduler.INativeEventListenerInvoker, DevExpress.Xamarin.Android.Scheduler\n" +
			"n_updateAppointmentViewPort:(D)Ljava/util/ArrayList;:GetUpdateAppointmentViewPort_DHandler:DevExpress.XamarinAndroid.Scheduler.INativeEventListenerInvoker, DevExpress.Xamarin.Android.Scheduler\n" +
			"";
		mono.android.Runtime.register ("DevExpress.XamarinAndroid.Scheduler.INativeEventListenerImplementor, DevExpress.Xamarin.Android.Scheduler", NativeEventListenerImplementor.class, __md_methods);
	}


	public NativeEventListenerImplementor ()
	{
		super ();
		if (getClass () == NativeEventListenerImplementor.class)
			mono.android.TypeManager.Activate ("DevExpress.XamarinAndroid.Scheduler.INativeEventListenerImplementor, DevExpress.Xamarin.Android.Scheduler", "", this, new java.lang.Object[] {  });
	}


	public void dayViewTopRowIndexChanged (double p0)
	{
		n_dayViewTopRowIndexChanged (p0);
	}

	private native void n_dayViewTopRowIndexChanged (double p0);


	public void doubleTap (com.devexpress.scheduler.views.hittesting.SchedulerHitInfo p0)
	{
		n_doubleTap (p0);
	}

	private native void n_doubleTap (com.devexpress.scheduler.views.hittesting.SchedulerHitInfo p0);


	public void idle ()
	{
		n_idle ();
	}

	private native void n_idle ();


	public void longPress (com.devexpress.scheduler.views.hittesting.SchedulerHitInfo p0)
	{
		n_longPress (p0);
	}

	private native void n_longPress (com.devexpress.scheduler.views.hittesting.SchedulerHitInfo p0);


	public com.devexpress.scheduler.viewInfos.containers.DayContainerViewInfo[] requestContainers (int p0)
	{
		return n_requestContainers (p0);
	}

	private native com.devexpress.scheduler.viewInfos.containers.DayContainerViewInfo[] n_requestContainers (int p0);


	public com.devexpress.scheduler.viewInfos.containers.WeekContainerViewInfo[] requestWeekContainers (int p0)
	{
		return n_requestWeekContainers (p0);
	}

	private native com.devexpress.scheduler.viewInfos.containers.WeekContainerViewInfo[] n_requestWeekContainers (int p0);


	public void tap (com.devexpress.scheduler.views.hittesting.SchedulerHitInfo p0)
	{
		n_tap (p0);
	}

	private native void n_tap (com.devexpress.scheduler.views.hittesting.SchedulerHitInfo p0);


	public java.util.ArrayList updateAppointmentViewPort (double p0)
	{
		return n_updateAppointmentViewPort (p0);
	}

	private native java.util.ArrayList n_updateAppointmentViewPort (double p0);

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