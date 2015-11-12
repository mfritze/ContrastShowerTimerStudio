package fritze.apps.contrastshowertimer;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import fritze.apps.contrastshowertimer.Managers.GlobalFragmentManager;

public class TimerActivity extends Activity{
	GlobalFragmentManager manager;
	ActionBar bar;
    Toaster toaster;

    // These beautiful constants provided by: Button Mashing
    private final String    IS_SHOWING_TIMER = "20934582345",
                            IS_HOT = "1349813453424",
                            SECONDS_REMAINING = "2345092874502",
                            SEGMENTS_REMAINING = "9834639823982";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timer_activity_layout);
		manager = new GlobalFragmentManager(this);
		bar = getActionBar();
		bar.hide();
        toaster = new Toaster(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		manager.displayNewTimer();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timer, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        boolean isShowingTimer = GlobalFragmentManager.isShowingTimer();
        savedInstanceState.putBoolean(IS_SHOWING_TIMER, isShowingTimer);
        Toaster.toast("Saving");
        if (isShowingTimer) {
            Toaster.toast("Saving in timer");
            savedInstanceState.putBoolean(IS_HOT, true);
            savedInstanceState.putInt(SECONDS_REMAINING, 1);
            savedInstanceState.putInt(SEGMENTS_REMAINING, 2);
        }
    }


    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        boolean isShowingTimer = savedInstanceState.getBoolean(IS_SHOWING_TIMER);
        Toaster.toast("Restoring");
        if (isShowingTimer) {
            Toaster.toast("Restoring in timer");
            boolean isHot = savedInstanceState.getBoolean(IS_HOT);
            int secondsRemaining = savedInstanceState.getInt(SECONDS_REMAINING);
            int segmentsRemaining = savedInstanceState.getInt(SEGMENTS_REMAINING);
            //GlobalFragmentManager.resumeTimer(isHot, secondsRemaining, segmentsRemaining);
        }
    }
}
