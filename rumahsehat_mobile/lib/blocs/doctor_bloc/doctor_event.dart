part of "doctor_bloc.dart";

abstract class DoctorEvent extends Equatable {
  @override
  List<Object?> get props => [];
}

class FetchAllDoctors extends DoctorEvent {}