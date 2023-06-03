open class ChineseDish(val name: String, val price: Int){

    // 음식의 정보를 출력하는 메서드
    fun printInfo(){
        println("음식 : $name")
        println("가격 : $price")
        printSpecification()
    }

    // 음식별 세부 사항을 출력하는 메서드
    open fun printSpecification(){}
}

class Jjajang(val largeSize: Int) : ChineseDish(name = "짜장" , price = 6000){
    override fun printSpecification() {
        when(largeSize){
            1 -> println("곱빼기여부 : 곱뺴기 입니다")
            2 -> println("곱빼기여부 : 곱빼기 아닙니다")
        }
    }
}

class Jjambbong(private val mussel: Int) : ChineseDish(name = "짬뽕", price = 8000){
    override fun printSpecification() {
        when(mussel){
            1 -> println("홍합여부 : 홍합이 있습니다")
            2 -> println("홍합여부 : 홍합이 없습니다")
        }
    }
}

class FriedRice(private val soup: Int) : ChineseDish(name = "볶음밥", price = 10000){
    override fun printSpecification() {
        when(soup){
            1 -> println("국물종류 : 짬뽕국물")
            2 -> println("국물종류 : 계란국물")
        }
    }
}