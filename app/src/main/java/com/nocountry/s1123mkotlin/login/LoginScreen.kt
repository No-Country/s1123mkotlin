import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.nocuntry.s1123mkotlin.R

@Composable
fun LoginScreen(navController: NavController) {
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current
    val googleSignInClient = remember { GoogleSignIn.getClient(context, GoogleSignInOptions.DEFAULT_SIGN_IN) }

    // Lanzador para el resultado del inicio de sesión con Google
    val googleSignInLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(navController, account)
        } catch (e: ApiException) {
            errorMessage = "Error al iniciar sesión con Google."
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.fondo))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Icon(
            painter = painterResource(id = R.drawable.medichild),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )

        GoogleSignInButton(googleSignInClient, googleSignInLauncher)

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Yellow)
        }
    }
}

@Composable
fun GoogleSignInButton(
    googleSignInClient: GoogleSignInClient,
    googleSignInLauncher: ActivityResultLauncher<Intent>
) {
    Button(
        onClick = {
            // Iniciar el proceso de inicio de sesión con Google
            val signInIntent = googleSignInClient.signInIntent
            googleSignInLauncher.launch(signInIntent)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp),
        shape = RoundedCornerShape(22.dp),
        colors = ButtonDefaults.buttonColors(
            colorResource(id = R.color.fondoBotones)
        )
    ) {
        Text(text = "Iniciar Sesión con Google", color= Color.DarkGray)
    }
}

fun firebaseAuthWithGoogle(navController: NavController, account: GoogleSignInAccount) {
    val auth = FirebaseAuth.getInstance()
    val googleToken = account.idToken
    val googleCredential = GoogleAuthProvider.getCredential(googleToken, null)

    auth.signInWithCredential(googleCredential)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                navController.navigate("Dashboard") // Redirigir al usuario al dashboard
            } else {
                val exception = task.exception
                if (exception != null) {
                    val errorMessage = exception.message
                }
            }
        }
}
