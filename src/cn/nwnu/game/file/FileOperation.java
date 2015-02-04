package cn.nwnu.game.file;

import cn.nwnu.game.record.Record;
import cn.nwnu.game.record.Set;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class FileOperation {
	
	Context mContext;
	public FileOperation(Context context)
	{
		this.mContext=context;
	}

	public void write()
	{
		 Log.i("tag","write");
		SharedPreferences preferences = mContext.getSharedPreferences("save", Context.MODE_WORLD_READABLE);
		Editor editor = preferences.edit();
		
		editor.putBoolean("musicmode", Set.isMusic());
		editor.putBoolean("timemode", Set.isTimer());
		
		editor.putInt("easygametimes", Record.getGameTimes(0));
		editor.putInt("easywintimes", Record.getWinTimes(0));
		editor.putInt("easyalwintimes", Record.getAlWinTimes(0));
		editor.putInt("easyallosetimes",Record.getAlLoseTimes(0));
		
		editor.putInt("middlegametimes", Record.getGameTimes(1));
		editor.putInt("middlewintimes", Record.getWinTimes(1));
		editor.putInt("middlealwintimes", Record.getAlWinTimes(1));
		editor.putInt("middleallosetimes",Record.getAlLoseTimes(1));
		
		editor.putInt("hardgametimes", Record.getGameTimes(2));
		editor.putInt("hardwintimes", Record.getWinTimes(2));
		editor.putInt("hardalwintimes", Record.getAlWinTimes(2));
		editor.putInt("hardallosetimes",Record.getAlLoseTimes(2));
		
		Log.i("file operation",""+Record.getGameTimes(0));
		
		editor.commit();
	}
	
	public void read()
	{
		SharedPreferences preferences = mContext.getSharedPreferences("save", Context.MODE_PRIVATE);
		
		Set.setMusic(preferences.getBoolean("musicmode", true));
		Set.setTimer(preferences.getBoolean("timemode", true));
		
		Record.setGameTimes(0, preferences.getInt("easygametimes", 1));
		Record.setWinTimes(0, preferences.getInt("easywintimes", 0));
		Record.setAlWinTimes(0, preferences.getInt("easyalwintimes", 0));
		Record.setAlLoseTimes(0, preferences.getInt("easyallosetimes", 0));
		
		Record.setGameTimes(1, preferences.getInt("middlegametimes", 1));
		Record.setWinTimes(1, preferences.getInt("middlewintimes", 0));
		Record.setAlWinTimes(1, preferences.getInt("middlealwintimes", 0));
		Record.setAlLoseTimes(1, preferences.getInt("middleallosetimes", 0));
		
		Record.setGameTimes(2, preferences.getInt("hardgametimes", 1));
		Record.setWinTimes(2, preferences.getInt("hardwintimes", 0));
		Record.setAlWinTimes(2, preferences.getInt("hardalwintimes", 0));
		Record.setAlLoseTimes(2, preferences.getInt("hardallosetimes", 0));
	}
}
