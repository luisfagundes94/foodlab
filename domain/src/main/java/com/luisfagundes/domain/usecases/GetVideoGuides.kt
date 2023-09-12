package com.luisfagundes.domain.usecases

import com.luisfagundes.domain.repositories.VideoRepository
import com.luisfagundes.framework.coroutines.IoDispatcher
import com.luisfagundes.framework.network.Result
import com.luisfagundes.framework.network.getResultOrThrow
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class GetVideoGuides @Inject constructor(
    private val repository: VideoRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke() = withContext(dispatcher) {
        return@withContext try {
            val data = repository.getVideoGuides().getResultOrThrow()
            val sortedData = data.sortedBy { it.rating }
            Result.Success(sortedData)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}