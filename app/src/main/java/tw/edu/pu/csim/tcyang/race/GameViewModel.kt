package tw.edu.pu.csim.tcyang.race

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {

    var screenWidthPx by mutableStateOf(0f)
        private set
    var screenHeightPx by mutableStateOf(0f)
        private set

    var gameRunning by mutableStateOf(false)

    var circleX by mutableStateOf(0f)
    var circleY by mutableStateOf(0f)

    var score by mutableStateOf(0)

    fun SetGameSize(w: Float, h: Float) {
        screenWidthPx = w
        screenHeightPx = h

        // ⭐ 初始位置：垂直置中、最左邊
        circleX = 100f
        circleY = screenHeightPx / 2
    }

    fun StartGame() {
        score = 0

        viewModelScope.launch {
            while (gameRunning) {
                delay(100)

                // ⭐ 加速
                circleX += 25

                // 撞右邊 → 回左邊 +1 分
                if (circleX >= screenWidthPx - 100) {
                    circleX = 100f
                    score++
                }
            }
        }
    }

    fun MoveCircle(x: Float, y: Float) {
        circleX += x
        circleY += y
    }
}
