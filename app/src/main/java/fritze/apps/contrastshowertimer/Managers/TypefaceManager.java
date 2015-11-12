package fritze.apps.contrastshowertimer.Managers;

import android.app.Activity;
import android.graphics.Typeface;

public class TypefaceManager {
	private static Typeface tfLight = null, tfMed = null, tfBold = null, tfReg = null;

	public static Typeface getTfLight(Activity activity) {
		if(tfLight == null){
			tfLight  = Typeface.createFromAsset(activity.getAssets(), "Roboto/Roboto-Light.ttf");
		}
		return tfLight;
	}

	public static Typeface getTfMed(Activity activity) {
		if(tfMed == null){
			tfMed  = Typeface.createFromAsset(activity.getAssets(), "Roboto/Roboto-Medium.ttf");
		}
		return tfMed;
	}

	public static Typeface getTfBold(Activity activity) {
		if(tfBold == null){
			tfBold  = Typeface.createFromAsset(activity.getAssets(), "Roboto/Roboto-Bold.ttf");
		}
		return tfBold;
	}
	
	public static Typeface getTfReg(Activity activity) {
		if(tfReg == null){
			tfReg  = Typeface.createFromAsset(activity.getAssets(), "Roboto/Roboto-Regular.ttf");
		}
		return tfReg;
	}
}
	
