package com.nocountry.s1123mkotlin.screens

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nocuntry.s1123mkotlin.R

@Composable
fun Dashboard(
    navController: NavController,
    onRemindersClick: () -> Unit,
    onConsultationsClick: () -> Unit,
    onPharmacyClick: () -> Unit,
    onSymptomsClick: () -> Unit,
    onUserProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.fondo))
            .padding(16.dp)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "MediChild",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            backgroundColor = colorResource(id = R.color.TopAppBar),
            actions = {
                // perfil de usuario
                IconButton(onClick = onUserProfileClick) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Perfil Pablo",
                    )
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Perfil Juana",
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                CardView("Recordatorios", R.drawable.recordatorio, navController, "recordatorios")
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                CardView("Síntomas", R.drawable.sintomas,navController, "sintomas")
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                CardView("Consultas", R.drawable.consultas, navController, "consultas")
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                CardView("Farmacias", R.drawable.farmacia, navController, "farmacias")
            }
        }
    }
}


@Composable
fun CardView(
    title: String,
    imageResourceName: Int,
    navController: NavController,
    route: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(route) },
        elevation = 8.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Image(
                painter = painterResource(id = imageResourceName),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun DashboardPreview() {
    val navController = rememberNavController()
    Dashboard(
        navController = navController,
        onConsultationsClick = {},
        onPharmacyClick = {},
        onSymptomsClick = {},
        onUserProfileClick = {},
        onRemindersClick = {}
    )
}

private fun getDrawableResourceId(name: String) =
    Resources.getSystem().getIdentifier(name, "drawable", "android")