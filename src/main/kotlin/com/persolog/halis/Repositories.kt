package com.persolog.halis

import org.springframework.data.repository.CrudRepository

interface ModeRepository: CrudRepository<Mode, Byte> {
}

interface RoomRepository: CrudRepository<Room, Byte> {
}

interface DataRepository: CrudRepository<Data, Byte> {
}

interface HumidityRepository: CrudRepository<Humidity, Int> {
}