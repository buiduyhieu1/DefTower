package com.example.deftower;

import java.util.Random;

import android.util.Log;

public class Rectangle {
	
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	 public float x1, y1, x2, y2;
	 private int numOfDirection; 	//so huong co the re khi di vao hcn. max la 3
	 public int direction[];
	
	public Rectangle(float X1, float X2, float Y1, float Y2, int NumOfDirect, int Direc[]){
		x1 = X1;
		x2 = X2;
		y1 = Y1;
		y2 = Y2;
		numOfDirection = NumOfDirect;
		direction = Direc;
	}
	
	public boolean isInside(float x, float y){
		if (x >= x1 && x < x2 && y >= y1 && y < y2) return true;
		return false;
	}
	
	public int getway(){
		if (numOfDirection == 1) return direction[0];
		Random rd = new Random();
		int b = rd.nextInt(numOfDirection*10-1);
		Log.v("coin card",((b) + 1 ) / 10 +"");
		return direction[(b + 1 ) / 10];
	}

}
