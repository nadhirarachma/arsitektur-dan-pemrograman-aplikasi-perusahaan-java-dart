class AppointmentModel {
  String kode;
  DateTime waktuAwal;
  bool isDone;
  String namaDokter;
  String namaPasien;
  int? idResep;

  AppointmentModel({
    required this.kode,
    required this.waktuAwal,
    required this.isDone,
    required this.namaDokter,
    required this.namaPasien,
    this.idResep,
  });

  factory AppointmentModel.fromJson(Map<String, dynamic> json) {
    int? idResep;
    if (json["resep"] != null) {
      idResep = json["resep"]["id"];
    }
    return AppointmentModel(
      kode: json["kode"],
      waktuAwal: DateTime.parse(json["waktuAwal"]),
      isDone: json["isDone"],
      namaDokter: json["dokter"]["username"],
      namaPasien: json["pasien"]["username"],
      idResep: idResep,
    );
  }
}
