package com.wadpam.open.tag.util;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Date;

public class ObjectComparator extends AbstractSortType implements Comparator<Object> {

    public ObjectComparator() {
        sort = 1;
    }

    public ObjectComparator(int isAsc, String methoName) {
        this.sort = isAsc;
        // this.className = className;
        this.methodName = methoName;
    }

    public int compare(Object arg0, Object arg1) {

        MethodWrapper methodWrapper1 = new MethodWrapper(arg0.getClass().getName(), methodName);
        // MethodInvoker.

        MethodWrapper methodWrapper2 = new MethodWrapper(arg1.getClass().getName(), methodName);
        try {
            Method d = arg0.getClass().getMethod(methodName, null);
            Object type = d.invoke(arg0, null);
            Object type1 = d.invoke(arg1, null);
            // Object type = PortalClassInvoker.invoke(arg0.getClass().getName(),methodName,arg0);
            // Object type1 = PortalClassInvoker.invoke(arg1.getClass().getName(),methodName,arg1);
            // Object type = MethodInvoker.invoke(methodWrapper1,false);
            // Object type1 = MethodInvoker.invoke(methodWrapper2,false);

            if (type == null || type1 == null)
                return sort;

            if (type instanceof String) {

                return sort * ((String) type).compareToIgnoreCase((String) type1);
            }
            else if (type instanceof Integer) {
                return sort * ((Integer) type).compareTo((Integer) type1);
            }
            else if (type instanceof Double) {
                return sort * ((Double) type).compareTo((Double) type1);
            }
            else if (type instanceof Date) {
                return sort * ((Date) type).compareTo((Date) type1);
            }
            else if (type instanceof Long) {
                return sort * ((Long) type).compareTo((Long) type1);

            }

        }
        catch (Exception e) {
            // /throw new Exception(e.getMessage());
        }
        // sort * MethodInvoker.invoke(methodWrapper1)
        // WeddingVO s1 = (WeddingVO) arg0;
        // WeddingVO s2 = (WeddingVO) arg1;
        // if(s1.getName() == null)
        // return sort;
        // else if(s2.getName() == null)
        // return sort;
        // else
        // return sort * s1.getName().compareToIgnoreCase(s2.getName());
        return 0;
    }

}

