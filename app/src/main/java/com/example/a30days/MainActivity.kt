package com.example.a30days

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.a30days.model.ExerciseDatasource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                ThirtyDaysOfNikiforovApp()
            }
        }
    }
}

@Composable
fun ThirtyDaysOfNikiforovApp() {
    val isDarkTheme = isSystemInDarkTheme()

    val gradientColors = if (isDarkTheme) {
        listOf(
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
            MaterialTheme.colorScheme.background,
            MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.6f)
        )
    } else {
        listOf(
            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
            MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f),
            MaterialTheme.colorScheme.background
        )
    }

    Scaffold(
        topBar = {
            ThirtyDaysOfNikiforovTopAppBar(gradientColors = gradientColors)
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(gradientColors))
                .padding(innerPadding)
        ) {
            DailyExerciseList(
                ExerciseDatasource.dailyExercises
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirtyDaysOfNikiforovTopAppBar(
    modifier: Modifier = Modifier,
    gradientColors: List<Color>
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.top_bar),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        modifier = modifier
            .background(brush = Brush.verticalGradient(gradientColors)),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        )
    )
}
