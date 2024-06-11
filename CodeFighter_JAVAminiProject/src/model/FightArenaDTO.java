package model;

public class FightArenaDTO {
	private int enter;
	private int count;
	private String id;
	
	public FightArenaDTO(String id) {
		this.id = id;
		enter = 0;
		count = 0;
	}

	public int getEnter() {
		return enter;
	}

	public void setEnter(int enter) {
		this.count++;
		this.enter = enter;
	}

	public int getCount() {
		return count;
	}
	
	public String getId() {
		return id;
	}
}
