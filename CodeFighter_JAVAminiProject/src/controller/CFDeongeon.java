package controller;

import java.util.Random;
import java.util.Scanner;

import model.CharacterDTO;
import model.ItemDAO;
import model.ItemDTO;

public class CFDeongeon {
	Scanner sc = new Scanner(System.in);
	Random rd = new Random();
	ItemDAO idao = new ItemDAO();
	
	public CharacterDTO start(CharacterDTO dto) {
		// 던전입장 시작
		// 난이도선택
		// 던전 입장 메시지 출력
		// 지도 보여주기
		// 던전 메뉴 선택 반복
		
		
		// 난이도선택 메뉴 보여주기
		System.out.println("//난이도선택");
		int level = sc.nextInt();
		// 난이도에 따른 조정
		// 지도 크기 선정
		int[][] map = new int[2][3];

		// 보스몹 위치 선정
		int bx = 1, by = 0;

		// 던전 입장 메시지 출력
		System.out.println("던전 입장 환영 문구 출력");

		// 지도 보여주기
		System.out.println("던전 지도 보여주기");

		// 현재 위치
		int nx = 0, ny = 0;

		// 던전 메뉴 선택
		while (true) {
			System.out.println("//던전메뉴");
			int choice = sc.nextInt();
			// 동,서,남,북 에 따른 nx,ny 이동
			if (choice == 1) {
				nx += 1;
			}

			// 몬스터 출현 확률 설정
			int isMon = rd.nextInt(10);

			int event = 0;

			if (nx == bx && ny == by) {
				// 보스방
				System.out.println("//보스방");
				event = 2;
			} else if (isMon > 5) {
				System.out.println("//일반몹 만남");
				// 몬스터 만남
				event = 1;
			} else {
				System.out.println("//아무이벤트 없음");
				// 아무 이벤트 없음
				event = 0;
			}

			if (event > 0) {
				dto = monsterFight(dto);
				if(event == 2) {
					break;
				}
			}

		}
		return dto;
	}

	private CharacterDTO monsterFight(CharacterDTO dto) {
		while (true) {
			// 전투메뉴선택
			System.out.println("//전투메뉴 선택");
			Monster m = new Monster("이름", 'c');
			int choice = sc.nextInt();
			if (choice == 1) {
				// 1.공격
				System.out.println("//공격");
				// 몬스터와 작용
				System.out.println("//몬스터 작용중");
				m.hp=0;
			}

			if (dto.getHp() <= 0) {
				System.out.println("사용자 죽음.");
				// 사용자 죽음.
				break;
			} else if (m.hp <= 0) {
				System.out.println("몬스터 죽음.");
				// 몬스터 죽음.
				if (m.itemno > 0) {
					System.out.println("아이템 떨굼.");
					dto.addInven(idao.getItem(m.itemno));
					// 아이템 떨굼.
				} else {
					System.out.println("아이템 없음.");
					// 아이템 없음.
				}
				break;
			}
		}
		return dto;
	}

}
