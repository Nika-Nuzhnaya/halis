package com.persolog.halis

import javax.persistence.*

@Entity
class Mode (val name: String,
            @Id
            val id: Byte)

@Entity
class Room (val name: String,
            val humidityFrom: Byte,
            val humidityTill: Byte,
            @Id val id: Byte)

@Entity
class Data (val dataFirst: Byte? = null,
            val dataSecond: Byte? = null,
            var isTime: Boolean = false,
            @Id @GeneratedValue(strategy = GenerationType.AUTO)
            val id: Byte = 0)

@Entity
class Humidity (var humidity: Byte,
                @Id
                val id: Int)