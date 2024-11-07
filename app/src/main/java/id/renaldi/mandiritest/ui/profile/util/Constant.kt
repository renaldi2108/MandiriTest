package id.renaldi.mandiritest.ui.profile.util

import id.renaldi.mandiritest.domain.model.Account

object Constants {
    val accountItems = listOf(
        Account(
            "My Orders",
            "You have 10 completed orders"
        ),
        Account(
            "Shipping Address",
            "2 addresses have been set"
        ),
        Account(
            "My Reviews",
            "Reviewed 3 items"
        ),
        Account(
            "Settings",
            "Notifications, password, language"
        ),
    )
}