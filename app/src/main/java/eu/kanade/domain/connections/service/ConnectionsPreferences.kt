// AM (CONNECTIONS) -->
package eu.kanade.domain.connections.service

import eu.kanade.tachiyomi.data.connections.ConnectionsService
import tachiyomi.core.preference.PreferenceStore

class ConnectionsPreferences(
    private val preferenceStore: PreferenceStore,
) {
    fun connectionsUsername(sync: ConnectionsService) = preferenceStore.getString(
        connectionsUsername(sync.id),
        "",
    )

    fun connectionsPassword(sync: ConnectionsService) = preferenceStore.getString(
        connectionsPassword(sync.id),
        "",
    )

    fun setConnectionsCredentials(sync: ConnectionsService, username: String, password: String) {
        connectionsUsername(sync).set(username)
        connectionsPassword(sync).set(password)
    }

    fun connectionsToken(sync: ConnectionsService) = preferenceStore.getString(
        connectionsToken(sync.id),
        "",
    )

    fun enableDiscordRPC() = preferenceStore.getBoolean("pref_enable_discord_rpc", false)

    fun discordRPCStatus() = preferenceStore.getInt("pref_discord_rpc_status", 1)

    fun discordRPCIncognito() = preferenceStore.getBoolean("pref_discord_rpc_incognito", false)

    fun discordRPCIncognitoCategories() = preferenceStore.getStringSet(
        "discord_rpc_incognito_categories",
        emptySet(),
    )

    fun useChapterTitles() = preferenceStore.getBoolean("pref_discord_rpc_use_chapter_titles", false)

    companion object {

        fun connectionsUsername(syncId: Long) = "pref_anime_connections_username_$syncId"

        private fun connectionsPassword(syncId: Long) = "pref_anime_connections_password_$syncId"

        private fun connectionsToken(syncId: Long) = "connection_token_$syncId"
    }
}
// <-- AM (CONNECTIONS)
