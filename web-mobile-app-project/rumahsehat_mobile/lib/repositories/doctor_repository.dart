import 'package:rumahsehat_mobile/api/doctor_api.dart';
import 'package:rumahsehat_mobile/models/doctor_model.dart';

class DoctorRepository {
  final DoctorApi _doctorApi;

  DoctorRepository(this._doctorApi);

  Future<List<DoctorModel>> getAllDoctors() async {
    final listDoctors = await _doctorApi.getAllDoctors();
    List<DoctorModel> listDoctorsConverted = [];
    for (var i = 0; i < listDoctors.length; i++) {
      listDoctorsConverted.add(DoctorModel.fromJson(listDoctors[i]));
    }
    return listDoctorsConverted;
  }
}
