package mahmoudroid.composebootcamp.util

fun calculateTotalTip(totalTip: Double, tipPercentage: Int): Double {
    return if (totalTip > 1 && totalTip.toString().isNotEmpty())
        (totalTip * tipPercentage) / 100
    else
        0.0
}

fun calculateTotalPerPerson(
    totalTip: Double,
    splitBy: Int,
    tipPercentage: Int,
): Double{
    val bill = calculateTotalTip(totalTip,tipPercentage) + totalTip
    return (bill / splitBy)
}