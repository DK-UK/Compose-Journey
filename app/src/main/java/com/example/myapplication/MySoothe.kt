package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.util.Locale

class MySoothe : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                mySootheApp()
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun searchBar(modifier: Modifier) {

        TextField(
            value = "",
            onValueChange = {},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            placeholder = { Text(text = "Searchbar") },
            modifier = modifier
        )
    }

    @Composable
    fun alignBodyCard() {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(alignBodyList)
            {

                alignYourBody(
                    modifier = Modifier.padding(8.dp),
                    it
                )
            }
        }
    }

    @Composable
    fun alignYourBody(modifier: Modifier, alignBody: AlignBody) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Image(
                painter = painterResource(id = alignBody.imgId),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)

            )
            Text(text = alignBody.imgText, style = MaterialTheme.typography.bodySmall)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun mySootheApp(){
        MyApplicationTheme {
            Scaffold(
                bottomBar = { bottomNavigationForSoothe() }
            ) {it ->
                HomeScreen(Modifier.padding(it))
            }
        }
    }

    @Composable
    fun HomeScreen(modifier: Modifier = Modifier) {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(5.dp)
        ) {
            searchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .heightIn(min = 56.dp)
            )

            HomeSection(
                title = "Align My Body",
                content = { alignBodyCard() },
                modifier = Modifier)

            HomeSection(
                title = "Your Favourite Collection",
                content = { favCollCard() },
                modifier = Modifier
            )

        }
    }

    @Composable
    fun favCollCard() {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            modifier = Modifier.height(120.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(alignBodyList) {
                favouriteCollectionCard(it)
            }
        }
    }

    @Composable
    fun favouriteCollectionCard(favCollecion: AlignBody) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.primary
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = favCollecion.imgId),
                    contentDescription = "fav collection",
                    modifier = Modifier.size(56.dp)
                )
                Text(
                    text = favCollecion.imgText, style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(end = 15.dp)
                )
            }
        }
    }

    @Composable
    fun HomeSection(
        title: String,
        content: @Composable () -> Unit,
        modifier: Modifier
    ) {
        Column(modifier) {
            Text(
                text = title.uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
            )

            content()
            Spacer(Modifier.height(16.dp))
        }
    }

    @Composable
    fun bottomNavigationForSoothe(modifier: Modifier = Modifier) {
        NavigationBar(

        ) {
            NavigationBarItem(selected = true, onClick = { /*TODO*/ }, icon = {
                Icon(imageVector = Icons.Filled.Spa, contentDescription = null)
            },
                label = { Text(text = "Home") })
            NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
                Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = null)
            },
                label = { Text(text = "Profile") })
        }
    }

    @Preview(name = "SearchBar", showBackground = true)
    @Composable
    fun previewSearchBar() {
        mySootheApp()
    }

    data class AlignBody(
        val imgId: Int,
        val imgText: String,
    )

    private val alignBodyList = mutableListOf<AlignBody>(
        AlignBody(
            R.drawable.ic_launcher_background,
            "Inversion1"
        ),
        AlignBody(
            R.drawable.ic_launcher_background,
            "Inversion2"
        ),
        AlignBody(
            R.drawable.ic_launcher_background,
            "Inversion3"
        ),
        AlignBody(
            R.drawable.ic_launcher_background,
            "Inversion4"
        ),
        AlignBody(
            R.drawable.ic_launcher_background,
            "Inversion5"
        ),
        AlignBody(
            R.drawable.ic_launcher_background,
            "Inversion6"
        ),
        AlignBody(
            R.drawable.ic_launcher_background,
            "Inversion7"
        )
    )
}