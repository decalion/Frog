package com.icaballero.proyectofinal.CustomViews;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
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
import android.support.v4.widget.SlidingPaneLayout.PanelSlideListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.duhnnae.proyectofinal.R;
import com.duhnnae.proyectofinal.R.dimen;
import com.icaballero.proyectofinal.LoadingActivity;
import com.icaballero.proyectofinal.Utils.Constantes;
import com.icaballero.proyectofinal.Utils.Disparo;
import com.icaballero.proyectofinal.Utils.FireBall;
import com.icaballero.proyectofinal.Utils.Moneda;
import com.icaballero.proyectofinal.Utils.Movimientos;
import com.icaballero.proyectofinal.Utils.PuntoGeometrico;
import com.icaballero.proyectofinal.Utils.ResourcesP1;
import com.icaballero.proyectofinal.Utils.UtilsMethods;

/**
 * CustomView que dibuja la pantalla fácil del juego. Ésta pantalla consiste en
 * una rana, mosquitos enemigos, y un mosquito final.
 * 
 * @author decalion
 * 
 */
public class EasyLevel extends View {

	public ResourcesP1 res = null;

	int xDiedJefe = 0;
	int yDiedJefe = 0;
	int ranaX = 0;
	int ranaY = 0;
	Prota protaRana;
	EasyLevel protagonista;
	int viewHeight = 0;
	int viewWidth = 0;
	Random generator = new Random();
	int colorDisparos;
	ArrayList<Disparo> disparos = new ArrayList<Disparo>();
	Paint p = new Paint();
	Paint paintRana = new Paint();
	Paint paintBack = new Paint();
	Paint paintPuntuacion = new Paint();
	Paint paintFondo;
	Paint paintBotones = new Paint();
	int radioDisparo;
	ArrayList<Mosquito> mosquitos = new ArrayList<EasyLevel.Mosquito>();
	ArrayList<Mosquito> mosquitosDead = new ArrayList<EasyLevel.Mosquito>();
	ArrayList<FireBall> fireBalls = new ArrayList<FireBall>();
	int numOfMosquitos = 80;
	int maxMosquitos = 90;
	int maxMosquitosInPantalla = 10;
	int mosquitoHeight = 40;
	int mosquitoWidth = 30;
	int mosquitoJefeHeight = 70;
	int mosquitoJefeWidth = 80;
	boolean jefeCreado = false;

	boolean pause = false;

	// objetos
	MosquitoJefe mosquitoJefe;
	Moneda moneda;

	// Ãštiles
	int maxDisparos = 10;
	int currentAlpha = 0;
	int maxAlpha;
	int cont = 0;
	long currTime = 0;
	boolean fondoUsado = true;
	boolean firstPaint = true;
	int puntuacion = 0;
	boolean stageFinished = false;
	boolean monedaOut = false;
	boolean dibujaMoneda = false;
	boolean doOnlyAtFirst = true;
	boolean exitedGame = false;
	private boolean readyToPlay = false;
	boolean buttonFirePressed = false;
	long timeFirePressed = 0;

	// controles
	Movimientos moves = new Movimientos();

	// Audio
	MediaPlayer m = new MediaPlayer();

	// ImÃ¡genes
	InputStream is = null;

	// More util variables
	boolean nowDied = true;
	long timeDied = 0;
	Button buttonRestart;
	Rect orig;
	Rect viewSize;
	boolean changeFondo = true;
	boolean alphaUp = true;
	boolean screenCleaned = false;
	int scaledSize = getResources().getDimensionPixelSize(R.dimen.myFontSize);

	final int SPLASH_DISPLAY_LENGHT = 6000;
	Context mContext;

	public EasyLevel(Context context) {
		super(context);
		init(null, 0);
	}

	public EasyLevel(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs, 0);
	}

	public EasyLevel(Context context, AttributeSet attrs, int defStyle) {
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

		// Se cargan todos los recursos
		res = new ResourcesP1(getContext(), getResources());
		pause = res.isPause();

		// Establece el color de los disparos
		// colorDisparos = Math.abs(generator.nextInt(200000));
		// colorDisparos = colorDisparos | 0xff23FF0F; // Forces to the color to
		// be opaque

		// Crea la rana a la izquierda de la pantalla.
		protaRana = new Prota(10, 10);
		protaRana.start();
		radioDisparo = (int) protaRana.ranaHeight / 8;
		maxAlpha = p.getAlpha();

		/*
		 * Bitmap b = BitmapFactory.decodeResource(getResources(),
		 * R.drawable.texture);
		 */

		Drawable d = new BitmapDrawable(getResources(), res.fondo);

		paintFondo = new Paint();
		paintFondo.setAlpha(0);

		setBackgroundDrawable(d);

		currTime = System.currentTimeMillis();

		/*
		 * buttonRestart = (Button) findViewById(R.id.buttonRestart);
		 * buttonRestart.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { try { finalize(); } catch
		 * (Throwable e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * } }); buttonRestart.setEnabled(false);
		 * buttonRestart.setVisibility(0);
		 */

		// //Instancia el textview y lo inicializa
		// tvPuntuacion = (TextView) findViewById(R.id.textView1);
		// tvPuntuacion.setText(0);

		paintPuntuacion.setColor(Color.BLACK);
		paintPuntuacion.setFakeBoldText(true);
		paintPuntuacion.setTextSize(viewHeight * 5 / 100);

		BotonesMovimiento botones = new BotonesMovimiento(moves);
		botones.start();
		mContext = getContext();
		setReadyToPlay(true);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// Sólo se hace la primera vez que se pinta el canvas
		if (doOnlyAtFirst) {
			doOnlyAtFirst = false;
			viewSize = new Rect(0, 0, viewWidth + (50 * viewWidth / 100),
					viewHeight + (50 * viewWidth / 100));
			orig = new Rect(0, 0, res.fondo.getWidth(), res.fondo.getHeight());
		}

		// Va alternando entre los tres fondos para dar la sensaciÃ³n de
		// animaciÃ³n
		// Alterna entre los dos fondos, segÃºn si la pantalla se ha completado
		// o no
		if (!stageFinished && protaRana.isAlive()) {
			if (changeFondo || firstPaint) {
				currTime = System.currentTimeMillis();
				paintFondo.setAlpha(0);
				changeFondo = false;
				firstPaint = false;
				res.currentFondo = res.waterTexture;
			}

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

		// Si la pantalla se ha terminado, oscurece el fondo de la pantalla
		if (stageFinished)
			canvas.drawBitmap(res.riprana3, orig, viewSize, paintFondo);
		else
			canvas.drawBitmap(res.currentFondo, orig, viewSize, paintFondo);

		// Pinta la rana
		if (protaRana.isAlive()) {
			boolean decreasingAlpha = true;
			if (Math.abs(System.currentTimeMillis()
					- protaRana.hitByMosquitoTime) < 5000
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
				protaRana.hitByMosquito = false;
			}

			canvas.drawBitmap(protaRana.bmp, protaRana.x, protaRana.y,
					paintRana);

		} else {
			// Guarda la puntuación actual
			if (nowDied) {
				nowDied = false;
				timeDied = System.currentTimeMillis();
				currentAlpha = paintBack.getAlpha();
				paintBack.setAlpha(0);
				paintPuntuacion.setColor(Color.WHITE);

				// Si la rana a muerto usamos un Handle con un delay de 6
				// segundos para que cargue la pantalla de Muerte, con la
				// puntuación correspondiente
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent intent = new Intent(mContext,
								LoadingActivity.class);
						intent.putExtra("name", 4);
						intent.putExtra("puntuacion", puntuacion);
						intent.putExtra("pantalla", 1);
						((Activity) mContext).startActivity(intent);

						// Finalizar la Activida para que no se quede abierta
						((Activity) mContext).finish();
						System.exit(0);

					}
				}, SPLASH_DISPLAY_LENGHT);

				// buttonRestart.setVisibility(100);
				// BitmapFactory.Options options = new BitmapFactory.Options();
				// options.inSampleSize = 1; // The higher, the smaller the
				// image size and resolution read in
				//
				// int oldWidth = riprana.getWidth();
				// options.inSampleSize = oldWidth/viewWidth;
				// riprana = BitmapFactory.decodeResource(getResources(),
				// R.drawable.riprrana, options);

			} else {

				if (paintBack.getAlpha() < currentAlpha
						&& paintBack.getAlpha() < maxAlpha)
					paintBack.setAlpha(paintBack.getAlpha() + 1);

				canvas.drawBitmap(res.riprana3, orig, viewSize, paintBack);
				canvas.drawBitmap(res.riprana4,
						viewWidth / 2 - (res.riprana4.getWidth() / 2), 30, p);
			}

		}

		// En caso de haber completado la pantalla, también llamamos a la
		// pantalla de puntuación, pero
		if (protaRana.win) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {

					Intent intent = new Intent(mContext, LoadingActivity.class);
					intent.putExtra("name", 6);
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

		if (mosquitos.size() < maxMosquitosInPantalla && maxMosquitos > 0) {
			Mosquito mosquito = new Mosquito(generator.nextInt(viewHeight
					- mosquitoHeight));
			mosquito.start();
			mosquitos.add(mosquito);
			// maxMosquitosInPantalla++;
		}

		if (maxMosquitos == 0 && !jefeCreado) {
			mosquitoJefe = new MosquitoJefe(generator.nextInt(viewHeight
					- mosquitoJefeHeight));
			mosquitoJefe.start();
			jefeCreado = true;
			playAudioMosquitoJefe();
		}

		// Pinta el mosquito si estÃ¡ muriendo
		for (Mosquito m : mosquitosDead) {
			if (m.alive)
				canvas.drawBitmap(m.bmp, m.x, m.y, p);
			else {
				mosquitosDead.remove(m);
				if (generator.nextBoolean())
					maxMosquitos--;
				break;
			}
		}

		// Pinta el mosquito si sigue vivo
		for (int i = mosquitos.size() - 1; i >= 0; i--) {
			Mosquito m = mosquitos.get(i);
			if (m.alive)
				canvas.drawBitmap(m.bmp, m.x, m.y, p);
			else {
				mosquitos.remove(m);
			}
		}

		if (jefeCreado && mosquitoJefe != null) {
			canvas.drawBitmap(mosquitoJefe.bmp, mosquitoJefe.x, mosquitoJefe.y,
					p);

			if (mosquitoJefe.mosquitoOut) {
				// Dibuja las bolas de fuego
				if (fireBalls.size() > 0) {
					for (int fireb = fireBalls.size() - 1; fireb >= 0; fireb--) {
						FireBall f = fireBalls.get(fireb);

						canvas.drawBitmap(f.bmp, f.xInit, f.yInit, p);

						if (!f.moving) {
							// if(Math.abs(System.currentTimeMillis()-f.timeStarted)<f.creatingTime/3){
							// f.bmp = res.fireball7;
							// }else
							// if(Math.abs(System.currentTimeMillis()-f.timeStarted)<f.creatingTime/2){
							// f.bmp = res.fireball8;
							// }else
							// if(Math.abs(System.currentTimeMillis()-f.timeStarted)<f.creatingTime){
							// f.bmp = res.fireball9;
							// }else{
							// f.bmp = res.fireball10;
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
							fireBalls.remove(f);
						}
					}
				}
			}
		}

		compruebaColisiones();

		// dibujar vida´

		if (protaRana.life == 0) {
			canvas.drawBitmap(res.vida, viewWidth - (viewWidth * 25 / 100),
					viewHeight * 2 / 100, p);

			canvas.drawBitmap(res.vida, viewWidth - (viewWidth * 30 / 100),
					viewHeight * 2 / 100, p);
			canvas.drawBitmap(res.vida, viewWidth - (viewWidth * 35 / 100),
					viewHeight * 2 / 100, p);
		} else if (protaRana.life == 1) {
			canvas.drawBitmap(res.vida, viewWidth - (viewWidth * 25 / 100),
					viewHeight * 2 / 100, p);

			canvas.drawBitmap(res.vida, viewWidth - (viewWidth * 30 / 100),
					viewHeight * 2 / 100, p);

		} else if (protaRana.life == 2) {
			canvas.drawBitmap(res.vida, viewWidth - (viewWidth * 25 / 100),
					viewHeight * 2 / 100, p);

		} else {

		}

		// Dibuja la puntuaciÃ³n
		paintPuntuacion.setTextSize(scaledSize);
		canvas.drawText(String.valueOf(puntuacion), viewWidth
				- (viewWidth * 10 / 100), viewHeight * 10 / 100,
				paintPuntuacion);

		// Dibuja la moneda si se ha eliminado al mosquito Jefe
		if (monedaOut) {

			if (generator.nextInt(100) < 80)
				canvas.drawBitmap(res.moneda1, moneda.initX, moneda.initY, p);
			else
				canvas.drawBitmap(res.moneda2, moneda.initX, moneda.initY, p);

		}

		/*
		 * //Si el programa estÃ¡ en pausa, no continÃºa consumiendo memoria e
		 * imprimiendo la pantalla. while(pause){ try { Thread.sleep(100); }
		 * catch (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 */

		// Dibuja los botones de moverse hacia arriba, abajo y disparo
		canvas.drawBitmap(res.moveUp, viewWidth - res.moveUp.getWidth(),
				viewHeight - res.moveUp.getHeight() - res.moveDown.getHeight(),
				paintBotones);
		canvas.drawBitmap(res.moveDown, viewWidth - res.moveDown.getWidth(),
				viewHeight - res.moveDown.getHeight(), paintBotones);
		if (!buttonFirePressed)
			canvas.drawBitmap(res.botonFire, 10,
					viewHeight - res.botonFire.getHeight() - 10, p);
		else {
			canvas.drawBitmap(res.botonFire2, 10,
					viewHeight - res.botonFire.getHeight() - 10, p);
			if (Math.abs(System.currentTimeMillis() - timeFirePressed) > 50)
				buttonFirePressed = false;
		}

		if (!exitedGame)
			invalidate();

	}

	/**
	 * Comprueba las colisiones entre los objetos que hay en pantalla
	 */
	private void compruebaColisiones() {

		boolean removeDisparo = false;
		if (disparos.size() > 0) {
			for (int z = disparos.size() - 1; z >= 0; z--) {
				Disparo d = disparos.get(z);
				removeDisparo = false;

				// Comprueba colisiones con el jefe sÃ³lo si estÃ¡ creado
				if (jefeCreado) {
					Rect rectMosquitoJefe = new Rect(mosquitoJefe.x
							+ mosquitoJefeWidth / 2, mosquitoJefe.y,
							mosquitoJefe.x + mosquitoJefe.mWdith,
							mosquitoJefe.y + mosquitoJefe.mHeight);

					if (d.alive
							&& rectMosquitoJefe.contains((int) d.xInit
									+ d.radius, (int) d.yInit + d.radius)) {
						d.alive = false;

						mosquitoJefe.setHitByBall();
						removeDisparo = true;
					}

				}

				for (int i = mosquitos.size() - 1; i >= 0; i--) {
					Mosquito m = mosquitos.get(i);
					Rect rectMosquitoPeque = new Rect(m.x, m.y, m.x + m.mWdith,
							m.y + m.mHeight);

					if (d.alive
							&& rectMosquitoPeque.contains((int) d.xInit
									+ d.radius, (int) d.yInit + d.radius)) {
						d.alive = false;
						m.setMosquitoDeadByBall();
						mosquitosDead.add(m);
						mosquitos.remove(m);
						// Suma el mosquito a la puntuaciÃ³n
						puntuacion++;
						// Aumenta el nÃºmero de mosquitos en la pantalla
						if (new Random().nextInt() < 40)
							maxMosquitosInPantalla++;

						removeDisparo = true;
						// int puntuacion =
						// Integer.parseInt(tvPuntuacion.getText().toString());
						// tvPuntuacion.setText(puntuacion+1);
						// Breaks because this ball can't kill any other
						// mosquito

					}
				}

				if (removeDisparo) {
					disparos.remove(z);
					break;
				}

			}
		}

		Rect rect = new Rect(0, protaRana.y, protaRana.ranaWidth, protaRana.y
				+ protaRana.ranaHeight);

		// Boleano que sirve para controlar si se ha salido del bucle
		boolean breaked = false;
		if (fireBalls.size() > 0) {
			for (int i = fireBalls.size() - 1; i >= 0; i--) {
				FireBall f = fireBalls.get(i);
				for (PuntoGeometrico p : f.getPuntosGeometricos()) {
					if (rect.contains(p.x, p.y)) {
						// protaRana.setDeadByFire(true);
						// protaRana.setAlive(false);
						fireBalls.remove(f);
						protaRana.setHitByMosquito();
						breaked = true;
						// Tenemos que hacer un break para roper el bucle y
						// evitar que siga buscando en la array
						break;
					}
				}
				if (breaked) {
					fireBalls.remove(f);
					break;
				}
			}
		}

		// Comprueba colisiÃ³n de mosquitos con la rana protagonista
		for (int i = mosquitos.size() - 1; i >= 0; i--) {
			Mosquito m = mosquitos.get(i);
			Rect rectMosquitoPeque = new Rect(m.x, m.y, m.x + m.mWdith, m.y
					+ m.mHeight);

			if (rectMosquitoPeque.intersect(rect)) {
				protaRana.setHitByMosquito();
				mosquitos.remove(m);
				m.setMosquitoDead();
				// mosquitosDead.add(m);

				// int puntuacion =
				// Integer.parseInt(tvPuntuacion.getText().toString());
				// tvPuntuacion.setText(puntuacion+1);
				// Breaks because this ball can't kill any other mosquito
				break;

			}
		}

		/*
		 * try { Thread.sleep(1); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {

		viewHeight = h;
		viewWidth = w;

		super.onSizeChanged(w, h, oldw, oldh);
	}

	/**
	 * Control de eventos que suceden cuando se toca la pantalla
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		// Controles de movimiento (Flechas)
		if (event.getAction() == MotionEvent.ACTION_DOWN) {

			float nowXMove = event.getX();
			float nowYMove = event.getY();

			RectF up = new RectF(viewWidth - res.moveDown.getWidth(),
					viewHeight - res.moveDown.getHeight()
							- res.moveUp.getHeight(), viewWidth, viewHeight
							- res.moveDown.getHeight());
			RectF down = new RectF(viewWidth - res.moveDown.getWidth(),
					viewHeight - res.moveDown.getHeight(), viewWidth,
					viewHeight);
			// En caso de tocar la flecha hacia arriba
			if (up.contains(nowXMove, nowYMove)) {
				// goUp(viewHeight/100*5);
				moves.setButtonPressed(true);
				moves.setMovingUp(true);
				moves.setStopped(false);
			}
			// En caso de tocar la flecha hacia abajo
			else if (down.contains(nowXMove, nowYMove)) {
				moves.setButtonPressed(true);
				moves.setMovingUp(false);
				moves.setStopped(false);

			}

			// Comprueba si se ha tocado la moneda
			if (monedaOut) {
				RectF premio = new RectF(moneda.initX, moneda.initY,
						moneda.initX + res.moneda1.getWidth(), moneda.initY
								+ res.moneda1.getHeight());

				// for(PuntoGeometrico p:moneda.getPuntos()){
				if (premio.contains(nowXMove, nowYMove)) {
					monedaOut = false;
					puntuacion += moneda.getValor();
					// }
				}
			}

		}

		if (event.getAction() == MotionEvent.ACTION_OUTSIDE
				|| event.getAction() == MotionEvent.ACTION_UP) {
			moves.setMovingUp(false);
			moves.setButtonPressed(false);
			moves.setStopped(true);
		}

		// Disparos de la rana
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

			// Si se toca el botón de disparo, se genera un disparo
			if (r.contains((int) nowX, (int) nowY) && protaRana.isAlive()
					&& !stageFinished) {
				protaRana.shooting = true;
				buttonFirePressed = true;
				timeFirePressed = System.currentTimeMillis();

				// Crea una nueva bala y la añade a la lista de disparos
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

	/**
	 * Movimiento hacia arriba de la rana, en porcentaje según pantalla
	 */
	public void goUp() {
		// SÃ³lo se mueve hacia arriba si la rana estÃ¡ viva
		if (protaRana.alive) {
			if (this.protaRana.y > 0) {
				this.protaRana.y -= (int) viewHeight * 1 / 100;
			}
		}

	}

	/**
	 * Movimiento hacia abajo de la rana, en porcentaje según la pantalla del
	 * dispositivo
	 */
	public void goDown() {
		if (this.protaRana.y < viewHeight - protaRana.ranaHeight) {
			this.protaRana.y += (int) viewHeight * 1 / 100;
		}
	}

	public Prota getRana() {
		return this.protaRana;

	}

	/**
	 * Clase rana,
	 * 
	 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
	 * 
	 */
	public class Prota extends Thread {

		boolean alive = true;
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
		long hitByMosquitoTime = 0;
		boolean hitByMosquito = false;
		boolean win = false;

		public Prota(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void run() {

			while (alive && !pause) {

				// Mientras el juego no esté pausado, comprueba las acciones de
				// la rana
				if (!pause) {

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

					// Simulates movement
					if (hitsByBalls == 0) {
						// Si ha sido tocado por un mosquito coge una imagen, si
						// no otra
						if (hitByMosquito) {
							this.inmune = true;
							if (generator.nextInt(100) < 80)
								bmp = res.ranaBmpHit;
							else
								bmp = res.ranaBmp2;
						} else {
							inmune = false;
							if (generator.nextInt(100) < 80)
								bmp = res.ranaBmp1;
							else
								bmp = res.ranaBmp2;
						}
					} else if (hitsByBalls == 1) {
						if (generator.nextInt(100) < 80)
							bmp = res.ranaBurn2;
						else
							bmp = res.ranaBurn3;
					} else if (hitsByBalls == 2) {
						if (generator.nextInt(100) < 80)
							bmp = res.ranaBurn3;
						else
							bmp = res.ranaBurn4;
					} else if (hitsByBalls > 2) {
						protaRana.setDeadByFire(true);
						protaRana.setAlive(false);
					}

					try {
						sleep(40);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				UtilsMethods.updatePuntuacion(puntuacion, getContext());
			}

			while (this.y < viewHeight) {
				// Si ha sido eliminada, mueve en dirección Y hacia abajo
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

		public synchronized void setHitByMosquito() {
			life++;
			hitByMosquito = true;
			hitByMosquitoTime = System.currentTimeMillis();
			if (life == 3) {
				this.dieByMosquito = true;
				this.setAlive(false);
			}
		}

		public synchronized void setAlive(boolean alive) {
			this.alive = alive;
		}

		public void setDeadByFire(boolean b) {
			this.dieByFire = b;
		}

	}

	public synchronized void setRanaMoving(boolean moving) {
		protaRana.mooving = moving;
		protaRana.cont = 0;
	}

	/**
	 * Clase Mosquito, que representa un objeto que se mueve, y extiende de
	 * Thread.
	 * 
	 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
	 * 
	 */
	public class Mosquito extends Thread {
		int x = viewWidth;
		int y;
		Bitmap bmp = res.mosquito1;
		int mWdith = bmp.getWidth();
		int mHeight = bmp.getHeight();
		int velocidad = generator.nextInt(100) + 50;
		boolean deadByBall = false;
		boolean alive = true;

		boolean imageChanged = true;

		public Mosquito(int y) {
			this.y = y;
		}

		@Override
		public void run() {

			while (alive && !pause) {

				if (!pause) {

					// Imprime una imagen u otra, según el estado en el que
					// esté.
					if (deadByBall) {
						if (generator.nextInt() < 80) {
							if (imageChanged)
								this.bmp = res.mosquitoDie1;
							imageChanged = false;
						} else {
							/*
							 * boleano para saber si la imagen ha cambiado y no
							 * cambiar el recurso todas las veces que pase por
							 * este bucle
							 */
							imageChanged = true;
							this.bmp = res.mosquitoDie2;
						}

					} else {
						// Genera sensación de movimiento, alternando entre dos
						// imágenes.
						if (generator.nextInt() < 80) {
							this.bmp = res.mosquito1;
						} else {
							this.bmp = res.mosquito2;
						}
						int randomMoveY = generator.nextInt(100);
						if (randomMoveY < 80) {
							// Do nothing
						} else if (randomMoveY < 90) {
							this.x += 1;
						} else if (randomMoveY < 95 && this.y > 0) {
							this.y -= (generator.nextInt(3) + 1);
						} else if (randomMoveY >= 95
								&& (this.y + this.mHeight) < viewHeight) {
							this.y += (generator.nextInt(3) + 1);
						}
					}

					if (this.deadByBall)
						this.y += viewHeight / 100 * 5;
					else if (this.x > -mWdith)
						this.x -= (int) viewWidth / 100 * 0.25;

					if (this.x < 5)
						this.x -= 2;

					try {
						sleep(velocidad);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (this.x < -this.mWdith) {
						this.setMosquitoDead();
						this.y = new Random().nextInt(viewHeight);
					} else if (this.y > viewHeight)
						this.setMosquitoDead();

				}
			}

			super.run();
		}

		public synchronized void setMosquitoDead() {
			this.alive = false;
		}

		public synchronized void setMosquitoDeadByBall() {
			this.deadByBall = true;

		}
	}

	/**
	 * Clase del mosquito jefe, el último enemigo de la pantalla. Tiene la
	 * habilidad especial de tirar bolas de fuego
	 * 
	 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
	 * 
	 */
	public class MosquitoJefe extends Thread {
		int x = viewWidth;
		int y;
		Bitmap bmp = res.mosquitoJefe2;
		int mWdith = bmp.getWidth();
		int mHeight = bmp.getHeight();
		int velocidad = generator.nextInt(100) + 50;
		boolean deadByBall = false;
		boolean alive = true;

		boolean imageChanged = true;
		boolean movingDown = false;
		boolean mosquitoOut = false;
		int lifes = 25;
		int maxFireballs = 7;
		long time;
		long hitTime = 100;
		boolean hited = false;
		boolean firing = false;
		boolean inmune = true;
		long fireTime = 500;
		long fireTimeStart = 0;
		FireBall fireBall;

		/**
		 * Constructor de la clase
		 * 
		 * @param y
		 *            altura a la que saldrá el objeto
		 */
		public MosquitoJefe(int y) {
			this.y = y;
		}

		public synchronized void setHitByBall() {
			this.lifes--;
			// Get the current time to set a new picture while mosquito is
			// hitted
			this.hited = true;
			this.time = System.currentTimeMillis();
			if (lifes == 0) {

				this.setMosquitoDeadByBall();
				dropMoney();
			}

		}

		/**
		 * Método para sotar la moneda una vez que el mosquito muere
		 */
		private void dropMoney() {

			moneda = new Moneda(res.moneda1.getWidth(), mosquitoJefe.x,
					mosquitoJefe.y, 50);
			monedaOut = true;

		}

		@Override
		public void run() {

			while (alive && !pause) {

				if (!pause) {

					if (!deadByBall) {
						if (!hited) {
							if (generator.nextInt() < 80) {
								this.bmp = res.mosquitoJefe2;
							} else {
								this.bmp = res.mosquitoJefe3;
							}

							if (generator.nextInt(1000) < 50
									&& mosquitoJefe.mosquitoOut
									&& fireBalls.size() < maxFireballs) {

								fireBall = new FireBall(this.x, this.y + 10,
										Constantes.fireballRadius, viewWidth,
										res.fireball7);
								fireBall.timeStarted = System
										.currentTimeMillis();
								fireBall.moving = false;
								fireBall.start();
								fireBalls.add(fireBall);
							}

						} else {
							this.bmp = res.mosquitoJefeHit;
							if ((Math.abs(System.currentTimeMillis()
									- this.time) > hitTime)) {
								hited = false;
							}
						}

						// Sale hacia la pantalla
						if (this.x > viewWidth - viewWidth / 4)
							this.x -= 5;
						else {
							// Marca al mosquito como que ya ha salido y se
							// muestra en pantalla
							mosquitoOut = true;

							if (!this.firing) {
								if (this.y <= 0)
									movingDown = false;
								else if (this.y >= viewHeight
										- (this.mHeight / 2))
									movingDown = true;
							}

						}

						if (!this.deadByBall) {
							if (movingDown && mosquitoOut)
								this.y -= 1 * viewHeight / 100;
							else if (mosquitoOut)
								this.y += 1 * viewHeight / 100;
						}

						// Cambia el movimiento del jefe aleatoriamente
						if (generator.nextInt(1000) < 15)
							if (movingDown)
								movingDown = false;
							else
								movingDown = true;

					} else {
						this.y += 2 * viewHeight / 100;
						if (generator.nextInt(100) < 80)
							this.bmp = res.mosquitoJefeDie1;
						else
							this.bmp = res.mosquitoJefeDie2;
					}

					try {
						sleep(velocidad);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// Comprueba que no haya salido de los lÃ­mites
					if (this.y > viewHeight)
						this.setMosquitoDead();

				}
			}

			super.run();

		}

		public synchronized void setMosquitoDead() {
			this.alive = false;
		}

		public synchronized void setMosquitoDeadByBall() {
			this.deadByBall = true;
			goToNextStage();
			stopMusic();

		}
	}

	/**
	 * Cuando el mosquito jefe sale por la pantalla, le acompaña una música.
	 */
	public void playAudioMosquitoJefe() {
		try {
			if (m.isPlaying()) {
				m.stop();
				m.release();
				m = new MediaPlayer();
			}

			AssetFileDescriptor descriptor = getResources().getAssets().openFd(
					"mosquitojefeaudio.mp3");
			m.setDataSource(descriptor.getFileDescriptor(),
					descriptor.getStartOffset(), descriptor.getLength());
			descriptor.close();

			m.prepare();
			m.setVolume(1f, 1f);
			m.setLooping(true);
			m.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Para la música, si ésta está sonando
	 */
	public synchronized void stopMusic() {
		try {
			if (m.isPlaying()) {
				m.stop();
				m.release();
				m = new MediaPlayer();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Método que hace que la rana se desplace hacia la siguiente pantalla
	 */
	public void goToNextStage() {

		new Thread() {

			// screenCleaned = false;

			@Override
			public void run() {
				// Comprueba si la pantalla estÃ¡ limpia de mosquitos.
				while (!screenCleaned) {
					if (!mosquitoJefe.alive)
						screenCleaned = true;

					for (Mosquito m : mosquitos) {
						if (m.isAlive()) {
							screenCleaned = false;
							break;
						}
					}

					try {
						sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				stageFinished = true;

				// Saca la rana de la pantalla
				while (protaRana.x < viewWidth) {
					protaRana.x += (int) viewWidth * 2 / 100;
					protaRana.win = true;

					try {
						sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			};

		}.start();
	}

	public MediaPlayer getMediaPlayer() {
		return this.m;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	/**
	 * Finaliza todos los threads que haya en la pantalla
	 */
	public synchronized void finish() {
		protaRana.alive = false;

		exitedGame = true;

		for (Mosquito m : mosquitos) {
			m.setMosquitoDead();
		}

		for (Disparo d : disparos) {
			d.alive = false;
		}

		if (mosquitoJefe != null && mosquitoJefe.alive)
			mosquitoJefe.alive = false;

		res = null;

		// this.destroyAll();

	}

	/**
	 * Clase BotonesMovimiento que controla si se ha tocado algún boton, y hace lo correspondiente 
	 * con el objeto en movimiento al que está vinculado, en este caso, la rana.
	 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
	 *
	 */
	public class BotonesMovimiento extends Thread {

		Movimientos m;

		public BotonesMovimiento(Movimientos moves) {
			m = moves;
		}

		public void run() {

			while (!pause) {
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

		};

	}

	public void destroyAll() {
		this.finish();
	}

	public boolean isReadyToPlay() {
		return readyToPlay;
	}

	public void setReadyToPlay(boolean readyToPlay) {
		this.readyToPlay = readyToPlay;
	}

}
