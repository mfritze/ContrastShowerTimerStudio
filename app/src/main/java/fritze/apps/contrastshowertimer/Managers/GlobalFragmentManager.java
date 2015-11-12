package fritze.apps.contrastshowertimer.Managers;

import android.app.FragmentManager;
import fritze.apps.contrastshowertimer.R;
import fritze.apps.contrastshowertimer.Models.ShowerTimer;
import fritze.apps.contrastshowertimer.TimerActivity;
import fritze.apps.contrastshowertimer.Fragments.DoneFragment;
import fritze.apps.contrastshowertimer.Fragments.NewTimerFragment;
import fritze.apps.contrastshowertimer.Fragments.TimerFragment;

public class GlobalFragmentManager {
	private static NewTimerFragment newTimerFragment;
	private static TimerFragment timerFragment;
	private static DoneFragment doneFragment;
	private static FragmentManager manager;
	private TimerActivity context;
	private static int containerId;
	private static boolean isShowingTimer;
	
	public GlobalFragmentManager(TimerActivity timerActivity){
		newTimerFragment = new NewTimerFragment();
		timerFragment = new TimerFragment();
		doneFragment = new DoneFragment();
		
		context = timerActivity;
		manager = context.getFragmentManager();
		containerId = R.id.mainFragmentHolder;
		isShowingTimer = false;
	}

	public static void animateNewTimer(ShowerTimer timer){
		if(isShowingTimer){
			manager.popBackStack();
			isShowingTimer = false;
			return;
		}
		isShowingTimer = true;
		timerFragment.setTimer(timer);
		manager.beginTransaction()
		    .setCustomAnimations(R.animator.flip_right_in, R.animator.flip_right_out,
                			 R.animator.flip_left_in, R.animator.flip_left_out)
		    .replace(containerId, timerFragment)
		    .commit();
	}
	
	public static void displayNewTimer(){
		isShowingTimer = false;
		manager.beginTransaction()
		.replace(containerId, newTimerFragment)
		.addToBackStack(null)
		.commit();
	}
	
	public static void displayDone(){
		manager.beginTransaction()
		.replace(containerId, doneFragment)
		.commit();
	}
	
	public static void displayTimer(){
		isShowingTimer = true;
		manager.beginTransaction()
		.replace(containerId, timerFragment)
		.commit();
	}
	
    public static boolean isShowingTimer(){return isShowingTimer;}

}
