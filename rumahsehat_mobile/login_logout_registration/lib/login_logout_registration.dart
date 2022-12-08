library login_logout_registration_registration;

import 'package:flutter/material.dart';

import 'package:login_logout_registration/screens/welcome_screen.dart';
import 'package:login_logout_registration/utils/network_service.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.

  @override
  Widget build(BuildContext context) {
    return Provider(
      create: (_) {
        NetworkService request = NetworkService();
        return request;
      },
      child: MaterialApp(
        title: "RumahSehat",
        debugShowCheckedModeBanner: false,
        theme: ThemeData(
            primaryColor: Colors.indigo, scaffoldBackgroundColor: Colors.white),
        home: const WelcomeScreen(),
      ),
    );
  }
}