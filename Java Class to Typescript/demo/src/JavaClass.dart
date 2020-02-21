class ClassMetadata{
  final String name;
  final List<ClassPropertyMetadata> properties;
  final String doc;
  final bool isEnum;
  final List<EnumEntryMetadata> enums;
}

class ClassPropertyMetadata{
  final String name;
  final TypeMetadata type;
  final bool isNullable;
  final bool isFinal;
  final String doc;
}

class TypeMetadata{
  final String name;
  final ObviousType type;
  final List<TypeMetadata> types;
}

enum ObviousType{
  STRING,
  ARRAY,
  OBJECT,
  BOOLEAN,
  ANY,
  ENUM,
  INT,
  DOUBLE,
  LONG,
  FLOAT,
  MAP,
  COLLECTION
}

class EnumEntryMetadata{
  final String name;
  final String doc;
}