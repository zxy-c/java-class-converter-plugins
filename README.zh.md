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
class Book{
    final String name;
    double price;
    Map<String,String> properties;
    List<Author> authors;    
}

class Author{
    String name;
    Object age;
}
```

after
```typescript
interface Book{
    readonly name:string;
    price:number;
    [key :string]: string;
    authors:Array<Author>
}

interface Author{
    name:string;
    age:number;
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
class Book{
    final String name;
    double price;
    Map<String,String> properties;
    List<Author> authors;    
}

class Author{
    String name;
    Object age;
}
```

after
```dart
class Book{
    final String name;
    double price;
    Map<String,String> properties;
    List<Author> authors; 
}

class Author{
    String name;
    var age;
}
```