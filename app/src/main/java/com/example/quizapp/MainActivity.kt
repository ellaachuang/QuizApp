package com.example.quizapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var title: TextView
    private lateinit var question: TextView
    private lateinit var score: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var startButton: Button

    companion object{
        val TAG = "MainActivity"
    }

    private lateinit var quiz : Quiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()
        loadQuestions()
        layout()

        val scoreText = getString(R.string.main_score)
        //TextView score.text = "$scoreText ${quiz.score}"
    }

    private fun wireWidgets(){
        title = findViewById(R.id.quiz_title)
        question = findViewById(R.id.questions)
        score = findViewById(R.id.main_score)
        trueButton = findViewById(R.id.main_true)
        falseButton = findViewById(R.id.main_false)
        startButton = findViewById(R.id.start_button)
    }

    private fun loadQuestions(){
        val inputStream = resources.openRawResource(R.raw.questions)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }

        Log.d(TAG, "onCreate: $jsonString")

        val gson = Gson()

        val type = object : TypeToken<List<Question>>() { }.type
        val questions = gson.fromJson<List<Question>>(jsonString, type)

        Log.d(TAG, "onCreate: $questions")

        quiz = Quiz(questions)

        //trueButton.setOnClickListerner
        //if (quiz.checkAnswerTrue){
        //MAKE title flash green or red based on answer
        //

        startButton.setOnClickListener{
            falseButton.visibility = View.VISIBLE
            trueButton.visibility = View.VISIBLE
            question.visibility = View.VISIBLE
            startButton.visibility = View.GONE

            question.setText = quiz

            falseButton.setOnClickListener {
                if (quiz.checkAnswerTrue == true){

                }
            }

        }
    }

    private fun layout(){
        title.setTextColor(Color.rgb(70, 90, 170))
        startButton.setBackgroundColor(Color.rgb(200, 20,20))
        falseButton.setBackgroundColor(Color.rgb(220, 30, 40))
        trueButton.setBackgroundColor(Color.rgb(0, 200, 90))

        falseButton.visibility = View.INVISIBLE
        trueButton.visibility = View.INVISIBLE
        question.visibility = View.INVISIBLE
    }

}


