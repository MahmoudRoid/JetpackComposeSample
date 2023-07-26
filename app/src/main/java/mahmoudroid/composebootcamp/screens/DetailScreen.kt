package mahmoudroid.composebootcamp.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun DetailScreen(navController: NavController) {

    MyView()

}


@Composable
fun MyView() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Green
        )
    {
        Text(text = "DetailsScreen")
    }
}

@Preview
@Composable
fun MyViewPreview() {
    MyView()
}