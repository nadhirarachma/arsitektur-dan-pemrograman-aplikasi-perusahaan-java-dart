import 'dart:developer';

import 'package:equatable/equatable.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:rumahsehat_mobile/models/doctor_model.dart';
import 'package:rumahsehat_mobile/repositories/doctor_repository.dart';

part "doctor_state.dart";
part "doctor_event.dart";

class DoctorBloc extends Bloc<DoctorEvent, DoctorState> {
  final DoctorRepository _doctorRepository;

  DoctorBloc(this._doctorRepository) : super(DoctorInit()) {
    on<FetchAllDoctors>(_onFetchAllDoctorsEvent);
  }

  Future<void> _onFetchAllDoctorsEvent(
      FetchAllDoctors event, Emitter<DoctorState> emit) async {
    emit(LoadingState());
    try {
      final listDoctors = await _doctorRepository.getAllDoctors();
      emit(SuccessFetchAllDoctors(listDoctors));
    } catch (e) {
      log('ERROR: ' + e.toString());
      emit(const FailedState(message: "Gagal mendapatkan data semua dokter."));
    }
  }
}
