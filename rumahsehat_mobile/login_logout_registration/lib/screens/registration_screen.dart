import 'dart:convert' as convert;
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

import 'package:login_logout_registration/screens/login_screen.dart';
import 'package:login_logout_registration/components/button.dart';
import 'package:login_logout_registration/components/input.dart';

class RegistrationScreen extends StatefulWidget {
  const RegistrationScreen({Key? key}) : super(key: key);

  @override
  _RegistrationScreenState createState() => _RegistrationScreenState();
}

class _RegistrationScreenState extends State<RegistrationScreen> {
  final TextEditingController unameController = TextEditingController();
  final TextEditingController nameController = TextEditingController();
  final TextEditingController emailController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();
  final TextEditingController ageController = TextEditingController();
  String textFieldsValue = "";

  @override
  Widget build(BuildContext context) {
    //String url = 'https://apap-087.cs.ui.ac.id/api/v1/pasien/add';
    String url = 'http://localhost:8080/api/v1/pasien/add';
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
                                          "Sign Up",
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
                                        InputField(
                                            hint: "Nama",
                                            controller: nameController),
                                        InputField(
                                            hint: "E-mail",
                                            controller: emailController),
                                        Container(
                                          margin: const EdgeInsets.symmetric(
                                              vertical: 7),
                                          padding: const EdgeInsets.symmetric(
                                              vertical: 5, horizontal: 20),
                                          width: size.width * 0.7,
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
                                        InputField(
                                            hint: "Umur",
                                            keyboardType: TextInputType.number,
                                            controller: ageController),
                                        const SizedBox(
                                          height: 20.0,
                                        ),
                                        Button(
                                            text: "Sign Up",
                                            onPressed: () async {
                                              var body = convert
                                                  .jsonEncode(<String, String>{
                                                'username':
                                                    unameController.text,
                                                'nama': nameController.text,
                                                'email': emailController.text,
                                                'password':
                                                    passwordController.text,
                                                'umur': ageController.text,
                                                "role": "Pasien",
                                                'saldo': "0",
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
                                              print(result);

                                              if (result.statusCode == 200) {
                                                showDialog<String>(
                                                  context: context,
                                                  builder:
                                                      (BuildContext context) =>
                                                          AlertDialog(
                                                    title: const Text(
                                                        'Register Success'),
                                                    content: const Text(
                                                        'Account has been successfully registered'),
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
                                                        onPressed: () => Navigator
                                                            .pushReplacementNamed(
                                                                context,
                                                                LoginScreen
                                                                    .routeName),
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
                                                nameController.clear();
                                                emailController.clear();
                                                passwordController.clear();
                                                ageController.clear();
                                              } else {
                                                showDialog<String>(
                                                  context: context,
                                                  builder:
                                                      (BuildContext context) =>
                                                          AlertDialog(
                                                    title: const Text(
                                                        'Register Failed'),
                                                    content: const Text(
                                                        'An error occurred, please try again'),
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
                                                        child: const Text(
                                                          'OK',
                                                          style: TextStyle(
                                                            fontSize: 14.0,
                                                            fontWeight:
                                                                FontWeight.bold,
                                                            color: Colors.white,
                                                          ),
                                                        ),
                                                      ),
                                                    ],
                                                  ),
                                                );
                                              }
                                            }),
                                        SizedBox(height: size.height * 0.03),
                                        TextButton(
                                          child: const Text(
                                            'Sudah Punya Akun? Login Disini',
                                            style: TextStyle(
                                              fontSize: 14.0,
                                              fontWeight: FontWeight.bold,
                                              color: Colors.black,
                                            ),
                                          ),
                                          onPressed: () {
                                            Navigator.push(
                                                context,
                                                MaterialPageRoute(
                                                    builder: (context) =>
                                                        const LoginScreen()));
                                          },
                                        ),
                                      ]))
                                ]))),
                        SizedBox(height: size.height * 0.5),
                      ]),
                    ),
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
