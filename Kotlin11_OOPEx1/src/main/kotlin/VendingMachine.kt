import java.util.*

class VendingMachine(){
    val scanner = Scanner(System.`in`)
    var change = 0
    var userMoney = 0
    var shortage = 0

    // 자판기 가동 메서드
    fun runVendingMachine(){
        println("음료수를 고르세요")
        print("1.콜라(1000원), 2.사이다(1000원), 3.커피(1500원), 4.복숭아아이스티(2000원) : ")
        val userChoice = scanner.nextInt()

        // 선택 번호에 따라 음료 계산
        orderBeverage(userChoice)

    }

    fun orderBeverage(userChoice: Int){
        when(userChoice){
            // 사용자가 콜라 주문
            VendingMachineState.VENDING_COKE.userChoice -> {
                val choosenBerverage = Coke()
                calculateMoney(choosenBerverage)

            }
            // 사용자가 사이다 주문
            VendingMachineState.VENDING_SPRITE.userChoice -> {
                val choosenBerverage = Sprite()
                calculateMoney(choosenBerverage)
            }
            // 사용자가 커피 주문
            VendingMachineState.VENDING_COFFEE.userChoice -> {
                val choosenBerverage = Coffee()
                calculateMoney(choosenBerverage)
            }
            // 사용자가 복숭아 아이스티 주문
            VendingMachineState.VENDING_TEA.userChoice -> {
                val choosenBerverage = PeachIceTea()
                calculateMoney(choosenBerverage)
            }
        }

    }

    fun calculateMoney(choosenBerverage: Berverage){
        // 사용자의 투입금액이 음료의 금액 이상일 때 까지 반복
        while(true){
            shortage = choosenBerverage.price - change
            println("현재 ${change}원/부족 ${shortage}원")
            print("동전을 넣어주세요 : ")
            userMoney = scanner.nextInt()
            change += userMoney
            shortage = choosenBerverage.price - change
            if(shortage <= 0){
                println("${choosenBerverage.name}가 나왔습니다.")
                change -= choosenBerverage.price
                println("잔액은 ${change}원 입니다.")
                choosenBerverage.printInfo()
                break
            }
        }
    }
}

enum class VendingMachineState(val userChoice: Int){
    VENDING_COKE(1), VENDING_SPRITE(2), VENDING_COFFEE(3), VENDING_TEA(4)
}