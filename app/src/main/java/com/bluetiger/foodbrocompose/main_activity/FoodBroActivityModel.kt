package com.bluetiger.foodbrocompose.main_activity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.FoodBroActivityEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class FoodBroActivityModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedNavRoute = mutableStateOf(NavRoutes.HOME)
    val selectedNavRoute: State<NavRoutes> = _selectedNavRoute

    private val _selectAbleNavRoutes = mutableStateListOf<NavRoutes>()
    val selectAbleNavRoutes: SnapshotStateList<NavRoutes> = _selectAbleNavRoutes

    lateinit var startDestination : NavRoutes

    init {
        val user = savedStateHandle.get<User>("User")
        if (user == null) {
            _selectAbleNavRoutes.addAll(
                NavRoutes.values().filter { !it.userDependingRoute }
            )
            startDestination = NavRoutes.USER_LIST
        } else {
            _selectAbleNavRoutes.addAll(
                NavRoutes.values().toList()
            )
            startDestination = NavRoutes.HOME
        }
    }

    fun onEvent(event: FoodBroActivityEvent) {
        when (event) {
            is FoodBroActivityEvent.NavRouteChanged -> {
                _selectedNavRoute.value = event.navRoutes
            }
        }
    }

    /*
    @Composable
    fun rememberFoodBroNavController(): NavHostController = rememberNavController()

    override lateinit var navController: NavHostController

    lateinit var scope: CoroutineScope
    lateinit var drawerState: DrawerState

    @Composable
    fun getUserNameWithoutAtSymbol() =
        "Graph."

    @Composable
    fun getNavRouteItems() : List<NavRoutes> = NavRoutes.values().toList()
    /*
        if (Graph.isDemoUser()) NavRoutes.values().filter { !it.userDependingRoute }
        else NavRoutes.values().toList()

     */

    val selectedItem = mutableStateOf(NavRoutes.HOME)

    @Composable
    fun InitComposAbles() {
        navController = rememberFoodBroNavController()
        scope = rememberCoroutineScope()
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        selectedItem.value =  NavRoutes.USER_LIST
    }



     */


}