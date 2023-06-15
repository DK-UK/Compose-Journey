package com.example.myapplication.sideEffectsDemo

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * LaunchedEffect - Executes only one time or on the changes of key passed in
 * LaunchedEffect(key=value){
 * // your code to execute
 * }
 *
 * produceState - is a combination of remember and LaunchedEffect it just
 * combine state in one single block
 *
 * derivedState - is used to track state changes like scroll state changes
 * in lazylist
 */

@Composable
fun sideEffectDemo() {
//    rememberUpdateStateSideEffect()
//    produceSideEffect()
//    produceSideEffect()
    derivedStateSideEffect()
}

@Composable
fun rememberUpdateStateSideEffect() {
    var count by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(key1 = Unit) {
        delay(2000)
        count++
    }

    counter(count)
}

@Composable
fun counter(counter: Int) {

    val count by rememberUpdatedState(newValue = counter)

    LaunchedEffect(key1 = Unit) {
        delay(5000)
        Log.e("Dhaval", "counter: $count")
    }

    Text(text = count.toString(), style = MaterialTheme.typography.headlineMedium)
}

@Composable
fun produceSideEffect() {

    val rotateBy by produceState(initialValue = 0) {
        while (true) {
            delay(16)
            value += 10 % 360
        }
    }
    loader(rotateBy.toFloat())
}

@Composable
fun loader(rotate: Float) {
    Box(contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = Icons.Filled.Refresh, contentDescription = "loading",
                Modifier.rotate(rotate)
            )
            Text(text = "Loading", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun derivedStateSideEffect() {
    showList()
}

@Composable
fun showList() {
    val lazyListState = rememberLazyListState(0)
    var list = List(1000) {
        it
    }
    val showButton by remember{
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 10
        }
    }

    Log.e("Dhaval", "showButton : ${showButton} -- firstItem : ${lazyListState.firstVisibleItemIndex}")

    Box {
        Column {
            LazyColumn(
                state = lazyListState
            ) {
                items(list) {
                    showText(txt = it.toString())
                }
            }
        }

        if (showButton) {
            val scope = rememberCoroutineScope()
            FloatingActionButton(onClick = {
                scope.launch {
                    lazyListState.animateScrollToItem(0)
                }
            },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .navigationBarsPadding()
                    .padding(8.dp)
            ) {
                Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = null)
            }
        }
    }
}

@Composable
fun showText(txt: String) {
    Text(text = txt, style = MaterialTheme.typography.displayMedium,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.CenterHorizontally))
}