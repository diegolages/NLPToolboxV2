/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufrj.cos.nlptoolbox.util;

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3)
// Source File Name:   StringUtil.java

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.gargoylesoftware.base.util:
//            DetailedIllegalArgumentException, DetailedNullPointerException

public class StringUtil
{

    private StringUtil()
    {
    }

    public static String getSubQuery( String f, String c, String d, String type ) {

//            <option value="=">equals</option>
//            <option value="<>">not equal to</option>
//            <option value="starts">start with</option>
//            <option value="contains">contains</option>
//            <option value="!contains">does not contain</option>
//            <option value="<">less than</option>
//            <option value=">">greater than</option>
//            <option value="<=">less or equal</option>
//            <option value=">=">greater or equal</option>

        System.out.println( "Field  = " + f );
        System.out.println( "Comp   = " + c );
        System.out.println( "Data   = " + d );
        System.out.println( "Type   = " + type );

        if (f == null || c == null || d == null) {
            return "";
        }


/* Date ---------------------------------------------------------------------*/
        if( type.equals("Date") ){

            if( c.equals( "contains" ) || c.equals( "!contains" ) || c.equals( "starts" ) ){

                return "-1 = -1";

            }

            return  f + " " + c + " " + getDateLong(d) + " ";
        }

/* Check --------------------------------------------------------------------*/
        if( type.equals("Check") ){

            if( c.equals( "=" ) || c.equals( "<>" ) ){

                if( d.equalsIgnoreCase( "yes" ) || d.equalsIgnoreCase( "sim" ) || d.equalsIgnoreCase( "true" ) || d.equalsIgnoreCase( "verdadeiro" ) ){

                    return  f + " " + c + " true ";

                } else if( d.equalsIgnoreCase( "no" ) || d.equalsIgnoreCase( "não" ) || d.equalsIgnoreCase( "false" ) || d.equalsIgnoreCase( "falso" ) ){

                    return  f + " " + c + " false ";
                }
            }

            return "-1 = -1";
        }

/* Opportunity Stage ---------------------------------------------------------*/
        if( type.equals("OpportunityStage") ){

            if( "Fechada com Sucesso".equalsIgnoreCase(d) ){

                d = "CLOSEDWON";

            }else if( "Perdida".equalsIgnoreCase(d) ){

                d = "CLOSEDLOST";
            }
        }

/* Customer Type -------------------------------------------------------------*/
        if( type.equals("CustomerType") ){

            if( "Empresa".equalsIgnoreCase(d) ){

                d = "2";

            }else if( "Agência".equalsIgnoreCase(d) || "Operadora".equalsIgnoreCase(d) || "Parceiro".equalsIgnoreCase(d)){

                d = "3";

            }else if( "Pessoa".equalsIgnoreCase(d) || "Pessoa Física".equalsIgnoreCase(d) || "Física".equalsIgnoreCase(d)){

                d = "1";
            }
        }

        if (c.equals("starts")) {

            return "lower(" + f + ")  like lower('" + d + "%')";

        } else if (c.equals("contains")) {

            return "lower(" + f + ")  like lower('%" + d + "%')";

        } else if (c.equals("!contains")) {

            return "lower(" + f + ")  not like lower('%" + d + "%')";

        } else {

            return f + " " + c + "'" + d + "'";

        }

    }


    public static String expandTabs(String inputString, int numberOfSpaces)
    {
        if(inputString.indexOf('\t') == -1)
            return inputString;
        int inputStringLength = inputString.length();
        StringBuffer buffer = new StringBuffer(inputStringLength);
        String spaces = nCopies(numberOfSpaces, ' ');
        for(int i = 0; i < inputStringLength; i++)
        {
            char c = inputString.charAt(i);
            if(c == '\t')
                buffer.append(spaces);
            else
                buffer.append(c);
        }

        return buffer.toString();
    }

    public static String nCopies(int copyCount, char charToCopy)
    {
        StringBuffer buffer = new StringBuffer(copyCount);
        for(int i = 0; i < copyCount; i++)
            buffer.append(charToCopy);

        return buffer.toString();
    }

    private static String joinArray(Object array, String separator)
    {
        assertNotNull("array", array);
        assertNotNull("separator", separator);
        int arrayLength = Array.getLength(array);
        if(arrayLength == 0)
            return "";
        StringBuffer buffer = new StringBuffer();
        buffer.append(Array.get(array, 0));
        for(int i = 1; i < arrayLength; i++)
        {
            buffer.append(separator);
            buffer.append(Array.get(array, i));
        }

        return buffer.toString();
    }

    public static String join(int array[], String separator)
    {
        return joinArray(array, separator);
    }

    public static String join(long array[], String separator)
    {
        return joinArray(array, separator);
    }

    public static String join(float array[], String separator)
    {
        return joinArray(array, separator);
    }

    public static String join(double array[], String separator)
    {
        return joinArray(array, separator);
    }

    public static String join(byte array[], String separator)
    {
        return joinArray(array, separator);
    }

    public static String join(Object array[], String separator)
    {
        return joinArray(((Object) (array)), separator);
    }

    public static String join(Collection collection, String separator)
    {
        return joinArray(((Object) (collection.toArray())), separator);
    }

    public static String[] splitAtFirst(String line, String separator)
    {
        int index = line.indexOf(separator);
        String first;
        String second;
        if(index == -1)
        {
            first = line;
            second = "";
        } else
        {
            first = line.substring(0, index);
            second = line.substring(index + separator.length());
        }
        return (new String[] {
            first, second
        });
    }

    public static String[] split(String line, String separator)
    {
        assertNotNull("line", line);
        assertNotNull("separator", separator);
        //if(separator.length() == 0)
        //    throw new new("separator", separator, "May not be empty");
        if(line.length() == 0)
            return new String[0];
        int separatorLength = separator.length();
        List list = new ArrayList();
        int previousIndex = 0;
        for(int index = line.indexOf(separator); index != -1; index = line.indexOf(separator, previousIndex))
        {
            list.add(line.substring(previousIndex, index));
            previousIndex = index + separatorLength;
        }

        list.add(line.substring(previousIndex));
        String stringArray[] = new String[list.size()];
        list.toArray(stringArray);
        return stringArray;
    }

    public static String replace(String sourceString, String match, String replace)
    {
        if(match.length() == 0)
            return sourceString;
        StringBuffer buffer = new StringBuffer(sourceString.length());
        int start = 0;
        int end = sourceString.indexOf(match);
        int matchLength = match.length();
        for(; end != -1; end = sourceString.indexOf(match, start))
        {
            buffer.append(sourceString.substring(start, end));
            buffer.append(replace);
            start = end + matchLength;
        }

        buffer.append(sourceString.substring(start));
        return buffer.toString();
    }

    private static void assertNotNull(String fieldName, Object object)
    {
        //if(object == null)
        //    throw new DetailedNullPointerException(fieldName);
        //else
            return;
    }

    public static String getDateLong( String d ){

        try {

            DateFormat  formatter   = new SimpleDateFormat("dd/MM/yyyy");
            Date        date        = (Date)formatter.parse(d);

            return Long.toString(date.getTime());

        } catch (ParseException pe) {

            pe.printStackTrace();
        }

        return "";
    }

    public static boolean isValidPhone( String phone ){

        return true;
//        Pattern pattern = Pattern.compile("\\(\\d{2}\\)\\d{4}-\\d{4}");
//
//        if (pattern != null) {
//
//            Matcher matcher = pattern.matcher(phone);
//
//            if (matcher.matches()) {
//
//                return true;
//            }
//        }
       // return false;
    }

    public static boolean isValidZip( String zip ){

        Pattern pattern = Pattern.compile("\\d{5}-\\d{3}");

        if (pattern != null) {

            Matcher matcher = pattern.matcher(zip);

            if (matcher.matches()) {

                return true;
            }
        }
        return false;
    }

    private static boolean passwordExists( String password ) throws Exception{

        //TODO
        return false;

//        ResultSet rs = util.DatabaseUtil.getGenericRS( "SELECT id FROM lageswebselfservice WHERE password = '" + password + "'" );
//
//        if( rs.next() ){
//
//            return true;
//        }
//
//        return false;
    }

    public static char getRandomChar(){

        String  chars   = "abcdefghijklmnopqrstuvwxyz";
        char    array[] = chars.toCharArray();

        return array[ (int)(Math.random()*array.length) ];
    }

    public static char getRandomNumber(){

        String  chars   = "1234567890";
        char    array[] = chars.toCharArray();

        return array[ (int)(Math.random()*array.length) ];
    }

    public static String generatePassword() throws Exception{

        String password = "";

        do{
            char chars[] = {getRandomChar(), getRandomChar(), getRandomChar(), getRandomChar(), getRandomChar(), getRandomNumber(), getRandomNumber(), getRandomNumber()};

            password    = new String( chars );

        }while( passwordExists( password ) );

        return password;
    }

    public static String cutString( String text, int maxsize ){

        if( text.length() < maxsize ){

            return text;
        }

        return text.substring(0, maxsize);
    }

    public static String breakString( String text, int maxsize ){

        if( text.length() < maxsize ){

            return text;
        }

        return text.substring(0, maxsize) + "\n" + breakString( text.substring(maxsize), maxsize );
    }

    public static String handleSpecialCharacters( String s ){

//        s = s.replaceAll("�", "a");
//        s = s.replaceAll("�", "a");
//        s = s.replaceAll("�", "a");
//        s = s.replaceAll("a", "a");
//        s = s.replaceAll("�", "A");
//        s = s.replaceAll("A", "A");
//        s = s.replaceAll("�", "A");
//        s = s.replaceAll("A", "A");
//
//        s = s.replaceAll("�", "e");
//        s = s.replaceAll("�", "e");
//        s = s.replaceAll("�", "E");
//        s = s.replaceAll("E", "E");
//
//        s = s.replaceAll("�", "i");
//        s = s.replaceAll("�", "I");
//
//        s = s.replaceAll("�", "o");
//        s = s.replaceAll("o", "o");
//        s = s.replaceAll("�", "o");
//        s = s.replaceAll("�", "O");
//        s = s.replaceAll("O", "O");
//        s = s.replaceAll("�", "O");
//
//        s = s.replaceAll("u", "u");
//        s = s.replaceAll("�", "U");
//
//        s = s.replaceAll("�", "c");
//        s = s.replaceAll("�", "C");

        return s;
    }

    public static String slurp (InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

     public static String slurp (Reader in) throws IOException {
        StringBuffer out = new StringBuffer();
        char[] b = new char[4096];
        for (int n; (n = in.read(b)) != -1;) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    static private DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

    public static String formatSQL(Date d) {
        return sdf1.format(d);
    }

    public static Date toDate(String s) throws Exception {
        return sdf1.parse(s);
    }

}
