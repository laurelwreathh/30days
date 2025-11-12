package com.example.a30days

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.a30days.model.DailyExercise

@Composable
fun DailyExerciseList(dailyExercises: List<DailyExercise>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(dailyExercises) { _: Int, dailyExercise: DailyExercise ->
            DailyExerciseCard(
                dailyExercise = dailyExercise,
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_medium),
                        vertical = dimensionResource(
                            id = R.dimen.padding_small
                        )
                    )

            )
        }
    }
}

@Composable
fun DailyExerciseCard(
    dailyExercise: DailyExercise,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    val enterAnimation = remember {
        slideInVertically(
            initialOffsetY = { it / 2 },
            animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
        )
    }

    AnimatedVisibility(visible = true, enter = enterAnimation) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = DampingRatioLowBouncy,
                        stiffness = StiffnessVeryLow
                    )
                )
                .clickable { expanded = !expanded },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f)
            ),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_medium))
            ) {
                Text(
                    text = stringResource(id = dailyExercise.day),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(id = dailyExercise.exerciseTitle),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))

                Image(
                    painter = painterResource(id = dailyExercise.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                if (expanded) {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
                    Text(
                        text = stringResource(id = dailyExercise.exerciseDescription),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
