package mahmoudroid.composebootcamp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mahmoudroid.composebootcamp.ui.theme.ComposeBootcampTheme
import mahmoudroid.composebootcamp.util.calculateTotalPerPerson
import mahmoudroid.composebootcamp.util.calculateTotalTip

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBootcampTheme {
                Init()
            }
        }
    }
}

@Composable
fun Init() {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // TopHeader()
            BIllSection()
        }
    }
}

@Composable
fun TopHeader(totalPerPerson: Double = 134.0) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(15.dp),
        color = Color(0xFFE9D7F7),
        shape = RoundedCornerShape(corner = CornerSize(12.dp))
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val total = "%.2f".format(totalPerPerson)

            Text(
                text = "Total Per Person",
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                text = "$${total}",
                style = MaterialTheme.typography.titleLarge,
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BIllSection() {
    val billValue = remember { mutableStateOf("") }

    val validBillValue = remember(billValue.value) {
        billValue.value.isNotEmpty()
    }

   // val validBillValue = billValue.value.isNotEmpty()

    val splitByState = remember { mutableStateOf(1) }
    val sliderPositionState = remember { mutableStateOf(0f) }
    val tipPercentage = (sliderPositionState.value * 100).toInt()
    val range = IntRange(1, 100)
    val tipAmountState = remember { mutableStateOf(0.0) }
    val totalPerPersonState = remember { mutableStateOf(0.0) }

    TopHeader(totalPerPersonState.value)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.Start
        ) {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Your Label") },
                placeholder = { Text(text = "Your Placeholder/Hint") },
                leadingIcon = { Icon(imageVector = Icons.Default.Add, "") },
                value = billValue.value,
                onValueChange = {
                    billValue.value = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                )
            )

            if (validBillValue){
                Spacer(modifier = Modifier.padding(top = 16.dp))

                // region split
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(text = "Split", modifier = Modifier.fillMaxWidth(0.4f))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                    ) {

                        RoundIconButton(false) {
                            if (splitByState.value < range.last){
                                splitByState.value += 1
                                totalPerPersonState.value = calculateTotalPerPerson(
                                    billValue.value.toDouble(),
                                    splitByState.value,
                                    tipPercentage
                                )
                            }
                        }
                        Text(
                            modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp),
                            text = splitByState.value.toString()
                        )
                        RoundIconButton(true) {
                            if (splitByState.value > 1) {
                                splitByState.value -= 1
                                totalPerPersonState.value = calculateTotalPerPerson(
                                    billValue.value.toDouble(),
                                    splitByState.value,
                                    tipPercentage
                                )
                            }
                        }

                    }

                }
                // endregion

                //region tip
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {

                    Text(text = "Tip", modifier = Modifier.fillMaxWidth(0.5f))

                    Text(text = "$ ${tipAmountState.value}")
                }
                //endregion

                // region percentage and slider

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = "$tipPercentage%")
                    Spacer(modifier = Modifier.height(14.dp))
                    Slider(
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                        value = sliderPositionState.value,
                        steps = 5,
                        onValueChange = { it ->
                            sliderPositionState.value = it

                            billValue.value.let { value ->
                                if (value.isNotEmpty()){

                                    tipAmountState.value = calculateTotalTip(
                                        value.toDouble(),
                                        tipPercentage
                                    )

                                    totalPerPersonState.value = calculateTotalPerPerson(
                                        value.toDouble(),
                                        splitByState.value,
                                        tipPercentage
                                    )

                                }
                            }

                        },
                    )

                }

                //endregion
            }

        }


    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundIconButton(isMinus: Boolean, onIconClicked: () -> Unit) {

    Card(
        modifier = Modifier.size(40.dp),
        shape = CircleShape,
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = { onIconClicked.invoke() }
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Icon(
                if (isMinus) Icons.Default.Delete else Icons.Default.Add,
                contentDescription = "",
                modifier = Modifier.fillMaxSize(0.7f)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBootcampTheme {
        Init()
    }
}