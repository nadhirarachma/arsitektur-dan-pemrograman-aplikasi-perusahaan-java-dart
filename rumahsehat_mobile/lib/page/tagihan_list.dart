import 'package:flutter/material.dart';
import 'package:login_logout_registration/components/button.dart';
import 'package:rumahsehat_mobile/widget/drawer.dart';

import 'dart:async';
import 'dart:convert';
import 'package:http/http.dart' as http;

Future<List<Tagihan>> fetchListTagihan(String username) async {
  // String url = 'http://apap-087.cs.ui.ac.id/api/v1/pasien/profile/';
  String url = 'http://localhost:8080/api/v1/tagihan/';
  final response = await http.get(Uri.parse(url + username));
  if (response.statusCode == 200) {
    List<dynamic> responseList = jsonDecode(response.body);
    List<Tagihan> tagihanList = List.empty(growable: true);

    for (var json in responseList) {
      if (json != null) {
        Tagihan t = Tagihan.fromJson(json);
        tagihanList.add(t);
      }
    }
    return tagihanList;
  } else {
    throw Exception('Gagal mendapatkan tagihan');
  }
}

class Tagihan {
  final String kode;
  final bool isPaid;
  final String tanggalTerbuat;
  final int jumlahTagihan;

  const Tagihan(
      {required this.kode,
      required this.isPaid,
      required this.tanggalTerbuat,
      required this.jumlahTagihan});

  factory Tagihan.fromJson(Map<String, dynamic> json) {
    return Tagihan(
        kode: json['kode'],
        isPaid: json['isPaid'],
        tanggalTerbuat: json['tanggalTerbuat'],
        jumlahTagihan: json['jumlahTagihan']);
  }
}

class TagihanList extends StatefulWidget {
  const TagihanList({Key? key, required this.username}) : super(key: key);

  final String username;
  @override
  State<TagihanList> createState() => _DaftarTagihan();
}

class _DaftarTagihan extends State<TagihanList> {
  late Future<List<Tagihan>> futureListTagihan;
  late Widget tabel;

  _getPasienProfile() async {
    futureListTagihan = fetchListTagihan(widget.username);
  }

  @override
  void initState() {
    super.initState();
    _getPasienProfile();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: const Color.fromRGBO(239, 248, 253, 0.9),
        appBar: AppBar(title: const Text('Profile')),
        drawer: DrawerPage(username: widget.username),
        body: SingleChildScrollView(
            child: FutureBuilder<List<Tagihan>>(
          future: futureListTagihan,
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              tabel = tagihanList(context, snapshot.data!);
              return Column(
                children: [
                  TextButton(
                      onPressed: (() {
                        Navigator.pop(context);
                      }),
                      child: const Text('Kembali')),
                  tabel,
                ],
              );
            } else if (snapshot.hasError) {
              return Text('${snapshot.error}');
            }
            return const CircularProgressIndicator();
          },
        )));
  }
}

Widget tagihanList(BuildContext context, List<Tagihan> list) {
  Widget result;

  if (list.isNotEmpty) {
    List<Widget> datas = List.empty(growable: true);
    Widget header = Row(
      children: const [
        Text("kode"),
        Text("tagihan"),
        Text("lunas"),
        Text("tanggal dibuat"),
        Text("aksi")
      ],
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
    );
    datas.add(header);
    for (var tagihan in list) {
      datas.add(dataTagihan(tagihan));
    }
    result = Card(
      child: Column(children: datas),
    );
  } else {
    result = const Text("Tidak ada tagihan");
  }

  return result;
}

Widget dataTagihan(Tagihan tagihan) {
  Widget data = Row(
    children: [
      Text(tagihan.kode),
      Text(tagihan.jumlahTagihan.toString()),
      Checkbox(value: tagihan.isPaid, onChanged: null),
      Text(tagihan.tanggalTerbuat),
      Button(text: "bayar", onPressed: () {})
    ],
    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
  );
  return data;
}
