import java.util.Scanner;

public class BaseballUI {
	private BaseballGame baseballGame;
	private Scanner scan;
	
	public BaseballUI() {} // ����� ���� ����
	
	public void beginUI() {
		int inputNum = 0;	//����� �Է� ����
		boolean result;
		baseballGame = new BaseballGame();
		scan = new Scanner(System.in);
		System.out.println("���� �߱� ������ �����մϴ�.");
		System.out.println("���ڸ� ���ڰ� ��ġ�ϸ� Home Run!");
		System.out.println("���ڿ� �ڸ����� ������ Strike,");
		System.out.println("���ڸ� ���߸� Ball,");
		System.out.println("��� �ٸ��� Out �Դϴ�.");
		System.out.println();
		
		while(true) {
			System.out.println("�ߺ����� �ʴ� ���ڸ� ���ڸ� �Է��ϼ���.");
			inputNum = scan.nextInt();
			result = baseballGame.attack(inputNum);
			if ( result == true ) {
				System.out.println("�����մϴ�. ������ �����մϴ�.");
				break;
			}
		}
		scan.close();
	}
}