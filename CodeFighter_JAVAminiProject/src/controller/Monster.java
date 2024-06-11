package controller;

import java.util.Random;

public class Monster {
	String name;
	int hp;
	int attack;
	int defense;
	int itemno;
	char grade;
	
	public Monster(String name,char grade) {
		//몬스터 생성
		//등급에 따라 아이템 결정.
		Random rd = new Random();
		itemno = rd.nextInt(300);
		itemno = 0;
		
		this.grade = grade;
		
		this.name = name;
	}
}
