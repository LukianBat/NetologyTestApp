package ru.lukianbat.feature.courses.feature.list.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.lukianbat.feature.courses.R
import ru.lukianbat.feature.courses.feature.list.presentation.list.CoursesListItem

private const val HEADER_COLOR_START_INDEX = 9

@Composable
fun CoursesListScreen(
    viewModel: CoursesListViewModel,
    goToCoursesTheme: (themeName: String) -> Unit
) {
    val listState: ListState? by viewModel.coursesThemesListStateLiveData.observeAsState()
    when (listState) {
        is ListState.ItemsState -> CoursesItemsList(
            (listState as ListState.ItemsState).coursesThemes,
            goToCoursesTheme
        )

        ListState.LoadingState -> LoadingProgress()

        is ListState.ErrorState -> LoadingError(
            viewModel,
            (listState as ListState.ErrorState).errorMessage
        )
    }
}

@Composable
private fun LoadingProgress() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator(
            color = colorResource(
                id = R.color.colorPrimaryDark
            )
        )
    }
}

@Composable
private fun LoadingError(viewModel: CoursesListViewModel, errorMessage: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = errorMessage,
            color = colorResource(R.color.textColorPrimary),
            fontSize = 21.sp,
            fontFamily = Font(resId = R.font.roboto_medium).toFontFamily(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)
        )
        Button(
            enabled = true,
            onClick = viewModel::onRetryButtonClicked,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.button_color_enabled),
                disabledBackgroundColor = colorResource(id = R.color.button_color_disabled)
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 16.dp
                )
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = stringResource(id = R.string.courses_list_retry_button).uppercase(),
                color = colorResource(id = R.color.colorBlack),
                fontSize = 12.sp,
                fontFamily = Font(resId = R.font.roboto_medium).toFontFamily(),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
private fun CoursesItemsList(
    coursesThemes: List<CoursesListItem>,
    goToCoursesTheme: (themeName: String) -> Unit
) {
    LazyColumn {
        items(coursesThemes) { theme ->
            when (theme) {
                is CoursesListItem.CoursesThemeItem -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 16.dp,
                                vertical = 8.dp
                            )
                            .clickable(
                                enabled = true,
                                onClick = { goToCoursesTheme(theme.coursesTheme.title) },
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(bounded = true)
                            )
                    ) {
                        Column {
                            Text(
                                text = theme.coursesTheme.title,
                                color = colorResource(R.color.textColorPrimary),
                                fontSize = 21.sp,
                                fontFamily = Font(resId = R.font.roboto_medium).toFontFamily(),
                            )
                            Text(
                                text = stringResource(
                                    R.string.courses_list_courses_count_text,
                                    theme.coursesTheme.coursesCount
                                ),
                                color = colorResource(R.color.edit_text_container_color),
                                fontSize = 14.sp,
                                fontFamily = Font(resId = R.font.roboto_medium).toFontFamily(),
                                modifier = Modifier.padding(vertical = 6.dp)
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.place_holder_shape),
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.CenterEnd)
                        )
                    }
                }

                is CoursesListItem.HeaderItem -> Text(
                    text = buildAnnotatedString {
                        append(theme.headerString)
                        addStyle(
                            style = SpanStyle(
                                color = colorResource(R.color.courses_list_header_color),

                                ),
                            start = HEADER_COLOR_START_INDEX,
                            end = theme.headerString.length
                        )
                    },
                    color = colorResource(R.color.textColorPrimary),
                    fontSize = 28.sp,
                    fontFamily = Font(resId = R.font.roboto_medium).toFontFamily(),
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 8.dp
                    )
                )
            }
            Divider(
                color = colorResource(R.color.divider_color),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}
