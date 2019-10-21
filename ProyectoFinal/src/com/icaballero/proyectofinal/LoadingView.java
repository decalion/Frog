package com.icaballero.proyectofinal;

import java.util.Random;

import com.duhnnae.proyectofinal.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class LoadingView extends View {
	
	Paint p = new Paint();
	Paint pbitmaps;
	int maxAlpha;
	boolean firstPaint = true;
	
	//Util
	int frogXPosition = 0;
	int frogYPosition = 0;
	boolean ranaXpositive = true;
	boolean ranaYpositive = true;
	Random generator = new Random();
	
	//view properties
	int viewWidth;
	int viewHeight;
	
	//Images
	Bitmap fondo = BitmapFactory.decodeResource(getResources(),
            R.drawable.ranahijolow2);

	public LoadingView(Context context) {
		super(context);
		init(null, 0);
	}

	public LoadingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs, 0);
	}

	public LoadingView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs, defStyle);
	}

	private void init(AttributeSet attrs, int defStyle) {
		// Load attributes
		final TypedArray a = getContext().obtainStyledAttributes(attrs,
				R.styleable.LoadingView, defStyle, 0);
		
		this.viewHeight = getHeight();
		this.viewWidth = getWidth();
		maxAlpha = p.getAlpha();
		pbitmaps = new Paint();
		pbitmaps.setAlpha(maxAlpha);
		setBackgroundColor(Color.BLACK);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if(firstPaint){
			p.setAlpha(100);
			p.setColor(Color.BLACK);
			firstPaint = false;
			p.setStyle(Style.FILL);
			canvas.drawPaint(p); 
			
			p.setColor(Color.WHITE);
			p.setTextSize(20); 
			
		}
		/*if(p.getAlpha()<maxAlpha)
			p.setAlpha(p.getAlpha()+1);
		else 
			p.setAlpha(0);
		
		p.setAlpha(p.getAlpha()+1);*/
		
		canvas.drawBitmap(fondo, getWidth()-fondo.getWidth(),  getHeight()-fondo.getHeight(), p);
		
		canvas.drawText("Loading...", getWidth()/2-(10*getWidth()/100), getHeight()/2, p);
		//canvas.drawText("[Loading...]", 20, 20, p);
		
		
		/*if(frogXPosition == getWidth()){
			frogXPosition = -ranaBmp1.getWidth();
			frogYPosition += ranaBmp1.getHeight();
		}
		
		if(ranaXpositive)
			frogXPosition +=1;
		else
			frogXPosition -=1;
		
		if(generator.nextInt(100)<80)
			canvas.drawBitmap(ranaBmp2, frogXPosition,frogYPosition, pbitmaps);
		else
		canvas.drawBitmap(ranaBmp1, frogXPosition,frogYPosition, pbitmaps);*/		

	}
}
