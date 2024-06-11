package view;

import java.util.Scanner;

import model.CharacterDTO;

public class InitView {
	Scanner sc = new Scanner(System.in);
	
	public CharacterDTO join() {
		System.out.println("//회원가입");
		String id = sc.next();
		String pw = sc.next();
		String name = sc.next();
		String job = sc.next();
		return new CharacterDTO(id,pw,name,job);
	}

	public CharacterDTO login() {
		System.out.println("//로그인");
		String id = sc.next();
		String pw = sc.next();
		
		return new CharacterDTO(id,pw);
	}

}
