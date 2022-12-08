import 'package:get_it/get_it.dart';
import 'package:rumahsehat_mobile/api/api.dart';

final locator = GetIt.instance;

void setupLocator() {
  locator
    ..registerLazySingleton(() => DoctorApi())
    ..registerLazySingleton(() => AppointmentApi());
}
