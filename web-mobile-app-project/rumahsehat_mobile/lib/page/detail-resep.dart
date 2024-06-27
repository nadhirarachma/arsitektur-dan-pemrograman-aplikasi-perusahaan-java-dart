import 'dart:convert';
import 'dart:async';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

Future<Resep> fetchResep(int id) async {
  // String url = 'http://localhost:8080/api/v1/resep/view/';
  String url = 'http://apap-087.cs.ui.ac.id/api/v1/resep/view/';
  final response = await http.get(Uri.parse(url + id.toString()));
  print(response.statusCode);
  print(response.body);
  if (response.statusCode == 200) {
    return Resep.fromJson(jsonDecode(response.body));
  } else {
    throw Exception('Gagal melihat daftar resep');
  }
}

class Resep {
  final int id;
  final String pasien;
  final String apotek;
  final String dokter;
  final String status;
  final int total;
  final List<Obat> listObat;

  const Resep({
    required this.id,
    required this.pasien,
    required this.apotek,
    required this.dokter,
    required this.status,
    required this.total,
    required this.listObat,
  });

  factory Resep.fromJson(Map<String, dynamic> json) {
    dynamic apotek;
    if (json['apotek'] != null) {
      apotek = json['apotek']['username'];
    } else {
      apotek = "Belum Ada";
    }

    int _total = 0;
    List<Obat> _listObat = [];
    for (var obj in json['jumlah']) {
      _total = (_total + obj['kuantitas']) as int;
      obj['obat']['kuantitas'] = obj['kuantitas'];
      Obat obat = Obat.fromJson(obj['obat']);
      _listObat.add(obat);
    }

    bool isDone = json['isDone'];
    return Resep(
      id: json['id'],
      pasien: json['appointment']['pasien']['username'],
      apotek: apotek,
      dokter: json['appointment']['dokter']['username'],
      status: isDone ? 'Sudah Dikonfirmasi' : 'Belum Dikonfirmasi',
      total: _total,
      listObat: _listObat,
    );
  }
}

class Obat {
  final String id;
  final String nama;
  final int stok;
  final int harga;
  final int kuantitas;

  const Obat({
    required this.id,
    required this.nama,
    required this.stok,
    required this.harga,
    required this.kuantitas,
  });

  factory Obat.fromJson(Map<String, dynamic> json) {
    return Obat(
      id: json['idObat'],
      nama: json['namaObat'],
      stok: json['stok'],
      harga: json['harga'],
      kuantitas: json['kuantitas'],
    );
  }
}

class DetailResepPage extends StatefulWidget {
  final String username;
  final int id;

  const DetailResepPage({
    Key? key,
    required this.username,
    required this.id,
  }) : super(key: key);

  @override
  State<StatefulWidget> createState() => _DetailResepState();
}

class _DetailResepState extends State<DetailResepPage> {
  late Future<Resep> futureResep;

  _getDetailResep() async {
    futureResep = fetchResep(widget.id);
  }

  @override
  void initState() {
    super.initState();
    _getDetailResep();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Detail Resep')),
      // drawer: DrawerPage(username: widget.username),
      body: SingleChildScrollView(
        child: FutureBuilder<Resep>(
          future: futureResep,
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              return Column(
                children: [
                  _buildDetailResep(context, snapshot.data!),
                  _buildDaftarObat(context, snapshot.data!.listObat),
                  _buildKolomTombol(context),
                ],
              );
              ;
            } else if (snapshot.hasError) {
              return Text('${snapshot.error}');
            }
            return const CircularProgressIndicator();
          },
        ),
      ),
    );
  }

  Widget _buildDetailResep(BuildContext context, Resep resep) {
    return Column(
      children: [
        const SizedBox(
          height: 16,
        ),
        const Text(
          'Detail Resep',
          style: TextStyle(
            fontSize: 18,
            fontWeight: FontWeight.bold,
          ),
        ),
        const SizedBox(
          height: 16,
        ),
        Container(
          margin: const EdgeInsets.all(12),
          padding: const EdgeInsets.all(12),
          decoration: BoxDecoration(
            color: Colors.white,
            borderRadius: const BorderRadius.all(
              Radius.circular(2),
            ),
            boxShadow: [
              BoxShadow(
                color: Colors.grey.withOpacity(0.4),
                spreadRadius: 3,
                blurRadius: 7,
                offset: const Offset(0, 2), // changes position of shadow
              ),
            ],
          ),
          child: Column(
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: [
                  _buildDetailResepItem('ID', resep.id.toString()),
                  _buildDetailResepItem('Dokter', resep.dokter),
                ],
              ),
              const SizedBox(
                height: 12,
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: [
                  _buildDetailResepItem('Pasien', resep.pasien),
                  _buildDetailResepItem(
                      'Total Obat', resep.listObat.length.toString()),
                ],
              ),
              const SizedBox(
                height: 12,
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: [
                  _buildDetailResepItem('Apoteker', resep.apotek),
                  _buildDetailResepItem('Status', resep.status),
                ],
              ),
            ],
          ),
        ),
      ],
    );
  }

  Widget _buildDetailResepItem(String title, String value) {
    return Expanded(
      child: SizedBox(
        width: double.maxFinite,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              title,
              style: TextStyle(fontWeight: FontWeight.bold),
            ),
            Text(value),
          ],
        ),
      ),
    );
  }

  Widget _buildDaftarObat(BuildContext context, List<Obat> listObat) {
    List<DataRow> rows = [];
    listObat.asMap().forEach(
      (index, obat) {
        rows.add(
          DataRow(
            cells: [
              DataCell(
                Text((index + 1).toString()),
              ),
              DataCell(
                Text(obat.nama),
              ),
              DataCell(
                Text(obat.kuantitas.toString()),
              ),
            ],
          ),
        );
      },
    );

    return Column(
      children: [
        const SizedBox(
          height: 16,
        ),
        const Text(
          'Daftar Obat',
          style: TextStyle(
            fontSize: 18,
            fontWeight: FontWeight.bold,
          ),
        ),
        const SizedBox(
          height: 16,
        ),
        Container(
          height: 1,
          margin: const EdgeInsets.symmetric(horizontal: 16),
          color: Colors.grey.shade300,
        ),
        Container(
          margin: const EdgeInsets.symmetric(horizontal: 12),
          child: DataTable(
            rows: rows,
            columns: const [
              DataColumn(
                label: Text(
                  'Nomor',
                  style: TextStyle(fontWeight: FontWeight.bold),
                ),
              ),
              DataColumn(
                label: Text(
                  'Nama Obat',
                  style: TextStyle(fontWeight: FontWeight.bold),
                ),
              ),
              DataColumn(
                label: Text(
                  'Jumlah',
                  style: TextStyle(fontWeight: FontWeight.bold),
                ),
              ),
            ],
          ),
        ),
        Container(
          height: 1,
          margin: const EdgeInsets.symmetric(horizontal: 16),
          color: Colors.grey.shade300,
        ),
        const SizedBox(
          height: 16,
        )
      ],
    );
  }

  Widget _buildKolomTombol(BuildContext context) {
    return Row(
      children: [
        const SizedBox(
          width: 12,
        ),
        _buildTombol(
          context,
          title: 'Kembali',
          textColor: Colors.white,
          decoration: const BoxDecoration(
            color: Colors.blue,
          ),
          onClick: () => {
            Navigator.pop(context)
          },
        ),
        const SizedBox(
          width: 12,
        ),
      ],
    );
  }

  Widget _buildTombol(
    BuildContext context, {
    required String title,
    Color textColor = Colors.black,
    BoxDecoration? decoration,
    VoidCallback? onClick,
  }) {
    return GestureDetector(
      onTap: onClick,
      child: Container(
        padding: const EdgeInsets.symmetric(vertical: 8, horizontal: 12),
        decoration: decoration?.copyWith(
          borderRadius: const BorderRadius.all(
            Radius.circular(4),
          ),
        ),
        child: Text(
          title,
          style: TextStyle(color: textColor),
        ),
      ),
    );
  }
}
