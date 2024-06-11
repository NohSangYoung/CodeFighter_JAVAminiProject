package controller;

import java.util.Scanner;

import model.CharacterDAO;
import model.CharacterDTO;
import view.InitView;

public class CFInit {
	Scanner sc = new Scanner(System.in);
	InitView initV = new InitView();
	CharacterDAO dao = new CharacterDAO();

	public CharacterDTO start() {
		CharacterDTO dto = null;
		while (true) {
			System.out.println("//초기메뉴 ");
			int choice = sc.nextInt();
			if (choice == 1) {
				// 로그인
				dto = initV.login();
				dto = dao.login(dto);
				System.out.println("로그인 성공");
				break;
			} else if (choice == 2) {
				// 회원가입
				// 회원 정보 기입
				dto = initV.join();
				// 회원 정보 dao에 전달
				int result = dao.join(dto);
				if(result >0) {
					//회원가입 성공
					System.out.println("success");
				}else {
					//회원가입 실패
					System.out.println("fail");
				}
			}
		}
		return dto;
	}

}
