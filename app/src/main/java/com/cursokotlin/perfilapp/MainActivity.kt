package com.cursokotlin.perfilapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Definimos un color claro personalizado para el fondo de toda la app
            val customLightBackground = Color(0xFFF7F5FA)

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = customLightBackground // <--- Aquí aplicamos el color claro
            ) {
                MainContainer()
            }
        }
    }
}

@Composable
fun MainContainer() {
    val context = LocalContext.current
    val customPurple = Color(0xFF673AB7)

    Scaffold(
        containerColor = Color.Transparent, // Hacemos transparente el Scaffold para que muestre el fondo del Surface
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Toast.makeText(context, "¡Acción realizada!", Toast.LENGTH_SHORT).show()
                },
                containerColor = customPurple,
                contentColor = Color.White
            ) {
                Text(text = "+", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            }
        }
    ) { _ ->
        Box(modifier = Modifier.fillMaxSize()) {
            ProfileScreen()
        }
    }
}

@Composable
fun ProfileScreen() {
    val customPurple = Color(0xFF673AB7)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 32.dp)
    ) {
        // 1. PORTADA
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.portada),
                contentDescription = "Imagen de portada",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)),
                contentScale = ContentScale.Crop
            )

            // Tarjeta flotante superior izquierda
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = Color.Black.copy(alpha = 0.55f),
                modifier = Modifier
                    .padding(top = 40.dp, start = 16.dp)
                    .align(Alignment.TopStart)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "UPP", fontWeight = FontWeight.Bold, fontSize = 10.sp, color = Color.Black)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Universidad Politécnica",
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "de Pachuca",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 9.sp
                        )
                    }
                }
            }

            // Botón de compartir flotante superior derecha
            Box(
                modifier = Modifier
                    .padding(top = 40.dp, end = 16.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.55f))
                    .align(Alignment.TopEnd),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "🔗", fontSize = 16.sp)
            }
        }

        // 2. CONTENEDOR GENERAL DE PERFIL Y DATOS
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            // Foto de perfil e insignia
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-45).dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.perfil),
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .size(110.dp)
                            .border(4.dp, Color(0xFFF7F5FA), CircleShape) // Borde del mismo color del fondo para fundirse
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.BottomEnd)
                            .background(customPurple, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "📷", fontSize = 14.sp)
                    }
                }

                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = customPurple.copy(alpha = 0.1f),
                    modifier = Modifier.padding(bottom = 15.dp)
                ) {
                    Text(
                        text = "🎓 UPP Software",
                        color = customPurple,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                    )
                }
            }

            // Textos descriptivos
            Column(modifier = Modifier.offset(y = (-30).dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Fernanda Hernández",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = " ✔️", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Ingeniera en Software | Desarrolladora Mobile & Web",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = customPurple
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Apasionada por el desarrollo de aplicaciones móviles, arquitectura de software e inteligencia artificial en la UPP.",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Lenguajes
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "💻 LENGUAJES DE PROGRAMACIÓN",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
                Text(
                    text = "Desliza ➔",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            val lenguajes = listOf("Python", "Java", "Kotlin", "C#", "C++", "JavaScript", "PHP", "HTML", "CSS","MySQL")

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(lenguajes) { lenguaje ->
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = Color.White, // Tarjetas blancas para destacar sobre el fondo claro
                        modifier = Modifier.height(44.dp),
                        shadowElevation = 1.dp
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "🔹", fontSize = 14.sp)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = lenguaje,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Mapa
            Text(
                text = "Ubicación en la UPP",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                val uppLocation = LatLng(20.0765, -98.7695)
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(uppLocation, 15f)
                }

                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState
                ) {
                    Marker(
                        state = MarkerState(position = uppLocation),
                        title = "Universidad Politécnica de Pachuca",
                        snippet = "Hidalgo, México"
                    )
                }
            }
        }
    }
}