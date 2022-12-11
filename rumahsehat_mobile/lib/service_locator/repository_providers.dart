import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:rumahsehat_mobile/api/api.dart';
import 'package:rumahsehat_mobile/repositories/appointment_repository.dart';
import 'package:rumahsehat_mobile/repositories/doctor_repository.dart';
import 'package:rumahsehat_mobile/service_locator/service_locator.dart';

final repositoryProviders = [
  RepositoryProvider<DoctorRepository>(
    create: (context) => DoctorRepository(locator<DoctorApi>()),
  ),
  RepositoryProvider<AppointmentRepository>(
    create: (context) => AppointmentRepository(locator<AppointmentApi>()),
  ),
];
