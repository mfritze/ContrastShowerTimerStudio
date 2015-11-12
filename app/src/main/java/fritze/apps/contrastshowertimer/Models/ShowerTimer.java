package fritze.apps.contrastshowertimer.Models;

public class ShowerTimer {
	private boolean isHot;
	private int countdownCount, duration, secondsRemaining;
	

	public ShowerTimer(int cycles, int duration, boolean isHotStart, boolean isHotEnd) {
		this.duration = duration;
		this.countdownCount = cycles*2;
        this.isHot = isHotStart;
        this.secondsRemaining = duration;

		if(isHotStart == isHotEnd){
			this.countdownCount++;
		}
	}

	public int getDuration() {
		return duration;
	}

	public int getCountdownCount() {
		return countdownCount;
	}

    public boolean isHot() {return this.isHot;}

    public void toggleIsHot() {
        this.isHot = !this.isHot;
    }

    public void setSecondsRemaining(int secondsRemaining) {
        this.secondsRemaining = secondsRemaining;
    }
}
