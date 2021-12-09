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
    const val FIELDS_DATA = "-statistics"
    const val FIELDS_ACHIEVES = "-frags, -max_series"
    const val ACHIEVES_COUNT = 365

    //recycler
    const val TYPE_BANNER = 0
    const val TYPE_CARD_WITH_IMAGE = 1
    const val TYPE_CARD_WITH_TEXT = 2
    const val TYPE_CARD_ACHIEVE = 3
    const val TYPE_BANNER_WITHOUT_IMAGE = 4

    //second account
    /*{
        "status": "ok",
        "access_token": "5395a3b4e15b97bce7d6bdac442f3e60a75652d9",
        "nickname": "vladimir_khodarenok",
        "account_id": "560508080",
        "expires_at": "1640110681"
    }*/
}