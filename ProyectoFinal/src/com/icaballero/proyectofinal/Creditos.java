package com.icaballero.proyectofinal;

import com.duhnnae.proyectofinal.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * La clase de los Creditos con el nombre de los autores.
 * 
 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
 *
 */
public class Creditos extends Activity {
	
	private final int MAX_SCORE=500;
	TextView puntuacion;
	Button back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creditos);
		
		//Recibir el Bundle con la puntuacion de la LoadingScreen
		Bundle bundle = Creditos.this.getIntent().getExtras();
		int i = bundle.getInt("puntuacion");
		
		puntuacion=(TextView)findViewById(R.id.tvhardendscore);
		puntuacion.setText(getString(R.string.hard_points) + i + "/" + MAX_SCORE);
		
		back=(Button)findViewById(R.id.bthardendback);
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentLoading = new Intent(Creditos.this, MainActivity.class);
				Creditos.this.startActivity(intentLoading);
    			Creditos.this.finish();
				
			}
		});
	}


}
