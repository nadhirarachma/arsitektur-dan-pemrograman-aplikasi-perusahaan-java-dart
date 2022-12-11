import 'dart:developer';

import 'package:equatable/equatable.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:rumahsehat_mobile/models/appointment_model.dart';
import 'package:rumahsehat_mobile/repositories/appointment_repository.dart';

part "appointment_event.dart";
part "appointment_state.dart";

class AppointmentBloc extends Bloc<AppointmentEvent, AppointmentState> {
  final AppointmentRepository _appointmentRepository;

  AppointmentBloc(this._appointmentRepository) : super(AppointmentInit()) {
    on<CreateAppointment>(_onCreateAppointmentEvent);
    on<FetchPatientAppointments>(_onFetchPatientAppointments);
  }

  Future<void> _onCreateAppointmentEvent(
      CreateAppointment event, Emitter<AppointmentState> emit) async {
    emit(AppointmentLoading());
    try {
      final message =
          await _appointmentRepository.createAppointment(event.data);
      emit(SuccessCreateAppointment(message: message));
    } catch (e) {
      log('ERROR: ' + e.toString());
      emit(const FailCreateAppointment(message: "Gagal membuat appointment."));
    }
  }

  Future<void> _onFetchPatientAppointments(
      FetchPatientAppointments event, Emitter<AppointmentState> emit) async {
    emit(AppointmentLoading());
    try {
      final listAppointments =
          await _appointmentRepository.getAppointmentByPatient();
      emit(SuccessFetchAppointmentsData(listAppointments));
    } catch (e) {
      log('ERROR: ' + e.toString());
      emit(const FailFetchAppointmensData(message: "Gagal mendapatkan data appointment."));
    }
  }
}
