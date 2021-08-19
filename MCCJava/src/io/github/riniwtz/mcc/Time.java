package io.github.riniwtz.mcc;

import java.math.BigDecimal;

public interface Time {
	void addTime(double timeDouble);
	void setTime(double timeDouble);
	BigDecimal getTime();
	int getGameTicks();
}
