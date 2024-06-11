package controller;

import java.util.Scanner;

import model.CharacterDAO;
import model.CharacterDTO;
import model.ItemDAO;
import model.ItemDTO;

public class CFEquipment {
	Scanner sc = new Scanner(System.in);
	ItemDAO idao = new ItemDAO();
	CharacterDAO dao = new CharacterDAO();
	
	public CharacterDTO start(CharacterDTO dto) {
		
		//장비확인
		
		while(true) {
			//장비 보여주기
			System.out.println("//장비보여주기(착용+인벤토리)");
			//착용장비
			//인벤장비
			
			//장비메뉴선택
			System.out.println("//장비 메뉴선택");
			int choice =sc.nextInt(); 

			if(choice == 1){
				//장비장착
				System.out.println("//장비 장착 - 인벤토리 장비선택");
				
				//인벤토리 장비 선택
				ItemDTO idto = getItem(dto);
				if(idto == null) {
					System.out.println("인벤토리의 장비가 아닙니다.");
					continue;
				}
				
				//기존 부위에 장비가 장착되어있는가?
				//장비 변경할것인가?
				dto = equipmentItem(idto,dto);
				
				//서버에 저장
				dao.save(dto);
			}else if(choice == 2){
				//장비버리기
				System.out.println("//장비 버리기");
				dto = delEquipment(dto);
				
				//서버에 저장
				dao.save(dto);
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

	private CharacterDTO delEquipment(CharacterDTO dto) {
		//장비 삭제
		System.out.println("//장비 삭제 메뉴");
		int choice = sc.nextInt();
		if(choice == 1) {
			//인벤토리 1개 삭제
			//인벤토리에서 아이템 선택
			ItemDTO idto = getItem(dto);
			
			//인벤토리에서 삭제 하기 
			dto = delItemInven(dto, idto);
			
		}else if(choice ==2) {
			//인벤토리 전부 삭제
			dto = delItemInven(dto);
		}
		
		return dto;
	}

	private CharacterDTO equipmentItem(ItemDTO idto, CharacterDTO dto) {
		ItemDTO tempDto = null;
		boolean isChange = false;
		//아이템 장착하기
		if(idto.getPart() == 0) {
			//머리
		}else if(idto.getPart() == 1) {
			//몸
		}else if(idto.getPart() == 2) {
			//무기
			
			if(dto.getWpArm() == 0) {
				//무기 장착완료
				isChange = true;
				dto.setWpArm(idto.getNo());
			}else {
				//무기를 착용하고 있다면 물어보고 바꾸기
				System.out.println("//무기바꿀꺼?");
				int choice = sc.nextInt();
				if(choice == 1) {
					isChange = true;
					tempDto = idao.getItem(dto.getWpArm());
					dto.setWpArm(idto.getNo());					
				}else {
					//무기 안바꿈.
				}
			}
			
		}
		
		//무기착용했으면 캐릭터 데이터 변경하기.
		//dto인벤에서 무기 제거하기.
		if(tempDto != null) {
			//tempDto는 기존의 캐릭터가 갖고있던 무기 적용되었던 값을 빼기
			int hp = dto.getHp() - tempDto.getHp();
			dto.setHp(hp);
			int mp = dto.getMp() - tempDto.getMp();
			dto.setMp(mp);
			int attack = dto.getAttack() - tempDto.getAttack();
			dto.setAttack(attack);
			int defense = dto.getDefense() - tempDto.getDefense();
			dto.setDefense(defense);
		}
		if(isChange == true) {
			int hp = dto.getHp() + idto.getHp();
			dto.setHp(hp);
			int mp = dto.getMp() + idto.getMp();
			dto.setMp(mp);
			int attack = dto.getAttack() + idto.getAttack();
			dto.setAttack(attack);
			int defense = dto.getDefense() + idto.getDefense();
			dto.setDefense(defense);
			
			dto = delItemInven(dto,idto);
		}
		
		return dto;
	}

	private ItemDTO getItem(CharacterDTO dto) {
		//인벤토리에서 아이템 가져오기
		String inven = dto.getInven();
		System.out.println("인벤토리에서 아이템 가져오기");
		String sel = sc.next();
		ItemDTO idto = null;
		for(String x : inven.split("/")) {
			if (x.equals(sel)) {
				idto = idao.getItem(Integer.valueOf(sel));
				break;
			}
		}
		return idto;
	}
	
	private CharacterDTO delItemInven(CharacterDTO dto,ItemDTO idto) {
		String inven = dto.getInven();
		inven = inven.replace(idto.getNo()+"/","");
		dto.setInven(inven);
		return dto;
	}
	private CharacterDTO delItemInven(CharacterDTO dto) {
		String inven = "";
		dto.setInven(inven);
		return dto;
	}

}
