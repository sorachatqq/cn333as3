package app.src.quizappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.src.quizappcompose.ui.theme.QuizAppComposeTheme
import app.src.quizappcompose.ui.theme.SetupNavGraph
import app.src.quizappcompose.ui.util.QuizMain
import app.src.quizappcompose.ui.util.MainMenu
import app.src.quizappcompose.ui.util.GuessNumMain

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppComposeTheme {

                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuizAppComposeTheme {
        MainMenu(navController = rememberNavController())
//        GuessNumMain()
    }
}