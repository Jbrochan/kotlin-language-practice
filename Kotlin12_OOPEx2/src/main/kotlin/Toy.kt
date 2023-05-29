open class Toy (val name: String, val price: Int, val size: String){

    fun printInfo(){
        println(name)
        println("가격 : ${price}원")
        println("크기 : $size")
    }
}

// 로보트 장난감
class Robot : Toy(name = "로보트 장난감", price = 5000, size = "로보트만큼 크다")
// 레고
class Lego : Toy(name = "레고", price =50000, size = "레고만큼 크다")
// BB탄 총
class BbGun : Toy(name = "BB탄 총", price = 10000, size = "BB탄 총 만큼 크다")
// 잠만보 인형
class PokemonDoll : Toy(name = "잠만보인형", price = 20000, size = "잠만보만큼 크다")