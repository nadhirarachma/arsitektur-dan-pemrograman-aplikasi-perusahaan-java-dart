part of "doctor_bloc.dart";

abstract class DoctorState extends Equatable {
  const DoctorState({
    String? message,
  });

  @override
  List<Object?> get props => [];
}

class DoctorInit extends DoctorState {}

class LoadingState extends DoctorState {}

class SuccessFetchAllDoctors extends DoctorState {
  final List<DoctorModel> listDoctors;
  const SuccessFetchAllDoctors(this.listDoctors);

  @override
  List<Object?> get props => [listDoctors];
}

class FailedState extends DoctorState {
  const FailedState({
    String? message,
  }) : super(
          message: message,
        );
}
