package cn.nwnu.game;


import java.util.HashMap;
import cn.nwnu.game.record.Record;
import cn.nwnu.game.record.Set;
import cn.nwnu.game.R;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainGameActivity extends Activity{
    private  ImageButton Exit,About,Setm,Begin,ProgressRate; 
    private TextView RollText;
    private String [] SteDataName=new String[12];
    private Animation myAnimation_Translate;
    int SoundInt=0;
  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        //设置为无标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);       
        //设置为全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);        
        //设置为横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.main);
		
		Record.init();
				
		Set.setTimer();
//		for(int i=0;i<12;i++){
//			SteDataName[i]="MBKOH3-5J89-8PNLY0-7VDC-OFIVA9";
//		}
//		
		/**
		 * 滚动数字的效果
		 */
		ImageView iv = (ImageView) findViewById(R.id.imagenum);
		myAnimation_Translate = new TranslateAnimation(iv.getLeft() - 400.0f,
				iv.getLeft(), iv.getTop(), iv.getTop());
		myAnimation_Translate.setDuration(4000);
		iv.startAnimation(myAnimation_Translate);
		
		//滚动字幕
        RollText=(TextView)findViewById(R.id.RollText);
        RollText.setText(R.string.RollText);
        
        RollText.setTextSize(12);
        AnimationSet asTextView=new AnimationSet(true);
        TranslateAnimation taSetTextView=new TranslateAnimation(
        		Animation.RELATIVE_TO_SELF,0f,
        		Animation.RELATIVE_TO_PARENT,0.95f,
        		Animation.RELATIVE_TO_SELF,0f,
        		Animation.RELATIVE_TO_PARENT,0f);
        asTextView.addAnimation(taSetTextView);
        taSetTextView.setFillBefore(true);
        taSetTextView.setDuration(20000);
        taSetTextView.setRepeatCount(9999999);
        RollText.startAnimation(asTextView);  
    
        
        
		Exit=(ImageButton)findViewById(R.id.exit);		
		Exit.setImageResource(R.drawable.exit);		
		Exit.setOnClickListener(new ExitListener());
		
		About=(ImageButton)findViewById(R.id.about);		
		About.setImageResource(R.drawable.about);		
		About.setOnClickListener(new AboutListener());
		
		Setm=(ImageButton)findViewById(R.id.set);		
		Setm.setImageResource(R.drawable.sound);		
		Setm.setOnClickListener(new SetListener());
		
		Begin=(ImageButton)findViewById(R.id.begin);		
		Begin.setImageResource(R.drawable.begin);		
		Begin.setOnClickListener(new BeginListener());
	
        ProgressRate=(ImageButton)findViewById(R.id.progressRate);		
		ProgressRate.setImageResource(R.drawable.progressrate);		
		ProgressRate.setOnClickListener(new ProgressRateListener());	
		
		
	  }

	    class ExitListener implements OnClickListener{

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
						System.exit(0);
						
					}
				});
				
				animationSet.addAnimation(scaleAnimation);
				animationSet.setFillBefore(true);
				animationSet.setDuration(100);
				Exit.startAnimation(animationSet);
				
				
				


				
				
			//	System.exit(0);
				
			}	    	
	    }
	    
				
	    class AboutListener implements OnClickListener{

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
							Intent intent = new Intent();
							intent.setClass(MainGameActivity.this, HelpActivity.class);
							MainGameActivity.this.startActivity(intent);
							
						}
					});
					
				
				animationSet.addAnimation(scaleAnimation);
				animationSet.setFillBefore(true);
				animationSet.setDuration(100);
				About.startAnimation(animationSet);

	
			}	    	
	    }
	    
	    class SetListener implements OnClickListener{

			public void onClick(View v) {
				// TODO Auto-generated method stub
				AnimationSet animationSet=new AnimationSet(true);
				ScaleAnimation scaleAnimation=new ScaleAnimation(1,1.3f,1,1.3f,
						Animation.RELATIVE_TO_SELF,0.5f,
						Animation.RELATIVE_TO_SELF,0.5f);
				animationSet.addAnimation(scaleAnimation);
				animationSet.setFillBefore(true);
				animationSet.setDuration(100);
				Setm.startAnimation(animationSet);
				
				Set.setnewMusic();
				
				
				//声音的开启与关闭
				if(SoundInt%2==0)
				{
					   Setm.setImageResource(R.drawable.quiet);
						 //关闭音乐  
                      Set.disMusic();
				}
				
				else
				{
					   Setm.setImageResource(R.drawable.sound);
	                    //开启音乐
		              Set.setnewMusic();
				}
				SoundInt++;
				
				
			}	    	
	    }
	    
	    
	    class BeginListener implements OnClickListener{
	    	 

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
								Intent intent = new Intent();
								intent.setClass(MainGameActivity.this, GameLevelActivity.class);
								MainGameActivity.this.startActivity(intent);				
							//	MainGameActivity.this.finish();
								
							}
						});
						
				
				animationSet.addAnimation(scaleAnimation);
				animationSet.setFillBefore(true);
				animationSet.setDuration(100);
				Begin.startAnimation(animationSet);
					

			}		
	    }

	    class ProgressRateListener implements OnClickListener{

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
							Intent intent = new Intent();
							intent.setClass(MainGameActivity.this, Billboard.class);
							MainGameActivity.this.startActivity(intent);
					//		MainGameActivity.this.finish();
							
						}
					});
				
				animationSet.addAnimation(scaleAnimation);
				animationSet.setFillBefore(true);
				animationSet.setDuration(100);
				ProgressRate.startAnimation(animationSet);
	
	    }
     } 
	    
//	}    
}