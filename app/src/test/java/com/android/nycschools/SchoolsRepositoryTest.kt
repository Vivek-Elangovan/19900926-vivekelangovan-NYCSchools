package com.android.nycschools

import com.android.nycschools.api.NetworkState
import com.android.nycschools.api.RetrofitService
import com.android.nycschools.api.SchoolRepository
import com.android.nycschools.api.Schools
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
class SchoolsRepositoryTest {

    private lateinit var schoolRepository: SchoolRepository

    @Mock
    lateinit var apiService: RetrofitService

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        schoolRepository = SchoolRepository(apiService)
    }

    @Test
    fun `get schools test`() {
        runBlocking {
            Mockito.`when`(apiService.getSchools()).thenReturn(Response.success(listOf<Schools>()))
            val response = schoolRepository.getSchools()
            assertNotNull(NetworkState.Success(response).data)
        }
    }
}