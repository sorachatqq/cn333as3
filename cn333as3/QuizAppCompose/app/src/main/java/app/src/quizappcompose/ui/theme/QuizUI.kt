package app.src.quizappcompose.ui.theme

import app.src.quizappcompose.data.Question

data class QuizUI(
    val result: String = "",
    val currentQuestionCount: Int = 1,
    val currentQuestion: Question,
    val score: Int = 0,
    val isGameOver: Boolean = false,
    val selectedAnswer: String = "",
    val currentQuestionIndex: Int = 0,
)