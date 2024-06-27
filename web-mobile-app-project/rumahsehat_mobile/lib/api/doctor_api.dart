import 'package:rumahsehat_mobile/api/request_helper.dart';

class DoctorApi {
  Future<List<dynamic>> getAllDoctors() async {
    final response = await RequestHelper.get("/get-dokter");
    return response;
  }
}
