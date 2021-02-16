import java.util.Random;

public class BaseballGame {
	private final int MAX = 3;
	private int randomNum;	
	private int[] randomNumArr, inputNumArr;	//������ �Է°��� �迭
	private int startNum, endNum;
	private Random random;
	private int sCnt, bCnt;	//Strike, Ball ����
	
	public BaseballGame() {
		random = new Random();
		randomNumArr = new int[MAX];	
		inputNumArr = new int[MAX];
		setRange();
		randomNumMake();
		System.out.println(randomNum);		//����� �뵵�� �ֿܼ� ���� ���
	}
	
	public void setRange() {	//startNum = 100, endNum = 1000-1, 999
		startNum = 1;
		for(int i = 1 ; i < MAX ; i++ ) 
			startNum *= 10;
		endNum = startNum * 10 - 1;
	}
	
	public boolean chkRange(int inputNum) {	
		//����ڰ� �Է� �� üũ. startNum ~ endNum �̿��� ���ڴ� false
		if ( inputNum < startNum || inputNum > endNum )
			return false;
		else 
			return true;
	}
	
	public void randomNumMake() {
		while(true) {
			randomNum = random.nextInt(endNum - startNum + 1) + startNum; 
			//random.nextInt(num) = 0 ~ num-1�̹Ƿ� 0 ~ 99 ������ ����  + 100. 100���ڸ� ����
			makeArr(randomNum, randomNumArr);	//���ڸ� ���� �迭�� �����
			if(chkNum(randomNumArr)) return;	//���ڸ��� ���� ���ڰ� ������ false. �ٽ� while�� ����
		}
	}
	
	public void makeArr(int num, int[] arr) {	
		int divisor = 1;
		for ( int i = 1 ; i < arr.length ; i++ )	//arr.length = 3
			divisor *= 10;			//divisor = 100
		for ( int i = 0 ; i < arr.length ; i++) {
			arr[i] = num / divisor;		// arr[i] �ڸ� ��
			num %= divisor;		// num % 100 >> num % 10 >> num % 1
			divisor /= 10;		// 100 / 10 >> 10 / 10 >> 1 / 10
		}
	}
	
	public boolean chkNum(int[] arr) {
		for ( int i = 0 ; i < arr.length-1 ; i++ ) {	//0, 1 ��° �ڸ�
			for ( int j = i + 1 ; j < arr.length ; j++ ) {	//1, 2��° �ڸ�
				if ( arr[i] == arr[j] ) 	
					return false;
				// 0��° == 1��°, 2��° / 1��° == 2��° �� ���ڰ� ������ false
			}
		}
		return true;
	}
	
	public boolean attack(int inputNum) {
		boolean result; 
		result = chkRange(inputNum);	//startNum ~ endNum ������ �������� ��ȿ�� Ȯ��
		if ( result = false )
			showResult(result); 	//chkRange���� ��ȿ�� ���ڰ� �ƴҰ�� �޽���
		else {
			makeArr( inputNum , inputNumArr );	//inputNum ���ڸ� �迭�� �����
			result = chkNum(inputNumArr);	//inputNum ���ڸ� �� �ߺ��� ���� üũ
			if( result == true ) {
				chkGame(); 		//randomNum�� inputNum ���ڿ� �ڸ� ��
				showResult(result);		//��� ���
			}
			else
				showResult(result); //�ߺ��� ���� ���� �� false, ��� ���
		}
		if ( sCnt == MAX )
			return true;
		else
			return false;
			
	}
	
	public void chkGame() {
		int sCnt = 0 , bCnt = 0;
		for ( int i = 0 ; i < randomNumArr.length ; i++ ) {
			for ( int j = 0 ; j < inputNumArr.length ; j++ ) {
				if ( randomNumArr[i] == inputNumArr[j] ) {
					if ( i == j ) 
						sCnt++;
					else bCnt++;
				}
			}
		}
		this.sCnt = sCnt;
		this.bCnt = bCnt;
	}
	
	public void showResult(boolean result) {
		if ( result == true) {
			if ( sCnt == MAX ) 
				System.out.println("Home Run!!");
			else if ( sCnt == 0 && bCnt == 0 )
				System.out.println("Out");
			else
				System.out.println( sCnt + " Strike " + bCnt + " Ball");
		}
		else
			System.out.println("��ȿ�� ���ڰ� �ƴմϴ�.");
	}
}














