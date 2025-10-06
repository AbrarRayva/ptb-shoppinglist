package com.example.shoppinglist.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.R

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.myself),
            contentDescription = "Foto Diri"
        )

        Spacer(Modifier.height(16.dp))

        Text("Nama: Muhammad Abrar Rayva")
        Text("NIM: 2311522012")
        Text("Hobi: Mendengarkan lagu dan bermain game")
        Text("TTL: Jakarta, 22 September 2005")
        Text("Peminatan: Fullstack Developer")
    }
}