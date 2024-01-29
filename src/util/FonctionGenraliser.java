package util;

import connexion.Connect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class FonctionGenraliser{

    public static String capitalize(String s)
    {
        return s.substring(0,1).toUpperCase()+s.substring(1);
    }
    public static String decapitalize(String s)
    {
        return s.toLowerCase();
    }

    public int getnbtab(ResultSet result) throws Exception
    {
        int nb=0;
        while (result.next())
        {
            nb++;
        }
        return nb;
    }

    public static String[] getallname(Object zavatra)
    {
        Field[] attribut=zavatra.getClass().getDeclaredFields();
        String[] lesattribut=new String[attribut.length];
        for (int i = 0; i < attribut.length; i++)
        {
            lesattribut[i]= attribut[i].getName();
        }
        return lesattribut;
    }

    public static String[] getalltype(Object zavatra)
    {
        Field[] attribut=zavatra.getClass().getDeclaredFields();
        String[] lesattribut=new String[attribut.length];
        for (int i = 0; i < attribut.length; i++)
        {
            lesattribut[i]= attribut[i].getType().getSimpleName();
        }
        return lesattribut;
    }

    public static void insertGeneralMysqlPostG(Object obj, Connection connect, String debut)throws Exception {
        boolean connexionOuvert = false;
        if (connect == null) {
            connexionOuvert = true;
            Connect myConnect = new Connect();
            connect = myConnect.getConnectionPostgresql();
        }
        Statement state = connect.createStatement();
        try {
            String classname = obj.getClass().getSimpleName();
            String sql = "INSERT INTO " + decapitalize(classname) + " VALUES(" + debut;
            String[] allname = getallname(obj);
            String[] alltype = getalltype(obj);
            for (int i = 1; i < allname.length; i++) {
                if (alltype[i].equalsIgnoreCase("LocalDateTime") || alltype[i].equalsIgnoreCase("LocalDate")) {
                    Method fomba = obj.getClass().getMethod("get" + capitalize(allname[i]));
                    sql += ",\'" + fomba.invoke(obj) + "\'";
                } else if (alltype[i].equalsIgnoreCase("String")) {
                    Method fomba = obj.getClass().getMethod("get" + capitalize(allname[i]));
                    sql += ",\'" + fomba.invoke(obj) + "\'";
                } else {
                    Method fomba = obj.getClass().getMethod("get" + capitalize(allname[i]));
                    sql += "," + fomba.invoke(obj);
                }
            }
            sql += ")";
            System.out.println(sql);

            state.executeUpdate(sql);

            if (connexionOuvert) {
                connect.commit();
            }
        } catch (Exception e) {
            if (connexionOuvert) {
                connect.rollback();
            }
            throw e;
        } finally {
            state.close();
            if (connexionOuvert) {
                connect.close();
            }

        }
    }

    public static Vector<Object> selectGeneraliser(Object objbase, String nomBase, Connection connect) throws Exception {
        boolean connexionOuvert = false;
        if (connect == null) {
            connexionOuvert = true;
            Connect myConnect = new Connect();
            connect = myConnect.getConnectionPostgresql();
        }
        Statement state = connect.createStatement();
        String sql="SELECT * from "+decapitalize(nomBase);
        try {
            ResultSet resultSet = state.executeQuery(sql);
            String classname = objbase.getClass().getName();
            String[] allname = getallname(objbase);
            String[] alltype = getalltype(objbase);
            Vector vect = new Vector<>();
            while (resultSet.next()) {
                Object obj = Class.forName(classname).newInstance();
                for (int i = 0; i < allname.length; i++) {
                    Method fomba = obj.getClass().getMethod("set" + capitalize(allname[i]), String.class);
                    fomba.invoke(obj, resultSet.getString(i + 1));
                }
                vect.add(obj);
            }
            return vect;
        } catch (Exception e) {
            throw e;
        } finally {
            state.close();
            if (connexionOuvert) {
                connect.close();
            }

        }

    }

    public int[] objtointtab(Object[] tabS)
    {
        int[] result=new int[tabS.length];
        for (int i = 0; i < tabS.length; i++) {
            result[i]=Integer.parseInt((String)tabS[i]);
        }
        return result;
    }

}
