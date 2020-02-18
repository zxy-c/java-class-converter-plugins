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
### Features
- Convert Java and Kotlin Class to Dart Class
    - Generate files to specified directory
    - Generated files are saved in the clipboard
    - Generated code saved in clipboard


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