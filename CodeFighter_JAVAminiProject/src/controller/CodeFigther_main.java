package controller;

import java.util.Scanner;

import model.CharacterDAO;
import model.CharacterDTO;
import view.InitView;

public class CodeFigther_main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CFInit cfInit = new CFInit();
		CFLobby cfLobby = new CFLobby();
		CharacterDTO dto = null;
		
		while(true) {
			dto = cfInit.start();
			cfLobby.start(dto);
		}
	}

}
