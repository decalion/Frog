package com.icaballero.proyectofinal.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;

/**
 * Métodos útiles para utilizar en diferentes clases.
 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
 *
 */
public class UtilsMethods {
	
	/**
	 * Actualiza la puntuación del usuario
	 * @param puntuacion
	 * @param context
	 */
	public static void updatePuntuacion(int puntuacion,Context context){
		SharedPreferences prefs =
			     context.getSharedPreferences("prefs",Context.MODE_PRIVATE);
			 
			SharedPreferences.Editor editor = prefs.edit();
			editor.putInt("puntuacion", puntuacion);
			editor.commit();
	}
	
	

	/**
	 * Cambia el tamaño de un archivo de tipo Bitmap
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static BitmapFactory.Options changeBitmapSize(
	        int reqWidth, int reqHeight) { // BEST QUALITY MATCH

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    //BitmapFactory.decodeFile(path, options);

	    // Calculate inSampleSize
	        // Raw height and width of image
	        final int height = options.outHeight;
	        final int width = options.outWidth;
	        //options.inPreferredConfig = Bitmap.Config.RGB_565;
	        int inSampleSize = 1;

	        if (height > reqHeight) {
	            inSampleSize = Math.round((float)height / (float)reqHeight);
	        }

	        int expectedWidth = width / inSampleSize;

	        if (expectedWidth > reqWidth) {
	            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
	            inSampleSize = Math.round((float)width / (float)reqWidth);
	        }


	    options.inSampleSize = inSampleSize;

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;

	        return options;//BitmapFactory.decodeFile(path, options);
	  }
	
}
