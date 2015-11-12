package fritze.apps.contrastshowertimer.Fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import fritze.apps.contrastshowertimer.Managers.GlobalFragmentManager;
import fritze.apps.contrastshowertimer.Managers.TypefaceManager;
import fritze.apps.contrastshowertimer.R;
import fritze.apps.contrastshowertimer.Models.ShowerTimer;


@SuppressLint("InflateParams")
public class TimerFragment extends Fragment{
	private View view;
	private TextView timerText, timerTitle;
	private ToggleButton pauseButton;
	private ShowerTimer sTimer;
	private boolean isPaused;
	private CountDownTimer timer;
	private RelativeLayout timerLayout;
	private int white, blue, red, green;

	private static final int SECOND = 1000;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        white = getResources().getColor(R.color.white);
        blue = getResources().getColor(R.color.blue);
        red = getResources().getColor(R.color.red);
        green = getResources().getColor(R.color.green);
	}
	
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.timer_layout, null);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		timerLayout = (RelativeLayout) view.findViewById(R.id.timer_layout);
		
		timerText = (TextView) view.findViewById(R.id.chronometerTimer);
		timerText.setTypeface(TypefaceManager.getTfReg(getActivity()));
		
		timerTitle = (TextView) view.findViewById(R.id.textViewTimerTitle);
		timerTitle.setTypeface(TypefaceManager.getTfBold(getActivity()));
		
		pauseButton = (ToggleButton) view.findViewById(R.id.toggleButtonPauseResume);
		//pauseButton.setOnCheckedChangeListener(listener)
	}

	@Override
	public void onResume() {
        // TODO this should check if you're resuming from a timer, and may actually continue
		super.onResume();
        setTimerTitleText("GET READY", this.green);
		readyCountdown();
	}

	private void setBackground(){
		if(this.sTimer.isHot()) {
			timerLayout.setBackgroundColor(this.red);
		} else {
			timerLayout.setBackgroundColor(this.blue);
		}
	}

    private void readyCountdown(){
        final int DURATION = 4000;
        setTimerText(DURATION, this.green);
        timer = new CountDownTimer(DURATION, SECOND/2) {

            @Override
            public void onTick(long millisUntilFinished) {
                int secondsLeft = (int) millisUntilFinished / 1000;
                setTimerText(secondsLeft, green);
            }

            @Override
            public void onFinish() {
                // TODO play a sound/notification etc
                timerTitle.setVisibility(View.GONE);
                setTimerText(sTimer.getDuration(), white);
                setBackground();
                countdown(sTimer.getCountdownCount(), sTimer.getDuration() * SECOND);
            }

        }.start();
    }

	private void countdown(final int cyclesRemaining, final int duration){
		/* Due to a bug in the countdown class, if there is a slight 
		 * error in time calculation, the last tick won't be displayed.
		 * Therefore it's calculated every 500ms, but the display will only
		 * update every second.
		 */

        if (cyclesRemaining < 1) {
            GlobalFragmentManager.displayDone();
            return;
        }
        timer = new CountDownTimer(duration, SECOND/2){

			@Override
			public void onTick(long millisUntilFinished) {
				int secondsLeft = (int) millisUntilFinished / 1000;
				setTimerText(secondsLeft, white);
                sTimer.setSecondsRemaining(secondsLeft);
			}

			@Override
			public void onFinish() {
				// TODO play a sound/notification etc
				timerText.setText("");
                sTimer.toggleIsHot();
                setBackground();
                countdown(cyclesRemaining - 1, duration);
			}
			
		};
		timer.start();
	}

	private void setTimerTitleText(String text, int textColor){
		timerTitle.setText(text);
		timerTitle.setTextColor(textColor);
	}
	
	private void setTimerText(int seconds, int textColor){
		timerText.setTextColor(textColor);
		String text = "";
		text += Integer.toString((int) seconds / 60) + ":";
		text += Integer.toString(seconds % 60);
		timerText.setText(text);
	}

	public void setTimer(ShowerTimer timer) {
		this.sTimer = timer;
	}
	
	private OnClickListener pauseButtonListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(isPaused){
				
			}
		}
	};
}
