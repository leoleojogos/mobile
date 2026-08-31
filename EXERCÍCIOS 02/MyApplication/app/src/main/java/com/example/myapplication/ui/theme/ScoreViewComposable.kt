package com.example.myapplication
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TeamScore(score: Score) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = score.teamName,
            fontSize = 20.sp
        )
        Button(onClick = { score.teamScore += 1 }) {
            Text(text = "+")
        }
        Text(
            modifier = Modifier.padding(16.dp),
            text = "${score.teamScore}",
            fontSize = 24.sp
        )
        Button(onClick = { score.teamScore -= 1 }) {
            Text(text = "-")
        }
    }
}

@Composable
fun ScoreView(team1: Score, team2: Score) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TeamScore(score = team1)
            TeamScore(score = team2)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestScoreView() {
    val team1 = Score(teamName = "Kotlin", teamScore = 0)
    val team2 = Score(teamName = "Java", teamScore = 0)
    ScoreView(team1, team2)
}