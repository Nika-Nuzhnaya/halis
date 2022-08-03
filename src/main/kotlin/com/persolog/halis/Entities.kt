package com.persolog.halis

import javax.persistence.*

@Entity
class Mode (val name: String,
            @Id
            val id: Byte)

@Entity
class Data (var from: Byte? = null,
            var till: Byte? = null,
            var isTime: Boolean = false,
            @Id @GeneratedValue(strategy = GenerationType.AUTO)
            val id: Byte = 0)

/*@Entity
class Selected (var modeId: Byte? = null,
                @ElementCollection
                var from: Byte,
                var till: Byte,
                @Id @GeneratedValue(strategy = GenerationType.AUTO)
                val id: Byte) {
    constructor(modeId: Byte?): this(null, null, 0)
}*/

@Entity
class Room (val name: String,
            val humidityFrom: Byte,
            val humidityTill: Byte,
            @Id val id: Byte)