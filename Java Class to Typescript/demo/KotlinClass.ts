/*
 *    Copyright (c) [$year] [zxy]
 *    [java-class-converter-plugins] is licensed under the Mulan PSL v1.
 *    You can use this software according to the terms and conditions of the Mulan PSL v1.
 *    You may obtain a copy of Mulan PSL v1 at:
 *       http://license.coscl.org.cn/MulanPSL
 *    THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR
 *    IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR
 *    PURPOSE.
 */

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