package dev.carlodips.albertsoncodingassignment.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.carlodips.albertsoncodingassignment.R
import dev.carlodips.albertsoncodingassignment.databinding.FragmentSearchBinding
import dev.carlodips.albertsoncodingassignment.utils.Constants
import dev.carlodips.albertsoncodingassignment.utils.observeEvent

@AndroidEntryPoint
class SearchFragment: Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val vm: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listenVM()
    }

    private fun listenVM() {
        vm.ldNavigate.observeEvent(viewLifecycleOwner) { numberOfUsers ->
            val bundle = bundleOf(Constants.NUMBER_OF_USERS to numberOfUsers.toInt())
            findNavController().navigate(
                R.id.action_searchFragment_to_randomUsersListFragment,
                bundle
            )
        }
    }
}