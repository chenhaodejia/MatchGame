package cn.nwnu.game;

import cn.nwnu.game.file.FileOperation;
import cn.nwnu.game.match.JViewGroup;
import cn.nwnu.game.music.Music;
import cn.nwnu.game.record.Record;
import cn.nwnu.game.record.Set;
import cn.nwnu.game.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class MatchActivity extends Activity {
	/** Called when the activity is first created. */

	JViewGroup mviewgroup;
	Music music;
	boolean isPause;
	boolean destory;
	static int level;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WindowManager manager = getWindowManager();
		int width = manager.getDefaultDisplay().getWidth();

		mviewgroup = new JViewGroup(this, width, this, level);
		mviewgroup.setBackgroundResource(R.drawable.boy);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(mviewgroup);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
				R.drawable.icon);

		isPause = false;
		destory = false;

		// ��������
		music = new Music(this);
		if (Set.isMusic()) {
			Music.play();
		}
		if (Set.isTimer()) {
			mviewgroup.getTimer().start();
		}
	}

	public boolean onTouchEvent(MotionEvent event) {
		boolean re = mviewgroup.onTouchEvent(event);
		// Log.i("activity","onTouchEvent");
		mviewgroup.invalidate();
		return re;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// �ر�����
		if (Set.isMusic() && music.isPlaying() && destory) {
			Music.stop();
		}
		if (Set.isTimer() && destory) {
			mviewgroup.getTimer().stop();
		}

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		Log.i("tag", "onPause");
		super.onPause();

		// ��ͣ����
		if (Set.isMusic() && (!destory) && music.isPlaying()) {
			Music.pause();
		}
		if (Set.isTimer() && (!destory)) {
			mviewgroup.getTimer().pause();
		}

		// ת����ͣ����
		if ((!isPause) && (!destory)) {
			Intent intent = new Intent();
			intent.setClass(MatchActivity.this, PauseActivity.class);
			startActivity(intent);
			isPause = true;
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.i("tag", "onResume");
		super.onResume();
		isPause = false;
		// ��������
		if (Set.isMusic() && (!music.isPlaying())) {
			Music.play();
		}
		if (Set.isTimer()) {
			mviewgroup.getTimer().restart();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (!mviewgroup.getEx().isEnd()) {
				new AlertDialog.Builder(this)
						.setTitle("��ܰ��ʾ")
						.setMessage("��ǰ�˳�����Ϊʧ�ܼ�¼���Ƿ������")
						// .setIcon(R.drawable.quit)
						.setPositiveButton("����",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {

										Record.Operate(false,
												MatchActivity.level);
										FileOperation fileop = new FileOperation(
												MatchActivity.this);
										fileop.write();
										destory = true;
										MatchActivity.this.finish();
									}
								})
						.setNegativeButton("ȡ��",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										// ȡ����ť�¼�
									}
								}).show();
			} else {
				destory = true;
				MatchActivity.this.finish();
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	public void showLoseDia() {
		Log.i("tag", "show lose dialogue.......................");
		new AlertDialog.Builder(this)
				.setTitle("����ʧ��")
				.setMessage("���ź���û��������...")
				// .setIcon(R.drawable.quit)
				.setPositiveButton("����һ��",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
							}
						})
				.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// ȡ����ť�¼�
					}
				}).show();
	}

	public static void setlevel(int level) {
		MatchActivity.level = level;
	}

}