package cn.nwnu.game.record;

import java.util.ArrayList;
import java.util.List;

public class Record {

	// 已玩游戏，已胜游戏
	static List<Integer> game_times = new ArrayList<Integer>();
	static List<Integer> win_times = new ArrayList<Integer>();
	// 最多连胜，最多连败
	static List<Integer> al_win_times = new ArrayList<Integer>();
	static List<Integer> al_lose_times = new ArrayList<Integer>();
	// 当前连胜，当前连败
	static List<Integer> cu_win_times = new ArrayList<Integer>();
	static List<Integer> cu_lose_times = new ArrayList<Integer>();

	public static void init() {
		game_times.add(0);
		game_times.add(0);
		game_times.add(0);

		win_times.add(0);
		win_times.add(0);
		win_times.add(0);

		al_win_times.add(0);
		al_win_times.add(0);
		al_win_times.add(0);

		al_lose_times.add(0);
		al_lose_times.add(0);
		al_lose_times.add(0);

		cu_win_times.add(0);
		cu_win_times.add(0);
		cu_win_times.add(0);

		cu_lose_times.add(0);
		cu_lose_times.add(0);
		cu_lose_times.add(0);

	}

	public static void clear() {
		game_times.clear();

		game_times.add(0);
		game_times.add(0);
		game_times.add(0);

		win_times.clear();
		win_times.add(0);
		win_times.add(0);
		win_times.add(0);

		al_win_times.clear();
		al_win_times.add(0);
		al_win_times.add(0);
		al_win_times.add(0);

		al_lose_times.clear();
		al_lose_times.add(0);
		al_lose_times.add(0);
		al_lose_times.add(0);

		cu_win_times.clear();
		cu_win_times.add(0);
		cu_win_times.add(0);
		cu_win_times.add(0);

		cu_lose_times.clear();
		cu_lose_times.add(0);
		cu_lose_times.add(0);
		cu_lose_times.add(0);

	}

	public static int getGameTimes(int level) {
		return game_times.get(level);
	}

	public static int getWinTimes(int level) {
		return win_times.get(level);
	}

	public static int getAlWinTimes(int level) {
		return al_win_times.get(level);
	}

	public static int getAlLoseTimes(int level) {
		return al_lose_times.get(level);
	}

	public static String getWinRate(int level) {
		float rate = 0;
		if (game_times.get(level) != 0) {
			rate = (float) win_times.get(level) / game_times.get(level);
			rate = rate * 100;
		}
		return rate + "%";
	}

	public static void Operate(boolean re, int level) {
		if (re) {
			// 胜利
			game_times.set(level, game_times.get(level) + 1);
			win_times.set(level, win_times.get(level) + 1);
			cu_win_times.set(level, cu_win_times.get(level) + 1);
			if (cu_win_times.get(level) > al_win_times.get(level)) {
				al_win_times.set(level, cu_win_times.get(level));
			}
		} else {
			// 失败
			game_times.set(level, game_times.get(level) + 1);
			cu_lose_times.set(level, cu_lose_times.get(level) + 1);
			if (cu_lose_times.get(level) > al_lose_times.get(level)) {
				al_lose_times.set(level, cu_lose_times.get(level));
			}
		}
	}

	public static void setGameTimes(int level, int data) {
		game_times.set(level, data);

	}

	public static void setWinTimes(int level, int data) {
		win_times.set(level, data);
	}

	public static void setAlWinTimes(int level, int data) {
		al_win_times.set(level, data);
	}

	public static void setAlLoseTimes(int level, int data) {
		al_lose_times.set(level, data);
	}

}
