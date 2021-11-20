package com.kanyideveloper.chickenapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.kanyideveloper.chickenapp.data.Chicken
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Light
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.kanyideveloper.chickenapp.ui.theme.ChickenAppTheme
import com.kanyideveloper.chickenapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.intellij.lang.annotations.JdkConstants


@AndroidEntryPoint
@ExperimentalCoilApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colors.background) {
                val viewModel: MainViewModel = hiltViewModel()
                val chickens = viewModel.state.value.chickens
                val isLoading = viewModel.state.value.isLoading

                if (isLoading){
                    CircularProgressBar()
                }

                ChickenList(chickens = chickens)
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun ChickenList(chickens: List<Chicken>) {
    LazyColumn {
        items(chickens) { chicken ->
            ChickenRow(chicken)
        }
    }
}

@Composable
@ExperimentalCoilApi
fun ChickenRow(chicken: Chicken){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                Image(painter = rememberImagePainter(
                    data = chicken.imageUrl,
                    builder = { crossfade(true) }
                ),
                    contentDescription = "Rabbit",
                    Modifier
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = chicken.name,
                    fontWeight = Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = chicken.description,
                    fontWeight = Light,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
    }
}

@Composable
fun CircularProgressBar() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}
