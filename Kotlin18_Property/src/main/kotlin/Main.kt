// Property : 문법적으로는 변수를 사용하는 것 처럼 사용하지만
// 실제로는 setter/getter 메서드를 사용하는 방식을 의미한다.

fun main(){
    val t1 = TestClass1(100 ,200)

    // 내부적으로 들여다보면 a1, a2 변수에 직접 접근하는 것이 아닌
    // getter 메서드를 호출해서 반환 받는 값을 사용한다.
    println("t1.a1 : ${t1.a1}")
    println("t1.a2 : ${t1.a2}")

    // a1 변수에 직접 접근하여 값을 저장하는 것이 아닌
    // setter 메서드를 호출하는 것을 사용한다.
    t1.a1 = 1000
    println("t1.a1 : ${t1.a1}")

    val t2 = TestClass2()

    // setter 가 호출된다.
    t2.v1 = 100
    t2.v3 = 5

    // getter 가 호출된다.
    println(t2.v1)
    println(t2.v2)
    println(t2.v3)
    println(t2.v4)
}

// var 이나 val 키워드를 사용하여 생성자의 매개변수로 선언된 변수들은 private 멤버로 정의된다.
// var 의 경우에는 setter 와 getter 가 모두 만들어지며
// val 의 경우 final 변수로 정의 되고 getter 만 제공된다.
class TestClass1(var a1: Int, val a2: Int)

class TestClass2{
    // 클래스에 정의한 모든 변수는 private 로 정의된다.
    // val 변수는 final 변수로 정의된다.
    // 변수만 정의하면 var 의 경우 setter/getter 가 자동으로 만드렁지고
    // val 의 경우 getter 만 자동으로 만들어진다.
    var v1 = 0
    val v2 = 0

    // 만약 getter 나 setter 를 원하는대로 만들겠다면
    // 다음과 같이 getter/setter 를 작성한다.
    // 여기서 field 는 변수를 의미한다.
    var v3 = 0
        get(){
            println("v3의 getter 호출")
            return field
        }
        set(value){
            println("v3의 setter 호출")
            if(value in 1.. 10){
                field = value
            }
        }

    var v4 = 0
        get(){
            println("v4의 getter 호출")
            return field
        }
}