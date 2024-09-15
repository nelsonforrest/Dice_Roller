package com.forrestnelson.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.forrestnelson.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }

    @Preview
    @Composable
    fun DiceRollerApp() {
        DiceWithButtonAndImage(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
        )
    }

    @Composable // added modifier parameter is used to modify the behavior of the Compose UI elements
    fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {

        // added a result variable for the onclick parameter
        // added the remember composable to store the result variable
        var result by remember { mutableStateOf( 1) }
        val imageResource = when(result) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        // added column function added for vertical layout so the dice is above the roll button
        // also passed modifier to center the horizontal alignment
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {

            // added image above button *compose automatically displays in sequential order
            // painter argument accepts image resource depending on result value
            Image(painter = painterResource(imageResource), contentDescription = result.toString())

            // spacer added to stop the button from crowding the dice image
            Spacer(modifier = Modifier.height(16.dp))

            // added button function
            Button(
                // .random syntax generates a random number based on the given number range 1-6
                onClick = { result = (1..6).random() },
                )
            {
                // added text function to display the roll string as text on the button
                Text(text = stringResource(R.string.roll), fontSize = 24.sp)
            }
        }
    }
}