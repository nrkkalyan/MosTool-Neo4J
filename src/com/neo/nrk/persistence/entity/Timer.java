package com.neo.nrk.persistence.entity;

public class Timer extends BaseEntity {

	public enum TimerType {
		ITUTIMER("ITU Timer"), CUSTOMTIMER("Custom Timer"), DEFAULTTIMER("Default Timer");
		private final String timerName;

		TimerType(String name) {
			this.timerName = name;
		}

		public String getTimerName() {
			return timerName;
		}

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int t1 = -1;
	private int t2 = 5;
	private int t3 = 3;
	private TimerType timerType = TimerType.DEFAULTTIMER;

	public int getT1() {
		return t1;
	}

	public void setT1(int t1) {
		this.t1 = t1;
	}

	public int getT2() {
		return t2;
	}

	public void setT2(int t2) {
		this.t2 = t2;
	}

	public int getT3() {
		return t3;
	}

	public void setT3(int t3) {
		this.t3 = t3;
	}

	public void setTimerType(TimerType timerType) {
		this.timerType = timerType;
	}

	public TimerType getTimerType() {
		return timerType;
	}

	@Override
	public void validate() {
		if (timerType == null) {
			throw new IllegalArgumentException("TimerType is required");
		}
		if (timerType == TimerType.DEFAULTTIMER) {
			if (t1 != -1) {
				throw new IllegalArgumentException("T1 must not be set for a Default TimerType");
			}
			if (t2 <= 0 || t3 <= 0) {
				throw new IllegalArgumentException("All timers must be positive for the Selected TimerType.");
			}
		}

		if (timerType == TimerType.CUSTOMTIMER || timerType == TimerType.ITUTIMER) {
			if (t1 <= 0 || t2 <= 0 || t3 <= 0) {
				throw new IllegalArgumentException("All timers must be positive for the selected TimerType.");
			}
		}

	}

}
