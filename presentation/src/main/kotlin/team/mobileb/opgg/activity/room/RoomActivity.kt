package team.mobileb.opgg.activity.room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import team.mobileb.opgg.theme.MaterialTheme
import team.mobileb.opgg.theme.SystemUiController
import team.mobileb.opgg.util.config.IntentConfig

class RoomActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SystemUiController(window).setNavigationBarColor(Color.White)
        setContent {
            MaterialTheme {
                Setup(intent.getIntExtra(IntentConfig.RoomActivityInitStateExtra, RoomState.Create))
            }
        }
    }

    @Composable
    private fun Setup(initState: Int) {
        var state by remember { mutableStateOf(initState) }

        Crossfade(state) { _state ->
            when (_state) {
                RoomState.Join -> {
                    JoinRoom(window = window, onStateChangeAction = { state = RoomState.Create })
                }
                RoomState.Create -> {
                    CreateRoom(window = window, onStateChangeAction = { state = RoomState.Join })
                }
            }
        }
    }
}
