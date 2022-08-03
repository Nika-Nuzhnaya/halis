package com.persolog.halis

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class HalisConfiguration {
    @Bean
    fun databaseInitializer (modeRepository: ModeRepository,
                             roomRepository: RoomRepository,
                             dataRepository: DataRepository) = ApplicationRunner {
        modeRepository.save (Mode (
            name = "Auto mode",
            id = 0
        ))
        modeRepository.save (Mode (
            name = "Set the desired humidity range",
            id = 1
        ))
        modeRepository.save (Mode (
            name = "Set the desired time range",
            id = 2
        ))

        roomRepository.save (Room (
            name = "Children's room",
            humidityFrom = 50,
            humidityTill = 60,
            id = 0
        ))
        roomRepository.save (Room (
            name = "Bedroom",
            humidityFrom = 40,
            humidityTill = 50,
            id = 1
        ))
        roomRepository.save (Room (
            name = "Other rooms",
            humidityFrom = 40,
            humidityTill = 60,
            id = 2
        ))
    }
}