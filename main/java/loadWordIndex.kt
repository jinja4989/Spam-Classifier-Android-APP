import android.content.Context
import org.json.JSONObject

fun loadWordIndex(context: Context): Map<String, Int> {
    val assetManager = context.assets
    val inputStream = assetManager.open("word_index.json")  // 파일 열기
    val jsonString = inputStream.bufferedReader().use { it.readText() }

    val jsonObject = JSONObject(jsonString)  // JSON 파싱
    val wordIndex = mutableMapOf<String, Int>()
    val keys = jsonObject.keys()

    while (keys.hasNext()) {
        val key = keys.next()
        wordIndex[key] = jsonObject.getInt(key)
    }
    return wordIndex  // Map<String, Int> 반환
}
