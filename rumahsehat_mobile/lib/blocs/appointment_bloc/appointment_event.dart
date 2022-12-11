part of "appointment_bloc.dart";

abstract class AppointmentEvent extends Equatable {
  @override
  List<Object?> get props => [];
}

class CreateAppointment extends AppointmentEvent {
  final Map<String, dynamic> data;

  CreateAppointment(this.data);
  @override
  List<Object?> get props => [data];
}

class FetchPatientAppointments extends AppointmentEvent {}