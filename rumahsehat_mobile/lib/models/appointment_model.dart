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

  factory AppointmentModel.fromJson(Map<String, dynamic> json) =>
      AppointmentModel(
        kode: json["kode"],
        waktuAwal: DateTime.parse(json["waktuAwal"]),
        isDone: json["isDone"],
        namaDokter: json["dokter"]["username"],
        namaPasien: json["pasien"]["username"],
      );
}
