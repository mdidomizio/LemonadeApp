package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }

        }
    }
}

fun onClick(function: () -> Unit) {
    var squeezeValue = (1..4).random()
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview()
@Composable
fun LemonadeApp() {
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )

        },
    ){
    //TODO add logic
    }

    var currentStep = 0
    LemonadeScreen(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        when (currentStep) {
            1 -> Image(
                painterResource(R.drawable.lemon_tree),
                contentDescription = R.string.screenOneTitle,
                modifier = Modifier.
                clickable { currentStep = 2 }
            )
            Text(stringResource(R.string.screenOneDescription))
            2 ->  Image(
                painterResource(R.drawable.lemon_squeeze),
                contentDescription = R.string.screenTwoTitle,
                modifier = Modifier.
                clickable { currentStep = 3 }
            )
            Text(stringResource(R.string.screenTwoDescription))
            3 -> Image(
                painterResource(R.drawable.lemon_drink),
                contentDescription = R.string.screenThreeTitle,
                modifier = Modifier.
                clickable { currentStep = 4 }
            )
            Text(stringResource(R.string.screenThreeDescription))
            4 -> Image(
                painterResource(R.drawable.lemon_restart),
                contentDescription = R.string.screenFourTitle,
                modifier = Modifier.
                clickable {
                    currentStep = 1
                    onClick()
                }
            )
            Text(stringResource(R.string.screenFourDescription))
        }
    }
}

@Composable
fun LemonadeScreen(
    modifier: Modifier = Modifier,
    imageResource: Int,
    screenDescription: String,
    contentLabel: String,
) {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeValue by remember { mutableStateOf(0) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = contentLabel.toString(),
            modifier = Modifier
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(16.dp)
                )
                .clickable(onClick = {
                    currentStep = (1..4).random()
                    /*if(result == 2) {
                    result = (1..4).random()
                    } else {
                        // TODO add the logic for navigating
                    }*/
                })

        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(stringResource(screenDescription.toInt()), fontSize = 18.sp)
    }
}
