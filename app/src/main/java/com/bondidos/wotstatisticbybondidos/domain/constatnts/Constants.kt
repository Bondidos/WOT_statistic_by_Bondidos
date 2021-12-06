package com.bondidos.wotstatisticbybondidos.domain.constatnts

object Constants {

    // retrofit
    const val BASE_URL = "https://api.worldoftanks.eu"

    // preferences
    const val THEME_PREFERENCE = "switch_to_dark"

    // user (Preferences)
    const val USER_NICKNAME = "nickname"
    const val USER_ACCOUNT_ID = "account_id"
    const val USER_TOKEN = "access_token"
    const val TOKEN_EXPIRES = "expires_at"

    //navigation
    const val NAVIGATE_TO_LOGIN = "LogIn"
    const val NAVIGATE_CONTINUE = "Continue"

    // webView Fragment
    const val REDIRECT_URI = "https://developers.wargaming.net/reference/all/wot/auth/login/"
    const val LOGIN_URL = "https://api.worldoftanks.eu/wot/auth/login/?application_id=5d489c586717c2b76ade8bea16607167&redirect_uri=https%3A%2F%2Fdevelopers.wargaming.net%2Freference%2Fall%2Fwot%2Fauth%2Flogin%2F"

    // repository
    const val APPLICATION_ID = "5d489c586717c2b76ade8bea16607167"
    const val ACCESS_TOKEN = "b98830431fc22d1565bfc3a84f59077f8baa0aff"
    const val ACCOUNT_ID = 560508396
    const val EXTRA = "statistics.epic, statistics.random"
    const val FIELDS = "-statistics.frags, -statistics.clan, -statistics.regular_team, -statistics.company, -statistics.historical, -statistics.team"
    const val ACHIEVES_COUNT = 365

    //recycler
    const val TYPE_BANNER = 0
    const val TYPE_CARD_WITH_IMAGE = 1
    const val TYPE_CARD_WITH_TEXT = 2
}