package com.example.appstore.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appstore.DataProvider
import com.example.appstore.components.CustomTopAppBar

@Composable
fun AppListPage(navController: NavHostController){

    Box(modifier = Modifier.fillMaxSize()) {
        ScaffoldWithTopBar(navController)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldWithTopBar(navController: NavHostController) {
    Scaffold(
        topBar = {
            CustomTopAppBar(navController, "AppList", true)
        }, content = {
            AppListContent()
        })


}

@Composable
fun AppListContent() {
    val appsList = remember { DataProvider.appList }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = appsList,
            itemContent = {
                AppListItem(apps = it)
            })
    }

}

