package mahmoudroid.composebootcamp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mahmoudroid.composebootcamp.ui.theme.ComposeBootcampTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBootcampTheme {
                Init()
                // A surface container using the 'background' color from the theme
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Init() {

    val buttonClickedState = remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                CreateProfileImage()

                // divider
                Divider(
                    modifier = Modifier.padding(top = 16.dp),
                    color = Color.LightGray
                )

                // specifications
                CreateInfo()


                // button
                Button(
                    onClick = {
                        buttonClickedState.value = !buttonClickedState.value
                    }
                ) {
                    Text(text = "click on me")
                }

                if (buttonClickedState.value) {
                    Content()
                }

            }

        }

    }

}

@Composable
private fun Content() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(3.dp),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(2.dp, Color.LightGray)
            ) {
                Portfolio(data = listOf("Project 1", "project 2", "project 3"))
            }
        }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data) { item ->

            Card(
                modifier = Modifier
                    .padding(top = 12.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp) // margin
                        .fillMaxWidth()
                        .padding(16.dp) // padding
                ) {
                    CreateProfileImage(modifier = Modifier.size(100.dp))
                    Column(
                        Modifier
                            .padding(5.dp)
                            .align(Alignment.CenterVertically),
                    ) {
                        Text(text = item, style = MaterialTheme.typography.titleMedium)
                        Text(text = "Fixed String", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }

        }
    }
}


@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(8.dp)) {

        Text(
            text = "Mahmoudroid",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Text(text = "Mahmoudroid@mahmoudroid.com")

        Text(text = "mahmoudroid.github.io")

    }
}

@Composable
private fun CreateProfileImage(modifier: Modifier = Modifier) {
    // circle image view
    Surface(
        modifier = modifier.size(150.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        shadowElevation = 4.dp
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_person),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBootcampTheme {
        Init()
    }
}