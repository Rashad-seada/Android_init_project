package com.example.marketapp.features.order.view.screens


import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.*
import com.example.marketapp.core.views.components.CustomProgressIndicator
import com.example.marketapp.core.views.components.MainButton
import com.example.marketapp.features.order.view.components.OrderSpecificationCard
import com.example.marketapp.features.order.view.viewmodel.order.OrderState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@SuppressLint("UnrememberedMutableState")
@Destination
@Composable
fun OrderScreen(
    navigator: DestinationsNavigator?,
    state: OrderState = OrderState(),
    onDescriptionChange : (String)-> Unit = {},
    onConfirmClick: (DestinationsNavigator, Context) -> Unit = { _, _ -> },
    onImageSelection: (Uri?) -> Unit = {},
    onFileSelection: (Uri?) -> Unit = {},
    onRecording: () -> Unit = {},
    ) {

    val allowedMimeTypes = arrayOf(
        "application/msword",       // .doc
        "application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .docx
        "text/plain",               // .txt
        "application/vnd.wordperfect", // .wpd
        "application/x-iwork-pages-sffpages", // .pages
        "application/pdf"            // .pdf
    )

    val context: Context = LocalContext.current

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            onImageSelection(it)
        }
    )

    val singleFilePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = {
            onFileSelection(it)
        }
    )


    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        it

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {

                Surface(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .size(50.dp)
                        .clickable {

                        },
                    shape = CircleShape,
                    color = if (isSystemInDarkTheme()) Neutral800.copy(alpha = 0.3f) else Neutral200.copy(
                        alpha = 0.3f
                    )
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(13.dp)
                            .clickable {
                                navigator?.let {
                                    navigator.popBackStack()
                                }
                            }
                            .align(Alignment.CenterStart),
                        painter = painterResource(
                            id = R.drawable.arrow_left
                        ),
                        contentDescription = null,
                        tint = if (isSystemInDarkTheme()) Neutral100 else Neutral900
                    )

                }


                Text(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(start = 0.dp)
                        .align(Alignment.Center),
                    text = context.getString(R.string.make_order),
                    style = TextStyle(
                        fontFamily = Lato,
                        color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                        fontSize = 18.sp
                    )
                )
            }
            Spacer(modifier = Modifier.height(40.dp))

            OrderSpecificationCard(
                state = state,
                onFileSelection = {
                    singleFilePicker.launch(allowedMimeTypes)
                },
                onImageSelection = {
                    singlePhotoPicker.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                onRecording = {
                    onRecording()
                },
                onDescriptionChange = {
                    onDescriptionChange(it)
                }
            )

            Spacer(modifier = Modifier.height(30.dp))


            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(55.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .clickable {
                        navigator?.let {
                            onConfirmClick(navigator, context)
                        }

                    },
                cardColor = Primary,
                borderColor = Color.Transparent
            ) {

                if (!state.isMakingOrderStep1) {
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = context.getString(R.string.next),
                        style = TextStyle(
                            fontFamily = Lato,
                            color = Neutral100,
                            fontSize = 16.sp,
                        )
                    )
                } else {
                    CustomProgressIndicator(
                        modifier = Modifier.size(20.dp)
                    )
                }


            }

            Spacer(modifier = Modifier.height(30.dp))


        }


    }

}

@Preview
@Composable
fun OrderScreenPreview() {
    MarketAppTheme {
        OrderScreen(
            navigator = null
        )
    }
}
