package controller;

import java.util.Scanner;

import model.CharacterDAO;
import model.CharacterDTO;
import view.InitView;
import view.StatView;

public class CFLobby {
	Scanner sc = new Scanner(System.in);
	InitView initV = new InitView();
	StatView statV = new StatView();
	CharacterDAO dao = new CharacterDAO();

	public void start(CharacterDTO dto) {
		//로비시작
		CFDeongeon cfDeongeon = new CFDeongeon();
		CFEquipment cfEquipment = new CFEquipment(); 
		CFFightArena cfFightArena = new CFFightArena();
		
		while(true) {
			System.out.println("//로비메뉴 ");
			int choice = sc.nextInt();
			if (choice == 1) {
				// 던전입장
				System.out.println("//던전입장");
				dto = cfDeongeon.start(dto);
				//체력회복,마나회복
				dto.setHp(100);
				
				//서버에 저장.
				dto = dao.save(dto);
			}else if (choice == 2) {
				// 장비확인
				System.out.println("//장비확인");
				dto = cfEquipment.start(dto);
			}else if (choice == 3) {
				// 스탯확인
				System.out.println("//스탯확인");
				statV.showStat(dto);
			}else if (choice == 4) {
				// 격투장
				System.out.println("//격투장");
				cfFightArena.start(dto);
			}else if (choice == 5) {
				// 종료
				System.out.println("//종료");
			}else {
				//잘못누름
			}
		}
		
	}
}
