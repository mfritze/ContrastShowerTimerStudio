package fritze.apps.contrastshowertimer.Fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.ToggleButton;
import fritze.apps.contrastshowertimer.R;
import fritze.apps.contrastshowertimer.Models.ShowerTimer;
import fritze.apps.contrastshowertimer.Managers.GlobalFragmentManager;
import fritze.apps.contrastshowertimer.Managers.TypefaceManager;

@SuppressLint("InflateParams")
public class NewTimerFragment extends Fragment{
	private View view;
	private Button startButton;
	private NumberPicker cyclesPicker, minutesPicker, 
			secondsPicker, secondsOrderPicker;
	private TextView cyclesText, durationText,
					 startText, endText;
	private ToggleButton startToggle, endToggle;
	private ShowerTimer timer;
	private int duration, cycles;
	private boolean isHotStart, isHotEnd;
	
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.new_timer_layout, null);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		cyclesPicker = (NumberPicker) view.findViewById(R.id.numberPickerCycles);
		cyclesPicker.setMinValue(0);
		cyclesPicker.setMaxValue(20);
		cyclesPicker.setWrapSelectorWheel(true);
		
		minutesPicker = (NumberPicker) view.findViewById(R.id.numberPickerMinutes);
		minutesPicker.setMinValue(0);
		minutesPicker.setMaxValue(59);
		minutesPicker.setWrapSelectorWheel(true);
		
		secondsPicker = (NumberPicker) view.findViewById(R.id.numberPickerSeconds);
		secondsPicker.setMinValue(0);
		secondsPicker.setMaxValue(9);
		secondsPicker.setWrapSelectorWheel(true);
		
		secondsOrderPicker = (NumberPicker) view.findViewById(R.id.numberPickerSecondsOrder);
		secondsOrderPicker.setMinValue(0);
		secondsOrderPicker.setMaxValue(5);
		secondsOrderPicker.setWrapSelectorWheel(true);
		
		startButton = (Button) view.findViewById(R.id.buttonStartTimer);
		startButton.setOnClickListener(startListener);
		startButton.setTypeface(TypefaceManager.getTfLight(getActivity()));
		
		cyclesText = (TextView) view.findViewById(R.id.textViewCyclesCount);
		cyclesText.setTypeface(TypefaceManager.getTfBold(getActivity()));
		
		durationText = (TextView) view.findViewById(R.id.textViewCycleDuration);
		durationText.setTypeface(TypefaceManager.getTfBold(getActivity()));
		
		endText = (TextView) view.findViewById(R.id.textViewStartTemp);
		endText.setTypeface(TypefaceManager.getTfBold(getActivity()));
		
		startText = (TextView) view.findViewById(R.id.textViewEndTemp);
		startText.setTypeface(TypefaceManager.getTfBold(getActivity()));
		
		startToggle = (ToggleButton) view.findViewById(R.id.toggleButtonStartCycle);
		endToggle = (ToggleButton) view.findViewById(R.id.toggleButtonEndCycle);

	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	private OnClickListener startListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			isHotStart = !startToggle.isChecked();
			isHotEnd = !endToggle.isChecked();
			cycles = cyclesPicker.getValue();
			duration = minutesPicker.getValue()*60 +
						secondsOrderPicker.getValue()*10 + 
						secondsPicker.getValue();
			
			timer = new ShowerTimer(cycles, duration, isHotStart, isHotEnd);
			GlobalFragmentManager.animateNewTimer(timer);
			
		}
	};
	


}
