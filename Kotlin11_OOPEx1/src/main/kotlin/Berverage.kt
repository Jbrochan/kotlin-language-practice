open class Berverage(val name: String, val price: Int, val amount: Int, val company: String) {

    // 음료의 정보를 출력하는 메서드
    fun printInfo(){
        println("${name}은 ${price}원이고")
        println("양은 ${amount}ml 입니다 ")
        println("회사는 ${company}입니다")
    }
}

class Coke : Berverage(name = "콜라", price = 1000, amount = 300, company = "코카콜라")
class Sprite : Berverage(name = "사이다", price = 1000, amount = 300, company = "칠성")
class Coffee : Berverage(name = "커피", price = 1500, amount = 500, company = "별다방")
class PeachIceTea : Berverage(name = "복숭아아이스티", price = 2000, amount = 700, company = "빽다방")

