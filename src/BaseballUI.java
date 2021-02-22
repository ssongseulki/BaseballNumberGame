import java.util.Scanner;

public class BaseballUI {
	private BaseballGame baseballGame;
	private Scanner scan;
	
	public BaseballUI() {} // 사용할 일이 없음
	
	public void beginUI() {
		int inputNum = 0;	//사용자 입력 숫자
		boolean result;
		baseballGame = new BaseballGame();
		scan = new Scanner(System.in);
		System.out.println("숫자 야구 게임을 시작합니다.");
		System.out.println("세자리 숫자가 일치하면 Home Run!");
		System.out.println("숫자와 자리까지 같으면 Strike,");
		System.out.println("숫자만 맞추면 Ball,");
		System.out.println("모두 다르면 Out 입니다.");
		System.out.println();
		
		while(true) {
			System.out.println("중복되지 않는 세자리 숫자를 입력하세요.");
			inputNum = scan.nextInt();
			result = baseballGame.attack(inputNum);
			if ( result == true ) {
				System.out.println("축하합니다. 게임을 종료합니다.");
				break;
			}
		}
		scan.close();
	}
}