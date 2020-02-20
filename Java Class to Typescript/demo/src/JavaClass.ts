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