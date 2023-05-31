import java.util.*
import kotlin.collections.ArrayList

class CarFactory {
    private val scanner = Scanner(System.`in`)

    // 생산된 자동차를 담을 리스트
    private val carList = ArrayList<Car>()

    // 각각의 자동차를 담을 리스트
    private val bbCarList = ArrayList<Car>()
    private val sedanList = ArrayList<Car>()
    private val busList = ArrayList<Car>()
    private val truckList = ArrayList<Car>()

    // 자동차 공장을 가동시키는 메서드
    fun runCarFactory(){
        getOrder(scanner)
    }

    // 사용자의 주문을 받는 메서드
    private fun getOrder(scanner: Scanner){
        while(true){
            try{
                println("생산할 자동차를 선택해주세요")
                print("1. 붕붕, 2. 승용차, 3. 버스, 4. 트럭, 0. 생산종료 : ")
                when(val userOrder = scanner.nextInt()){
                    CarCategory.CATEGORY_BB.category -> {
                        produceCar(userOrder)
                    }
                    CarCategory.CATEGORY_SEDAN.category -> {
                        produceCar(userOrder)
                    }
                    CarCategory.CATEGORY_BUS.category -> {
                        produceCar(userOrder)
                    }
                    CarCategory.CATEGORY_TRUCK.category -> {
                        produceCar(userOrder)
                    }
                    CarCategory.CATEGORY_DONE.category -> {
                        printOrderBook()
                        break
                    }
                    else -> {
                        println("다시 입력해주세요\n")
                    }
                }
            } catch (e: Exception){
                scanner.nextLine()
                println("다시 입력해주세요\n")
            }
        }
    }

    // 자동차를 생산하고 메서드
    private fun produceCar(userOrder: Int) {
        when (userOrder) {
            CarCategory.CATEGORY_BB.category -> {
                bbCarList.add(BbCar())
                carList.add(BbCar())
            }

            CarCategory.CATEGORY_SEDAN.category -> {
                sedanList.add(Sedan())
                carList.add(Sedan())
            }

            CarCategory.CATEGORY_BUS.category -> {
                busList.add(Bus())
                carList.add(Bus())
            }

            CarCategory.CATEGORY_TRUCK.category -> {
                truckList.add(Truck())
                carList.add(Truck())
            }
        }
    }

    // 생산된 자동차의 정보를 계산하고 출력하는 메서드
    private fun printOrderBook(){
        val sampleBbCar = BbCar()
        val sampleSedan = Sedan()
        val sampleBus = Bus()
        val sampleTruck = Truck()

        val totalCarSpeedAvg = (bbCarList.size * sampleBbCar.maxSpeed + sedanList.size * sampleSedan.maxSpeed + busList.size * sampleBus.maxSpeed + truckList.size * sampleTruck.maxSpeed)/(bbCarList.size + sedanList.size + busList.size + truckList.size)
        val totalCarPeopleOnBoard = bbCarList.size * sampleBbCar.peopleOnBoard + sedanList.size * sampleSedan.peopleOnBoard + busList.size * sampleBus.peopleOnBoard + truckList.size * sampleTruck.peopleOnBoard

        // 생산 내역 출력
        println("\n총 샌산 자동차 수 : ${carList.size}")
        println("붕붕 : ${bbCarList.size}")
        println("승용차 : ${sedanList.size}")
        println("버스 : ${busList.size}")
        println("트럭 : ${truckList.size}\n")

        // 각 자동차의 정보를 출력
        for(car in carList){
            car.printInfo()
        }

        // 생산된 자동차의 세부 정보 출력
        println("생산된 자동차의 평균 속도 : ${totalCarSpeedAvg}km/h")
        println("생산된 자동차들의 총 탑승인원수 : ${totalCarPeopleOnBoard}명")
        println("연료가 꽃향기인 자동차의 수 : ${bbCarList.size}대")
        println("연료가 휘발유인 자동차의 수 : ${sedanList.size}대")
        println("연료가 가스인 자동차의 수 : ${busList.size + truckList.size}대")
    }
}

enum class CarCategory(val category: Int){
    CATEGORY_BB(1), CATEGORY_SEDAN(2), CATEGORY_BUS(3), CATEGORY_TRUCK(4),
    CATEGORY_DONE(0)
}