package com.example.fooddeliveryapp_task.presentation.home.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryapp_task.R
import com.example.fooddeliveryapp_task.presentation.home.component.CommonButton
import com.example.fooddeliveryapp_task.presentation.home.model.CartItem
import com.example.fooddeliveryapp_task.presentation.home.viewmodel.HomeViewModel
import com.example.fooddeliveryapp_task.utils.lightBackgroundColors
import com.example.fooddeliveryapp_task.utils.toCurrencyString


@Composable
fun OrderScreen(viewModel: HomeViewModel) {
    val cartItems by viewModel.cartItems.collectAsState()
    val subtotal by viewModel.subtotal.collectAsState()
    val deliveryFee by viewModel.deliveryFee.collectAsState()
    val tax by viewModel.tax.collectAsState()
    val totalOrderPrice by viewModel.totalOrderPrice.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
                if (cartItems.isEmpty()) {
                    Text(
                        stringResource(R.string.txt_your_cart_is_empty),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }

            items(cartItems, key = { it.id }) { item ->
                CartItemCard(
                    item = item,
                    onIncrement = { viewModel.incrementQuantity(item.id) },
                    onDecrement = { viewModel.decrementQuantity(item.id) }
                )
            }

            if (cartItems.isNotEmpty()) {
                item {
                    Text(
                        stringResource(R.string.txt_order_summary),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            SummaryRow(stringResource(R.string.txt_subtotal), subtotal.toCurrencyString())
                            SummaryRow(stringResource(R.string.txt_delivery), deliveryFee.toCurrencyString())
                            SummaryRow(stringResource(R.string.txt_total_tax), tax.toCurrencyString())
                            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                            SummaryRow(stringResource(R.string.txt_total), totalOrderPrice.toCurrencyString(), bold = true)
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Text(stringResource(R.string.txt_information), style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(10.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            InfoRow(
                                stringResource(R.string.txt_your_delivery_address),
                                "Green Complex, Hyderabad"
                            )
                            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                            InfoRow(stringResource(R.string.txt_payment_method), "Cash")
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }

        if (cartItems.isNotEmpty()) {
            CommonButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                text = stringResource(R.string.txt_order_now) + " (${totalOrderPrice.toCurrencyString()})",
                onClick = {

                }
            )
        }
    }
}

@Composable
fun CartItemCard(
    item: CartItem,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(vertical = 8.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val backgroundColor = remember {
                lightBackgroundColors.random()
            }
            Box(
                modifier = Modifier
                    .size(70.dp) // Adjusted size
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = item.imageResId),
                    contentDescription = item.name,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(4.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    item.name,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
                    color = Color.Black
                )
                Text(
                    item.pricePerItem.toCurrencyString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(R.color.green_500),
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 8.dp), // Added padding
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = item.totalPrice.toCurrencyString(), // Use calculated total price
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.green_500),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp)) // More rounded
                        .background(Color(0xFFF0F0F0)) // Slightly different gray
                        .padding(horizontal = 6.dp, vertical = 4.dp)
                ) {
                    QuantityButton(
                        iconRes = R.drawable.outline_remove_24,
                        contentDescription = "Remove one ${item.name}",
                        onClick = onDecrement
                    )

                    Text(
                        text = item.quantity.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = colorResource(R.color.black),
                        modifier = Modifier.padding(horizontal = 4.dp) // Padding for quantity text
                    )

                    QuantityButton(
                        iconRes = R.drawable.baseline_add_24,
                        contentDescription = "Add one ${item.name}",
                        onClick = onIncrement
                    )
                }
            }
        }
    }
}

@Composable
fun QuantityButton(
    iconRes: Int,
    contentDescription: String,
    onClick: () -> Unit,
    enabled: Boolean = true // Optional: to disable button (e.g., minus when quantity is 1)
) {
    Box(
        modifier = Modifier
            .size(28.dp)
            .clip(RoundedCornerShape(8.dp)) // Consistent rounding
            .background(Color.White)
            .clickable(enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(iconRes),
            contentDescription = contentDescription,
            modifier = Modifier.size(18.dp)
        )
    }
}


@Composable
fun SummaryRow(label: String, value: String, bold: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp), // Added some vertical padding
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            label,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
            style = if (bold) MaterialTheme.typography.titleSmall else MaterialTheme.typography.bodyMedium
        )
        Text(
            value,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
            style = if (bold) MaterialTheme.typography.titleSmall else MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) { // Increased padding
        Text(
            label,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Gray
        ) // LabelMedium for better visibility
        Spacer(modifier = Modifier.height(4.dp))
        Text(value, style = MaterialTheme.typography.bodyLarge) // BodyLarge for value
    }
}

