package cn.nwnu.game;

import cn.nwnu.game.R;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

public class HelpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(R.layout.help);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
				R.drawable.icon);

	}

}
