
public class Time {
	protected float time = 0.0F;
	protected float maxTime = 24000F;
	protected boolean isNight = false;
	
	Time() {
		if (getTime() >= 13000)
			isNight = true;
	}
	
	public void addTime(float time) {
		this.time += time;
	}
	
	public void setTime(float time) {
		this.time = time;
	}
	
	public float getTime() {
		if (time >= maxTime)
			this.time = (float) ((this.time / maxTime) - (Math.floor(this.time / maxTime))) * maxTime;
		
		return Math.round(time);
	}
}
