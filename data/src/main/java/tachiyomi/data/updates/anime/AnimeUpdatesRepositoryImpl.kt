package tachiyomi.data.updates.anime

import kotlinx.coroutines.flow.Flow
import tachiyomi.data.handlers.anime.AnimeDatabaseHandler
import tachiyomi.domain.updates.anime.model.AnimeUpdatesWithRelations
import tachiyomi.domain.updates.anime.repository.AnimeUpdatesRepository

class AnimeUpdatesRepositoryImpl(
    private val databaseHandler: AnimeDatabaseHandler,
) : AnimeUpdatesRepository {

    override suspend fun awaitWithSeen(seen: Boolean, after: Long, limit: Long): List<AnimeUpdatesWithRelations> {
        return databaseHandler.awaitList {
            animeupdatesViewQueries.getUpdatesBySeenStatus(
                seen = seen,
                after = after,
                limit = limit,
                mapper = animeUpdateWithRelationMapper,
            )
        }
    }

    override fun subscribeAllAnimeUpdates(after: Long, limit: Long): Flow<List<AnimeUpdatesWithRelations>> {
        return databaseHandler.subscribeToList {
            animeupdatesViewQueries.getRecentAnimeUpdates(after, limit, animeUpdateWithRelationMapper)
        }
    }

    override fun subscribeWithSeen(seen: Boolean, after: Long, limit: Long): Flow<List<AnimeUpdatesWithRelations>> {
        return databaseHandler.subscribeToList {
            animeupdatesViewQueries.getUpdatesBySeenStatus(
                seen = seen,
                after = after,
                limit = limit,
                mapper = animeUpdateWithRelationMapper,
            )
        }
    }
}
