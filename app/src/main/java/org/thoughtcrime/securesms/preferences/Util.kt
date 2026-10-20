package org.thoughtcrime.securesms.preferences

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import network.loki.messenger.R
import org.thoughtcrime.securesms.ApplicationContext

fun Context.sendInvitationToUseSession() {

    val DOWNLOAD_URL = "https://getsession.org/download"

    val accountId = (applicationContext as ApplicationContext).loginStateRepository.get().requireLocalNumber()

    // Landing page (session-link) that opens the session://add#<id> deeplink handled by HomeActivity
    val txt = "https://bgurmendi.github.io/add/#$accountId"

    Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            txt
        )
        type = "text/plain"
    }.let { Intent.createChooser(it, getString(R.string.sessionInviteAFriend)) }
        .let(::startActivity)
}

fun Context.copyPublicKey() {
    val accountId = (applicationContext as ApplicationContext).loginStateRepository.get().requireLocalNumber()
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("Account ID", accountId)
    clipboard.setPrimaryClip(clip)
    Toast.makeText(this, R.string.copied, Toast.LENGTH_SHORT).show()
}
