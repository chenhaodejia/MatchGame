package cn.nwnu.game.timer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.nwnu.game.cal.Distance;
import cn.nwnu.game.cal.Form;

import cn.nwnu.game.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

public class TimeFigure {

	private Bitmap imgv, imgh;

	private final int SX = 10, SY = 10;

	/**
	 * 缩放未平移以后的矩阵
	 */
	private Matrix matrix;

	private final Point[][] location = new Point[3][10];

	private int drawlong, drawshort;

	private final Paint paint = new Paint();
	
	private final List<Set<Integer>> showNumberSet = new ArrayList<Set<Integer>>(3);
	

	public TimeFigure(Resources resources, int width) {
		InputStream is = resources.openRawResource(R.drawable.h);
		imgh = BitmapFactory.decodeStream(is);
		is = resources.openRawResource(R.drawable.v);
		imgv = BitmapFactory.decodeStream(is);

		int imglong = imgh.getWidth();
		int imgwidth = imgh.getHeight();

		int dt = (width - SX * 2) / 9 * 2/ 3;
		int length = (int) ((float) dt / 5 * 4);

		int half = length / 2;
		
		Distance[] dis = Distance.getDistance();
		for (int i = 0; i < location.length; i++)
			for (int j = 0; j < location[i].length; j++) {
				int x = SX + i * dt + dis[j].dx * half;
				int y = SY + dis[j].dy * half;
				Point point = new Point(x, y);
				location[i][j] = point;
			}

		matrix = new Matrix();
		float f = length / (float) imglong;
		matrix.setScale(f, f);

		drawlong = length;
		drawshort = (int) (f * imgwidth);
	}


	public void onDraw(Canvas canvas) {
		Distance[] dis = Distance.getDistance();
		for (int i = 0; i < showNumberSet.size(); i++) {
			Set<Integer> set = showNumberSet.get(i);
			for (int num : set) {
				Matrix matrix = new Matrix(this.matrix);
				if (dis[num].horizontal) {
					matrix.postTranslate(location[i][num].x - drawlong / 2,
							location[i][num].y - drawshort / 2);
					canvas.drawBitmap(imgh, matrix, paint);

				} else {
					matrix.postTranslate(location[i][num].x - drawshort / 2,
							location[i][num].y - drawlong / 2);
					canvas.drawBitmap(imgv, matrix, paint);
				}
			}
		}
	}
	
	public void setRemainderTime(int time,View view){
		showNumberSet.clear();
		showNumberSet.add(Form.getDataSet(time/100));
		showNumberSet.add(Form.getDataSet(time%100/10));
		showNumberSet.add(Form.getDataSet(time%10));
		view.postInvalidate();
	}
}