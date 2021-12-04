package com.coppel.superhero.room

import androidx.room.TypeConverter
import com.coppel.superhero.models.Connections

class ConnectionsConverter {

    @TypeConverter
    fun toString(heroConnections: Connections?): String {
        var connections = ""
        if (heroConnections == null) {
            return connections
        } else {
            connections = "${heroConnections.groupAffiliation}," +
                    heroConnections.relatives
        }
        return connections
    }

    @TypeConverter
    fun toConnections(connections: String?): Connections? {
        var heroConnections: Connections? = Connections(
            "", ""
        )

        if (connections.equals("")) {
            return heroConnections

        } else {
            val connectionsList: List<String> = connections!!.split(",")
            heroConnections = Connections(
                connectionsList[0],
                connectionsList[1]
            )

            return heroConnections
        }
    }
}