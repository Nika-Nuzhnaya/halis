package com.persolog.halis

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class HalisController(
    private val dataRepository: DataRepository
) {
    val currentHumidity = (20..60).random().toByte()
    var variableSelectedMode: Byte = 0
    var data = Data()
    var dataAndMode: MutableMap<Data, Byte> = mutableMapOf()

    @GetMapping("/")
    fun main(model: MutableMap<String?, Any?>): String {
        var isEmptyTank: Boolean = false
        model["currentHumidity"] = currentHumidity
        val currentWaterLevel: Byte = (0..5).random().toByte()
        if (currentWaterLevel == 0.toByte() || currentWaterLevel == 1.toByte()) {
            isEmptyTank = true
        }
        model["isEmptyTank"] = isEmptyTank
        model["currentWaterLevel"] = currentWaterLevel

        return "main"
    }

    @PostMapping("modes")
    fun modeSelection(@RequestParam mode: Byte, model: MutableMap<String, Byte>): String {
        variableSelectedMode = mode //передаём в глобальную переменную выбранный пользователем режим
        dataAndMode[data] = mode //передаём в нашу карту объект Данные — режим
        dataRepository.save(data) //записываем Данные в репу
        return "modes-page"
    }

    /*@PostMapping("rooms")
    fun roomSelection(@RequestParam room: Byte, selectedMode: Selected): String {
        selectedMode.data["roomId"] = selectedRoom.toShort()
        return "rooms"
    }*/



    /*
    @PostMapping
    fun working () {
        var indicatorSmaller: Byte = (20..70).random().toByte()
        var indicatorBigger: Byte = (20..70).random().toByte()
        if (indicatorSmaller > indicatorBigger) {
            indicatorSmaller = indicatorBigger.also {
                indicatorBigger = indicatorSmaller
            }
        }

        /*inline fun timer(
            name: String? = null,
            daemon: Boolean = false,
            initialDelay: Long = 0.toLong(),
            period: Long,
            crossinline action: TimerTask.() -> Unit
        ): Timer {
        }*/

        if (currentHumidity < indicatorSmaller)
            println("Turn on")
        if (currentHumidity >= indicatorBigger)
            println("Turn off")
    }*/

}