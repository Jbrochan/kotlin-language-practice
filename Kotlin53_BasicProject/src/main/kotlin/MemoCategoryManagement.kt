import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.*

class MemoCategoryManagement {
    private val scanner = Scanner(System.`in`)
    private val file = File("memo.json")
    private val jsonParser = JSONParser()

    // 메모 카테고리 관리
    fun runMemoCategoryManagement(){
        while(true){
            try{
                printCategory()
                println("\n1. 카테고리 등록")
                println("2. 카테고리 삭제")
                println("3. 카테고리 수정")
                println("4. 이전")
                print("카테고리 관리 메뉴 선택 : ")
                when(val userInput = scanner.nextInt()){
                    // 1. 카테고리 등록
                    CategoryStatus.CATEGORY_REGISTRATION.userInput -> {
                        categoryRegistration()
                    }
                    // 2. 카테고리 삭제
                    CategoryStatus.CATEGORY_DELETION.userInput -> {
                        categoryDeletion()
                    }
                    // 3. 카테고리 수정
                    CategoryStatus.CATEGORY_MODIFICATION.userInput -> {
                        categoryModification()
                    }
                    // 4. 이전
                    CategoryStatus.CATEGORY_BACK.userInput -> {
                        return
                    }
                }
            } catch(e: Exception){
                scanner.next()
                println("다시 입력해주세요\n")
            }
        }
    }

    // 등록되어있는 카테고리를 출력하는 메서드
    private fun printCategory(){
        val fileReader = FileReader("memo.json")
        val jsonArray = returnJsonArray(fileReader)

        if(jsonArray.size == 1){
            println("\n등록된 카테고리가 없습니다")
        } else{
            println()
            for(index in 1 until jsonArray.size){
                println("$index : ${((jsonArray[index] as JSONArray)[0] as JSONObject)["카테고리 이름"]}")
            }
        }
    }

    // 카테고리를 등록하는 메서드
    private fun categoryRegistration(){
        try{
            scanner.nextLine()    // nextLine()을 위한 \n 문자 처리
            print("등록할 카테고리 이름을 입력해주세요 : ")
            val newCategoryName = scanner.nextLine()
            val fileReader = FileReader(file)
            val jsonArray = returnJsonArray(fileReader)

            val fileWriter = FileWriter(file)


            // 새롭게 등록할 카테고리의 JSON Array
            val newCategoryObject = JSONObject()
            val newCategoryArray = JSONArray()
            newCategoryObject["카테고리 이름"] = newCategoryName
            newCategoryArray.add(newCategoryObject)
            jsonArray.add(newCategoryArray)
            fileWriter.write(jsonArray.toJSONString())
            fileWriter.flush()
            fileWriter.close()

        } catch (e: Exception){
            println("다시 입력해주세요\n")
        }
    }

    // 카테고리를 삭제하는 메서드
    private fun categoryDeletion(){
        while(true){
            try{
                print("삭제할 카테고리 번호를 입력해주세요 : " )
                val deletionCategoryNum = scanner.nextInt()
                val fileReader = FileReader(file)
                val jsonArray = returnJsonArray(fileReader)

                if(deletionCategoryNum in 1 until jsonArray.size){
                    val fileWriter = FileWriter(file)
                    jsonArray.removeAt(deletionCategoryNum)
                    fileWriter.write(jsonArray.toJSONString())
                    fileWriter.flush()
                    fileWriter.close()
                } else {
                    println("잘못 입력하였습니다\n")
                    continue
                }
                return
            }catch (e: Exception){
                scanner.next()
                println("다시 입력해주세요\n")
            }
        }
    }

    // 카테고리를 변경하는 메서드
    private fun categoryModification(){
        while(true){
            try{
                print("수정할 카테고리 번호를 입력해주세요 : ")
                val modificationCategoryNum = scanner.nextInt()
                val fileReader = FileReader(file)
                val jsonArray = returnJsonArray(fileReader)

                if(modificationCategoryNum in 1 until jsonArray.size){
                    val fileWriter = FileWriter(file)
                    scanner.nextLine()    // nextLine()을 위한 \n 문자 처리
                    print("${((jsonArray[modificationCategoryNum] as JSONArray)[0] as JSONObject)["카테고리 이름"]} -> ")
                    ((jsonArray[modificationCategoryNum] as JSONArray)[0] as JSONObject)["카테고리 이름"] = scanner.nextLine()
                    fileWriter.write(jsonArray.toJSONString())
                    fileWriter.flush()
                    fileWriter.close()
                } else{
                    println("잘못 입력하였습니다\n")
                    continue
                }
                return
            } catch (e: Exception){
                scanner.nextLine()
                println("다시 입력해주세요\n")
            }
        }
    }

    // JSON 파일을 읽어 전체 json array 를 반환하는 메서드
    private fun returnJsonArray(fileReader: FileReader) : JSONArray {
        return jsonParser.parse(fileReader) as JSONArray
    }



}

enum class CategoryStatus(val userInput: Int){
    CATEGORY_REGISTRATION(1), CATEGORY_DELETION(2), CATEGORY_MODIFICATION(3), CATEGORY_BACK(4)
}