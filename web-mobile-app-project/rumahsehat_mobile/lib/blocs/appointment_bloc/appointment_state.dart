part of "appointment_bloc.dart";

abstract class AppointmentState extends Equatable {
  const AppointmentState({
    String? message,
  });

  @override
  List<Object?> get props => throw UnimplementedError();
}

class AppointmentInit extends AppointmentState {}

class AppointmentLoading extends AppointmentState {}

class SuccessCreateAppointment extends AppointmentState {
  const SuccessCreateAppointment({
    String? message,
  }) : super(
          message: message,
        );
}

class FailCreateAppointment extends AppointmentState {
  const FailCreateAppointment({
    String? message,
  }) : super(
          message: message,
        );
}

class SuccessFetchAppointmentsData extends AppointmentState {
  final List<AppointmentModel> listAppointments;
  const SuccessFetchAppointmentsData(this.listAppointments);

  @override
  List<Object?> get props => [listAppointments];
}

class FailFetchAppointmensData extends AppointmentState {
  const FailFetchAppointmensData({
    String? message,
  }) : super(
          message: message,
        );
}