package com.systems.automaton.mindfullife.presentation.calendar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.action.actionParametersOf
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.google.gson.Gson
import com.systems.automaton.mindfullife.R
import com.systems.automaton.mindfullife.domain.model.CalendarEvent
import com.systems.automaton.mindfullife.presentation.glance_widgets.CalendarWidgetItemClick
import com.systems.automaton.mindfullife.presentation.glance_widgets.eventJson
import com.systems.automaton.mindfullife.util.date.formatEventStartEnd

@Composable
fun CalendarEventWidgetItem(
    event: CalendarEvent,
) {
    Box(
        GlanceModifier
            .padding(vertical = 4.dp)
            .clickable(
            onClick = actionRunCallback<CalendarWidgetItemClick>(
                parameters = actionParametersOf(
                    eventJson to Gson().toJson(event, CalendarEvent::class.java)
                )
            )
        )
    ) {
        Box(
            modifier = GlanceModifier
                .cornerRadius(16.dp)
                .background(ImageProvider(R.drawable.small_item_rounded_corner_shape))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = GlanceModifier.padding(start = 8.dp)
            ) {
                Box(
                    modifier = GlanceModifier
                        .width(6.dp)
                        .height(26.dp)
                        .cornerRadius(6.dp)
                        .background(Color(event.color)),
                ) {}
                Spacer(GlanceModifier.width(4.dp))
                Column(
                    modifier = GlanceModifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        event.title,
                        style = TextStyle(
                            color = ColorProvider(Color.White),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        ),
                        maxLines = 2
                    )
                    Spacer(GlanceModifier.height(6.dp))
                    Text(
                        formatEventStartEnd(
                            start = event.start,
                            end = event.end,
                            location = event.location,
                            allDay = event.allDay,
                        )
                    )
                }
            }
        }
    }
}