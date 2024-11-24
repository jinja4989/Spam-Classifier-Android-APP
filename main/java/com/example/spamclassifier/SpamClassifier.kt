package com.example.spamclassifier
import android.content.Context
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class SpamClassifier(context: Context) {
    private var interpreter: Interpreter

    init {
        interpreter = Interpreter(loadModelFile(context))
    }

    // TensorFlow Lite 모델 로드
    private fun loadModelFile(context: Context): MappedByteBuffer {
        val assetFileDescriptor = context.assets.openFd("spam_classifier.tflite")
        val inputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val declaredLength = assetFileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    fun classify(input: FloatArray): Float {
        val output = Array(1) { FloatArray(1) }  // 출력 배열 모양 [1, 1]
        interpreter.run(arrayOf(input), output)  // TensorFlow Lite 모델 실행
        return output[0][0]  // 첫 번째 값 반환
    }

}
