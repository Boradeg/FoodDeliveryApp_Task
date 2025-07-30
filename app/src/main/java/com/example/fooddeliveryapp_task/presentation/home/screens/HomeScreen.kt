package com.example.fooddeliveryapp_task.presentation.home.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fooddeliveryapp_task.R
import com.example.fooddeliveryapp_task.domain.model.Category
import com.example.fooddeliveryapp_task.domain.model.FoodScreenData
import com.example.fooddeliveryapp_task.domain.model.RecommendedItem
import com.example.fooddeliveryapp_task.domain.model.SaleItem
import com.example.fooddeliveryapp_task.presentation.home.model.HomeUiState
import com.example.fooddeliveryapp_task.presentation.home.model.PromoBannerItem
import com.example.fooddeliveryapp_task.presentation.home.viewmodel.HomeViewModel
import com.example.fooddeliveryapp_task.utils.lightBackgroundColors

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val currentPromoItems by viewModel.promoItems.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = uiState) {
            is HomeUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is HomeUiState.Success -> {
                HomeScreenContent(
                    foodData = state.foodScreenData,
                    promoItems = currentPromoItems,
                )
            }

            is HomeUiState.Error -> {
                ErrorStateContent(
                    message = state.message,
                    onRetry = { viewModel.fetchHomeScreenData() },
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun HomeScreenContent(
    foodData: FoodScreenData,
    promoItems: List<PromoBannerItem>,
    modifier: Modifier = Modifier
) {
    val categories = foodData.categories
    val saleItem = foodData.saleItems
    val recommendedItems = foodData.recommendedItems

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        if (promoItems.isNotEmpty()) {
            PromoBannerSlider(items = promoItems)
            Spacer(modifier = Modifier.height(10.dp))
        }

        if (categories.isNotEmpty()) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(18.dp),
            ) {
                items(
                    items = categories,
                ) { category ->
                    CategoryCard(category)
                }
            }
        }

        VoucherRow(
            text = stringResource(R.string.txt_you_have_5_voucher_here),
        )
        Spacer(Modifier.height(16.dp))

        if (recommendedItems.isNotEmpty()) {
            FoodSection(
                title = stringResource(R.string.txt_recommended_for_you),
                items = recommendedItems,
            )
            Spacer(Modifier.height(16.dp))
        }

        if (saleItem.isNotEmpty()) {
            SaleFoodSection(
                title = stringResource(R.string.txt_sale_up_to_50),
                items = saleItem,
            )
            Spacer(Modifier.height(16.dp))
        }


    }
}

@Composable
fun ErrorStateContent(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Error: $message",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text(stringResource(R.string.txt_retry))
        }
    }
}

@Composable
fun PromoBannerSlider(
    modifier: Modifier = Modifier,
    items: List<PromoBannerItem>,
    onClick: () -> Unit = {}
) {
    val pagerState = rememberPagerState(pageCount = { items.size })
    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp)
    ) { page ->
        PromoBanner(item = items[page], onClick = onClick)
    }
}

@Composable
fun PromoBanner(
    item: PromoBannerItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFE3F2FD))
            .clickable { onClick() }
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color(0xFFBA3A00),
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFFFF7B0D))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = item.buttonText,
                        color = Color.White,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
            Image(
                painter = painterResource(item.imageRes),
                contentDescription = stringResource(R.string.txt_banner_image),
                modifier = Modifier.size(100.dp)
            )
        }
    }
}

@Composable
fun CategoryCard(item: Category) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(item.backgroundColor, CircleShape)
                .padding(6.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(painterResource(item.imageResId), contentDescription = item.title)
        }
        Spacer(Modifier.height(10.dp))
        Text(
            text = item.title,
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(R.color.black_300)
        )
    }
}

@Composable
fun VoucherRow(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth() // Added to make it span width like other sections
            .background(Color(0x1A00247D), RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.ic_gift),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(5.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400
                ),
                color = colorResource(R.color.royal_blue),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.ic_right_arrow),
                contentDescription = null
            )
        }
    }
}

@Composable
fun FoodSection(title: String, items: List<RecommendedItem>, onViewAllClick: () -> Unit = {}) {
    Column(Modifier.fillMaxWidth()) {
        SectionHeader(title, onViewAllClick)
        Spacer(Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(
                items = items,
            ) { item ->
                FoodItemCard(item)
            }
        }
    }
}

@Composable
fun SaleFoodSection(title: String, items: List<SaleItem>, onViewAllClick: () -> Unit = {}) {
    Column(Modifier.fillMaxWidth()) {
        SectionHeader(title, onViewAllClick)
        Spacer(Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(
                items = items,
            ) { item ->
                SaleFoodItemCard(item)
            }
        }
    }
}


@Composable
private fun SectionHeader(title: String, onViewAllClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, style = MaterialTheme.typography.titleMedium)
        Text(
            text = stringResource(R.string.txt_view_all),
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.W500),
            color = colorResource(R.color.green_500),
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable { onViewAllClick() }
        )
    }
}

@Composable
fun FoodItemCard(item: RecommendedItem) {


    Column(modifier = Modifier.width(160.dp)) {
        Box {
            Box(
                modifier = Modifier
                    .background(item.backgroundColor, RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Image(
                    painter = painterResource(id = item.imageName),
                    contentDescription = item.title,
                    modifier = Modifier
                        .height(120.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                )
            }


        }

        Spacer(Modifier.height(4.dp))
        Text(
            item.title,
            style = MaterialTheme.typography.labelLarge,
            maxLines = 1
        )
        Text(
            text = "${item.deliveryTime} • ⭐ ${item.rating}",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray
        )

        if (item.tags.isNotEmpty()) {
            Row(Modifier.padding(top = 4.dp)) {
                item.tags.take(2).forEach { tag ->
                    val tagBg = remember { lightBackgroundColors.random() }
                    Box(
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .background(tagBg, RoundedCornerShape(6.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(tag, fontSize = 10.sp, maxLines = 1) // Added maxLines
                    }
                }
            }
        }
    }
}

@Composable
fun SaleFoodItemCard(item: SaleItem) {

    Column(modifier = Modifier.width(160.dp)) {
        Box {
            Box(
                modifier = Modifier
                    .background(item.backgroundColor, RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Image(
                    painter = painterResource(id = item.imageName),
                    contentDescription = item.title,
                    modifier = Modifier
                        .height(120.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                )
            }

            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 12.dp,
                            bottomStart = 0.dp,
                            topEnd = 0.dp,
                            bottomEnd = 12.dp
                        )
                    )
                    .background(colorResource(id = R.color.green_500))
                    .align(Alignment.TopStart)
                    .padding(horizontal = 5.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "-${item.discountPercent}%",
                    color = Color.White,
                    fontSize = 12.sp,
                )
            }
        }

        Spacer(Modifier.height(4.dp))
        Text(
            item.title,
            style = MaterialTheme.typography.labelLarge,
            maxLines = 1
        )
        Text(
            text = "${item.time} • ⭐ ${item.rating}",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray
        )
    }
}
