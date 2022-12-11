package com.example.tareaonline2_5

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tareaonline2_5.ui.theme.TareaOnline2_5Theme


private val messages: List<MyMessage> = listOf(
    MyMessage("Bullbasaur", "Planta / Veneno", "Ivysaur", R.drawable.bullbasaur),
    MyMessage("Charmander", "Fuego","Charmeleon", R.drawable.charmander),
    MyMessage("Pikachu", "Eléctrico","Raichu", R.drawable.pikachu),
    MyMessage("Snorlax", "Normal","Ninguna", R.drawable.snorlax),
    MyMessage("Squirtle", "Agua","Wartortle", R.drawable.squirtle),
    MyMessage("Abra", "Psíquico","Kadabra", R.drawable.abra),
    MyMessage("Dratini", "Dragón","Dragonair", R.drawable.dratini),
    MyMessage("Eeevee", "Normal","Varias", R.drawable.eevee),
    MyMessage("Jigglypuff", "Normal / Hada", "Wigglytuff", R.drawable.jigglypuff),
    MyMessage("Meowth", "Normal", "Persian", R.drawable.meowth),
    MyMessage("Psyduck", "Agua","Golduck", R.drawable.psyduck),
    MyMessage("Zubat", "Veneno / Volador","Golbat", R.drawable.zubat),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TareaOnline2_5Theme {
                ViewContainer(messages)
            }
        }
    }
}

data class MyMessage(val title: String, val body: String, val evolucion: String, val image: Int)

@Composable
fun ViewContainer(messages: List<MyMessage>){
    Scaffold(
        topBar = { Toolbar()},
        content = { Listado(messages)}
    )
}


@Composable
fun Toolbar(){
    TopAppBar(title = { Text(text = "TareaOnline2_5")})
}

@Composable
fun Listado(messages: List<MyMessage>){
    LazyColumn {
        items(messages){ message ->
            MyComponent(message)
        }
    }
}


@Composable
fun MyComponent(message: MyMessage){
    val context = LocalContext.current // tomo el contexto para usarlo más abajo
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(20.dp)
            .clickable(true, onClick = {
                Toast
                    .makeText(context, "Evolución: ${message.evolucion}", Toast.LENGTH_SHORT)
                    .show()
            })
            ) {
        MyImage(message)
        MyTexts(message)
    }
}

@Composable
fun MyImage(message: MyMessage){
        Image(
            painterResource(message.image),
            message.title,
            modifier = Modifier
                .size(70.dp)
        )
}


@Composable
fun MyTexts(message: MyMessage){
    Column(modifier = Modifier.padding(start = 8.dp)) {
        MyText(
            message.title,
            MaterialTheme.colors.primary,
            MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.height(10.dp))
        MyText(
            message.body,
            MaterialTheme.colors.onBackground,
            MaterialTheme.typography.subtitle2
        )
    }

}

@Composable
fun MyText(text: String, color: Color, style: TextStyle){
    Text(text, color = color, style=style)
}

@Preview(showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewComponent(){
    TareaOnline2_5Theme {
        ViewContainer(messages)
    }
}