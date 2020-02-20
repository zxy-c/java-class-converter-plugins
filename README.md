*java-converter-plugin* for JetBrains 
===========================
[中文](README.zh.md)

Declarations for converting Java and Kotlin classes to other languages

## [Java Class to Typescript](https://plugins.jetbrains.com/plugin/13800-java-class-to-typescript)
### Features
- Convert Java and Kotlin Class to Typescript Interface
    - Generate files to specified directory
    - Generated files are saved in the clipboard
    - Generated code saved in clipboard
    
### How to use   
Right-click on the Java class or Kotlin class and select the sub-items in the *Convert to Typescript Interface* group as required
    
before
```java
/**
 * classDoc
 */
public class JavaClass {
    private String demo;
    private int number1;
    private long number2;
    private final Boolean aBoolean = true;
    private List<String> list;
    private String[] array;
    private CustomClass customClass;
    private Map<String,CustomClass> map;
    private C c;
    /**
     * fieldDoc
     * fieldDoc
     */
    private float f;
}

enum C{
    /**
     * enumDoc
     * enumDoc
     */
    A,
    B
}

class CustomClass{
    private String a;

}
```

after
```typescript
/*
 * classDoc
 */
interface JavaClass {
    demo: string;
    number1: number;
    number2: number;
    readonly aBoolean: boolean;
    list: Array<string>;
    array: Array<string>;
    customClass: CustomClass;
    map: {
        [key: string]: CustomClass
    };
    c: C;
    /*
     * fieldDoc
     * fieldDoc
     */
    f: number;
}

interface CustomClass {
    a: string;
}

enum C {
    /*
     * enumDoc
     * enumDoc
     */
    A,
    B
}
```

## Java Class to Dart
### Features
- Convert Java and Kotlin Class to Dart Class
    - Generate files to specified directory
    - Generated files are saved in the clipboard
    - Generated code saved in clipboard

### How to use   
Right-click on the Java class or Kotlin class and select the sub-items in the *Convert to Dart Class* group as required

before
```java
/**
 * classDoc
 */
public class JavaClass {
    private String demo;
    private int number1;
    private long number2;
    private final Boolean aBoolean = true;
    private List<String> list;
    private String[] array;
    private CustomClass customClass;
    private Map<String,CustomClass> map;
    private C c;
    /**
     * fieldDoc
     * fieldDoc
     */
    private float f;
}

enum C{
    /**
     * enumDoc
     * enumDoc
     */
    A,
    B
}

class CustomClass{
    private String a;

}
```

after
```dart
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
```