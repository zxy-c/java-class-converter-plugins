interface KotlinClass{
    readonly name:string;
    readonly javaClass:JavaClass;
    body?:string
}

interface JavaClass{
    demo:string;
    number1:number;
    number2:number;
    aBoolean:boolean;
    list:Array<string>;
    array:Array<string>;
    customClass:CustomClass
}

interface CustomClass{
    a:string
}