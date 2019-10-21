package com.icaballero.proyectofinal.CustomViews;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.duhnnae.proyectofinal.R;
import com.icaballero.proyectofinal.LoadingActivity;
import com.icaballero.proyectofinal.CustomViews.HardLevel.Aranas;
import com.icaballero.proyectofinal.Utils.Disparo;
import com.icaballero.proyectofinal.Utils.DisparosOvni;
import com.icaballero.proyectofinal.Utils.Movimientos;
import com.icaballero.proyectofinal.Utils.ResourcesP2;
import com.icaballero.proyectofinal.Utils.Telaranas;

/**
 * CustomView que dibuja la pantalla normal del juego. Ésta pantalla consiste en
 * una rana, meteoritos, y tres ovnis.
 * 
 * @author decalion
 * 
 */
public class NormalLevel extends View {
	
	public ResourcesP2 res=null;
	
	boolean doAtFisrst = true;
	Rect viewSize;
	Rect orig;
	Rana rana;
	Random generator=new Random();
	
	Paint puntuacion=new Paint();
	int viewHeight = 0;
	int viewWidth = 0;
	Movimientos moves = new Movimientos();

	ArrayList<Disparo> disparos=new ArrayList<Disparo>();
	
	//Piedras
	ArrayList<Piedra> piedras=new ArrayList<Piedra>();	
	int maxDisparo=10;
	int maxAlpha;
	int maxPiedras=50;
	int maxPiedrasPantalla=20;
	int piedrasMuerta=0;
	Paint dibujapiedra=new Paint();
	int piedraHeight = 40;
	
	int piedraWidth = 30;
	
	
	//Ovni
	Ovni ovni;
	int maxOvni=3;
	int maxOvniMaxInPantalla=1;
	public ArrayList<Ovni>naves=new ArrayList<NormalLevel.Ovni>();
	boolean test=true;
	DisparosOvni disparoslaser;
	public ArrayList<DisparosOvni>arrayDisparos=new ArrayList<DisparosOvni>();
	int maxLaser=100;
	int maxLaserPantalla=5;
	int prueba;
	int ovnimuertos=0;
	int ovniHeigth=40;

	Paint pintar=new Paint();
	Paint pintarlaser=new Paint();
	boolean ovnicreado=false;

	AssetManager manager=getContext().getAssets();
	Bitmap fondo;
	InputStream is =null;
	//Botones
	Paint paint=new Paint();
	Paint botones=new Paint();//botones
	Paint p = new Paint();//Disparos
	Paint botonDisapros=new Paint();
	long timeFirePressed=0;

	MediaPlayer musica=new MediaPlayer();
	boolean pause = false;
	
	int puntuacion1=0;
	int colorDisparo;
	int radioDisparo;
	boolean buttonFirePressed=false;

	boolean nowDied=true;
	long timeDied=0;
	Paint paintblack=new Paint();
	int currentAlpha=0;
	Context mContext;
	final int SPLASH=6000;

	
	
	

	
	Bitmap nave=BitmapFactory.decodeResource(getResources(), R.drawable.ovni1);
	int naveHeight = nave.getHeight();
	int naveWigth = nave.getWidth();
	
	
	
	
	
	private void decodeResources() {
		// TODO Auto-generated method stub
		try {
			is=manager.open("fondoespacio.jpg");
			fondo=BitmapFactory.decodeStream(is);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		

	}
	
	
	

	public NormalLevel(Context context) {
		super(context);
		init(null, 0);
	}

	public NormalLevel(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs, 0);
	}

	public NormalLevel(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs, defStyle);
	}
	/**
	 * Al iniciar el view, se procede a inicializar variables y objetos.
	 * 
	 * @param attrs
	 * @param defStyle
	 */

	private void init(AttributeSet attrs, int defStyle) {
		// Load attributes
		res=new ResourcesP2(getContext(), getResources());
		pause=res.isPause();
		
		decodeResources();
		Drawable d=new BitmapDrawable(getResources(),fondo);
		setBackgroundDrawable(d);
		rana = new Rana(10, 10);
		rana.start();
		colorDisparo = colorDisparo | 0xff23FF0F;
		radioDisparo = (int)rana.ranaHeight/8;
		maxAlpha=p.getAlpha();
		puntuacion.setColor(Color.WHITE);
		puntuacion.setFakeBoldText(true);
		puntuacion.setTextSize(19);
		
		mContext=getContext();
		MovesButton button=new MovesButton();
		button.start();
		//piedras1=new Piedra(10);
		
	
	}
	

	@Override
	protected void onDraw(Canvas canvas) {
	
		if(doAtFisrst){
			doAtFisrst = false;
			viewSize = new Rect(0,0,viewWidth+(10*viewWidth/100),viewHeight+(10*viewWidth/100));
			orig = new Rect(0,0,fondo.getWidth(),fondo.getHeight());
		}
		
		//Dibuja la rana
		if(rana.isAlive())
		{
			canvas.drawBitmap(rana.bmp, rana.x,rana.y, paint);
		}
		else
		{
			if(nowDied)
			{
				nowDied=false;
				timeDied=System.currentTimeMillis();
				currentAlpha=paintblack.getAlpha();
				puntuacion.setColor(Color.BLACK);
				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Intent intent =new Intent(mContext,LoadingActivity.class);
						intent.putExtra("name", 4);
						intent.putExtra("puntuacion",puntuacion1);
						intent.putExtra("pantalla", 2);
						((Activity)mContext).startActivity(intent);
						((Activity)mContext).finish();
						System.exit(0);
					}
				}, SPLASH);
//				
			}
			else {

				if (paintblack.getAlpha() < currentAlpha
						&& paintblack.getAlpha() < maxAlpha)
					paintblack.setAlpha(paintblack.getAlpha() + 1);

				canvas.drawBitmap(res.riprrana3, orig, viewSize, paintblack);
				canvas.drawBitmap(res.riprrana4,viewWidth / 2 - (res.riprrana4.getWidth() / 2), 30, p);
			}
		}
		
		
		if(rana.win){
			new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Intent intent =new Intent(mContext,LoadingActivity.class);
					intent.putExtra("name", 7);
					intent.putExtra("puntuacion",puntuacion1);
					((Activity)mContext).startActivity(intent);
					((Activity)mContext).finish();
					System.exit(0);
				}
			}, SPLASH);
		}
		
		

		
	
		
		//Dibuja los disparos
		if(disparos.size()>0){
			for(int dis = disparos.size()-1;dis>=0;dis--){
				Disparo d = disparos.get(dis);
				
				if(d.alive){
				p.setColor(d.color);
				
				
				if(generator.nextInt(100)<80)
					canvas.drawBitmap(d.escupitajo,d.xInit, d.yInit, p);
				else
					canvas.drawBitmap(res.escupitajo1,d.xInit, d.yInit, p);
				
				}else{
					disparos.remove(d);
					break;
					}
			}
			}
		
		
		
		//Piedras
		if(piedras.size()<maxPiedrasPantalla && maxPiedras>0)
		{
			Piedra piedra=new Piedra(generator.nextInt(viewHeight-piedraHeight));
			piedra.start();
			piedras.add(piedra);
	
		}
	
		for(int i=piedras.size()-1;i>=0;i--)
		{
			Piedra p= piedras.get(i);
			if(rana.alive)
			{
				canvas.drawBitmap(p.bmp,p.x,p.y,dibujapiedra);
			}
			else{
				
				piedras.remove(p);
				maxPiedras--;
				piedrasMuerta++;
				break;
				
			}
			
		}
		
		//Dibuja las naves
			if(naves.size()<maxOvniMaxInPantalla && maxOvni>0)
			{
				Ovni ovni=new Ovni(generator.nextInt(viewHeight-ovniHeigth));
				ovni.start();
				naves.add(ovni);
				
				
			}
		
			
			
			
			
		
		for(int i=naves.size()-1;i>=0;i--)
		{
			Ovni ov=naves.get(i);
			if(ov.alive)
			{
				canvas.drawBitmap(ov.bmp, ov.x, ov.y,paint);
			}else
			{
				naves.remove(ov);
				maxOvni--;
				ovnimuertos++;
				break;
				
			}
		}
		
	
		
		
		//Dibujar pis
		if (arrayDisparos.size() > 0) {
			for (int dis = arrayDisparos.size() - 1; dis >= 0; dis--) {
				DisparosOvni d = arrayDisparos.get(dis);

				if (d.alive) {
					//p.setColor(d.color);

					// canvas.drawCircle(d.xInit, d.yInit, d.radius, p);
					if (generator.nextInt(100) < 25)
						canvas.drawBitmap(d.laser, d.xInit, d.yInit, p);
					else
						canvas.drawBitmap(res.laser2, d.xInit, d.yInit, p);

				} 	
					
				else {
					arrayDisparos.remove(d);
					//maxTelaranas--;
					break;
				}
			}
		}
		
		
		
		//Dibuja los botones de disparo
		if(!buttonFirePressed)
		{
			canvas.drawBitmap(res.botonFire, 10,viewHeight-res.botonFire.getHeight()-10,botonDisapros);
			
		}
		else
		{
			canvas.drawBitmap(res.botonFire2, 10, viewHeight-res.botonFire.getHeight()-10,botonDisapros);
			if(Math.abs(System.currentTimeMillis()-timeFirePressed)>50)
				buttonFirePressed=false;
			
		}
		//Dibuja la puntuacion
		canvas.drawText(String.valueOf(puntuacion1),viewWidth-(viewWidth*10/100),viewHeight*10/100 , puntuacion);
		
		
		

				

		
		
		//Dibuja los botones
				canvas.drawBitmap(res.moveUp, viewWidth-res.moveUp.getWidth(), viewHeight-res.moveUp.getHeight()-res.moveDown.getHeight(), paint);
				canvas.drawBitmap(res.moveDown, viewWidth-res.moveDown.getWidth(), viewHeight-res.moveDown.getHeight(), paint);
		
				//Dibuja los botones de disparo
				if(!buttonFirePressed)
				{
					canvas.drawBitmap(res.botonFire, 10,viewHeight-res.botonFire.getHeight()-10,botonDisapros);
					
				}
				else
				{
					canvas.drawBitmap(res.botonFire2, 10, viewHeight-res.botonFire.getHeight()-10,botonDisapros);
					if(Math.abs(System.currentTimeMillis()-timeFirePressed)>50)
						buttonFirePressed=false;
					
				}
		
				
				// dibujar vida´
				
				if(rana.life==0){
					canvas.drawBitmap(res.vida,
							viewWidth - (viewWidth * 25 / 100), viewHeight * 2 / 100, p);
					
					canvas.drawBitmap(res.vida,
							viewWidth - (viewWidth * 30 / 100), viewHeight * 2 / 100, p);
					canvas.drawBitmap(res.vida,
							viewWidth - (viewWidth * 35 / 100), viewHeight * 2 / 100, p);
				}else 	if(rana.life==1){
					canvas.drawBitmap(res.vida,
							viewWidth - (viewWidth * 25 / 100), viewHeight * 2 / 100, p);
					
					canvas.drawBitmap(res.vida,
							viewWidth - (viewWidth * 30 / 100), viewHeight * 2 / 100, p);

				}else 	if(rana.life==2){
					canvas.drawBitmap(res.vida,
							viewWidth - (viewWidth * 25 / 100), viewHeight * 2 / 100, p);
					

				}else{
					
				}
				
				
				
				
				
		//Llama al metode para hacer las colisiones	
		colisiones();
		
		
		
		
		
		
		invalidate();
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		viewHeight=h;
		viewWidth=w;
		super.onSizeChanged(w, h, oldw, oldh);
	}
	//Metodo para hacer la colisiones
	/**
	 * Metode para hacer las colisiones contra los objectos
	 */
	private void colisiones() {
		// TODO Auto-generated method stub
		boolean removeDisparo=false;
		for(int z=disparos.size()-1;z>=0;z--){
			Disparo d = disparos.get(z);
			removeDisparo = false;
			
		//colisión los disparos de la rana con las piedras
		if(disparos.size()>0)
		{
			for(int i=piedras.size()-1;i>=0;i--)
			{
				Piedra pi=piedras.get(i);
				Rect contactoPiedra=new Rect(pi.x,pi.y,pi.x+pi.mWdith,pi.y+pi.mHeight);
				if(d.alive && contactoPiedra.contains((int)d.xInit+d.radius, (int)d.yInit+d.radius))
				{
					d.alive=false;
					piedrasMuerta++;
					piedras.remove(pi);
					maxPiedras--;
					puntuacion1++;
					removeDisparo=true;
					
				}
			}
			//Colision disparos de la rana con los ovnis
			for (int i = naves.size() - 1; i >= 0; i--) {
				Ovni ov = naves.get(i);
				Rect rectOvni = new Rect(ov.x, ov.y, ov.x + ov.mWdith, ov.y
						+ ov.mHeight);

				if (d.alive && rectOvni.contains((int) d.xInit + d.radius,(int) d.yInit + d.radius)) {
					
					if(ov.life>1)
					{
						d.alive = false;
						ov.setHitByBall();
					}else
					{
						naves.remove(ov);
						maxOvni--;
						ovnimuertos++;
						System.out.println(ovnimuertos);
						puntuacion1 = puntuacion1 + 30;
						ov.alive=false;
					}
					
					}

				}
		
		}
		
		}
		

		//Colisión con las piedras con la rana
		Rect rect = new Rect(0,rana.y,rana.ranaWidth,rana.y+rana.ranaHeight);
		for(int i=piedras.size()-1;i>=0;i--)
		{
			Piedra pie= piedras.get(i);
			Rect rectPiedra=new Rect(pie.x,pie.y,pie.x+pie.mWdith,pie.y+pie.mHeight);
			if(rectPiedra.intersect(rect))
					{
				rana.setHitByPiedra();
				piedras.remove(pie);
				pie.setPiedraAlive();
				piedrasMuerta++;
				break;
				
				
				
					}

		}
		// Colisión de los disparos del ovni con la rana
		boolean romper = false;
		for(int i= arrayDisparos.size()-1; i>=0;i--){
			DisparosOvni d = arrayDisparos.get(i);
			RectF rect2 = new RectF(0, rana.y, rana.ranaWidth,
					rana.y + rana.ranaHeight);
			RectF disparo = new RectF(d.xInit, d.yInit, d.xInit
					+ res.laser1.getWidth(), d.yInit
					+ res.laser1.getHeight());

			if (d.alive
					&& RectF.intersects(rect2, disparo))  {
				d.alive = false;
                  
				rana.setHitByPiedra();
				arrayDisparos.remove(d);

				romper=true;
				break;
				 }
			if(romper){
				arrayDisparos.remove(d);
				break;
			}
			
			
		}
		
	
		
		
		try {
			Thread.sleep(1);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			
			
		
		
	}
	
	
	

	
	
	
	/**
	 * Control de eventos que suceden cuando se toca la pantalla
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		
		//Movimientos de la rana
		if(event.getAction() == MotionEvent.ACTION_DOWN)
		{
			float nowXMove = event.getX();
			float nowYMove = event.getY();

			RectF up = new RectF(viewWidth-res.moveDown.getWidth(),viewHeight-res.moveDown.getHeight()-res.moveUp.getHeight(), viewWidth,viewHeight-res.moveDown.getHeight());
			RectF down = new RectF(viewWidth-res.moveDown.getWidth(),viewHeight-res.moveDown.getHeight(), viewWidth,viewHeight);
			if(up.contains(nowXMove,nowYMove)){
				
				moves.setButtonPressed(true);
				moves.setMovingUp(true);
				moves.setStopped(false);
			}
			else if(down.contains(nowXMove,nowYMove)){
				moves.setButtonPressed(true);
				moves.setMovingUp(false);
				moves.setStopped(false);

			}
			if(event.getAction() == MotionEvent.ACTION_OUTSIDE || event.getAction() == MotionEvent.ACTION_UP ){
				moves.setMovingUp(false);
				moves.setButtonPressed(false);
				moves.setStopped(true);
			}
			
			//Disparar
			
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				float nowX = event.getX();
				float nowY = event.getY();
				//RectF r = new RectF(0, this.rana.y-10*this.rana.bmp.getHeight()*10/100, (int)this.rana.bmp.getWidth()+10*this.rana.bmp.getWidth()*10/100, this.rana.y+this.rana.ranaHeight+10*this.rana.bmp.getHeight()*10/100);
				
				RectF r= new RectF(0,viewHeight-(res.botonFire.getHeight()*2),(res.botonFire.getWidth()*2),viewHeight);
				if(r.contains((int)nowX, (int)nowY) && rana.isAlive()){
					rana.shooting = true;
					buttonFirePressed=true;
					timeFirePressed=System.currentTimeMillis();
					
					
					//Crea una nueva bola
					try{
					Disparo disparo = new Disparo(rana.ranaWidth-rana.ranaWidth/6, rana.y+rana.ranaHeight/3, colorDisparo, radioDisparo, viewWidth, res.escupitajo);
					if(!disparos.contains(disparo) && disparos.size()<maxDisparo){
						disparos.add(disparo);
						disparo.start();
					}
					}catch(Exception e){
						System.out.println("Error disparos");
					}
					
				}
			}
				
			
		}
		
		
		return super.onTouchEvent(event);
	}
	
	
	
	/**
	 * Metodo para mover la rana para abajo
	 */
	private void goDown() {
		if(this.rana.y < viewHeight-rana.ranaHeight){
			this.rana.y += (int)viewHeight*1/100;
		}
		// TODO Auto-generated method stub
		
		
		
	}
	/**
	 * Metodo para mover la rana para arriba
	 */
	private void goUp() {
		// TODO Auto-generated method stub
		if(rana.alive)
		{
			if(this.rana.y>0)
			{
				this.rana.y-=(int)viewHeight*1/100;
				
			}
		}
		
	}
	
	
/**
 * 
 * @author Ismael Caballero, Daniel Córdoba, Cristian Bautista
 * Clase Rana
 *
 */

public class Rana extends Thread
{
	boolean alive = true;
	int x=0;
	int y=0;
	int cont = 0;
	Bitmap bmp = res.ranaBmp1;
	int ranaWidth = bmp.getWidth();
	int ranaHeight = bmp.getHeight();
	boolean mooving = false;
	boolean shooting = false;
	boolean dieByFire = false;
	boolean dieByPiedra = false;
	boolean inmune = false;
	int hitsByBalls = 0;
	int life = 0;
	long hitByPiedraTime = 0;
	boolean hitByPiedra = false;
	boolean win = false;
	
	/**
	 * Constructor Rana
	 * @param x
	 * @param y
	 */
	
	public Rana(int x, int y){
		
		this.x = x;
		this.y = y;
	}

	@Override
	public void run() {
		
		while(alive){
			
			if(!pause){
				
					if (ovnimuertos == 3){//&& piedrasMuerta==30) {
					
					
					while (rana.x < viewWidth) {
						rana.x += (int) viewWidth * 2 / 100;
						win=true;

						try {
							sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			
			if(shooting && hitsByBalls==0){
				bmp =res.ranaBmpOpen;
				try {
					sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				shooting = false;
			}
			
			//Simulates movement
						if(hitsByBalls==0){
							//Si ha sido tocado por un mosquito coge una imagen, si no otra
							if(hitByPiedra){
								this.inmune = true;
								if(generator.nextInt(100)<80)
									bmp = res.ranaBmpHit;
									else
										bmp = res.ranaBmp2;
							}else{
								inmune = false;
							if(generator.nextInt(100)<80)
							bmp = res.ranaBmp1;
							else
								bmp = res.ranaBmp2;
							}
						}
						else if(hitsByBalls == 1){
							if(generator.nextInt(100)<80)
								bmp = res.ranaBurn2;
							else
								bmp = res.ranaBurn3;
						}else if(hitsByBalls == 2){
							if(generator.nextInt(100)<80)
								bmp = res.ranaBurn3;
							else
								bmp = res.ranaBurn4;
						}else if(hitsByBalls>2){
							rana.setDeadByFire(true);
							rana.setAlive(false);
						}
				
					
					try {
						sleep(40);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
	}
		
		while(this.y<viewHeight){
		if(dieByFire){
			this.y += viewHeight/100;
			if(generator.nextInt(100)<80)
			this.bmp = res.ranaBurnDie1;
			else
				this.bmp = res.ranaBurnDie2;
		}else if(dieByPiedra){
			this.y += viewHeight/100;
			if(generator.nextInt(100)<80)
			this.bmp = res.ranaBurnDie1;
			else
				this.bmp = res.ranaBurnDie2;
		}
		
		try {
			sleep(25);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	/**
	 * Metodo para quitar vida a la rana
	 */
	public synchronized void setHitByPiedra(){
		life++;
		hitByPiedra = true;
		hitByPiedraTime = System.currentTimeMillis();
		if(life == 3){
			this.dieByPiedra = true;
			this.setAlive(false);
		}
	}
	

		
	/**
	 * Metodo para saber si esta viva o muerta la rana
	 * @param alive
	 */
	
	public synchronized void setAlive(boolean alive){
		this.alive = alive;
	}
	
	public void setDeadByFire(boolean b) {
		this.dieByFire = b;
	}
	
}
/**
 * @author Ismael Caballero, Daniel Córdoba, Cristian Bautista
 * Clase MovesButton
 *
 */
	

public class MovesButton extends Thread
{
	Movimientos m;
	/**
	 * Constructor
	 */
	public MovesButton() {
		m=moves;
		
		// TODO Auto-generated constructor stub
	}
	public void run() {
		
		while(true){

		if(m.isMovingUp()){
			goUp();
			
		}else if(!m.isMovingUp()){
			goDown();

		}
	
			try {
				sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
						
	}
	
	
	
}
/**
 * 
 * @author Ismael Caballero, Daniel Córdoba, Cristian Bautista 
 * Clase del objecto piedra
 */
public class Piedra extends Thread
{
	int x=viewWidth;
	int y;
	Bitmap bmp=res.meteo;
	int velocidad=generator.nextInt(50)+25;
	int mWdith=bmp.getWidth();
	int mHeight=bmp.getHeight();
	boolean piedralive=true;
	Piedra pied;
	
	
	/**
	 * Constructor
	 * @param y
	 */
	
	public Piedra(int y) {
		this.y=y;
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		while(piedralive)
		{
			
			this.x-=0.3*viewWidth/100;
			
//			
			try {
				sleep(velocidad);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(this.x<-this.mWdith){
				this.setPiedraAlive();
				piedras.remove(pied);
				piedrasMuerta++;
				Log.d("com.duhnnae.proyectofinal.CustomViews",String.valueOf(piedrasMuerta));
				
				
				
				
		}
		}
		
		
	}
	/**
	 * Metode para saber si la piedra esta viva
	 */
	public synchronized void setPiedraAlive(){
		this.piedralive = false;
	}
	
	
	
	
}
/**
 * 
 * @author Ismael Caballero, Daniel Córdoba, Cristian Bautista
 *	Clase del objecto ovni
 *
 */
public class Ovni extends Thread {
	public boolean alive = true;
	boolean deadByBall = false;
	int x = viewWidth;
	int y;
	Bitmap bmp = nave;
	int mWdith = bmp.getWidth();
	int mHeight = bmp.getHeight();
	int velocidad = generator.nextInt(10) + 25;
	int life = 15;
	long time;
	long hitTime = 100;
	boolean hited = false;
	public boolean pause = false;
//	public ArrayList<DisparosOvni> disparolaser = new ArrayList<DisparosOvni>();
	boolean ovniInPantalla;
	boolean startMove = false;
	boolean moveBotttom = false;
	boolean moveTop = true;
	public Ovni() {

	}
/**
 * Constructor
 * @param y
 */
	public Ovni(int y) {
		this.y = y;
	}



	/**
	 * Metodo para restar vidas al ovni
	 */
	public synchronized void setHitByBall() {
		this.life--;
		this.hited = true;
		this.time = System.currentTimeMillis();
		if (life == 0) {

			this.setOvniDeadByBall();
			

		}

	}

	@Override
	public void run() {

		while (alive) {

			if (!this.pause) {
				
				
				
				if(this.x>viewWidth-viewWidth/2+75)
				{
					this.x-=5*viewHeight/100;
				}
				if (moveTop) {
					y -=  1*viewHeight/100;
					ovniInPantalla = true;
					if (y <=-mHeight/2) {
						moveTop = false;
						moveBotttom = true;
					}
					// moverse hacia abajo
				} else if (moveBotttom) {

					y +=  1*viewHeight/100;

					if (y >= viewHeight) {
						moveBotttom = false;
						moveTop = true;
					}
				}
				
				//Meter los disparos del ovni la array
				
				if (x < viewWidth - 115) {
					if(arrayDisparos.size()<maxLaserPantalla && maxLaser>0){
						
						disparoslaser=new DisparosOvni(this.x, this.y, this.mHeight / 9,viewWidth, res.laser1);
						disparoslaser.start();
						arrayDisparos.add(disparoslaser);
						  

				}
				}
				
				

				try {
					sleep(velocidad);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
				if (x < -120) {
					setOvniDead();
				}
				

			}
		}

		super.run();
	}
/**
 * Metodo para saber si el ovni si está vivo o muerto
 */
	public synchronized void setOvniDead() {
		this.alive = false;
	}

	public synchronized void setOvniDeadByBall() {
		this.deadByBall = true;

	}

}

}


