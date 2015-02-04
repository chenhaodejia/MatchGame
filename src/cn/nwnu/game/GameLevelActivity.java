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

public class GameLevelActivity extends Activity {

	ImageButton easy, middle, hard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.gamelevel);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
				R.drawable.icon);

		easy = (ImageButton) findViewById(R.id.easy);
		easy.setImageResource(R.drawable.easy1);
		middle = (ImageButton) findViewById(R.id.middle);
		middle.setImageResource(R.drawable.middle1);
		hard = (ImageButton) findViewById(R.id.hard);
		hard.setImageResource(R.drawable.hard1);


		easy.setOnClickListener(new View.OnClickListener() {

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
							//动执行结束
							
							MatchActivity.setlevel(0);
							Intent intent = new Intent();
							intent.setClass(GameLevelActivity.this, MatchActivity.class);
							GameLevelActivity.this.startActivity(intent);
							GameLevelActivity.this.finish();
						}
					});
					
				
				animationSet.addAnimation(scaleAnimation);
				animationSet.setFillBefore(true);
				animationSet.setDuration(100);
				easy.startAnimation(animationSet);

			}
		});

	
		middle.setOnClickListener(new View.OnClickListener() {

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
							//动执行结束
							
							MatchActivity.setlevel(1);
							Intent intent = new Intent();
							intent.setClass(GameLevelActivity.this, MatchActivity.class);
							GameLevelActivity.this.startActivity(intent);
							GameLevelActivity.this.finish();
						}
					});
					
				
				animationSet.addAnimation(scaleAnimation);
				animationSet.setFillBefore(true);
				animationSet.setDuration(100);
				middle.startAnimation(animationSet);

			}
		});

;

		hard.setOnClickListener(new View.OnClickListener() {

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
							//动执行结束
							
							MatchActivity.setlevel(2);
							Intent intent = new Intent();
							intent.setClass(GameLevelActivity.this, MatchActivity.class);
							GameLevelActivity.this.startActivity(intent);
							GameLevelActivity.this.finish();
						}
					});
					
				
				animationSet.addAnimation(scaleAnimation);
				animationSet.setFillBefore(true);
				animationSet.setDuration(100);
				hard.startAnimation(animationSet);

				

			}
		});

	}
}
