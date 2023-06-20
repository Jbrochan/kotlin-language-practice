import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.*
import kotlin.system.exitProcess

class MemoProgram {
    private val scanner = Scanner(System.`in`)
    private val file = File("memo.json")
    private val jsonParser = JSONParser()



    // 메모장 프로그램을 작동시키는 메서드
    fun runMemoProgram(){
        // 생성된 파일이 없다면 파일을 생성하고 비밀번호를 설정
        setPassword()

        // 생성된 파일이 있다면 비밀번호를 입력
        login()

        // 메인 메뉴에서 선택
        mainMenu()
    }

    // 파일을 생성하고 비밀번호를 설정하는 메서드
    private fun setPassword(){

        // 메모장 파일이 없을 경우 새로운 비밀번호 생성
        if(!file.exists()) {
            try {
                // 메모장 파일 생성
                file.createNewFile()

                val fileWriter = FileWriter("memo.json")
                var userPassword: String

                // 비밀번호 설정 및 검증
                while (true) {
                    print("설정할 비밀번호를 입력해주세요 : ")
                    userPassword = scanner.next()
                    print("한번 더 입력해주세요 : ")
                    val userPasswordValidation = scanner.next()
                    // 비밀번호 검증
                    if (userPassword == userPasswordValidation) {
                        println()
                        break
                    } else {
                        println("다시 입력해주세요\n")
                        continue
                    }
                }

                // 메모장 데이터를 저장할 JSON Array
                val memoJson = JSONArray()

                // 비밀번호 객체를 만들어 JSON Array 에 저장
                val passwordObject = JSONObject()
                passwordObject["비밀번호"] = userPassword
                memoJson.add(passwordObject)
                fileWriter.write(memoJson.toJSONString())
                fileWriter.flush()
                fileWriter.close()
            } catch (e: Exception) {
                println("다시 입력해주세요\n")
            }
        } else{
            return
        }
    }

    private fun login() : Boolean{
        try{
            val fileReader = FileReader("memo.json")
            val jsonArray = jsonParser.parse(fileReader) as JSONArray
            val userPassword = (jsonArray[0] as JSONObject)["비밀번호"]

            while(true){
                print("로그인 하시려면 비밀 번호를 입력하세요 : ")
                val userPasswordValidation = scanner.next()
                if(userPassword == userPasswordValidation){
                    return true
                } else{
                    println("다시 입력해주세요\n")
                    continue
                }
            }
        } catch (e: Exception){
            println("다시 입력해주세요\n")
            return false
        }
    }

    // 메인 메뉴 메서드
    private fun mainMenu(){
        while(true){
            try{
                println("\n1. 메모 카테고리 관리")
                println("2. 메모 카테고리 선택")
                println("3. 메모 내용 전체 보기")
                println("4. 종료")
                print("메뉴를 선택해주세요 : ")
                when(scanner.nextInt()){
                    // 1. 메모 카테고리 관리
                    MainStatus.MAIN_MANAGE.userInput -> {
                        val memoCategoryManagement = MemoCategoryManagement()
                        memoCategoryManagement.runMemoCategoryManagement()
                    }
                    // 2. 메모 카테고리 선택
                    MainStatus.MAIN_SELECT.userInput -> {
                        val memoCategorySelection = MemoCategorySelection()
                        memoCategorySelection.runMemoCategorySelection()
                    }
                    // 3. 메모 내용 전체 보기
                    MainStatus.MAIN_OVERVIEW.userInput -> {
                        val memoCategoryOverview = MemoCategoryOverview()
                        memoCategoryOverview.runMemoCategoryOverview()
                    }
                    // 4. 종료
                    MainStatus.MAIN_QUIT.userInput -> {
                        exitProcess(0)
                    }
                    else -> {
                        println("다시 입력해주세요\n")
                    }

                }
            } catch (e: Exception){
                scanner.next()
                println("다시 입력해주세요\n")
            }
        }
    }
}

// 메모 프로그램의 메인 메뉴를 보여주는 상태
enum class MainStatus(val userInput: Int){
    MAIN_MANAGE(1), MAIN_SELECT(2), MAIN_OVERVIEW(3), MAIN_QUIT(4)
}