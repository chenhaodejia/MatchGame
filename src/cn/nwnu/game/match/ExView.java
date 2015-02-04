package cn.nwnu.game.match;

import cn.nwnu.game.cal.SString;
import cn.nwnu.game.file.FileOperation;
import cn.nwnu.game.record.Record;
import cn.nwnu.game.record.Set;
import cn.nwnu.game.timer.TimeView;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class ExView extends View {
	Figure figure;
	int height, width;
	int level;
	Context mContext;

	TimeView timer;
	TextView hint;

	boolean end;

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public ExView(Context context, int width, int level) {
		super(context);
		end = false;
		this.level = level;
		Log.i("exview", "level=" + ExView.this.level);
		this.mContext = context;
		Log.i("tag", "width=" + width);
		this.width = width;
		this.height = ((width - 40) * 2) / 9 + 10;

		this.hint = new TextView(mContext);

		this.timer = new TimeView(mContext, width, new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				end=true;
				new AlertDialog.Builder(mContext)
						.setTitle("����ʧ��")
						.setMessage("ʱ�䵽,������!")
						// .setIcon(R.drawable.quit)
						.setPositiveButton("����Ϸ",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										Record.Operate(false, ExView.this.level);
										FileOperation fileop = new FileOperation(
												mContext);
										fileop.write();
										next();
									}
								})
						.setNegativeButton("ȡ��",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										// ȡ����ť�¼�
									}
								}).show();
			}
		});

		Figure.load(getResources(), width);

		figure = new Figure(this, level + 1);

		hint.setText(SString.getString(figure.getIndex()));
		if (Set.isTimer()) {
			timer.start();
		}

	}

	public void next() {
		figure.nextQuestion();
		end = false;
		hint.setText(SString.getString(figure.getIndex()));
		if (Set.isTimer()) {
			timer.start();
		}
		invalidate();
	}

	public TimeView getTimer() {
		return this.timer;
	}

	public void reset() {
		figure.reset();
		invalidate();
	}

	public TextView getHint() {
		if (hint != null) {
			return hint;
		} else {
			return null;
		}
	}

	public int getheight() {
		return height;
	}

	public int getwidth() {
		return width;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		figure.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (figure.onTouchEvent(event)) {

			if (!end) {
				Record.Operate(true, ExView.this.level);
				FileOperation fileop = new FileOperation(mContext);
				fileop.write();
				new AlertDialog.Builder(mContext)
						.setTitle("����ɹ�")
						.setMessage("��ϲ�㣬�ش���ȷ")
						// .setIcon(R.drawable.quit)
						.setPositiveButton("����һ��",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {

										next();
									}
								})
						.setNegativeButton("ȡ��",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										// ȡ����ť�¼�
									}
								}).show();
			}
			end = true;

		}
		return true;
	}

	public void showAnswer() {
		// TODO Auto-generated method stub
		figure.showResult();
	}

}
