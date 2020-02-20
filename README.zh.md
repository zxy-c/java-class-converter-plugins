*java-converter-plugin* JetBrains插件
=========================

将Java、Kotlin的类转换成其他语言的定义方式

## [Java Class to Typescript](https://plugins.jetbrains.com/plugin/13800-java-class-to-typescript)
### 功能
- 将Java、Kotlin的类转换成Typescript的接口
    - 生成文件至指定目录
    - 生成文件保存在剪切板
    - 生成代码保存在剪切板
    
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
### 功能
- 将Java、Kotlin的类转换成Dart的类
    - 生成文件至指定目录
    - 生成文件保存在剪切板
    - 生成代码保存在剪切板
    

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