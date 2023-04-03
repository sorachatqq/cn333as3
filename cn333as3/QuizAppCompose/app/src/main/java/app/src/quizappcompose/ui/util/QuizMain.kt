package app.src.quizappcompose.ui.util

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import app.src.quizappcompose.data.Question
import app.src.quizappcompose.ui.theme.Screen


@Composable
fun QuizMain(navController: NavController, viewModel: QuizIndex = viewModel()) {
    val quizUiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        QuizTheme(
            currentQuestion = quizUiState.currentQuestion,
        )

        quizUiState.currentQuestion.quizList.forEach { option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = quizUiState.selectedAnswer == option,
                    onClick = { viewModel.selectedAnswer(option) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colors.primary
                    )
                )
                Text(
                    text = option,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        if (quizUiState.result.isNotBlank()) {
            Text(
                text = quizUiState.result,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        if (quizUiState.isGameOver ) {
            FinalScore(
                score = quizUiState.score,
                onPlayAgain = {
                    viewModel.reset()
                }
            )
        } else {
            Button(
                onClick = { viewModel.checksum() },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Next")
            }

        }
    }
    Button(
        modifier = Modifier.padding(top = 750.dp).padding(horizontal = 20.dp),
        onClick = {
            navController.navigate(route = Screen.MainMenu.route)
        },
    )
    {
        Text("Back")
    }

}

@Composable
fun QuizTheme(
    currentQuestion: Question,
    viewModel: QuizIndex = viewModel()
) {
    val quizUiState by viewModel.uiState.collectAsState()
    Text(
        text = quizUiState.currentQuestionCount.toString()+ " / 10",
        style = MaterialTheme.typography.h4,
        modifier = Modifier.padding(top=100.dp, bottom = 50.dp)
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = currentQuestion.question,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 50.dp)
        )
    }
}

@Composable
private fun FinalScore(
    viewModel: QuizIndex = viewModel(),
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val quizUiState by viewModel.uiState.collectAsState()
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {
        },
        title = { Text("Wow!") },
        text = { Text("You have got " + quizUiState.score.toString() + " Point(s)")},
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text("Exit")
            }
        },
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text("Restart")
            }
        }
    )
}




