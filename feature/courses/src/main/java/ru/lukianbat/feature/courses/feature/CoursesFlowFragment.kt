package ru.lukianbat.feature.courses.feature

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.lukianbat.feature.courses.common.di.CoursesFlowComponentController
import ru.lukianbat.feature.courses.R
import javax.inject.Inject

class CoursesFlowFragment : Fragment(R.layout.fragment_courses_flow) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<CoursesFlowViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as CoursesFlowComponentController)
            .provideCoursesFlowComponent()
            .inject(this)
    }
}
