package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LemonadeApp(
) {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeValue by remember { mutableStateOf(0) }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Lemonade", fontWeight = FontWeight.Bold
                )
            }, colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        )

    }) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            when (currentStep) {
                1 -> {
                    LemonadeScreen(imageResource = R.drawable.lemon_tree,
                        screenDescription = R.string.screenOneDescription.toString(),
                        contentLabel = R.string.screenOneTitle.toString(),
                        onScreenClick = {
                            currentStep = 2
                            squeezeValue = (2..4).random()
                        })
                }

                2 -> {
                    LemonadeScreen(imageResource = R.drawable.lemon_squeeze,
                        screenDescription = R.string.screenTwoDescription.toString(),
                        contentLabel = R.string.screenTwoTitle.toString(),
                        onScreenClick = {
                            squeezeValue--
                            if(squeezeValue == 0) currentStep = 3
                        })
                }

                3 -> {
                    LemonadeScreen(imageResource = R.drawable.lemon_drink,
                        screenDescription = R.string.screenThreeDescription.toString(),
                        contentLabel = R.string.screenThreeTitle.toString(),
                        onScreenClick = {
                            currentStep = 4
                        })
                }

                4 -> {
                    LemonadeScreen(
                        imageResource = R.drawable.lemon_restart,
                        screenDescription = R.string.screenFourDescription.toString(),
                        contentLabel = R.string.screenFourTitle.toString(),
                        onScreenClick = {
                            currentStep = 1
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LemonadeScreen(
    imageResource: Int,
    screenDescription: String,
    contentLabel: String,
    onScreenClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = contentLabel,
            modifier = Modifier
                .background(
                    color = Color.LightGray, shape = RoundedCornerShape(16.dp)
                )
                .clickable(onClick = onScreenClick)

        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(stringResource(screenDescription.toInt()), fontSize = 18.sp)
    }
}
