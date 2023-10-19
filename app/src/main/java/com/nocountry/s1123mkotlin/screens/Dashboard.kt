import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.google.android.engage.social.datamodel.Profile
import com.nocountry.s1123mkotlin.login.UserProfile
import com.nocuntry.s1123mkotlin.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun DashboardScreen(
    navController: NavController,
    profiles: List<UserProfile>
) {
    var query by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Dashboard") }
            )
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Columna para los perfiles de usuario
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .background(color = Color(0xFF4D88BC))
            ) {
                if (profiles.isNotEmpty()) {
                    UserProfileAvatar(imageResId = profiles[0].imageResId)

                    Spacer(modifier = Modifier.height(16.dp))

                    profiles.forEach { profile ->
                        UserProfileCard(profile = profile, navController)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Columna para las cardview

            LazyColumn {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        DashboardCard(
                            title = "Consultas",
                            imageResId = R.drawable.consultas,
                            onClick = { navController.navigate("consultations") }
                        )
                        DashboardCard(
                            title = "Recordatorios",
                            imageResId = R.drawable.recordatorio,
                            onClick = { navController.navigate("reminders") }
                        )
                    }
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        DashboardCard(
                            title = "Farmacias",
                            imageResId = R.drawable.farmacia,
                            onClick = { navController.navigate("pharmacies") }
                        )
                        DashboardCard(
                            title = "Síntomas",
                            imageResId = R.drawable.sintomas,
                            onClick = { navController.navigate("symptoms") }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun UserProfileAvatar(imageResId: Int) {
    // Agregar aquí el avatar del perfil del usuario (Imagen)
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = null,
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(Color.Gray)
    )
}

@Composable
fun UserProfileCard(profile: UserProfile, navController: NavController) {
    Card(
        modifier = Modifier
            .size(120.dp)
            .clickable { navController.navigate("profile/${profile.profileId}") }
            .padding(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Mostrar la imagen del perfil
            Image(
                painter = painterResource(id = profile.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Mostrar el nombre del perfil
            Text(
                text = profile.name,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun DashboardCard(title: String, imageResId: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(200.dp)
            .clickable(onClick = onClick)
            .background(colorResource(id = R.color.fondo))
            .padding(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
