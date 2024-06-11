package controller;

import java.util.Scanner;

import model.CharacterDAO;
import model.CharacterDTO;
import model.FightArenaDAO;
import model.FightArenaDTO;

public class CFFightArena {
	Scanner sc = new Scanner(System.in);
	FightArenaDAO fdao = new FightArenaDAO();
	CharacterDAO dao = new CharacterDAO();
	
	public void start(CharacterDTO dto) {
		//격투장 입장
		System.out.println("격투장 입장 환영");
		
		//격투장 대기 인원 조회
		int userCount = fdao.isJoin();
		if(userCount ==2) {
			System.out.println("격투장이 사용중입니다.");
			return;
		}
		
		//DB에 격투장 입장 올리기.
		int row = fdao.join(dto);
		if(row == 0) {
			System.out.println("격투장 입장에 실패 하였습니다.");
			return;
		}
		
		//격투장 입장 성공
		System.out.println("격투장 입장에 성공하였습니다.");
		
		//격투장 인원수가 2명이 될때까지 기다리기.
		while(userCount != 2) {
			userCount = fdao.isJoin();
			try {
				System.out.println("//1초 기다리기....");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		System.out.println("결투를 시작합니다.");
		
		//싸움 시작
		fightBegin(dto);
		
		
	}

	private void fightBegin(CharacterDTO dto) {
		CharacterDTO enemy = fdao.getEnemy(dto);
		FightArenaDTO fdto = new FightArenaDTO(dto.getId());
		FightArenaDTO fenemy = new FightArenaDTO(enemy.getId());
		while(true) {
			
			//결투 메뉴 보여주기
			System.out.println("//결투 메뉴");
			int choice = sc.nextInt();
			if(choice ==1) {
				//공격 
				System.out.println("//공격 선택");
				fdto.setEnter(1);
			}else if(choice ==2) {
				//방어
				fdto.setEnter(2);
			}else if(choice ==3) {
				//스킬
				fdto.setEnter(3);
			}
			
			//서버에 업데이트
			fdao.update(fdto);
			
			//상대방 기다리기
			while (fdao.isReady(fdto)==false) {
				//상대방 1초씩 기다리기;
				System.out.println("상대방 기다리기 ...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//데이터 적용하기.
			fenemy = fdao.getEnemy(fenemy);
			
			//상호 공격 /방어 / 스킬 적용해서 값 적용하기
			int hp = dto.getHp() - enemy.getAttack();
			dto.setHp(hp);
			int enemyHp = enemy.getHp()-50;
			enemy.setHp(enemyHp);
			
			if(hp<=0 && enemyHp<=0) {
				//무승부
				break;
			}else if(enemyHp<=0) {
				//이김
				System.out.println("//이겼다!");
				break;
			}else if(hp <=0) {
				//졌음
				break;
			}
			
		}
	}
	
}
