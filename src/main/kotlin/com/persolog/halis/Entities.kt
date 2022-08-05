package com.persolog.halis

import javax.persistence.*

@Entity
class Mode (val name: String,
            @Id
            val id: Long)

@Entity
class Room (val name: String,
            val humidityFrom: Byte,
            val humidityTill: Byte,
            @Id val id: Long)

@Entity
class Data (val dataFirst: Byte? = null,
            val dataSecond: Byte? = null,
            var isTime: Boolean = false,
            @Id @GeneratedValue(strategy = GenerationType.AUTO)
            val id: Long = 0)