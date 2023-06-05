import java.lang.Exception
import java.util.*
import kotlin.system.exitProcess

class ChineseRestaurant {
    private val scanner = Scanner(System.`in`)

    // 각각의 음식을 담을 리스트
    val dishList = ArrayList<ChineseDish>()


    // 중국집에서 주문을 받는 메서드
    fun getOrder(){
        while(true){
            try{
                println("메뉴를 선택해주세요")
                print("1. 짜장면, 2. 짬뽕, 3. 볶음밥, 4. 종료 : ")
                when(val orderNumber = scanner.nextInt()){
                    // 1. 짜장면
                    MenuNumber.MENU_JJAJANG.menuNumber -> {
                        print("곱빼기 인가요? (1. 곱빼기 O , 2. 곱빼기 X) : ")
                        val largeSize = scanner.nextInt()
                        dishList.add(Jjajang(largeSize))
                    }
                    // 2. 짬뽕
                    MenuNumber.MENU_JJAMBBONG.menuNumber -> {
                        print("홍합이 있나요? (1. 있음, 2. 없음) : ")
                        val mussel = scanner.nextInt()
                        dishList.add(Jjambbong(mussel))

                    }
                    // 3. 볶음밥
                    MenuNumber.MENU_FRIED_RICE.menuNumber -> {
                        print("국물 종류가 무엇인가요? (1. 짬뽕국물, 2. 계란국물) : ")
                        val soup = scanner.nextInt()
                        dishList.add(FriedRice(soup))
                    }
                    // 4. 종료
                    MenuNumber.MENU_DONE.menuNumber -> {
                        printOrder()
                        exitProcess(0)
                    }

                }
            }catch (e: Exception){
                scanner.nextLine()
                println("다시 선택해주세요")
            }
        }
    }

    // 주문 내역 계산 후 출력하는 메서드
    private fun printOrder(){
        var totalPrice = 0
        var jjajangNum = 0
        var jjambbongNum = 0
        var friedRiceNum = 0

        // 주문 총 금액 계산 및 출력
        for(dish in dishList){
            // 주문 총 금액 계산
            totalPrice += dish.price
            // 주문 수량 계산
            when(dish.name){
                "짜장" -> {
                    jjajangNum ++
                }
                "짬뽕" -> {
                    jjambbongNum ++
                }
                "볶음밥" -> {
                    friedRiceNum ++
                }
            }
        }

        println("\n주문 총 금액 : ${totalPrice}원")
        println("짜장면 : ${jjajangNum}개")
        println("짬뽐 : ${jjambbongNum}개")
        println("볶음밥 ; ${friedRiceNum}개\n")

        // 주문한 메뉴 각각 출력
        for(dish in dishList){
            dish.printInfo()
            println()
        }
    }
}

enum class MenuNumber(val menuNumber: Int){
    MENU_JJAJANG(1), MENU_JJAMBBONG(2), MENU_FRIED_RICE(3), MENU_DONE(4)
}