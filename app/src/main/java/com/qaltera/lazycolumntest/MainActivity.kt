package com.qaltera.lazycolumntest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qaltera.lazycolumntest.ui.theme.LazyColumnTestTheme
import com.qaltera.lazycolumntest.ui.theme.TestDataBlock
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumnTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    runTest()
                }
            }
        }
    }
}

@Composable
fun runTest() {
    var itemsState: MutableState<List<TestDataBlock>> = remember {
        mutableStateOf(listOf())
    }

    LaunchedEffect(Unit) {
        delay(1000)
        itemsState.value = MutableList<TestDataBlock>(30) { rowIndex ->
            val id = rowIndex
            TestDataBlock(id = id.toString(), data = 1)
        }
        delay(1000)
        itemsState.value = MutableList<TestDataBlock>(30) { rowIndex ->
            val id = rowIndex
            TestDataBlock(id = id.toString(), data = 2)
        }
        delay(1000)
        itemsState.value = listOf()
    }

    List(dataItems = itemsState.value)
}

@Preview
@Composable
fun List(
    dataItems: List<TestDataBlock> = listOf(TestDataBlock("1",1), TestDataBlock("2",2))
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(items = dataItems,
            key = { index, item ->
                item.id
            }
        ) { rowIndex, rowItem ->
            drawElement(rowItem)
        }
    }
}

@Composable
fun drawElement(rowItem: TestDataBlock) {
    SideEffect {
        println("[111] recreate elementitem ${rowItem.id}")
    }
    Text(text = "${rowItem.data}")
}