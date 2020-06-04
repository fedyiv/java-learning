package org.fedyiv.generics.erasure;

//View -> Show Bytecode --> synthetic generated method compareTo(Object)
public class NameError implements Comparable<NameError> {

    @Override
    public int compareTo(NameError nameError) {
        return 0;
    }
/* This function introduces compile time error because of the type erasure
*int compareTo(T var1) is translated as compareTo(Object var1)
*
    public int compareTo(Object o) {

    }
 */
}
