package fritze.apps.contrastshowertimer;

import android.widget.Toast;

public class Toaster {
	private static TimerActivity context;
	public Toaster(TimerActivity activityContext){
		context = activityContext;
	}
	
	static public void toast(String message){
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
}
