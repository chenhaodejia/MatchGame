package cn.nwnu.game;

import cn.nwnu.game.match.MyButton;
import cn.nwnu.game.R;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageButton;

public class PauseActivity extends Activity {

	ImageButton quit, exit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.pause);

		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
				R.drawable.icon);

		quit = (ImageButton) findViewById(R.id.pausequit);
		quit.setImageResource(R.drawable.quit1);

		quit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				AnimationSet animationSet=new AnimationSet(true);
				ScaleAnimation scaleAnimation=new ScaleAnimation(1,1.3f,1,1.3f,
						Animation.RELATIVE_TO_SELF,0.5f,
						Animation.RELATIVE_TO_SELF,0.5f);
				
		         scaleAnimation.setAnimationListener(new AnimationListener() {
						
						public void onAnimationStart(Animation animation) {
							// TODO Auto-generated method stub
							}		
						
						public void onAnimationRepeat(Animation animation) {
							// TODO Auto-generated method stub
							
						}
						
						public void onAnimationEnd(Animation animation) {
							// TODO Auto-generated method stub
							//¶¯Ö´ÐÐ½áÊø
							PauseActivity.this.finish();
						}
					});
					
				
				animationSet.addAnimation(scaleAnimation);
				animationSet.setFillBefore(true);
				animationSet.setDuration(100);
				quit.startAnimation(animationSet);
				
					
			}
		});

	}


}
