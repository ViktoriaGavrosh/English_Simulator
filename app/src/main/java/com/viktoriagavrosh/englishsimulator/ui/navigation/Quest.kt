package com.viktoriagavrosh.englishsimulator.ui.navigation

import androidx.annotation.Keep
import com.viktoriagavrosh.englishsimulator.ui.navigation.Quest.EnToRu
import com.viktoriagavrosh.englishsimulator.ui.navigation.Quest.RuToEn

/**
 * Constants describes what action will be shown by Ui
 *
 * @see RuToEn
 * @see EnToRu
 * @param text text for ui
 */
@Keep
enum class Quest(val text: String) {

    /**
     * Quest "Translate from Russian into English"
     */
    RuToEn(text = "На английский"),

    /**
     * Quest "Translate from English into Russian"
     */
    EnToRu(text = "На русский")
}

