package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    val team1 = Score(teamName = "Jorge", teamScore = 0)
                    val team2 = Score(teamName = "Banco de dados", teamScore = 0)
                    ScoreView(team1, team2)
                }
            }
        }
    }
}