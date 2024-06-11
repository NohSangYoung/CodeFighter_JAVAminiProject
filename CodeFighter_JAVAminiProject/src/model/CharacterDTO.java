package model;

public class CharacterDTO {
	
	private String id;
	private String pw;
	
	private String name;
	private String job;
	private int hp;
	private int mp;
	private int attack;
	private int defense;
	
	private int wpHead;
	private int wpBody;
	private int wpArm;
	private String inven;
	
	public CharacterDTO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
	
	public String getInven() {
		return inven;
	}

	public void setInven(String inven) {
		this.inven = inven;
	}
	
	public void addInven(ItemDTO idto) {
		this.inven += idto.getNo()+"/";
	}
	

	public CharacterDTO(String id, String pw, String name, String job) {
		this(id,pw);
		this.name = name;
		this.job = job;
	}

	

	public void setId(String id) {
		this.id = id;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public void setWpHead(int wpHead) {
		this.wpHead = wpHead;
	}

	public void setWpBody(int wpBody) {
		this.wpBody = wpBody;
	}

	public void setWpArm(int wpArm) {
		this.wpArm = wpArm;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getName() {
		return name;
	}

	public String getJob() {
		return job;
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

	public int getWpHead() {
		return wpHead;
	}

	public int getWpBody() {
		return wpBody;
	}

	public int getWpArm() {
		return wpArm;
	}
}
