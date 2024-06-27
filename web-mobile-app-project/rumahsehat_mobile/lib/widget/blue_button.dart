import "package:flutter/material.dart";

class BlueButton extends StatelessWidget {
  final VoidCallback? onPressed;
  final String text;
  final double minWidth;
  final Color? backgroundColor;
  const BlueButton({
    Key? key,
    required this.onPressed,
    required this.text,
    this.minWidth = double.infinity,
    this.backgroundColor = const Color.fromARGB(255, 144, 202, 249),
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return TextButton(
      onPressed: onPressed,
      style: ButtonStyle(
        minimumSize: MaterialStateProperty.all(
          Size(minWidth, 44.0),
        ),
        backgroundColor: MaterialStateProperty.resolveWith<Color?>(
          (states) {
            return backgroundColor;
          },
        ),
        shape: MaterialStateProperty.all<OutlinedBorder>(
          RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(8.0),
          ),
        ),
      ),
      child: Text(
        text,
        style: const TextStyle(
          color: Colors.white,
        ),
      ),
    );
  }
}
