package com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements

import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.Month.April
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.Month.August
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.Month.December
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.Month.EmptyMonth
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.Month.February
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.Month.January
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.Month.July
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.Month.June
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.Month.March
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.Month.May
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.Month.November
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.Month.October
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.Month.September

enum class Month {
    EmptyMonth,
    January,
    February,
    March,
    April,
    May,
    June,
    July,
    August,
    September,
    October,
    November,
    December
}

fun getMonthByNumber(num: Int): Month {
    return when (num) {
        1 -> January
        2 -> February
        3 -> March
        4 -> April
        5 -> May
        6 -> June
        7 -> July
        8 -> August
        9 -> September
        10 -> October
        11 -> November
        12 -> December
        else -> EmptyMonth
    }
}
