package com.ajailani.composelearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.ajailani.composelearning.ui.theme.ComposeLearningTheme
import com.ajailani.composelearning.ui.theme.Teal200
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App {
                ScreenContent()
            }
        }
    }
}

@Composable
fun App(content: @Composable () -> Unit) {
    ComposeLearningTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun ScreenContent() {
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {
        Column {
            /*val painter = painterResource(id = R.drawable.zuko)
            val contentDescription = "Prince Zuko"
            val title = "Prince Zuko"

            ImageCard(
                painter = painter,
                contentDescription = contentDescription,
                title = title
            )

            StylingText(text = "Jetpack Compose")

            Form(scaffoldState)

            NumbersList()

            ConstraintsLayoutExample()

            EffectHandler()

            SimpleAnimationExample()*/
        }
    }
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier
        .fillMaxWidth(0.5f)
        .padding(20.dp)
    ) {
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp
        ) {
            Box(modifier = Modifier.height(200.dp)) {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop
                )

                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
                ) {

                }

                // Image Title
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        text = title,
                        style = TextStyle(color = Color.White, fontSize = 16.sp)
                    )
                }
            }
        }
    }
}

@Composable
fun StylingText(text: String) {
    val fontFamily = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold)
    )

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color(0xFF101010))
        .padding(20.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Teal200,
                        fontSize = 25.sp
                    )
                ) {
                    append("Hello, ")
                }

                append(text)
            },
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Form(scaffoldState: ScaffoldState) {
    var textFieldState by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            OutlinedTextField(
                value = textFieldState,
                label = {
                    Text(text = "Enter your name")
                },
                onValueChange = {
                    textFieldState = it
                },
                singleLine = true,
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = "Hello, $textFieldState"
                        )
                    }
                }) {
                    Text(text = "Hit me")
                }
            }
        }
    }
}

@Composable
fun NumbersList() {
    LazyColumn {
        itemsIndexed(
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
        ) { _, item ->
            NumberItem(number = item)
        }

        /*items(1000) {
            NumberItem(number = it+1)
        }*/
    }
}

@Composable
fun NumberItem(number: Int) {
    Text(
        text = "Number $number",
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 20.dp)
    )
}

@Composable
fun ConstraintsLayoutExample() {
    // Define constraint set
    val constraints = ConstraintSet {
        val firstBox = createRefFor("firstBox")
        val secondBox = createRefFor("secondBox")
        val guideline = createGuidelineFromTop(0.5f)

        constrain(firstBox) {
            top.linkTo(guideline)
            start.linkTo(parent.start)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(secondBox) {
            top.linkTo(parent.top)
            start.linkTo(firstBox.end)
            end.linkTo(parent.end)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        createHorizontalChain(firstBox, secondBox, chainStyle = ChainStyle.Packed)
    }

    // Create constraints layout
    ConstraintLayout(
        constraintSet = constraints,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier
            .background(Color.Green)
            .layoutId("firstBox")
        )

        Box(modifier = Modifier
            .background(Color.Red)
            .layoutId("secondBox")
        )
    }
}

@Composable
fun EffectHandler() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) {
        val counter = produceState(initialValue = 0) {
            /* Put asynchronous code here, such as
            network call or retrieving data from database */

            delay(3000L)
            value = 6
        }

        if (counter.value % 2 == 0 && counter.value > 0) {
            LaunchedEffect(key1 = scaffoldState.snackbarHostState) {
                scaffoldState.snackbarHostState.showSnackbar(
                    "Even number"
                )
            }
        }

        Button(onClick = {  }) {
            Text(text = "Click ${counter.value}")
        }
    }
}

@Composable
fun SimpleAnimationExample() {
    var sizeState by remember { mutableStateOf(200.dp) }
    val sizeAnimate by animateDpAsState(
        targetValue = sizeState,

        // Animation spec
        tween(
            durationMillis = 3000,
            delayMillis = 300,
            easing = FastOutSlowInEasing
        )
        /*spring(
            Spring.DampingRatioHighBouncy
        )*/
        /*keyframes {
            durationMillis = 5000
            sizeState at 0 with LinearEasing
            sizeState * 1.2f at 1000 with FastOutLinearInEasing
            sizeState * 1.5f at 5000
        }*/
    )

    val infiniteTransition = rememberInfiniteTransition()
    val colorAnimate by infiniteTransition.animateColor(
        initialValue = Color.Cyan,
        targetValue = Color.Red,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    Box(
        modifier = Modifier
            .size(sizeAnimate)
            .background(colorAnimate),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { 
            sizeState += 50.dp
        }) {
            Text(text = "Increase size")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App {
        ScreenContent()
    }
}