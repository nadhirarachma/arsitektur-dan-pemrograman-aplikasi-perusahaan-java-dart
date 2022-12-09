import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class RequestHelper {
  static const apiPath = "https://apap-087.cs.ui.ac.id/api/v1";

  static Future<dynamic> get(String path) async {
    final urlPath = '$apiPath$path';

    final response = await http.get(
      Uri.parse(urlPath),
    );

    return _responseHandler(response);
  }

  static Future<dynamic> post(String path, Map<String, dynamic>? body) async {
    final urlPath = '$apiPath$path';

    final response = await http.post(
      Uri.parse(urlPath),
      body: json.encoder.convert(body),
    );

    return _responseHandler(response);
  }

  static _responseHandler(http.Response response) {
    int statusCode = response.statusCode;
    if (statusCode != 200) {
      throw ErrorDescription('Error $statusCode');
    }
    return json.decode(response.body);
  }
}
