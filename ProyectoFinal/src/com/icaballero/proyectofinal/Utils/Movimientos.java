package com.icaballero.proyectofinal.Utils;

public class Movimientos {
	
	private boolean movingUp = false;
	private boolean isButtonPressed = false;
	private boolean stopped = true;
	
	public Movimientos(){
		
	}

	public boolean isMovingUp() {
		return movingUp;
	}

	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

	public boolean isButtonPressed() {
		return isButtonPressed;
	}

	public void setButtonPressed(boolean isButtonPressed) {
		this.isButtonPressed = isButtonPressed;
	}

	public boolean isStopped() {
		return stopped;
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}

	
}
