package dev.carlodips.albertsoncodingassignment.ui.random_users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.carlodips.albertsoncodingassignment.R
import dev.carlodips.albertsoncodingassignment.ui.theme.AlbertsonCodingAssignmentTheme
import dev.carlodips.albertsoncodingassignment.utils.Constants
import dev.carlodips.albertsoncodingassignment.utils.observeEvent

@AndroidEntryPoint
class RandomUsersListFragment : Fragment() {

    private val vm: RandomUsersListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                AlbertsonCodingAssignmentTheme {
                    RandomUsersListScreen(
                        uiState = vm.uiState
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listenVM()
    }

    private fun listenVM() {
        vm.ldNavigateToDetails.observeEvent(viewLifecycleOwner) { index ->
            val bundle = bundleOf(Constants.USER_INDEX to index)

            findNavController().navigate(
                R.id.action_randomUsersListFragment_to_randomUserDetailsFragment,
                bundle
            )
        }
    }
}