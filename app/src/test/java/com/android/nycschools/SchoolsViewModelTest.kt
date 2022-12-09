package com.android.nycschools

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.nycschools.api.NetworkState
import com.android.nycschools.api.SchoolRepository
import com.android.nycschools.api.Schools
import com.android.nycschools.ui.SchoolsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class SchoolsViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var schoolsViewModel: SchoolsViewModel
    lateinit var schoolRepository: SchoolRepository
    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        schoolRepository = mock(SchoolRepository::class.java)
        schoolsViewModel = SchoolsViewModel(schoolRepository)
    }

    @Test
    fun getSchoolsTest() {
        runBlocking {
            Mockito.`when`(schoolRepository.getSchools())
                .thenReturn(NetworkState.Success(listOf<Schools>(Schools("12432", "Prosper ISD", "9809209859", "7255 Texas Rangers Dr", "frisco",  "75034"))))
            schoolsViewModel.getSchools()
            val result = schoolsViewModel.schoolList.getOrAwaitValue()
            assertEquals(listOf<Schools>(Schools("12432", "Prosper ISD", "9809209859", "7255 Texas Rangers Dr", "frisco",  "75034")), result)
        }
    }

    @Test
    fun `empty school list test`() {
        runBlocking {
            Mockito.`when`(schoolRepository.getSchools())
                .thenReturn(NetworkState.Success(listOf<Schools>()))
            schoolsViewModel.getSchools()
            val result = schoolsViewModel.schoolList.getOrAwaitValue()
            assertEquals(listOf<Schools>(), result)
        }
    }
}