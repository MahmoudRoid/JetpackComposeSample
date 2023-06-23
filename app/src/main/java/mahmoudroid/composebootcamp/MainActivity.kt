package mahmoudroid.composebootcamp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mahmoudroid.composebootcamp.ui.theme.ComposeBootcampTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBootcampTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {

    val moneyCount = remember { mutableStateOf(100) }

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "${moneyCount.value} $")
            Spacer(modifier = Modifier.height(90.dp))
            CreateCircle(){
                moneyCount.value += 10
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateCircle(updateCounter: () -> Unit) {
    Log.i("TAG", "CreateCircle: ")
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .padding(all = 3.dp)
            .size(105.dp),
        shape = CircleShape,
        colors = CardDefaults.cardColors(Color.Cyan),
        onClick = {
            Log.i("TAG", "CreateCircle: onClick")
            updateCounter.invoke()
        }
    ) {

        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
            Text(text = "TAP")
        }
    }

}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBootcampTheme {
        MyApp()
    }
}