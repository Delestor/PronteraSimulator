package com.cadena.ragnarok.connection

class GlobalConnection {
    companion object{
        var clientId: Int = -1
        var connection: ConnectionSocket = ConnectionSocket()
    }
}
