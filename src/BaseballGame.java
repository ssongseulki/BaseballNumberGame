import java.util.Random;

public class BaseballGame {
	private final int MAX = 3;
	private int randomNum;	
	private int[] randomNumArr, inputNumArr;	//난수와 입력값의 배열
	private int startNum, endNum;
	private Random random;
	private int sCnt, bCnt;	//Strike, Ball 개수
	
	public BaseballGame() {
		random = new Random();
		randomNumArr = new int[MAX];	
		inputNumArr = new int[MAX];
		setRange();
		randomNumMake();
		System.out.println(randomNum);		//디버깅 용도로 콘솔에 난수 출력
	}
	
	public void setRange() {	//startNum = 100, endNum = 1000-1, 999
		startNum = 1;
		for(int i = 1 ; i < MAX ; i++ ) 
			startNum *= 10;
		endNum = startNum * 10 - 1;
	}
	
	public boolean chkRange(int inputNum) {	
		//사용자가 입력 값 체크. startNum ~ endNum 이외의 숫자는 false
		if ( inputNum < startNum || inputNum > endNum )
			return false;
		else 
			return true;
	}
	
	public void randomNumMake() {
		while(true) {
			randomNum = random.nextInt(endNum - startNum + 1) + startNum; 
			//random.nextInt(num) = 0 ~ num-1이므로 0 ~ 99 사이의 난수  + 100. 100의자리 난수
			makeArr(randomNum, randomNumArr);	//세자리 난수 배열로 만들기
			if(chkNum(randomNumArr)) return;	//세자리에 같은 숫자가 들어오면 false. 다시 while문 돌기
		}
	}
	
	public void makeArr(int num, int[] arr) {	
		int divisor = 1;
		for ( int i = 1 ; i < arr.length ; i++ )	//arr.length = 3
			divisor *= 10;			//divisor = 100
		for ( int i = 0 ; i < arr.length ; i++) {
			arr[i] = num / divisor;		// arr[i] 자리 수
			num %= divisor;		// num % 100 >> num % 10 >> num % 1
			divisor /= 10;		// 100 / 10 >> 10 / 10 >> 1 / 10
		}
	}
	
	public boolean chkNum(int[] arr) {
		for ( int i = 0 ; i < arr.length-1 ; i++ ) {	//0, 1 번째 자리
			for ( int j = i + 1 ; j < arr.length ; j++ ) {	//1, 2번째 자리
				if ( arr[i] == arr[j] ) 	
					return false;
				// 0번째 == 1번째, 2번째 / 1번째 == 2번째 의 숫자가 같으면 false
			}
		}
		return true;
	}
	
	public boolean attack(int inputNum) {
		boolean result; 
		result = chkRange(inputNum);	//startNum ~ endNum 사이의 숫자인지 유효성 확인
		if ( result = false )
			showResult(result); 	//chkRange에서 유효한 숫자가 아닐경우 메시지
		else {
			makeArr( inputNum , inputNumArr );	//inputNum 세자리 배열로 만들기
			result = chkNum(inputNumArr);	//inputNum 세자리 중 중복된 숫자 체크
			if( result == true ) {
				chkGame(); 		//randomNum과 inputNum 숫자와 자리 비교
				showResult(result);		//결과 출력
			}
			else
				showResult(result); //중복된 숫자 있을 때 false, 결과 출력
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
			System.out.println("유효한 숫자가 아닙니다.");
	}
}














