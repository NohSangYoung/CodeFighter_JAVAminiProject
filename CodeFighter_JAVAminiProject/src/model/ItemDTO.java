package model;

public class ItemDTO {
	private int no;
	private String name;
	private int part;
	private int hp;
	private int mp;
	private int attack;
	private int defense;
	
	
	public ItemDTO(int no, String name, int part, int hp, int mp, int attack, int defense) {
		super();
		this.no = no;
		this.name = name;
		this.part = part;
		this.hp = hp;
		this.mp = mp;
		this.attack = attack;
		this.defense = defense;
	}
	
	
	public int getNo() {
		return no;
	}
	public String getName() {
		return name;
	}
	public int getPart() {
		return part;
	}
	public int getHp() {
		return hp;
	}
	public int getMp() {
		return mp;
	}
	public int getAttack() {
		return attack;
	}
	public int getDefense() {
		return defense;
	}
	
	
}
