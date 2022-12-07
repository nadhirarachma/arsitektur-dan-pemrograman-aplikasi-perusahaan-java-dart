import 'package:flutter/material.dart';
import 'package:login_logout_registration/components/button.dart';
import 'package:login_logout_registration/components/input.dart';
import 'package:rumahsehat_mobile/widget/drawer.dart';
import 'dart:convert' as convert;
import 'package:http/http.dart' as http;

class Topup extends StatefulWidget {
  const Topup({Key? key, required this.username}) : super(key: key);

  final String username;

  @override
  State<Topup> createState() => _Topup();
}

class _Topup extends State<Topup> {
  final String url = 'http://apap-087.cs.ui.ac.id/api/v1/pasien/topup';
  // final String url = 'http://localhost:10087/api/v1/pasien/topup';
  final TextEditingController jumlahController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: const Color.fromRGBO(239, 248, 253, 0.9),
        appBar: AppBar(title: const Text('Top Up')),
        drawer: DrawerPage(username: widget.username),
        body: SingleChildScrollView(
          child: Form(
              child: Column(
            children: [
              InputField(
                hint: 'Masukkan jumlah',
                controller: jumlahController,
                keyboardType: TextInputType.number,
              ),
              Button(
                  text: "Konfirmasi",
                  onPressed: () async {
                    bool valid = true;
                    String message = '';
                    if (int.tryParse(jumlahController.text) == null) {
                      valid = false;
                      message = 'Saldo top up harus berupa angka';
                    } else if (int.parse(jumlahController.text) <= 0) {
                      valid = false;
                      message = 'Saldo harus bernilai positif';
                    }
                    if (!valid) {
                      showDialog(
                          context: context,
                          builder: (context) => AlertDialog(
                                title: const Text('Top Up Gagal'),
                                content: Text(message),
                                actions: [
                                  TextButton(
                                      onPressed: () {
                                        Navigator.pop(context);
                                      },
                                      child: const Text('OK'))
                                ],
                              ));
                    } else {
                      var body = convert.jsonEncode(<String, dynamic>{
                        'username': widget.username,
                        'jumlah': int.parse(jumlahController.text)
                      });
                      var response = await http.post(Uri.parse(url),
                          headers: <String, String>{
                            'Content-type': 'application/json; charset=utf-8'
                          },
                          body: body);
                      print(body);
                      if (response.statusCode == 200) {
                        Navigator.pop(context);
                        showDialog(
                            context: context,
                            builder: ((context) => AlertDialog(
                                  title: const Text('Top Up Berhasil'),
                                  content:
                                      const Text('Saldo berhasil ditambah'),
                                  actions: [
                                    TextButton(
                                        onPressed: () {
                                          Navigator.pop(context);
                                        },
                                        child: const Text('OK'))
                                  ],
                                )));
                      }
                    }
                  })
            ],
          )),
        ));
  }
}
