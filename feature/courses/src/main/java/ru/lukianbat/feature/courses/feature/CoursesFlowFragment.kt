package ru.lukianbat.feature.courses.feature

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.lukianbat.feature.courses.common.di.CoursesFlowComponent
import ru.lukianbat.feature.courses.common.di.CoursesFlowComponentController
import ru.lukianbat.feature.courses.common.navigation.CoursesFlowNavigationGraph
import javax.inject.Inject

class CoursesFlowFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<CoursesFlowViewModel> { viewModelFactory }
    private lateinit var flowComponent: CoursesFlowComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)
        flowComponent = (requireActivity().applicationContext as CoursesFlowComponentController)
            .provideCoursesFlowComponent()
        flowComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent { CoursesFlowNavigationGraph(flowComponent) }
        }
    }
}
