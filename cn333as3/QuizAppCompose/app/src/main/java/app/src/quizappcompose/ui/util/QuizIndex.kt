package app.src.quizappcompose.ui.util

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import app.src.quizappcompose.data.Question
import app.src.quizappcompose.data.questions
import app.src.quizappcompose.ui.theme.QuizUI
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuizIndex : ViewModel() {
    private var questionIndex = 0
    private val usedQuestions = mutableListOf<Int>()

    private val _uiState = MutableStateFlow(QuizUI(
        currentQuestion = nextQuestion(),
    ))
    val uiState: StateFlow<QuizUI> = _uiState.asStateFlow()

    private fun nextQuestion(): Question {
        if (usedQuestions.size == questions.size) {
            return questions[questionIndex]
        }

        var index = (questions.indices).random()
        while (usedQuestions.contains(index)) {
            index = (questions.indices).random()
        }
        usedQuestions.add(index)
        questionIndex = index
        questions[index].quizList = questions[index].quizList.shuffled()
        return questions[index]
    }

    private fun checkAnswer(answer: String): Boolean {
        val correct = answer == _uiState.value.currentQuestion.correctAnswer
        if (usedQuestions.size == 10) {
            _uiState.update { currentState ->
                currentState.copy(
                    isGameOver = true
                )
            }
        }
        else {
            _uiState.update { currentState ->
                currentState.copy(
                    currentQuestionCount = currentState.currentQuestionCount.inc()
                )
            }
        }
        if (correct) {
            _uiState.update { currentState ->
                currentState.copy(
                    score = currentState.score.inc()
                )
            }
        }
        return correct
    }

    fun checksum() {
        if(_uiState.value.selectedAnswer == ""){
            _uiState.update { currentState ->
                currentState.copy(
                    result = "Please select some answer."
                )
            }
        }
        else{
            checkAnswer(_uiState.value.selectedAnswer)
            _uiState.update { currentState ->
                currentState.copy(
                    result = "You have got " +_uiState.value.score.toString() + " Points",
                    selectedAnswer = "",
                    currentQuestion = nextQuestion()
                )
            }
        }
    }

    fun selectedAnswer(option: String) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedAnswer = option
            )
        }
    }

    fun reset() {
        questionIndex = 0
        usedQuestions.clear()
        _uiState.value = QuizUI(
            isGameOver = false,
            currentQuestion = nextQuestion(),
            selectedAnswer = "",
            result = "",
            currentQuestionCount = 1
        )
    }
}