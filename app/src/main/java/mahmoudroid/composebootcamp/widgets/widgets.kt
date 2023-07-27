package mahmoudroid.composebootcamp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import mahmoudroid.composebootcamp.R
import mahmoudroid.composebootcamp.model.Movie
import mahmoudroid.composebootcamp.model.getMovies


@Preview
@Composable
fun MovieRowItem(
    item: Movie = getMovies()[0],
    clickCallback: (String) -> Unit = {}
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(130.dp)
        .clickable {
            clickCallback.invoke(item.id)
        }
        .padding(4.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            //modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                shadowElevation = 0.dp,
                color = Color.Transparent
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.images[0])
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.FillBounds,
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
                    modifier = Modifier.clip(CircleShape)
                )

            }

            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = item.title, style = MaterialTheme.typography.headlineSmall)
                Text(text = "Director : ${item.director}", style = MaterialTheme.typography.labelSmall)
                Text(text = "Released: ${item.year}", style = MaterialTheme.typography.labelSmall)
            }


        }

    }
}