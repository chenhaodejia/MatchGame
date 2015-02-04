package cn.nwnu.game;

import cn.nwnu.game.match.MyButton;
import cn.nwnu.game.record.Record;
import cn.nwnu.game.R;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class Billboard extends Activity {

	TextView tv1, tv2, tv3, tv4, tv5;
	int level;
	ImageButton clear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.billboard);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
				R.drawable.icon);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		adapter.add("����");
		adapter.add("�м�");
		adapter.add("�߼�");

		Spinner spinner = (Spinner) findViewById(R.id.spinner1);

		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spinner = (Spinner) parent;

				level = spinner.getSelectedItemPosition();
				

				tv1.setText("   ������Ϸ��" + Record.getGameTimes(level) + "��");
				tv2.setText("   ��ʤ��Ϸ��" + Record.getWinTimes(level) + "��");
				tv3.setText("   ��ʤ�ʣ�" + Record.getWinRate(level));
				tv4.setText("   �����ʤ��" + Record.getAlWinTimes(level) + "��");
				tv5.setText("   ������ܣ�" + Record.getAlLoseTimes(level) + "��");
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}

		});

		clear = (ImageButton) findViewById(R.id.clear);
		clear.setImageResource(R.drawable.clear1);
		
		clear.setOnClickListener(new View.OnClickListener() {

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
						//��ִ�н���
						Record.clear();

						tv1.setText("   ������Ϸ��" + Record.getGameTimes(level) + "��");
						tv2.setText("   ��ʤ��Ϸ��" + Record.getWinTimes(level) + "��");
						tv3.setText("   ��ʤ�ʣ�" + Record.getWinRate(level));
						tv4.setText("   �����ʤ��" + Record.getAlWinTimes(level) + "��");
						tv5.setText("   ������ܣ�" + Record.getAlLoseTimes(level) + "��");
						
					}
				});
				
				animationSet.addAnimation(scaleAnimation);
				animationSet.setFillBefore(true);
				animationSet.setDuration(100);
				clear.startAnimation(animationSet);
		
		
			}
		});

		level = 0;
		tv1 = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		tv3 = (TextView) findViewById(R.id.textView3);
		tv4 = (TextView) findViewById(R.id.textView5);
		tv5 = (TextView) findViewById(R.id.textView6);

		tv1.setText("������Ϸ��" + Record.getGameTimes(level) + "��");
		tv2.setText("��ʤ��Ϸ��" + Record.getWinTimes(level) + "��");
		tv3.setText("��ʤ�ʣ�" + Record.getWinRate(level));
		tv4.setText("�����ʤ��" + Record.getAlWinTimes(level) + "��");
		tv5.setText("������ܣ�" + Record.getAlLoseTimes(level) + "��");
	}

}
