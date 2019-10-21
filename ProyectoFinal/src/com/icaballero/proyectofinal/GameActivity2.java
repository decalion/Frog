package com.icaballero.proyectofinal;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.duhnnae.proyectofinal.R;
import com.icaballero.proyectofinal.CustomViews.NormalLevel;
import com.icaballero.proyectofinal.CustomViews.NormalLevel.Rana;
/**
 * Actividad de la segunda pantalla. Contiene un customView que dibuja los objetos en la pantalla, y los controles necesarios para controlar al protagonista
 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
 *
 */
public class GameActivity2 extends Activity {
	//Instancia la CustomView
	NormalLevel vista;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		vista = (NormalLevel) findViewById(R.id.myView1);

	}

	@Override
	protected void onPause() {

		// Si queda algun Thread Vivo lo Elimina
		System.exit(0);

		Log.d("aaaa", "onPause");
		// finish();
		super.onPause();

	}

	@Override
	protected void onStop() {

		Log.d("aaaa", "onStop");
		super.onStop();

	}

	@Override
	protected void onDestroy() {

		finish();
		super.onDestroy();
	}

}
