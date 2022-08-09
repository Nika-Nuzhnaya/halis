package com.persolog.halis

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*
import kotlin.concurrent.schedule

@Controller
class HalisController(
    private val dataRepository: DataRepository,
    private val roomRepository: RoomRepository,
    private val modeRepository: ModeRepository,
    private val humidityRepository: HumidityRepository
) {

    //TODO: При возврате на главную, если не был запущен режим, не менять влажность и уровень воды
    //TODO: Сделать вывод сообщения а-ля "Tank filled", изменить уровень воды и оставить без изменений вложность
    //TODO: Удалять запись после запуска режима
    //TODO: В будущем (когда заработает working) когда работает программа, с увеличением влажнрости уменьшается currentWaterLevel

    //TODO: чтобы прога как-то просчитывала, хватит ли воды на тот или иной режим
    //      (?)в зависимости от текущих показателей(?)*,
    //      *ведь тогда нужно учитывать скорость, с которой меняется влажность. А если прибор только-только включили, и сразу установили режим, откуда взять эти данные?
    //TODO: Предупреждения, алерты. написаны на странице с интерфейсом
    //TODO: Возможность создать свой авто-режим
    //TODO: Возможность установить таймер включения (и выключения, возможно, тоже)

    var currentHumidity: Byte = 0
    var variableSelectedMode: Byte = 0
    var data = Data()
    var dataAndMode: MutableMap<Data, Byte> = mutableMapOf()
    @GetMapping("/")
    fun main(model: MutableMap<String, Any>): String {

        //var currentHumidity: Humidity = Humidity((20..60).random().toByte())

        var isEmptyTank: Boolean = false

        /*val timer = Timer("schedule", true);

        timer.schedule(1000) {
            while (currentHumidity.humidity < 100.toByte()) {
                humidityRepository.save(currentHumidity)
                val humidities: Iterable<Humidity> = humidityRepository.findAll()
                model["currentHumidity"] = humidities
                currentHumidity.humidity++
            }
        }*/

        model["currentHumidity"] = currentHumidity
        val currentWaterLevel: Byte = (0..5).random().toByte()
        if (currentWaterLevel == 0.toByte() || currentWaterLevel == 1.toByte()) {
            isEmptyTank = true
        }
        model["isEmptyTank"] = isEmptyTank
        model["currentWaterLevel"] = currentWaterLevel

        val modes: Iterable<Mode> = modeRepository.findAll()
        model["modes"] = modes
        return "main"
    }

    @PostMapping("modes")
    fun modeSelection(@RequestParam mode: Byte, model: MutableMap<String, Any>): String {
        variableSelectedMode = mode //передаём в глобальную переменную выбранный пользователем режим
        dataAndMode[data] = mode //передаём в нашу карту запись "объект Данные — Режим"
        model["mode"] = mode

        val rooms: Iterable<Room> = roomRepository.findAll()
        model["rooms"] = rooms
        return "modes-page"
    }

    @PostMapping("rooms")
    fun roomSelection(@RequestParam room: Byte, model: MutableMap<String, Any>): String {
        if (room == 0.toByte()) {
            data = Data(50, 60)
        } else if (room == 1.toByte()) {
            data = Data(40, 50)
        } else if (room == 2.toByte()) {
            data = Data(40, 60)
        }
        dataRepository.save(data)
        model["room"] = room

        val dataEntries: Iterable<Data?> = dataRepository.findAll()
        model["dataEntries"] = dataEntries
        model["mode"] = variableSelectedMode
        model["data"] = data
        return "rooms"
    }

    @PostMapping("result")
    fun dataSelection(@RequestParam firstParam: Byte, @RequestParam secondParam: Byte, model: MutableMap<String, Any>): String {
        data = Data(firstParam, secondParam)
        if (variableSelectedMode == 2.toByte())
            data.isTime = true

        dataRepository.save(data)
        val dataEntries: Iterable<Data?> = dataRepository.findAll()
        model["dataEntries"] = dataEntries
        model["mode"] = variableSelectedMode
        model["data"] = data
        return "result"
    }


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

class Monitoring: Thread() {

}