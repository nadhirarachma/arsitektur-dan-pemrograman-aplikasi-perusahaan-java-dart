import 'package:flutter/material.dart';

import 'package:login_logout_registration/screens/login_screen.dart';
import 'package:login_logout_registration/screens/registration_screen.dart';

import 'package:login_logout_registration/components/button.dart';

class WelcomeScreen extends StatelessWidget {
  static const routeName = '/WelcomeScreen';
  const WelcomeScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
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
                    const SizedBox(height: 100),
                    const Text(
                      "Welcome to RumahSehat!",
                      style: TextStyle(
                          fontSize: 25.0,
                          fontWeight: FontWeight.bold,
                          color: Colors.blue),
                    ),
                    SizedBox(height: size.height * 0.05),
                    Button(
                        text: "LOGIN",
                        onPressed: () async {
                          Navigator.push(context,
                              MaterialPageRoute(builder: (context) {
                                return const LoginScreen();
                              }));
                        }),
                    Button(
                        text: "SIGN UP",
                        onPressed: () async {
                          Navigator.push(context,
                              MaterialPageRoute(builder: (context) {
                                return const RegistrationScreen();
                              }));
                        }),
                    SizedBox(height: size.height * 0.5),
                  ],
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}