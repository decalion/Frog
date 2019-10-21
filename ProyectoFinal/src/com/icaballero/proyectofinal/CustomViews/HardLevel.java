package com.icaballero.proyectofinal.CustomViews;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.duhnnae.proyectofinal.R;
import com.icaballero.proyectofinal.LoadingActivity;
import com.icaballero.proyectofinal.Utils.Constantes;
import com.icaballero.proyectofinal.Utils.Disparo;
import com.icaballero.proyectofinal.Utils.FireBall;
import com.icaballero.proyectofinal.Utils.GreatFirebal;
import com.icaballero.proyectofinal.Utils.LightBall;
import com.icaballero.proyectofinal.Utils.Movimientos;
import com.icaballero.proyectofinal.Utils.PuntoGeometrico;
import com.icaballero.proyectofinal.Utils.ResourcesP3;
import com.icaballero.proyectofinal.Utils.Telaranas;

/**
 * View del tercer nivel
 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
 *
 */
public class HardLevel extends View {

	public ResourcesP3 res = null;

	Paint paintFondo;
	Paint paintPuntuacion = new Paint();
	Paint p = new Paint();
	Paint paintRana = new Paint();
	Paint paintBack = new Paint();
	Rect viewSize;
	Rect orig;
	public boolean pause = false;
	boolean doOnlyAtFirst = true;
	boolean exitedGame = false;
	boolean stageFinished = false;
	boolean alphaUp = true;
	boolean changeFondo = true;
	int radioDisparo;
	int colorDisparos;
	int maxAlpha;
	public ArrayList<Disparo> disparos = new ArrayList<Disparo>();
	int puntuacion = 0;
	int viewHeight = 0;
	int viewWidth = 0;
	long currTime = 0;

	Random generator = new Random();

	// Ãštiles
	int maxDisparos = 10;
	int currentAlpha = 0;
	boolean buttonFirePressed = false;
	long timeFirePressed = 0;

	int cont = 0;

	boolean fondoUsado = true;
	boolean firstPaint = true;

	boolean monedaOut = false;
	boolean dibujaMoneda = false;

	// More util variables
	boolean nowDied = true;
	long timeDied = 0;
	Button buttonRestart;

	// Aranas

	Aranas arana;
	int maxAranas = 91;
	int maxAranasInPantalla = 10;
	public ArrayList<Aranas> aaranas = new ArrayList<HardLevel.Aranas>();
	boolean test = true;
	Telaranas telanas;
	ArrayList<Telaranas> telaranasArray = new ArrayList<Telaranas>();
	// ArrayList<Telaranas> araytelaranas = new ArrayList<Telaranas>();
	//int maxTelaranas = 1;
	//int maxTelaranasInPantalla = 1;
	boolean aranaMuerta = false;
	
	int maxTelaranas=100;
	final int MAX_TELARANA_IN_PANTALLA =7;
	boolean paralizar = false;
	int prueba;
	int colorTelanas;

	// Final Boss
	FinalGameBoss jefefinal;
	boolean jefefinalout = false;
	boolean jefefinalInPantalla = false;
	ArrayList<FireBall> finalbossFireballArrayList = new ArrayList<FireBall>();
	int maxFireball = 10;
	int maxFireballInPantalla = 4;

	ArrayList<GreatFirebal> finalbossGreatFireball = new ArrayList<GreatFirebal>();
	int maxGreatFireball = 10;
	int maxGreatFireballInPantalla = 1;

	// Utils para cambio de Screen
	final int SPLASH_DISPLAY_LENGHT = 6000;
	Context mContext;
	boolean win = false;
	int aranasmuertas = 0;
	int bossmuerto = 0;
	int finalbossmuerto = 0;

	// Controles
	Movimientos moves = new Movimientos();

	Prota protaRana;
	AssetManager aManager = getContext().getAssets();
	


	// Alto de la arana
	int aranasHeight;




//	// Vidas de la rana
//	Bitmap vidaverdefull = BitmapFactory.decodeResource(getResources(),
//			R.drawable.vidaverdefull);
//	Bitmap vidaverde2 = BitmapFactory.decodeResource(getResources(),
//			R.drawable.vidaverde2);
//	Bitmap vidaamarillafull = BitmapFactory.decodeResource(getResources(),
//			R.drawable.vidaamarilla2);
//	Bitmap vidaamarilla2 = BitmapFactory.decodeResource(getResources(),
//			R.drawable.vidaamarilla1);
//	Bitmap vidaroja = BitmapFactory.decodeResource(getResources(),
//			R.drawable.vidaroja);

	
	/**
	 * Constructor
	 * @param context
	 */
	public HardLevel(Context context) {
		super(context);
		init(null, 0);

	}

	/**
	 * Constructor
	 * @param context
	 * @param attrs
	 */
	public HardLevel(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs, 0);

	}

	/**
	 * Constructor
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public HardLevel(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs, defStyle);
	}



	/**
	 * Method Inicial del juego aqui se cargan los Atributos principales, la rana, los botones de Accion y las imagenes.
	 * @param attrs
	 * @param defStyle
	 */
	private void init(AttributeSet attrs, int defStyle) {
		res = new ResourcesP3(getContext(), getResources());
		//decodeResources();
		colorDisparos = colorDisparos | 0xff23FF0F; // Forces to the color to be
													// opaque
		colorTelanas = colorTelanas | 0xff23FF0F;
		aranasHeight = res.aranas.getHeight();
		protaRana = new Prota(10, 10);
		protaRana.start();
		radioDisparo = (int) protaRana.ranaHeight / 8;
		maxAlpha = p.getAlpha();
		Drawable d = new BitmapDrawable(getResources(), res.fondo);
		paintFondo = new Paint();
		paintFondo.setAlpha(0);
		setBackgroundDrawable(d);
		currTime = System.currentTimeMillis();
		paintPuntuacion.setColor(Color.BLACK);
		paintPuntuacion.setFakeBoldText(true);
		paintPuntuacion.setTextSize(19);
		BotonesMovimiento botones = new BotonesMovimiento(moves);
		botones.start();

		mContext = getContext();
	}

	
	
	/**
	 * Method onDraw en este metodo se dibujan todos los objetos del juego, dondo, botones,rana, enemigos.
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (doOnlyAtFirst) {
			doOnlyAtFirst = false;
			viewSize = new Rect(0, 0, viewWidth + (10 * viewWidth / 100),
					viewHeight + (10 * viewWidth / 100));
			orig = new Rect(0, 0, res.fondo.getWidth(), res.fondo.getHeight());
		}

		if (paintFondo.getAlpha() == 0) {
			alphaUp = true;
		} else if (paintFondo.getAlpha() == maxAlpha)
			alphaUp = false;

		if (alphaUp && Math.abs(System.currentTimeMillis() - currTime) < 5000) {
			if (paintFondo.getAlpha() < maxAlpha)
				paintFondo.setAlpha(paintFondo.getAlpha() + 1);
		} else if (Math.abs(System.currentTimeMillis() - currTime) < 10000) {
			if (paintFondo.getAlpha() > 0)
				paintFondo.setAlpha(paintFondo.getAlpha() - 1);
		} else {
			currTime = System.currentTimeMillis();
			changeFondo = true;
		}

		// if(stageFinished)
		// canvas.drawBitmap(riprana3, orig,viewSize,paintFondo);
		// else
		// canvas.drawBitmap(currentFondo, orig,viewSize,paintFondo);

		// Pinta la rana
		if (protaRana.isAlive()) {
			boolean decreasingAlpha = true;
			if (Math.abs(System.currentTimeMillis() - protaRana.hitByAranaTime) < 5000
					&& protaRana.life == 1) {
				if (paintRana.getAlpha() <= maxAlpha / 2)
					decreasingAlpha = false;
				else if (paintRana.getAlpha() == maxAlpha)
					decreasingAlpha = true;

				if (decreasingAlpha)
					paintRana.setAlpha(paintRana.getAlpha() - 5);
				else
					paintRana.setAlpha(paintRana.getAlpha() + 5);
			} else {
				paintRana.setAlpha(maxAlpha);
				protaRana.hitByArana = false;
			}

			canvas.drawBitmap(protaRana.bmp, protaRana.x, protaRana.y,
					paintRana);

		} else {
			if (nowDied) {
				nowDied = false;
				timeDied = System.currentTimeMillis();
				currentAlpha = paintBack.getAlpha();
				paintBack.setAlpha(0);
				paintPuntuacion.setColor(Color.WHITE);

				// Si la rana a muerto usamos un Handle con un delay de 6
				// segundos para que cargue la pantalla de Muerte.
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent intent = new Intent(mContext,
								LoadingActivity.class);
						intent.putExtra("name", 4);
						intent.putExtra("puntuacion", puntuacion);
						intent.putExtra("pantalla", 3);
						((Activity) mContext).startActivity(intent);

						// Finalizar la Activida para que no se quede abierta
						((Activity) mContext).finish();
						

					}
				}, SPLASH_DISPLAY_LENGHT);

			} else {

				if (paintBack.getAlpha() < currentAlpha
						&& paintBack.getAlpha() < maxAlpha)
					paintBack.setAlpha(paintBack.getAlpha() + 1);

				canvas.drawBitmap(res.riprana3, orig, viewSize, paintBack);
				canvas.drawBitmap(res.riprana4,
						viewWidth / 2 - (res.riprana4.getWidth() / 2), 30, p);
			}

		}

		// Si todas las aranas y bosses han muerto usamos un Handle con un delay
		// de 6 segundos para cargar la Pantalla de Creditos
		if (protaRana.goCreditScreen) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {

					Intent intent = new Intent(mContext, LoadingActivity.class);
					intent.putExtra("name", 5);
					intent.putExtra("puntuacion", puntuacion);
					((Activity) mContext).startActivity(intent);

					// Finalizar la Actividad para que no se quede abierta
					((Activity) mContext).finish();

					// Parche para finalizar la Actividad, por alguna razon el
					// finish no funciona!
					System.exit(0);

				}
			}, SPLASH_DISPLAY_LENGHT);
		}

		// Dibuja los disparos
		if (disparos.size() > 0) {
			for (int dis = disparos.size() - 1; dis >= 0; dis--) {
				Disparo d = disparos.get(dis);

				if (d.alive) {
					p.setColor(d.color);

					// canvas.drawCircle(d.xInit, d.yInit, d.radius, p);
					if (generator.nextInt(100) < 80)
						canvas.drawBitmap(d.escupitajo, d.xInit, d.yInit, p);
					else
						canvas.drawBitmap(res.escupitajo2, d.xInit, d.yInit, p);

				} else {
					disparos.remove(d);
					break;
				}
			}
		}

		// Llenar le array de aranas
		if (aaranas.size() < maxAranasInPantalla && maxAranas > 0) {
			arana = new Aranas(generator.nextInt(viewHeight - aranasHeight));
			arana.start();
			aaranas.add(arana);
		}

		// Crear al jefe final
		if (aranasmuertas >= 100 && !jefefinalout) {
			jefefinal = new FinalGameBoss(viewHeight / 2);
			jefefinal.start();
			jefefinalout = true;
			jefefinalInPantalla = true;

		}

		//Si han muerto todas las aranas pintamos al Jefe Final
		if (aranasmuertas >= 100 && jefefinalout) {

			canvas.drawBitmap(jefefinal.bitmap, jefefinal.x, jefefinal.y, p);

			
			//Si esta en posicion pintamos las Fireball
			if (jefefinal.JefenPantalla) {

				if (finalbossFireballArrayList.size() > 0) {
					for (int fireb = finalbossFireballArrayList.size() - 1; fireb >= 0; fireb--) {
						FireBall f = finalbossFireballArrayList.get(fireb);

						canvas.drawBitmap(f.bmp, f.xInit, f.yInit, p);

						if (!f.moving) {

							f.moving = true;

						} else {
							if (generator.nextBoolean())
								f.bmp = res.fireball11;
							else if (generator.nextBoolean())
								f.bmp = res.fireball12;
							else
								f.bmp = res.fireball10;

						}

						if (f.xInit < -f.radius * 2 || !f.alive) {
							finalbossFireballArrayList.remove(f);
						}
					}
				}

				//Si su vida es menor o = a 60 pintamos las GreatFireball
				if (jefefinal.life < 60) {

					if (finalbossGreatFireball.size() > 0) {
						for (int fireb = finalbossGreatFireball.size() - 1; fireb >= 0; fireb--) {
							GreatFirebal f = finalbossGreatFireball.get(fireb);

							canvas.drawBitmap(f.greatFireball, f.x, f.y, p);

							if (!f.moving) {

								f.moving = true;

							} else {
								if (generator.nextBoolean())
									f.greatFireball = res.greatFireball2;
							}

							if (f.x < -f.radius * 2 || !f.alive) {
								finalbossGreatFireball.remove(f);
							}
						}
					}

				}

			}

		}

		// pintar las aranas
		for (int i = aaranas.size() - 1; i >= 0; i--) {
			Aranas a = aaranas.get(i);
			if (a.alive) {
				canvas.drawBitmap(a.bmp, a.x, a.y, p);

			} else {
				aaranas.remove(a);
				maxAranas--;
				aranasmuertas++;
				break;

			}

		}
		
		//Generar los disparos
		generarTelaranas();
		
		//Pintar las telaranas
		if (telaranasArray.size() > 0) {
			for (int dis = telaranasArray.size() - 1; dis >= 0; dis--) {
				Telaranas d = telaranasArray.get(dis);

				if (d.alive) {
					p.setColor(d.color);

					// canvas.drawCircle(d.xInit, d.yInit, d.radius, p);
					if (generator.nextInt(1000) < 25)
						canvas.drawBitmap(d.telearana, d.x, d.y, p);
					else
						canvas.drawBitmap(res.atelearanas2, d.x, d.y, p);

				} 	else if(d.x>viewWidth){
					telaranasArray.remove(d);
				}
					
				else {
					telaranasArray.remove(d);
					//maxTelaranas--;
					break;
				}
			}
		}

//		for (int i = aaranas.size() - 1; i >= 0; i--) {
//			Aranas a = aaranas.get(i);
//			if (a.alive) {
//
//				// Dibujar la telearana
//				if (a.telearanas.size() > 0) {
//					for (int dis = a.telearanas.size() - 1; dis >= 0; dis--) {
//						Telaranas d = a.telearanas.get(dis);
//
//						if (d.alive) {
//
//							prueba = generator.nextInt(100);
//
//							if (prueba < 5) {
//								canvas.drawBitmap(d.telearana, d.x + 10, d.y
//										+ aranasHeight / 2, p);
//
//							} else {
//								canvas.drawBitmap(atelearanas2, d.x + 10, d.y
//										+ aranasHeight / 2, p);
//							}
//
//						} else {
//							a.telearanas.remove(d);
//							break;
//						}
//
//					}
//
//				}
//
//			}
//
//		}

		// Dibuja los botones de moverse hacia arriba y abajo
		canvas.drawBitmap(res.moveUp, viewWidth - res.moveUp.getWidth(), viewHeight
				- res.moveUp.getHeight() - res.moveDown.getHeight(), p);
		canvas.drawBitmap(res.moveDown, viewWidth - res.moveDown.getWidth(), viewHeight
				- res.moveDown.getHeight(), p);

		if (!buttonFirePressed)
			canvas.drawBitmap(res.botonFire, 10,
					viewHeight - res.botonFire.getHeight() - 10, p);
		else {
			canvas.drawBitmap(res.botonFire2, 10,
					viewHeight - res.botonFire.getHeight() - 10, p);
			if (Math.abs(System.currentTimeMillis() - timeFirePressed) > 50)
				buttonFirePressed = false;
		}

		compruebaColisiones();
		// Dibuja la puntuaciÃ³n
		canvas.drawText(String.valueOf(puntuacion), viewWidth
				- (viewWidth * 10 / 100), viewHeight * 10 / 100,
				paintPuntuacion);

		// dibujar vida´
		
		if(protaRana.life==0){
			canvas.drawBitmap(res.vida,
					viewWidth - (viewWidth * 25 / 100), viewHeight * 2 / 100, p);
			
			canvas.drawBitmap(res.vida,
					viewWidth - (viewWidth * 30 / 100), viewHeight * 2 / 100, p);
			canvas.drawBitmap(res.vida,
					viewWidth - (viewWidth * 35 / 100), viewHeight * 2 / 100, p);
		}else 	if(protaRana.life==1){
			canvas.drawBitmap(res.vida,
					viewWidth - (viewWidth * 25 / 100), viewHeight * 2 / 100, p);
			
			canvas.drawBitmap(res.vida,
					viewWidth - (viewWidth * 30 / 100), viewHeight * 2 / 100, p);

		}else 	if(protaRana.life==2){
			canvas.drawBitmap(res.vida,
					viewWidth - (viewWidth * 25 / 100), viewHeight * 2 / 100, p);
			

		}else{
			
		}
		
		

		while (pause) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!exitedGame) {

			Log.d("puntuacion", "" + aranasmuertas);
			invalidate();
		}

	}

	
	
	/**
	 * En este Method se comprueban todas las colisiones entre la rana y enemigos
	 */
	private void compruebaColisiones() {

		if (disparos.size() > 0) {
			for (int z = disparos.size() - 1; z >= 0; z--) {
				Disparo d = disparos.get(z);

				// Colisiones con aranas
				for (int i = aaranas.size() - 1; i >= 0; i--) {
					Aranas m = aaranas.get(i);
					Rect rectArana = new Rect(m.x, m.y, m.x + m.mWdith, m.y
							+ m.mHeight);

					if (d.alive
							&& rectArana.contains((int) d.xInit + d.radius,
									(int) d.yInit + d.radius)) {
						// Si la vida es >0 le quitamos una vida si es menor
						// muere
						if (arana.life > 1) {
							d.alive = false;
							arana.setHitByBall();

						} else {

							d.alive = false;
							m.setAranaDeadByBall();
							aaranas.remove(m);
							maxAranas--;
							aranasmuertas++;
							puntuacion = puntuacion + 4;
						}

					}

				}

				if (jefefinalInPantalla) {
					if (jefefinal.JefenPantalla) {
						if (!jefefinal.dead) {
							Rect rectJefeFinal = new Rect(jefefinal.x,
									jefefinal.y, jefefinal.x + jefefinal.width,
									jefefinal.y + jefefinal.height);
							if (d.alive
									&& rectJefeFinal.contains((int) d.xInit
											+ d.radius, (int) d.yInit
											+ d.radius)) {

								if (jefefinal.life > 1) {
									d.alive = false;
									jefefinal.setHitByBall();
								} else {
									jefefinal.setFinalBossDead();
									// jefefinal.alive=false;
									// finalbossmuerto++;
									puntuacion = puntuacion + 100;
									jefefinal.dead = true;
								}

							}

						}
					}
				}

			}

		}

		// Colisiones Telaranas con la rana

		Rect rana = new Rect(0, protaRana.y, protaRana.ranaWidth, protaRana.y
				+ protaRana.ranaHeight);
		
		
		boolean romper = false;
		for(int i= telaranasArray.size()-1; i>=0;i--){
			Telaranas d = telaranasArray.get(i);
			RectF rect = new RectF(0, protaRana.y, protaRana.ranaWidth,
					protaRana.y + protaRana.ranaHeight);
			RectF disparo = new RectF(d.x, d.y, d.x
					+ res.atelearanas.getWidth(), d.y
					+ res.atelearanas.getHeight());

			if (d.alive
					&& RectF.intersects(rect, disparo))  {
				d.alive = false;
				protaRana.setParalizy(true);
				paralizar = true;
				telaranasArray.remove(d);
				//maxTelaranas--;
				romper=true;
				break;
				 }
			if(romper){
				telaranasArray.remove(d);
				break;
			}
			
			
		}
		
//		for (int i = aaranas.size() - 1; i >= 0; i--) {
//			Aranas a = aaranas.get(i);
//
//			a.telanasColision();
//		}

		// Colisiones rana con aranas
	
		for (int i = aaranas.size() - 1; i >= 0; i--) {
			Aranas a = aaranas.get(i);
			Rect rectArana = new Rect(a.x, a.y, a.x + a.mWdith, a.y + a.mHeight);

			if (rectArana.intersect(rana)) {
				protaRana.setHitByTelarana();
				aaranas.remove(a);
				maxAranas--;
				a.setAranaDead();
				aranasmuertas++;

				break;

			}
			
		}
		
		//Colisiones Fireball con Rana
		boolean breaked = false;
		if(finalbossFireballArrayList.size()>0){
		for(int i = finalbossFireballArrayList.size()-1;i>=0;i--){
			FireBall f = finalbossFireballArrayList.get(i);
			for(PuntoGeometrico p:f.getPuntosGeometricos()){
			if(rana.contains(p.x, p.y)){
				//protaRana.setDeadByFire(true);
				//protaRana.setAlive(false);
				finalbossFireballArrayList.remove(f);
				//protaRana.hitsByBalls++;
				protaRana.setHitByTelarana();
				breaked = true;
				//Tenemos que hacer un break para romper el bucle y evitar que siga buscando en la array
				break;
			}
			}
			if(breaked){
				finalbossFireballArrayList.remove(f);
				break;
			}
		}
		}
	
		
		//Colisiones GreatFireball con rana
		boolean breaked2 = false;
		if(finalbossGreatFireball.size()>0){
		for(int i = finalbossGreatFireball.size()-1;i>=0;i--){
			GreatFirebal f = finalbossGreatFireball.get(i);
			for(PuntoGeometrico p:f.getPuntosGeometricos()){
			if(rana.contains(p.x, p.y)){
				//protaRana.setDeadByFire(true);
				//protaRana.setAlive(false);
				finalbossGreatFireball.remove(f);
				//protaRana.hitsByBalls++;
				protaRana.setHitByTelarana();
				breaked2 = true;
				//Tenemos que hacer un break para romper el bucle y evitar que siga buscando en la array
				break;
			}
			}
			if(breaked2){
				finalbossGreatFireball.remove(f);
				break;
			}
		}
		}
		
		
		

	}

	
	/**
	 * Este Method es para obtener el Ancho y alto de la pantalla
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {

		viewHeight = h;
		viewWidth = w;

		super.onSizeChanged(w, h, oldw, oldh);
	}

	
	/**
	 * En este Method es donde se comprueba si algo de la pantalla a sido tocado , boton de disparo , boton de movimientos ... etc
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		// Controles de movimiento (Flechas)
		if (event.getAction() == MotionEvent.ACTION_DOWN) {

			float nowXMove = event.getX();
			float nowYMove = event.getY();

			RectF up = new RectF(viewWidth - res.moveDown.getWidth(), viewHeight
					- res.moveDown.getHeight() - res.moveUp.getHeight(), viewWidth,
					viewHeight - res.moveDown.getHeight());
			RectF down = new RectF(viewWidth - res.moveDown.getWidth(), viewHeight
					- res.moveDown.getHeight(), viewWidth, viewHeight);
			if (up.contains(nowXMove, nowYMove)) {
				// goUp(viewHeight/100*5);
				moves.setButtonPressed(true);
				moves.setMovingUp(true);
				moves.setStopped(false);
			} else if (down.contains(nowXMove, nowYMove)) {
				moves.setButtonPressed(true);
				moves.setMovingUp(false);
				moves.setStopped(false);

			}
		}

		if (event.getAction() == MotionEvent.ACTION_OUTSIDE
				|| event.getAction() == MotionEvent.ACTION_UP) {
			moves.setMovingUp(false);
			moves.setButtonPressed(false);
			moves.setStopped(true);
		}

		// if (event.getAction() == MotionEvent.ACTION_DOWN) {
		// float nowX = event.getX();
		// float nowY = event.getY();
		// RectF r = new RectF(0, this.protaRana.y - 10
		// * this.protaRana.bmp.getHeight() * 10 / 100,
		// (int) this.protaRana.bmp.getWidth() + 10
		// * this.protaRana.bmp.getWidth() * 10 / 100,
		// this.protaRana.y + this.protaRana.ranaHeight + 10
		// * this.protaRana.bmp.getHeight() * 10 / 100);
		//
		// if (r.contains((int) nowX, (int) nowY) && protaRana.isAlive()) {
		// protaRana.shooting = true;
		//
		// // Crea una nueva bola
		// try {
		// Disparo disparo = new Disparo(protaRana.ranaWidth
		// - protaRana.ranaWidth / 6, protaRana.y
		// + protaRana.ranaHeight / 3, colorDisparos,
		// radioDisparo, viewWidth, escupitajo);
		// if (!disparos.contains(disparo)
		// && disparos.size() < maxDisparos) {
		// disparos.add(disparo);
		// disparo.start();
		// }
		// } catch (Exception e) {
		// System.out.println("Error.");
		// }
		//
		// }
		// }

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			float nowX = event.getX();
			float nowY = event.getY();
			// RectF r = new RectF(0,
			// this.protaRana.y-10*this.protaRana.bmp.getHeight()*10/100,
			// (int)this.protaRana.bmp.getWidth()+10*this.protaRana.bmp.getWidth()*10/100,
			// this.protaRana.y+this.protaRana.ranaHeight+10*this.protaRana.bmp.getHeight()*10/100);
			RectF r = new RectF(0,
					viewHeight - (res.botonFire.getHeight() * 2),
					(res.botonFire.getWidth() * 2), viewHeight);
			// RectF f = new R

			if (r.contains((int) nowX, (int) nowY) && protaRana.isAlive()
					&& !stageFinished) {
				protaRana.shooting = true;
				buttonFirePressed = true;
				timeFirePressed = System.currentTimeMillis();

				// Crea una nueva bola
				try {
					Disparo disparo = new Disparo(protaRana.ranaWidth
							- protaRana.ranaWidth / 6, protaRana.y
							+ protaRana.ranaHeight / 3, colorDisparos,
							radioDisparo, viewWidth, res.escupitajo);
					if (!disparos.contains(disparo)
							&& disparos.size() < maxDisparos) {
						disparos.add(disparo);
						disparo.start();
					}
				} catch (Exception e) {
					System.out.println("Error.");
				}

			}
		}

		return super.onTouchEvent(event);
	}
	
	
	//Generar Telaranas
	   public void generarTelaranas(){
		   
		   for (int i = aaranas.size() - 1; i >= 0; i--) {
				Aranas a = aaranas.get(i);
				if (a.alive) {
					
					if(a.x< viewWidth - a.ESTATIC_X && !a.aranaShoot){
						if(telaranasArray.size()<MAX_TELARANA_IN_PANTALLA && maxTelaranas>0){
							
							telanas=new Telaranas(a.x, a.y+a.mHeight/2, colorTelanas, a.mHeight/9, viewWidth, res.atelearanas);
							telanas.start();
							telaranasArray.add(telanas);
							   a.aranaShoot=true;
						}
						
					}
					
				}
				
				
		   
		   }
	   }

	/**
	 * Este Method es para que la rana se mueva hacia arriba
	 */
	public void goUp() {
		// SÃ³lo se mueve hacia arriba si la rana estÃ¡ viva
		if (protaRana.alive) {
			if (paralizar) {
				// moves.setStopped(true);
			} else {
				if (this.protaRana.y > 0) {
					this.protaRana.y -= (int) viewHeight * 1 / 100;
				}
			}

		}

	}

	/**
	 * Este Method es para que la rana se mueva hacia abajo
	 */
	public void goDown() {
		if (paralizar) {

			// moves.setStopped(true);

		} else {
			if (this.protaRana.y < viewHeight - protaRana.ranaHeight) {
				this.protaRana.y += (int) viewHeight * 1 / 100;
			}
		}
	}

	/**
	 * Este Method devuelve un Objeto de la clase prota(Rana)
	 * @return
	 */
	public Prota getRana() {
		return this.protaRana;

	}

	/**
	 * Clase donde se Definen los Atributos,Movimientos y Habilidades de la Rana para la Pantalla Hard Level.
	 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
	 *
	 */
	public class Prota extends Thread {

		public boolean alive = true;
		int x = 0;
		int y = 0;
		int cont = 0;
		Bitmap bmp = res.ranaBmp1;
		int ranaWidth = bmp.getWidth();
		int ranaHeight = bmp.getHeight();
		boolean mooving = false;
		boolean shooting = false;
		boolean dieByFire = false;
		boolean dieByMosquito = false;
		boolean inmune = false;
		int hitsByBalls = 0;
		int life = 0;
		long hitByAranaTime = 0;
		boolean hitByArana = false;
		boolean hitByTelearanas = false;
		public boolean pause = false;
		public boolean goCreditScreen = false;
		
		/**
		 * Constructor de la rana
		 * @param x
		 * @param y
		 */

		public Prota(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void run() {

			while (alive) {

				if (!this.pause) {

					// Si han muertos todas las aranas
					if (aranasmuertas == 100 && finalbossmuerto == 1) {
						goCreditScreen = true;
						// Saca la rana de la pantalla
						while (protaRana.x < viewWidth) {
							protaRana.x += (int) viewWidth * 2 / 100;

							try {
								sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						// Cambiar a la pantalla de Creditos

					}

					else {

						if (shooting && hitsByBalls == 0) {
							bmp = res.ranaBmpOpen;
							try {
								sleep(200);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							shooting = false;
						}

						// Si la rana esta paralizada
						if (hitByTelearanas) {
							this.bmp = res.ranaparalizada2;
							moves.setStopped(true);
							try {
								sleep(1500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							hitByTelearanas = false;
							if (!hitByTelearanas) {
								this.bmp = res.ranaBmp1;
								paralizar = false;

								moves.setStopped(false);
							}

						}// Parche por si se bugea al colisionar aranas mientras
							// esta paralizada
						else {
							this.bmp = res.ranaBmp1;
							paralizar = false;

							moves.setStopped(false);
						}

						try {
							sleep(40);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}

			while (this.y < viewHeight) {
				if (dieByFire) {
					this.y += viewHeight / 100;
					if (generator.nextInt(100) < 80)
						this.bmp = res.ranaBurnDie1;
					else
						this.bmp = res.ranaBurnDie2;
				} else if (dieByMosquito) {
					this.y += viewHeight / 100;
					if (generator.nextInt(100) < 80)
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
		 * Method para quitarle vida a la rana
		 */
		public synchronized void setHitByTelarana() {
			life++;
			hitByArana = true;
			hitByAranaTime = System.currentTimeMillis();
			if (life == 3) {
				this.dieByMosquito = true;
				this.setAlive(false);
			}
		}

		/**
		 * Method para Cambiar el estado de la rana viva o muerta
		 * @param alive
		 */
		public synchronized void setAlive(boolean alive) {
			this.alive = alive;
		}

		public void setDeadByFire(boolean b) {
			this.dieByFire = b;
		}

		// Paralizar la rana
		public synchronized void setParalizy(boolean b) {
			this.hitByTelearanas = b;

		}

	}

	public synchronized void setRanaMoving(boolean moving) {
		protaRana.mooving = moving;
		protaRana.cont = 0;
	}

	

	
	

	/**
	 * Clase donde se Definen los atributos,movimientos y habilidades de las Aranas.
	 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
	 *
	 */
	public class Aranas extends Thread {
		public boolean alive = true;
		boolean deadByBall = false;
		int x = viewWidth;
		int y;
		Bitmap bmp = res.aranas;
		int mWdith = bmp.getWidth();
		int mHeight = bmp.getHeight();
		int velocidad = generator.nextInt(100) + 50;
		int life = 5;
		long time;
		long hitTime = 100;
		boolean hited = false;
		public boolean pause = false;
		//public ArrayList<Telaranas> telearanas = new ArrayList<Telaranas>();
		 final int ESTATIC_X=115;
		 final int ESTATIC_X_OUT=120;
		 final int MTIP = 1;
		 boolean muerte = false;
		 boolean aranaShoot = false;



		/**
		 * Constructor
		 * @param y
		 */
		public Aranas(int y) {
			this.y = y;
		}



		// Method para descontar las vidas de las aranas
		public synchronized void setHitByBall() {
			this.life--;

			this.hited = true;
			this.time = System.currentTimeMillis();
			if (life == 0) {

				this.setAranaDeadByBall();

			}

		}

		@Override
		public void run() {

			while (alive) {

				if (!this.pause) {
					x-=0.3*viewWidth/100;

					
					
//					if(x< viewWidth - ESTATIC_X && !muerte){
//						if(telaranasArray.size()<MAX_TELARANA_IN_PANTALLA && maxTelaranas>0){
//							
//							telanas=new Telaranas(x, y+mHeight/2, colorTelanas, mHeight/9, viewWidth, atelearanas);
//							telanas.start();
//							telaranasArray.add(telanas);
//							   
//						}
//						
//					}
					
					
					// Esperar a que la arana salga por pantalla para generar
					// los disparos
//					if (x < viewWidth - 115) {
//						if (telearanas.size() < maxTelaranasInPantalla
//								&& maxTelaranas > 0) {
//
//							// Controlar la generacion de disparos
//							if (generator.nextInt(100) < 4) {
//								if (prueba < 5) {
//									telanas = new Telaranas(x, y, mHeight / 9,
//											viewWidth, atelearanas);
//								} else {
//									telanas = new Telaranas(x, y, mHeight / 9,
//											viewWidth, atelearanas2);
//								}
//								telanas.start();
//								telearanas.add(telanas);
//
//							}
//
//						}
//
//					}
					
					if(aranaShoot){
						try {
							sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						aranaShoot=false;
						
					}

					try {
						sleep(velocidad);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// Si sale por la izquierda la arana muere
					if (x < -ESTATIC_X_OUT) {
						setAranaDead();
					}

				}
			}

			super.run();
		}

		public synchronized void setAranaDead() {
			this.alive = false;
		}

		public synchronized void setAranaDeadByBall() {
			this.deadByBall = true;

		}

	}

	// Botones para mover.
	public class BotonesMovimiento extends Thread {
		public boolean alive = true;
		Movimientos m;
		public boolean pause = false;

		public BotonesMovimiento(Movimientos moves) {
			m = moves;
		}

		public void run() {

			while (alive) {
				if (!this.pause) {

					if (m.isButtonPressed() && !m.isStopped()) {
						if (m.isMovingUp()) {
							goUp();

						} else if (!m.isMovingUp()) {
							goDown();

						}

						try {
							sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					try {
						sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};

	}

	/**
	 * En ests clase se definen los atributos del jefe final del juego , sus
	 * movimientos y habilidades
	 * 
	 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
	 * 
	 */
	public class FinalGameBoss extends Thread {
		public boolean alive = true;
		int x = viewWidth;
		int y;
		Bitmap bitmap = res.aranajefe;
		boolean startMove = false;
		boolean moveBotttom = false;
		boolean moveTop = true;
		int height = bitmap.getHeight();
		int width = bitmap.getWidth();
		int velocidad = generator.nextInt(100) + 25;
		int life = 150;
		boolean hited = false;
		long time;
		long hitTime = 100;
		boolean deadByBall = false;
		boolean dead = false;
		boolean JefenPantalla = false;
		final int ESTATIC_X=100;
		final int ESTATIC_Y=12;

		// Habilidades del Jefe Final
		FireBall fireBall;
		GreatFirebal greatFireball;
		LightBall lightball;

		public FinalGameBoss(int y) {
			this.y = y;

		}

		public synchronized void setHitByBall() {
			this.life--;

			this.hited = true;
			this.time = System.currentTimeMillis();
			if (life == 0) {

				this.setFinalBossDead();

			}

		}

		@Override
		public void run() {

			while (alive) {

				// Sale hacia la pantalla
				if (this.x > viewWidth - viewWidth / 2 - ESTATIC_X) {
					this.x -= 5*viewHeight/100;

				}

				if (!hited) {

					bitmap = res.aranajefe;
				} else {
					this.bitmap = res.aranajefehited;
					if ((Math.abs(System.currentTimeMillis() - this.time) > hitTime)) {
						hited = false;
					}
				}

				// Si el jefe a salido por la pantalla Generemos las Fireball.
				if(x<=viewWidth - viewWidth / 2 - ESTATIC_X){
				
				if (JefenPantalla) {
					if (generator.nextInt(250) < 40
							&& !dead
							&& finalbossFireballArrayList.size() < maxFireballInPantalla
							&& maxFireball > 0) {

						fireBall = new FireBall(this.x + width / 2 - width * 5
								/ 100, this.y + height / 2,
								Constantes.fireballRadius, viewWidth,
								res.fireball7);
						fireBall.timeStarted = System.currentTimeMillis();
						fireBall.moving = false;
						fireBall.start();
						finalbossFireballArrayList.add(fireBall);
					}

				}
				
				if(life<80){
					maxFireballInPantalla=7;
				}
				
				

				// Si la vida del jefe es = O menor que 50 Generamos las
				// GreatFireball
				if (life <= 60 && !dead) {
					if (generator.nextInt(500) < 15
							&& finalbossGreatFireball.size() < maxGreatFireballInPantalla
							&& maxGreatFireball > 0) {

						greatFireball = new GreatFirebal(this.x + width / 2
								- width * 5 / 100, this.y + height / 2,
								Constantes.greatFireballRadius, viewWidth,
								res.greatFireball1);
						greatFireball.timeStarted = System.currentTimeMillis();
						greatFireball.moving = false;
						greatFireball.start();
						finalbossGreatFireball.add(greatFireball);
					}
				}
				}
				// Moverse hacia arrriba
				if (moveTop) {
					y -=  1*viewHeight/100;
					JefenPantalla = true;
					if (y <=-height/2) {
						moveTop = false;
						moveBotttom = true;
					}
					// moverse hacia abajo
				} else if (moveBotttom) {

					y +=  1*viewHeight/100;

					if (y >= viewHeight /2) {
						moveBotttom = false;
						moveTop = true;
					}
				}

				// Si el boss final a muerto sale por la pantalla por la parte
				// de abajo.
				if (dead) {
					bitmap=res.aranajefedead;
					moveTop = false;
					moveBotttom = false;
					y +=  2*viewHeight/100;

					// si a salido completamente rompemos el bucle.
					if (y >= viewHeight + ESTATIC_Y) {
						alive = false;
						finalbossmuerto++;
					}
				}

				try {
					sleep(velocidad);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			super.run();
		}

		public synchronized void setFinalBossDead() {
			this.deadByBall = true;

		}

	}

}
