package ru.lukianbat.feature.courses.feature.list.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ru.lukianbat.coreui.utils.drawable
import ru.lukianbat.coreui.utils.viewBinding
import ru.lukianbat.feature.courses.R
import ru.lukianbat.feature.courses.common.di.CoursesFlowComponentController
import ru.lukianbat.feature.courses.databinding.FragmentCoursesListBinding
import ru.lukianbat.feature.courses.feature.list.presentation.view.CoursesListAdapter
import javax.inject.Inject

class CoursesListFragment : Fragment(R.layout.fragment_courses_list) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by navGraphViewModels<CoursesListViewModel>(R.id.navigation_cities) { viewModelFactory }

    private val binding by viewBinding(FragmentCoursesListBinding::bind)

    private val retryButton get() = binding.retryButton
    private val errorMessageTextView get() = binding.errorMessageTextView
    private val recyclerView get() = binding.recyclerView
    private val progressBar get() = binding.progressBar

    private lateinit var coursesListAdapter: CoursesListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as CoursesFlowComponentController)
            .provideCoursesFlowComponent()
            .coursesCityComponent()
            .create()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as OnBackPressedDispatcherOwner).onBackPressedDispatcher.addCallback(this) {
            requireActivity().findNavController(R.id.host_cities).popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initLiveData()
    }

    private fun initView() {
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        coursesListAdapter = CoursesListAdapter()
        val dividerItemDecoration = DividerItemDecoration(
            requireContext(),
            layoutManager.orientation
        )
        dividerItemDecoration.setDrawable(
            drawable(R.drawable.divider_layer) ?: return
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = coursesListAdapter

        retryButton.setOnClickListener { viewModel.onRetryButtonClicked() }
    }

    private fun initLiveData() {
        viewModel.coursesThemesListStateLiveData.observe(viewLifecycleOwner, {
            when (val state = it) {
                is ListState.ItemsState -> {
                    showError(false)
                    progressBar.isVisible = false
                    coursesListAdapter.submitList(state.coursesThemes)
                }
                ListState.LoadingState -> {
                    showError(false)
                    progressBar.isVisible = true
                }
                is ListState.ErrorState -> {
                    errorMessageTextView.text = state.errorMessage
                    progressBar.isVisible = false
                    showError(true)
                }
            }
        })
    }

    private fun showError(show: Boolean) {
        errorMessageTextView.isVisible = show
        retryButton.isVisible = show
        recyclerView.isVisible = !show
    }
}
