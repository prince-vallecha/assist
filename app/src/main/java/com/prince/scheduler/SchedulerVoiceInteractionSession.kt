package com.prince.scheduler

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.service.voice.VoiceInteractionSession
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log

class SchedulerVoiceInteractionSession(context: Context) :
    VoiceInteractionSession(context) {

    private var speechRecognizer: SpeechRecognizer? = null

    override fun onShow(args: Bundle?, showFlags: Int) {
        super.onShow(args, showFlags)
        startListening()
    }

    private fun startListening() {
        val recognizer = SpeechRecognizer.createSpeechRecognizer(context)
        speechRecognizer = recognizer

        val recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
        }

        recognizer.setRecognitionListener(object : RecognitionListener {
            override fun onResults(results: Bundle?) {
                val transcript = results
                    ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    ?.firstOrNull()

                if (!transcript.isNullOrBlank()) {
                    Log.d("SchedulerAssist", "Heard: $transcript")
                }
                finish()
            }

            override fun onError(error: Int) {
                Log.w("SchedulerAssist", "Speech recognition error: $error")
                finish()
            }

            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })

        recognizer.startListening(recognizerIntent)
    }

    override fun onHide() {
        super.onHide()
        speechRecognizer?.destroy()
        speechRecognizer = null
    }
}
