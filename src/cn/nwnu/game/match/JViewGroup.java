package cn.nwnu.game.match;

import cn.nwnu.game.MatchActivity;
import cn.nwnu.game.PauseActivity;
import cn.nwnu.game.file.FileOperation;
import cn.nwnu.game.record.Record;
import cn.nwnu.game.record.Set;
import cn.nwnu.game.timer.TimeView;
import cn.nwnu.game.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class JViewGroup extends ViewGroup {

	ExView ex;
	TextView hint, timet;
	ImageButton next, reset, answer, pause;
	Context mContext;
	MatchActivity activity;
	int level;
	TimeView timer;

	public JViewGroup(Context context, int width, MatchActivity activity,
			int level) {
		super(context);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.activity = activity;

		ex = new ExView(context, width, level);
		hint = ex.getHint();
		hint.setTextColor(Color.BLACK);
		next = new ImageButton(context);
		next.setImageResource(R.drawable.next1);
		next.getBackground().setAlpha(0);
		reset = new ImageButton(context);
		reset.setImageResource(R.drawable.reset1);
		reset.getBackground().setAlpha(0);
		answer = new ImageButton(context);
		answer.setImageResource(R.drawable.answer1);
		answer.getBackground().setAlpha(0);
		pause = new ImageButton(context);
		pause.setImageResource(R.drawable.pause1);
		pause.getBackground().setAlpha(0);
		timer = ex.getTimer();
		timet = new TextView(context);

		// 下一题按钮

		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/**
				 * 下一题
				 */
				
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
							if (!ex.isEnd()) {
								new AlertDialog.Builder(mContext)
										.setTitle("温馨提示")
										.setMessage("当前退出将会作为失败记录，是否放弃？")
										// .setIcon(R.drawable.quit)
										.setPositiveButton("新游戏",
												new DialogInterface.OnClickListener() {
													public void onClick(
															DialogInterface dialog,
															int whichButton) {

														Record.Operate(false,
																JViewGroup.this.level);
														FileOperation fileop = new FileOperation(
																mContext);
														fileop.write();
														next();

													}
												})
										.setNegativeButton("取消",
												new DialogInterface.OnClickListener() {
													public void onClick(
															DialogInterface dialog,
															int whichButton) {
														// 取消按钮事件
													}
												}).show();
							} else {
								next();
							}

						}
					});
					
				
				animationSet.addAnimation(scaleAnimation);
				animationSet.setFillBefore(true);
				animationSet.setDuration(100);
				next.startAnimation(animationSet);
				
				
			}
		});

		// 重做按钮

		reset.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/**
				 * 重做
				 */
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
							
							ex.reset();
						}
					});
					
				
				animationSet.addAnimation(scaleAnimation);
				animationSet.setFillBefore(true);
				animationSet.setDuration(100);
				reset.startAnimation(animationSet);
				
				
			
			}
		});

		// 放弃按钮


		answer.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/**
				 * 放弃，查看答案
				 */
				
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
							
							ex.showAnswer();
							if (!ex.isEnd()) {
								ex.setEnd(true);
								Record.Operate(false, JViewGroup.this.level);
								FileOperation fileop = new FileOperation(mContext);
								fileop.write();
							}
						}
					});
					
				
				animationSet.addAnimation(scaleAnimation);
				animationSet.setFillBefore(true);
				animationSet.setDuration(100);
				answer.startAnimation(animationSet);
				
			
			}
		});

		// 暂停按钮

		pause.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/**
				 * 暂停
				 */
				
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
					
							JViewGroup.this.activity.onPause();
						}
					});
					
				
				animationSet.addAnimation(scaleAnimation);
				animationSet.setFillBefore(true);
				animationSet.setDuration(100);
				pause.startAnimation(animationSet);
		
			}
		});

		ex.setVisibility(VISIBLE);
		ex.setId(7);
		hint.setVisibility(VISIBLE);
		hint.setId(2);
		next.setVisibility(VISIBLE);
		next.setId(3);
		reset.setVisibility(VISIBLE);
		reset.setId(4);
		answer.setVisibility(VISIBLE);
		answer.setId(5);
		pause.setVisibility(VISIBLE);
		pause.setId(6);
		timer.setVisibility(VISIBLE);
		timer.setId(1);
		timet.setText("时间：");
		timet.setTextColor(Color.BLACK);
		timet.setVisibility(VISIBLE);
		timet.setId(8);

		addView(ex, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		addView(hint, new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		addView(next, new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		addView(reset, new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		addView(answer, new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		addView(pause, new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		addView(timer, new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		addView(timet, new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
	}

	public ExView getEx() {
		return ex;
	}

	public TimeView getTimer() {
		return timer;
	}

	public void next() {
		if (Set.isTimer()) {
			timer.stop();
		}
		ex.next();
	}

	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			switch (child.getId()) {
			case 7:
				child.setVisibility(View.VISIBLE);
				child.measure(ex.getwidth(), ex.getheight());
				child.layout(0, 0, ex.getwidth(), ex.getheight());
				break;
			case 2:
				child.setVisibility(View.VISIBLE);
				child.measure(hint.getWidth(), hint.getHeight());
				child.layout(0, ex.getheight() + 10, child.getMeasuredWidth(),
						ex.getheight() + child.getMeasuredHeight() + 10);
				break;
			case 3:
				child.setVisibility(View.VISIBLE);
				child.measure(next.getWidth(), next.getHeight());
				child.layout(0, ex.getheight() + hint.getHeight() + 10 * 2,
						child.getMeasuredWidth(), child.getMeasuredHeight()
								+ ex.getheight() + hint.getHeight() + 10 * 2);
				break;
			case 4:
				child.setVisibility(View.VISIBLE);
				child.measure(reset.getWidth(), reset.getHeight());
				child.layout(
						next.getWidth() + 10,
						ex.getheight() + hint.getHeight() + 10 * 2,
						next.getWidth() + 10 + child.getMeasuredWidth(),
						child.getMeasuredHeight() + ex.getheight()
								+ hint.getHeight() + 10 * 2);
				break;
			case 5:
				child.setVisibility(View.VISIBLE);
				child.measure(answer.getWidth(), answer.getHeight());
				child.layout(
						next.getWidth() + reset.getWidth() + 20,
						ex.getheight() + hint.getHeight() + 10 * 2,
						next.getWidth() + reset.getWidth() + 20
								+ child.getMeasuredWidth(),
						child.getMeasuredHeight() + ex.getheight()
								+ hint.getHeight() + 10 * 2);
				break;
			case 6:
				child.setVisibility(View.VISIBLE);
				child.measure(pause.getWidth(), pause.getHeight());
				child.layout(
						next.getWidth() + reset.getWidth() + answer.getWidth()
								+ 30,
						ex.getheight() + hint.getHeight() + 10 * 2,
						next.getWidth() + reset.getWidth() + answer.getWidth()
								+ 30 + child.getMeasuredWidth(),
						child.getMeasuredHeight() + ex.getheight()
								+ hint.getHeight() + 10 * 2);
				break;
			case 1:
				child.setVisibility(View.VISIBLE);
				child.measure(timer.getwidth(), timer.getheight());
				child.layout(timet.getWidth() + 10,
						ex.getheight() + hint.getHeight() + 60,
						timet.getWidth() + 10 + timer.getwidth() + 10,
						timer.getheight() + ex.getheight() + hint.getHeight()
								+ 60);
				// child.layout(hint.getWidth()+timet.getWidth(),ex.getheight(),timet.getWidth()+hint.getWidth()+timer.getwidth(),timer.getheight()+ex.getheight()+20);
				// child.layout(0, 0, timer.getwidth(), timer.getheight());
				break;
			case 8:
				child.setVisibility(View.VISIBLE);
				child.measure(timet.getWidth(), timet.getHeight());
				child.layout(0, ex.getheight() + hint.getHeight() + 10 * 3,
						timet.getWidth(), timet.getHeight() + ex.getheight()
								+ hint.getHeight() + 10 * 2);
				break;
			}
		}

	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		ex.invalidate();
		timer.invalidate();
	}

}
