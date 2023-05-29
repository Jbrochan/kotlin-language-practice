import java.util.*

class ToyFactory {
    private val scanner = Scanner(System.`in`)
    private var orderNum = 0

    private val robotList = ArrayList<Toy>()
    private val legoList = ArrayList<Toy>()
    private val bbGunList = ArrayList<Toy>()
    private val pokemonDollList = ArrayList<Toy>()

    private var robotNum = 0
    private var legoNum = 0
    private var bbGunNum = 0
    private var pokemonDollNum = 0

    // 장난감 공장 생산 가동
    fun runFactory(){
        while(true){
            println("생산할 장난감의 종류를 선택해주세요")
            print("1. 로보토 장난감, 2. 레고, 3. BB탄 총, 4. 잠만보인형, 0. 생산끝 : ")
            orderNum = scanner.nextInt()

            // 입력된 번호에 따라 동작한다
            if(orderNum in 1..4){
                produceToy(orderNum)
            } else if(orderNum == 0){
                printToyInfo()
                break
            } else{
                println("잘 못 입력하셨습니다.")
                continue
            }


        }
    }

    // 입력된 장난감 번호에 맞게 생산하는 메서드
    private fun produceToy(orderNum: Int){
        when(orderNum){
            ToyFactoryState.FACTORY_ROBOT.orderNum -> {
                robotList.add(Robot())
            }
            ToyFactoryState.FACTORY_LEGO.orderNum -> {
                legoList.add(Lego())
            }
            ToyFactoryState.FACTORY_GUN.orderNum -> {
                bbGunList.add(BbGun())
            }
            ToyFactoryState.FACTORY_POKEMON.orderNum -> {
                pokemonDollList.add(PokemonDoll())
            }
        }
    }

    // 주요 장난감의 정보와 주문 내역을 출력하는 메서드
    private fun printToyInfo(){
        val sampleRobot = Robot()
        val sampleLego = Lego()
        val sampleGun = BbGun()
        val sampleDoll = PokemonDoll()

        val priceOfRobot = sampleRobot.price * robotList.size
        val priceOfLego = sampleLego.price * legoList.size
        val priceOfBbGun = sampleGun.price * bbGunList.size
        val priceOfPokemonDoll = sampleDoll.price * pokemonDollList.size
        val priceTotal = priceOfRobot + priceOfLego + priceOfBbGun + priceOfPokemonDoll
        val priceAvg = priceTotal/(robotList.size + legoList.size + bbGunList.size + pokemonDollList.size)

        // 주문 내역 출력
        println()
        println("총 : ${robotList.size + legoList.size + bbGunList.size + pokemonDollList.size}개")
        println("로보토 장난감 : ${robotList.size}개")
        println("레고 : ${legoList.size}개")
        println("BB탄 총 : ${bbGunList.size}개")
        println("잠만보인형 : ${pokemonDollList.size}개")
        println()

        // 주요 장난감의 정보 출력
        sampleRobot.printInfo()
        println()
        sampleLego.printInfo()
        println()
        sampleGun.printInfo()
        println()
        sampleDoll.printInfo()
        println()

        // 주문 가격 출력
        println("생산된 장난감 총 가격 : ${priceTotal}원")
        println("생산된 장난감 평균 가격 : ${priceAvg}원")
    }


}

enum class ToyFactoryState(val orderNum: Int){
    FACTORY_ROBOT(1), FACTORY_LEGO(2), FACTORY_GUN(3), FACTORY_POKEMON(4),
    FACTORY_DONE(0)
}