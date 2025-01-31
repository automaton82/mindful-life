package com.systems.automaton.mindfullife.domain.use_case.alarm

import com.systems.automaton.mindfullife.domain.repository.AlarmRepository
import javax.inject.Inject

class GetAllAlarmsUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository
) {
    suspend operator fun invoke() = alarmRepository.getAlarms()
}