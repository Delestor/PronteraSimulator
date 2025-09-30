package com.cadena.ragnarok.connection

import com.cadena.ragnarok.entities.OnlineCharacter

class GlobalOnlineCharacters {
    companion object{
        var onlineCharacterList = mutableListOf<OnlineCharacter>()
        var pendingOnlineChartersList = mutableListOf<Int>()
    }
}
