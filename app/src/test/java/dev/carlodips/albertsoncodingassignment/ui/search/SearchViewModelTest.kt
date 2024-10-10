package dev.carlodips.albertsoncodingassignment.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = SearchViewModel()
    }

    @Test
    fun test_input_is_5_should_be_valid_returns_true() = runTest {
        viewModel.ldInput.value = "5"

        assertTrue(viewModel.isInputValid())
    }

    @Test
    fun test_input_is_5000_should_be_valid_returns_true() = runTest {
        viewModel.ldInput.value = "5000"

        assertTrue(viewModel.isInputValid())
    }

    @Test
    fun test_input_is_0_should_not_be_valid_returns_false() = runTest {
        viewModel.ldInput.value = "0"

        assertFalse(viewModel.isInputValid())
    }

    @Test
    fun test_input_is_10000_returns_false() = runTest {
        viewModel.ldInput.value = "10000"

        assertFalse(viewModel.isInputValid())
    }

    @Test
    fun test_input_is_empty_returns_false() = runTest {
        viewModel.ldInput.value = ""

        assertFalse(viewModel.isInputValid())
    }

    @Test
    fun test_input_is_blank_returns_false() = runTest {
        viewModel.ldInput.value = "   "

        assertFalse(viewModel.isInputValid())
    }

}