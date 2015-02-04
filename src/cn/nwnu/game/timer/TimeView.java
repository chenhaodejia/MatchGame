package cn.nwnu.game.timer;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.Log;
import android.view.View;

public class TimeView extends View implements Runnable{

	Object obj=new Object();
	int startTime=100;
	boolean running=true;
	TimeFigure tf;
	int time;
	Handler handler;
	Thread t;
	int height,width;
	
	public TimeView(Context context,int width,Handler handler) {
		super(context);
		this.handler=handler;
		
		
		this.width=(width-40)/9*2/3*3+20;
		this.height=(width-40)/9*2/3*2+15;

	
		Log.i("timeView",""+this.width+","+this.height);
		
		//width
		tf=new TimeFigure(getResources(), width);
		time=startTime;
	}
	
	public int getheight()
	{
		return height;
	}
	
	public int getwidth()
	{
		return width;
	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		tf.onDraw(canvas);
	}

	@Override
	public void run() {
		while (true){
			if (running){
				tf.setRemainderTime(time, this);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					break;
				}
				time--;
				if (time==0){
					handler.sendEmptyMessage(0);
					return;
				}
			}else{
				synchronized (obj) {
					try {
						obj.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * 暂停
	 */
	public void pause(){
		running=false;
	}
	
	/**
	 * 暂停之后开始
	 */
	public void restart(){
		running=true;
		synchronized (obj) {
			obj.notifyAll();
		}
	}
	
	
	public void start(){
		Log.i("timeview","start");
		time=startTime;
		if (t!=null && t.isAlive()){
			t.interrupt();
		}
		t=new Thread(this);
		running=true;
		t.start();
	}
	
	public void stop(){
		if (t!=null && t.isAlive())
			t.interrupt();
	}
}


