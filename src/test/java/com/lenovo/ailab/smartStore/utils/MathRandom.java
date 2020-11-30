package com.lenovo.ailab.smartStore.utils;

/**
 * Title: MathRandom.java
 * 
 * @Autohr "chenpeng"
 * @data 2019年10月23日
 * @Email chenpeng10@lenovo.com
 * @description:
 **/
public class MathRandom {

	public static void main(String[] args) {


		int max10 = 1500, min10 = 500;
		int max11 = 2000, min11 = 4000;
		int max12 = 4000, min12 = 6500;
		int max13 = 9000, min13 = 7500;

		int max20 = 3500, min20 = 2500;
		int max21 = 2000, min21 = 4000;
		int max22 = 3000, min22 = 5000;
		int max23 = 9000, min23 = 6500;

		int max30 = 6500, min30 = 4500;
		int max31 = 4000, min31 = 2000;
		int max32 = 6000, min32 = 4000;
		int max33 = 9000, min33 = 6500;

		
		int ranX=0;
		int ranY=0;

		for (int i = 0; i < 4000; i++) {

			for (int j = 0; j < 10; j++) {
				ranX = (int) (Math.random() * (max30 - min30) + min30);
				ranY = (int) (Math.random() * (max33 - min33) + min33);
				if (Math.random() > 0.5) {
					System.out.println((ranX / 10000f) + "," + (ranY / 10000f) + "," + i);
				}
			}
			
			for (int j = 0; j < 9; j++) {
				ranX = (int) (Math.random() * (max30 - min30) + min30);
				ranY = (int) (Math.random() * (max32 - min32) + min32);
				if (Math.random() > 0.5) {
					System.out.println((ranX / 10000f) + "," + (ranY / 10000f) + "," + i);
				}
			}
			
			for (int j = 0; j < 8; j++) {
				ranX = (int) (Math.random() * (max30 - min30) + min30);
				ranY = (int) (Math.random() * (max31 - min31) + min31);
				if (Math.random() > 0.5) {
					System.out.println((ranX / 10000f) + "," + (ranY / 10000f) + "," + i);
				}
			}
			
			for (int j = 0; j < 7; j++) {
				ranX = (int) (Math.random() * (max20 - min20) + min20);
				ranY = (int) (Math.random() * (max23 - min23) + min23);
				if (Math.random() > 0.5) {
					System.out.println((ranX / 10000f) + "," + (ranY / 10000f) + "," + i);
				}
			}
			
			for (int j = 0; j < 6; j++) {
				ranX = (int) (Math.random() * (max20 - min20) + min20);
				ranY = (int) (Math.random() * (max22 - min22) + min22);
				if (Math.random() > 0.5) {
					System.out.println((ranX / 10000f) + "," + (ranY / 10000f) + "," + i);
				}
			}
			
			for (int j = 0; j < 5; j++) {
				ranX = (int) (Math.random() * (max20 - min20) + min20);
				ranY = (int) (Math.random() * (max21 - min21) + min21);
				if (Math.random() > 0.5) {
					System.out.println((ranX / 10000f) + "," + (ranY / 10000f) + "," + i);
				}
			}
			
			for (int j = 0; j < 4; j++) {
				ranX = (int) (Math.random() * (max10 - min10) + min20);
				ranY = (int) (Math.random() * (max13 - min13) + min13);
				if (Math.random() > 0.5) {
					System.out.println((ranX / 10000f) + "," + (ranY / 10000f) + "," + i);
				}
			}
			
			for (int j = 0; j < 4; j++) {
				ranX = (int) (Math.random() * (max10 - min10) + min20);
				ranY = (int) (Math.random() * (max12 - min12) + min12);
				if (Math.random() > 0.5) {
					System.out.println((ranX / 10000f) + "," + (ranY / 10000f) + "," + i);
				}
			}
			
			for (int j = 0; j < 4; j++) {
				ranX = (int) (Math.random() * (max10 - min10) + min20);
				ranY = (int) (Math.random() * (max11 - min11) + min11);
				if (Math.random() > 0.5) {
					System.out.println((ranX / 10000f) + "," + (ranY / 10000f) + "," + i);
				}
			}
		}
	}

}
