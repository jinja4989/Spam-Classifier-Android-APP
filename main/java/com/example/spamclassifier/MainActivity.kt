package com.example.spamclassifier

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import loadWordIndex

class MainActivity : AppCompatActivity() {
    private lateinit var textProcessor: TextProcessor
    private lateinit var spamClassifier: SpamClassifier

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputText = findViewById<EditText>(R.id.inputText)
        val predictButton = findViewById<Button>(R.id.predictButton)
        val resultText = findViewById<TextView>(R.id.resultText)

        // 단어 사전 로드
        val wordIndex: Map<String, Int> = loadWordIndex(this)  // 함수 호출 방식
        textProcessor = TextProcessor(wordIndex, maxSequenceLength = 100)

        // TensorFlow Lite 모델 초기화
        spamClassifier = SpamClassifier(this)

        // 예측 버튼 클릭 리스너
        predictButton.setOnClickListener {
            val message = inputText.text.toString()
            val processedInput = textProcessor.processText(message)
            val prediction = spamClassifier.classify(processedInput)

            // 결과 출력
            resultText.text = if (prediction > 0.5) "스팸 메시지" else "일반 메시지"
        }
    }
}




