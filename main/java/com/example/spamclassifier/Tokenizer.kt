package com.example.spamclassifier

class TextProcessor(private val wordIndex: Map<String, Int>, private val maxSequenceLength: Int) {
    fun processText(input: String): FloatArray {
        val tokens = input.split("\\s+".toRegex())  // 공백 기준으로 단어 분리
        val paddedTokens = FloatArray(maxSequenceLength) { 0f }

        for ((i, token) in tokens.withIndex()) {
            if (i < maxSequenceLength) {
                paddedTokens[i] = wordIndex[token]?.toFloat() ?: 0f
            }
        }
        return paddedTokens
    }
}