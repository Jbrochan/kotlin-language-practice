open class Car(val type: String, val maxSpeed: Int, val fuel: String, val peopleOnBoard: Int) {
    // 자동차의 정보를 출력하는 메서드
    fun printInfo(){
        println("종류 : $type")
        println("최대속도 : ${maxSpeed}km/h")
        println("연료 : $fuel")
        println("탑승인원수 : ${peopleOnBoard}명\n")
    }
}

class BbCar : Car(type = "붕붕", maxSpeed = 300, fuel = "꽃향기", peopleOnBoard = 1)
class Sedan : Car(type = "승용차", maxSpeed = 200, fuel = "휘발유", peopleOnBoard = 4)
class Bus : Car(type = "버스", maxSpeed = 150, fuel = "가스", peopleOnBoard = 50)
class Truck : Car(type = "트럭", maxSpeed = 100, fuel = "가스", peopleOnBoard = 3)
