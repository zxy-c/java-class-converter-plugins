///classDoc
class JavaClass {
  String demo;
  int number1;
  int number2;
  final bool aBoolean;
  List<String> list;
  List<String> array;
  CustomClass customClass;
  Map<String, CustomClass> map;
  C c;

  ///fieldDoc
  ///fieldDoc
  int f;
}

class CustomClass {
  String a;
}

enum C {
  ///enumDoc
  ///enumDoc
  A,
  B
}