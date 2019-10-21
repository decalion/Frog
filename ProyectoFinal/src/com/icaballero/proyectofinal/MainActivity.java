package com.icaballero.proyectofinal;

import com.duhnnae.proyectofinal.R;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
/**
 * Actividad principal del programa, donde da inicio el juego.
 * En el proceso de prácticas, estan disponibles las tres pantallas. En la versión final, sólamente 
 * estará el botón de jugar.
 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
 *
 */
public class MainActivity extends Activity {

	Button buttonStart;
	LoadingActivity lactiv;
	MediaPlayer m = new MediaPlayer();
	Bundle bundle = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //buttonStart = (Button) findViewById(R.id.buttonStart);
        Bitmap riprana1 = BitmapFactory.decodeResource(getResources(), R.drawable.riprana1);
        Drawable d = new BitmapDrawable(getResources(),riprana1);
        setContentView(R.layout.activity_main);
        playAudio();
    }

    /**
     * Para la música, y abre la siguiente actividad 
     * @param view
     */
	public void goGameActivity(View view){
    	try{
    		
    		bundle.putInt("bundle", 1);
    		 Intent intentLoading = new Intent(MainActivity.this, LoadingActivity.class);
    		intentLoading.putExtra("name", 1);
    		 //Intent intent = new Intent(this, GameActivity.class);
    		
    		MainActivity.this.startActivity(intentLoading);  
    		stopMusic();
    		
    			
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
	
	/**
	 * Abre la pantalla de loading, y se dirige hacia la pantalla 2
	 * @param view
	 */
	public void goPantallados(View view){
		//final Intent intentLoading = new Intent(this, GameActivity2.class);
		Intent intentLoading = new Intent(MainActivity.this, LoadingActivity.class);
		intentLoading.putExtra("name", 2);
		MainActivity.this.startActivity(intentLoading);  
		stopMusic();
	}
	
	/**
	 * Abre la pantalla loading, y se dirige hacia la pantalla 3
	 * @param view
	 */
	public void goNormalevel(View view){
		Intent intentLoading = new Intent(MainActivity.this, LoadingActivity.class);
		intentLoading.putExtra("name", 3);
		MainActivity.this.startActivity(intentLoading);  
		stopMusic();
		
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /**
     * Inicia la música de la pantalla de bienvenida
     */
    public void playAudio() {
        try {
            if (m.isPlaying()) {
                m.stop();
                m.release();
                m = new MediaPlayer();
            }

            AssetFileDescriptor descriptor = getAssets().openFd("ranaintro.mp3");
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            m.prepare();
            m.setVolume(1f, 1f);
            m.setLooping(true);
            m.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void onPause() {
    	try{
    	if(m.isPlaying()){
    	m.stop();
    	m.release();
    	}
    	}catch(Exception e){
    		
    	}
    	super.onPause();
    }
    
    @Override
    protected void onDestroy() {
    	try{
    	if(m.isPlaying()){
    		m.stop();
    		m.release();
    	}
    	}catch(Exception e){
    		
    	}
    	super.onDestroy();
    }
    
    @Override
    protected void onResume() {
    	playAudio();
    	super.onResume();
    }
    
    public void stopMusic(){
    	try{
    	if(m.isPlaying()){
    		m.stop();
    		m.release();
    	}
    	}catch(Exception e){
    		
    	}
    }
    
    
}
