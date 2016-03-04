package com.example.an_uilayout;

public class Fruit {
	private String name;
	private int imageId;
	public Fruit(String name,int imageId){
		this.name=name;
		this.imageId=imageId;
	}
	public String getName(){
		return name;
	}
	public int getImangeId(){
		return imageId;
	}
}
