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
import fritze.apps.contrastshowertimer.R;
import fritze.apps.contrastshowertimer.Managers.GlobalFragmentManager;

@SuppressLint("InflateParams")
public class DoneFragment extends Fragment {
	private View view;
	private Button repeat, newTimer;
	
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.finished_layout, null);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		repeat = (Button) view.findViewById(R.id.buttonRepeatTimer);
		newTimer = (Button) view.findViewById(R.id.buttonNewTimer);
		repeat.setOnClickListener(repeatListener);
		newTimer.setOnClickListener(newTimerListener);
	}
	
	private OnClickListener repeatListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
	    // TODO this probably breaks back/forward states
	    GlobalFragmentManager.displayTimer();
		}
	};
	
	private OnClickListener newTimerListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
        GlobalFragmentManager.displayNewTimer();
		}
	};
	
}
