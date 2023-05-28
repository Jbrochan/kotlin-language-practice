import java.util.*

//학생수를 입력 받는다.
//입력받은 학생의 수 만큼 반복하며
//학생의 이름, 학년, 국어, 영어, 수학 점수를 받는다.
//입력이 완료되면
//각 학생들의 정보를 출력하고
//각 과목별 총점과 평균을 출력한다.
//학생의 정보는 각각을 ArrayList 를 만들어서 각각 담아준다.

fun main(){
    val scanner = Scanner(System.`in`)

    val nameList = ArrayList<String>()
    val classList = ArrayList<Int>()
    val koreanList = ArrayList<Int>()
    val englishList = ArrayList<Int>()
    val mathList = ArrayList<Int>()

    var koreanTotal = 0
    var englishTotal = 0
    var mathTotal = 0
    var koreanAvg = 0
    var englishAvg = 0
    var mathAvg = 0

    // 학생 수와 정보를 입력
    print("학생 수를 입력해주세요 : ")
    val studentNum = scanner.nextInt()
    for(i in 1..studentNum){
        print("이름을 입력해주세요 : ")
        nameList.add(scanner.next())

        print("학년을 입력해주세요 : ")
        classList.add(scanner.nextInt())

        print("국어 점수를 입력해주세요 : ")
        koreanList.add(scanner.nextInt())

        print("영어 점수를 입력해주세요 : ")
        englishList.add(scanner.nextInt())

        print("수학 점수를 입력해주세요 : ")
        mathList.add(scanner.nextInt())

        println()
    }

    // 학생들의 정보 출력
    println()
    println("입력한 학생의 정보를 출력합니다")
    for(i in 0 until studentNum){
        println("이름 : ${nameList[i]}")
        println("학년 : ${classList[i]}")
        println("국어 점수 : ${koreanList[i]}")
        println("영어 점수 : ${englishList[i]}")
        println("수학 점수 : ${mathList[i]}")
        println()
    }

    // 총점 및 평균 계산 및 출력
    for(i in 0 until studentNum){
        koreanTotal += koreanList[i]
        englishTotal += englishList[i]
        mathTotal += mathList[i]
    }
    koreanAvg = koreanTotal/studentNum
    englishAvg = englishTotal/studentNum
    mathAvg = mathTotal/studentNum

    println()
    println("총점 및 평균 점수를 출력합니다")
    println("국어의 총점입니다 : $koreanTotal")
    println("영어의 총점입니다 : $englishTotal")
    println("수학의 총점입니다 : $mathTotal")
    println("국어의 평균입니다 : $koreanAvg")
    println("영어의 평균입니다 : $englishAvg")
    println("수학의 평균입니다 : $mathAvg")
}
