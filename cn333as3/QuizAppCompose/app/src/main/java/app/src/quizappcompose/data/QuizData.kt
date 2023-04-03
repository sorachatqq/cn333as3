package app.src.quizappcompose.data

data class Question(
    val question: String,
    var quizList: List<String>,
    val correctAnswer: String
)

val questions = listOf(
    Question(
        "What is the smallest planet in our solar system?",
        listOf("Venus", "Earth", "Mars", "Mercury"),
        "Mercury"
    ),
    Question(
        "Who wrote the novel To Kill a Mockingbird?",
        listOf("Ernest Hemingway", "William Faulkner", "Harper Lee", "F. Scott Fitzgerald"),
        "Harper Lee"
    ),
    Question(
        "What is the largest organ in the human body?",
        listOf("Brain", "Heart", "Liver", "Skin"),
        "Skin"
    ),
    Question(
        "Who directed the movie Jaws?",
        listOf("Steven Spielberg", "Martin Scorsese", "Quentin Tarantino", "Alfred Hitchcock"),
        "Steven Spielberg"
    ),
    Question(
        "What is the capital city of Canada?",
        listOf("Toronto", "Ottawa", "Vancouver", "Montreal"),
        "Ottawa"
    ),
    Question(
        "Who wrote the play Romeo and Juliet?",
        listOf("William Shakespeare", "Tennessee Williams", "Arthur Miller", "Eugene O'Neill"),
        "William Shakespeare"
    ),
    Question(
        "What is the name of the largest ocean on Earth?",
        listOf("Atlantic Ocean", "Indian Ocean", "Pacific Ocean", "Arctic Ocean"),
        "Pacific Ocean"
    ),
    Question(
        "Who invented the telephone?",
        listOf("Thomas Edison", "Alexander Graham Bell", "Nikola Tesla", "Albert Einstein"),
        "Alexander Graham Bell"
    ),
    Question(
        "What is the chemical symbol for gold?",
        listOf("Ag", "Fe", "Au", "Hg"),
        "Au"
    ),
    Question(
        "What is the name of the largest desert in the world?",
        listOf("Sahara", "Gobi", "Kalahari", "Arabian"),
        "Sahara"
    )
)