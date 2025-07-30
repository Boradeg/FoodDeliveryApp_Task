package com.example.fooddeliveryapp_task.presentation.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryapp_task.R

val latoFontFamily = FontFamily(
    Font(R.font.lat_black, FontWeight.Black),
    Font(R.font.lato_bold, FontWeight.Bold),
    Font(R.font.lato_medium, FontWeight.Medium),
    Font(R.font.lato_semi_bold, FontWeight.SemiBold),
    Font(R.font.lato_light, FontWeight.Light),
    Font(R.font.lato_regular, FontWeight.Normal),
    Font(R.font.lato_thin, FontWeight.Thin),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodySmall = TextStyle(
        fontSize = 14.sp,
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 21.sp,
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
    ),
    bodyLarge = TextStyle(
        fontSize = 18.sp,
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 28.sp,
    ),

    displaySmall = TextStyle(
        fontSize = 24.sp,
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 36.sp,
    ),
    displayMedium = TextStyle(
        fontSize = 32.sp,
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 48.sp,
    ),
    displayLarge = TextStyle(
        fontSize = 40.sp,
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 40.sp,
    ),

    labelMedium = TextStyle(
        fontSize = 13.sp,
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 19.sp,
    ),

    labelSmall = TextStyle(
        fontSize = 11.sp,
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 19.sp,
    ),


    headlineMedium = TextStyle(
        fontSize = 20.sp,
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp,
    ),
)