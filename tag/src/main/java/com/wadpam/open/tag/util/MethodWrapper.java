package com.wadpam.open.tag.util;

import java.io.Serializable;

/**
 * @author mak sophea
 * 
 */
public class MethodWrapper implements Serializable {

    public MethodWrapper(String className, String methodName) {
        this(className, methodName, new Object[0]);
    }

    public MethodWrapper(String className, String methodName, Object arg) {
        this(className, methodName, new Object[]{arg});
    }

    public MethodWrapper(String className, String methodName, Object[] args) {
        _className = className;
        _methodName = methodName;
        _args = args;
    }

    public String getClassName() {
        return _className;
    }

    public String getMethodName() {
        return _methodName;
    }

    public Object[] getArgs() {
        // Object[] args = new Object[_args.length];
        //
        // System.arraycopy(_args, 0, args, 0, _args.length);

        return _args;
    }

    private String   _className;
    private String   _methodName;
    private Object[] _args;

}