class AppointmentModel {
  String kode;
  DateTime waktuAwal;
  bool isDone;
  String namaDokter;
  String namaPasien;

  AppointmentModel({
    required this.kode,
    required this.waktuAwal,
    required this.isDone,
    required this.namaDokter,
    required this.namaPasien,
  });

  // factory AppointmentModel.fromJson(Map<String, dynamic> json) => AppointmentModel(
  //       id: json["uuid"],
  //       username: json["username"],
  //       tarif: json["tarif"],
  //     );
}
