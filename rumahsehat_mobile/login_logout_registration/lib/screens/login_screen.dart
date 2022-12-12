import 'dart:convert' as convert;
import 'dart:developer';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:login_logout_registration/screens/registration_screen.dart';

import 'package:login_logout_registration/components/input.dart';
import 'package:login_logout_registration/components/button.dart';

import 'package:rumahsehat_mobile/page/homepage.dart';
import 'package:rumahsehat_mobile/services/secure_storage.dart';

class LoginScreen extends StatefulWidget {
  static const routeName = '/login';
  final VoidCallback? onSignIn;

  const LoginScreen({Key? key, this.onSignIn}) : super(key: key);

  @override
  _LoginScreenState createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  final LocalStorageService _storageService = LocalStorageService();

  final TextEditingController unameController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();
  String textFieldsValue = "";

  @override
  Widget build(BuildContext context) {
    String url = 'https://apap-087.cs.ui.ac.id/api/v1/login';
    // String url = 'http://localhost:8080/api/v1/login';
    Size size = MediaQuery.of(context).size;
    return Scaffold(
      body: SingleChildScrollView(
        child: Stack(
          alignment: Alignment.center,
          children: <Widget>[
            Container(
              decoration: const BoxDecoration(
                gradient: LinearGradient(
                    begin: Alignment.topCenter,
                    end: Alignment.bottomCenter,
                    colors: [
                      Color.fromRGBO(239, 248, 253, 0.9),
                      Color.fromRGBO(239, 248, 253, 0.9)
                    ]),
              ),
              child: Center(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.center,
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    const SizedBox(
                      height: 40.0,
                    ),
                    const Text(
                      "HELLO !",
                      style: TextStyle(
                        fontSize: 23.0,
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    const SizedBox(
                      height: 10.0,
                    ),
                    const SizedBox(
                      height: 10.0,
                    ),
                    const SizedBox(
                      height: 15.0,
                    ),
                    Form(
                      child: Column(children: <Widget>[
                        Card(
                            margin: const EdgeInsets.symmetric(
                                horizontal: 50.0, vertical: 5.0),
                            clipBehavior: Clip.antiAlias,
                            color: Colors.white,
                            elevation: 13.0,
                            child: Padding(
                                padding: const EdgeInsets.symmetric(
                                    horizontal: 12.0, vertical: 22.0),
                                child: Row(children: <Widget>[
                                  Expanded(
                                      child: Column(
                                          crossAxisAlignment:
                                              CrossAxisAlignment.center,
                                          mainAxisAlignment:
                                              MainAxisAlignment.center,
                                          children: <Widget>[
                                        const SizedBox(
                                          height: 10.0,
                                        ),
                                        const Text(
                                          "Log In",
                                          style: TextStyle(
                                            fontSize: 23.0,
                                            color: Colors.black,
                                            fontWeight: FontWeight.bold,
                                          ),
                                        ),
                                        const SizedBox(
                                          height: 30.0,
                                        ),
                                        InputField(
                                            hint: "Username",
                                            controller: unameController),
                                        Container(
                                          margin: const EdgeInsets.symmetric(
                                              vertical: 7),
                                          padding: const EdgeInsets.symmetric(
                                              vertical: 5, horizontal: 20),
                                          width: size.width * 0.6,
                                          decoration: BoxDecoration(
                                            color: const Color.fromRGBO(
                                                206, 238, 255, 0.7),
                                            borderRadius:
                                                BorderRadius.circular(29),
                                          ),
                                          child: TextFormField(
                                            controller: passwordController,
                                            obscureText: true,
                                            cursorColor: Colors.black,
                                            decoration: const InputDecoration(
                                              icon: Icon(
                                                Icons.perm_contact_cal,
                                                color: Colors.blue,
                                              ),
                                              hintText: "Password",
                                              border: InputBorder.none,
                                            ),
                                            validator: (value) {
                                              if (value!.isEmpty) {
                                                return 'Please fill out this field.';
                                              }
                                              textFieldsValue = value;
                                              return null;
                                            },
                                          ),
                                        ),
                                        const SizedBox(
                                          height: 20.0,
                                        ),
                                        Button(
                                            text: "Sign In",
                                            onPressed: () async {
                                              var body = convert
                                                  .jsonEncode(<String, String>{
                                                'username':
                                                    unameController.text,
                                                'password':
                                                    passwordController.text,
                                              });
                                              print(body);
                                              var result = await http.post(
                                                  Uri.parse(url),
                                                  headers: <String, String>{
                                                    "Content-Type":
                                                        "application/json; charset=UTF-8",
                                                    "Accept": "application/json"
                                                  },
                                                  body: body);
                                              if (result.body.substring(2, 7) ==
                                                  "token") {
                                                final decodedBody = convert
                                                    .jsonDecode(result.body);
                                                final token =
                                                    decodedBody["token"];
                                                log(token);
                                                await _storageService
                                                    .writeSecureData(
                                                        "token", token);
                                                String username =
                                                    unameController.text;
                                                showDialog<String>(
                                                  context: context,
                                                  builder:
                                                      (BuildContext context) =>
                                                          AlertDialog(
                                                    title: const Text(
                                                        'Login Success'),
                                                    content: Text(
                                                        'Welcome to RumahSehat, ' +
                                                            body
                                                                .split(",")[0]
                                                                .split('"')[3]),
                                                    shape:
                                                        RoundedRectangleBorder(
                                                      borderRadius:
                                                          BorderRadius.circular(
                                                              30),
                                                    ),
                                                    backgroundColor:
                                                        Colors.blue[200],
                                                    actions: <Widget>[
                                                      TextButton(
                                                        onPressed: () =>
                                                            Navigator.push(
                                                                context,
                                                                MaterialPageRoute(
                                                                    builder:
                                                                        (context) {
                                                          return Home(
                                                              username:
                                                                  username);
                                                        })),
                                                        child: const Text(
                                                          'OK',
                                                          style: TextStyle(
                                                            fontSize: 14.0,
                                                            fontWeight:
                                                                FontWeight.bold,
                                                            color:
                                                                Colors.indigo,
                                                          ),
                                                        ),
                                                      ),
                                                    ],
                                                  ),
                                                );
                                                unameController.clear();
                                                passwordController.clear();
                                              } else {
                                                showDialog<String>(
                                                  context: context,
                                                  builder:
                                                      (BuildContext context) =>
                                                          AlertDialog(
                                                    title: const Text(
                                                        'Login Failed'),
                                                    content: const Text(
                                                        'Wrong Username or Password'),
                                                    shape:
                                                        RoundedRectangleBorder(
                                                      borderRadius:
                                                          BorderRadius.circular(
                                                              30),
                                                    ),
                                                    backgroundColor:
                                                        Colors.blue[200],
                                                    actions: <Widget>[
                                                      TextButton(
                                                        onPressed: () =>
                                                            Navigator.pop(
                                                                context, 'OK'),
                                                        child: const Text('OK'),
                                                      ),
                                                    ],
                                                  ),
                                                );
                                              }
                                            }),
                                        SizedBox(height: size.height * 0.03),
                                        const Text('Belum Punya Akun?'),
                                        TextButton(
                                          child: const Text(
                                            'Registrasi Sekarang',
                                            style: TextStyle(
                                              fontSize: 14.0,
                                              fontWeight: FontWeight.bold,
                                              color: Colors.indigo,
                                            ),
                                          ),
                                          onPressed: () {
                                            Navigator.push(
                                                context,
                                                MaterialPageRoute(
                                                    builder: (context) =>
                                                        const RegistrationScreen()));
                                          },
                                        ),
                                      ]))
                                ]))),
                      ]),
                    ),
                    SizedBox(height: size.height * 0.5),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
