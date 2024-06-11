package controller;

import java.util.Scanner;

import model.CharacterDTO;

public class CFEquipment {
	
	public CharacterDTO start(CharacterDTO dto) {
		Scanner sc = new Scanner(System.in);
		//장비확인
		
		while(true) {
			//장비 보여주기
			System.out.println("//장비보여주기(착용+인벤토리)");			//착용장비
			//인벤장비
			
			//장비메뉴선택
			System.out.println("//장비 메뉴선택");
			int choice =sc.nextInt(); 

			if(choice == 1){
				//장비장착
				System.out.println("//장비 장착");
				//인벤토리 장비 선택
				
			}else if(choice == 2){
				//장비버리기
				System.out.println("//인벤비우기");
			}else if (choice ==3) {
				//돌아가기
				System.out.println("//돌아가기");
				break;
			}else {
				//잘못누름
			}
			
			
		}
		return dto;
	}

}
