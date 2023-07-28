package mahmoudroid.composebootcamp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import mahmoudroid.composebootcamp.R
import mahmoudroid.composebootcamp.model.Movie
import mahmoudroid.composebootcamp.model.getMovies


@Preview
@Composable
fun MovieRowItem(
    item: Movie = getMovies()[0],
    clickCallback: (String) -> Unit = {}
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Card(modifier = Modifier
        .fillMaxWidth()
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
                Text(
                    text = "Director : ${item.director}",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(text = "Released: ${item.year}", style = MaterialTheme.typography.labelSmall)

                AnimatedVisibility(visible = expanded) {

                    Column {

                        Text(
                            text = buildAnnotatedString {

                                withStyle(
                                    style = SpanStyle(
                                        color = Color.DarkGray,
                                        fontSize = 13.sp
                                    )
                                ) {
                                    append(" Plot: ")
                                }

                                withStyle(
                                    style = SpanStyle(
                                        color = Color.DarkGray,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Light
                                    )
                                ) {
                                    append(item.plot)
                                }

                            },
                            modifier = Modifier.padding(6.dp)
                        )

                        Divider(modifier = Modifier.padding(6.dp))

                        Text(
                            text = "Director: ${item.director}",
                            style = MaterialTheme.typography.labelSmall
                        )

                        Text(
                            text = "Actors: ${item.actors}",
                            style = MaterialTheme.typography.labelSmall
                        )

                        Text(
                            text = "Rating: ${item.rating}",
                            style = MaterialTheme.typography.labelSmall
                        )

                    }

                }

                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "",
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded = !expanded
                        },
                )
            }


        }

    }
}