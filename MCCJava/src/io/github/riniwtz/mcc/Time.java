package io.github.riniwtz.mcc;

public interface Time {
	void addTime(double timeDouble);
	void setTime(double timeDouble);
	double getTime();
	int getGameTicks();
}
