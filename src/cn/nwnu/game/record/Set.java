package cn.nwnu.game.record;

public class Set {

	static boolean isMusic = true;
	static boolean isTimer = true;

	public static boolean isMusic() {
		return isMusic;
	}

	public static void setMusic() {
		if (Set.isMusic) {
			Set.isMusic = false;
		} else {
			Set.isMusic = true;
		}
	}

	public static void setnewMusic() {
			Set.isMusic = true;
	}
	
	public static void disMusic() {
			Set.isMusic = false;
	}
	
	public static void setMusic(boolean music) {
		Set.isMusic = music;
	}

	public static void setTimer(boolean timer) {
		Set.isTimer = timer;
	}

	public static boolean isTimer() {
		return isTimer;
	}

	public static void setTimer() {
		if (Set.isTimer) {
			Set.isTimer = false;
		} else {
			Set.isTimer = true;
		}
	}

}
