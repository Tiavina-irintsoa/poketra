package util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AffichageGeneraliser {
    public String formule(Object zavatra) throws Exception
    {

        String[] type=getalltype(zavatra);
        String answer="<h1>"+zavatra.getClass().getSimpleName()+"</h1>";
        answer+="<div><form action="+"confirmer.jsp?class="+zavatra.getClass().getSimpleName()+" method=post>";
        Field[] attribut=zavatra.getClass().getDeclaredFields();
        for (int i = 0; i < attribut.length; i++)
        {
            if(type[i].compareTo("LocalDateTime")==0 || type[i].compareTo("LocalDate")==0)
            {
                answer+="<p>"+attribut[i].getName()+"</p>";
                answer+="<input type=date name="+attribut[i].getName()+" value="+java.time.LocalDate.now()+">";
            }
            else
            {
                answer+="<p>"+attribut[i].getName()+"</p>";
                answer+="<input type=text name="+attribut[i].getName()+">";
            }

        }
        answer+="<button type=\"submit\" class=\"btn btn-primary mt-3\">Send</button></form>";

        return answer;
    }

    public String formuleSansId(Object zavatra) throws Exception
    {

        String[] type=getalltype(zavatra);
        String answer="        <div class=\"content-wrapper\">\n" +
                "            <!-- Content -->\n" +
                "\n" +
                "            <div class=\"container-xxl flex-grow-1 container-p-y\">\n" +
                "                <h1>"+zavatra.getClass().getSimpleName()+"</h1><div class=\"row\">\n" +
                "\n" +
                "                    <div class=\"col-md-12\">\n" +
                "                        <div class=\"card mb-12\">\n" +
                "                            <div class=\"card-header d-flex align-items-center justify-content-between\">\n" +
                "                                <h5 class=\"mb-0\">Ajouter</h5>\n" +
                "                            </div>\n" +
                "                            <div class=\"card-body\">";

        answer+="<div><form action=\"ajout"+capitalizeFirstLetter(zavatra.getClass().getSimpleName())+"Submit\" method=\"post\">";
        Field[] attribut=zavatra.getClass().getDeclaredFields();
        for (int i = 1; i < attribut.length; i++)
        {
            if(type[i].compareTo("LocalDateTime")==0 || type[i].compareTo("LocalDate")==0)
            {
                answer+="<div class=\"row mb-3\"> <label class=\"col-sm-4 col-form-label\" for=\""+attribut[i].getName()+"\">"+attribut[i].getName()+":</label>";
                answer+="<div class=\"col-sm-10\"> <input type=date name="+attribut[i].getName()+" id="+attribut[i].getName()+" value="+java.time.LocalDate.now()+"> </div> </div>";
            }
            else
            {
                answer+="<div class=\"row mb-3\"> <label class=\"col-sm-2 col-form-label\" for=\""+attribut[i].getName()+"\">"+attribut[i].getName()+":</label>";
                answer+="<div class=\"col-sm-10\"> <input type=text name="+attribut[i].getName()+" id="+attribut[i].getName()+"> </div> </div>";
            }

        }
        answer+="                                    <div class=\"row justify-content-end\">\n" +
                "                                        <div class=\"col-sm-10\">";
        answer+="<br><button type=\"submit\" class=\"btn btn-primary mt-3\">Send</button></form>";
        answer+="</div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>";

        return answer;
    }

    public String[] getallname(Object zavatra)
    {
        Field[] attribut=zavatra.getClass().getDeclaredFields();
        String[] lesattribut=new String[attribut.length];
        for (int i = 0; i < attribut.length; i++)
        {
            lesattribut[i]= attribut[i].getName();
        }
        return lesattribut;
    }

    public double allsomme( Object zavatra[], String lesommer)
    {
        double valiny=0;
        try
        {
            for (int i = 0; i < zavatra.length; i++)
            {
                Method fonction=zavatra[i].getClass().getMethod("get"+lesommer);
                valiny=valiny+(double)fonction.invoke(zavatra[i]);
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
        return valiny;

    }

    public String getliste(Object[] zavatra,String asommer)
    {
        String answer="<h1>"+zavatra[0].getClass().getSimpleName()+"</h1>";
        String[] allname=getallname(zavatra[0]);

        answer+="<table border=\"1\" >";
        answer+="<tr>";
        for (int i = 0; i < allname.length; i++)
        {
            answer+="<td>"+allname[i]+"</td>";
        }
        answer+="</tr>";
        for (int a = 0; a < zavatra.length; a++)
        {
            answer+="<tr>";
            for (int in = 0; in < allname.length; in++)
            {
                try
                {
                    Method fomba =zavatra[a].getClass().getMethod("get"+allname[in]);
                    answer+="<td>"+fomba.invoke(zavatra[a])+"</td>";
                } catch (Exception e)
                {
                    // TODO: handle exception
                }
            }
            answer+="</tr>";
        }
        answer+="</table></div>";
        double val=allsomme(zavatra,asommer);

        answer+="<p>"+"somme "+asommer+"="+val+"</p>";
        return answer;

    }


    public String formulecustom(Object zavatra,String link,String[] tsita ,String[][] contentliste,String[] alister, String[] predef,String[] apredef,int[] aranger) throws Exception
    {
        String answer="<h1>"+zavatra.getClass().getSimpleName()+"</h1>";
        answer+="<div><form action="+link+" method=post>";
        Field[] attribut=zavatra.getClass().getDeclaredFields();
        for (int i = 0; i < aranger.length; i++)
        {
            if (Comparetab(tsita,attribut[aranger[i]].getName())!=-1)
            {
                answer+="<input type=hidden name="+attribut[aranger[i]].getName()+">";
            }
            else
            {
                int n=Comparetab(alister,attribut[aranger[i]].getName());
                int n1=Comparetab(apredef, attribut[aranger[i]].getName());
                if (n!=-1)
                {
                    answer+="<p>"+attribut[aranger[i]].getName()+"</p>";
                    answer+="<select name="+attribut[aranger[i]].getName()+">";
                    for (int j = 0; j < contentliste[n].length; j++)
                    {
                        answer+="<option values="+contentliste[n][j]+">"+contentliste[n][j]+"</option>";
                    }
                    answer+="</select>";
                }
                else if (n1!=-1)
                {
                    answer+="<p>"+attribut[aranger[i]].getName()+"</p>";
                    answer+="<input type=text name="+attribut[aranger[i]].getName()+" value="+predef[n1]+">";
                }
                else if(attribut[aranger[i]].getName().compareTo("Date")==0)
                {
                    answer+="<p>"+attribut[aranger[i]].getName()+"</p>";
                    answer+="<input type=date name="+attribut[aranger[i]].getName()+" value="+java.time.LocalDate.now()+">";


                }
                else
                {
                    answer+="<p>"+attribut[aranger[i]].getName()+"</p>";
                    answer+="<input type=text name="+attribut[aranger[i]].getName()+">";
                }
            }

        }
        answer+="<br><br><input type=submit value=confirmer></form>";

        return answer;

    }

    public String formuleliste(Object zavatra,String[][] contentliste,String[] alister)
    {
        String[] type=getalltype(zavatra);
        String answer="<h1>"+zavatra.getClass().getSimpleName()+"</h1>";
        answer+="<div><form action=confirmer.jsp?class="+zavatra.getClass().getSimpleName()+" method=post>";
        Field[] attribut=zavatra.getClass().getDeclaredFields();
        for (int i = 0; i < attribut.length; i++)
        {
            int n=Comparetab(alister,attribut[i].getName());
            if (n!=-1)
            {
                answer+="<p>"+attribut[i].getName()+"</p>";
                answer+="<select name="+attribut[i].getName()+">";
                for (int j = 0; j < contentliste[n].length; j++)
                {
                    answer+="<option values="+contentliste[n][j]+">"+contentliste[n][j]+"</option>";
                }
                answer+="</select>";
            }
            else if(type[i].compareTo("LocalDateTime")==0 || type[i].compareTo("LocalDate")==0)
            {
                answer+="<p>"+attribut[i].getName()+"</p>";
                answer+="<input type=date name="+attribut[i].getName()+" value="+java.time.LocalDate.now()+">";
            }
            else
            {
                answer+="<p>"+attribut[i].getName()+"</p>";
                answer+="<input type=text name="+attribut[i].getName()+">";
            }
        }

        answer+="<br><br><input type=submit value=confirmer></form>";

        return answer;

    }

    public String furmulaipreredef(Object zavatra,String[] predef,String[] apredef)
    {
        String answer="<h1>"+zavatra.getClass().getSimpleName()+"</h1>";
        answer+="<div><form action="+"confirmer.jsp?class="+zavatra.getClass().getSimpleName()+" method=post>";
        Field[] attribut=zavatra.getClass().getDeclaredFields();
        for (int i = 0; i < attribut.length; i++)
        {
            int n=Comparetab(apredef, attribut[i].getName());
            if (n!=-1)
            {
                answer+="<p>"+attribut[i].getName()+"</p>";
                answer+="<input type=text name="+attribut[i].getName()+" value="+predef[n]+">";
            }
            else
            {
                answer+="<p>"+attribut[i].getName()+"</p>";
                answer+="<input type=text name="+attribut[i].getName()+">";
            }


        }
        answer+="<br><input type=submit value=confirmer></form>";

        return answer;


    }

    public int Comparetab(String[] tsita, String name)
    {
        for (int i = 0; i < tsita.length; i++)
        {
            if (tsita[i].compareTo(name)==0)
            {
                return i;
            }
        }
        return -1;
    }

    public String genereTable(String[] tete, String[][] content)
    {
        String result="<table>";
        result+="<tr>";
        int ref=-1;
        for (int i = 0; i < tete.length; i++) {
            result += "<td style=\"padding-left: 20px\">"+tete[i]+"</td>";
            if (tete[i].equalsIgnoreCase("img")) {
                ref=i;
            }
        }
        result+= "</tr>";
        for (int i = 0; i < content.length; i++) {
            result+="<tr style=\"padding-top: 20px\">";
            for (int j = 0; j < content[i].length; j++) {
                if (j == ref) {
                    result+="<td style=\"padding-left: 20px\"><img  width=\"100px\" height=\"100px\" src=\"img/"+content[i][j]+"\" /> </td>";

                }
                else
                {
                    result+="<td style=\"padding-left: 20px\">"+content[i][j]+"</td>";
                }

            }
            result +="</tr>";
        }
        result+="</table></div>";

        return result;
    }

    public String genertabligne(String[] tete, String[] content)
    {
        String result="<table>";
        for (int i = 0; i < tete.length; i++) {
            result+="<tr><td>"+tete[i]+" : </td><td>"+content[i]+"</td></tr>";
        }
        result+="</table>";

        return result;
    }

    public String[] addnumfirst(String[] tete)
    {
        String[] val=new String[tete.length+1];
        val[0]="Numero";
        for (int i = 0; i < tete.length; i++) {
            val[i+1]=tete[i];
        }
        return val;
    }


    public String[] getalltype(Object zavatra)
    {
        Field[] attribut=zavatra.getClass().getDeclaredFields();
        String[] lesattribut=new String[attribut.length];
        for (int i = 0; i < attribut.length; i++)
        {
            lesattribut[i]= attribut[i].getType().getSimpleName();
        }
        return lesattribut;
    }

    public String genertable(Object[] zavatra)
    {
        String answer="<h1>"+zavatra[0].getClass().getSimpleName()+"</h1>";
        String[] allname=getallname(zavatra[0]);
        answer+="<div class=\"content-wrapper\"> <div class=\"container-xxl flex-grow-1 container-p-y\"> <div class=\"card\"> <div class=\"table-responsive text-nowrap\">";
        answer+="<table class=\"table\"border=\"1\" >";
        answer+="                    <table class=\"table\">\n" +
                "                        <thead>\n" +
                "                        <tr>\n" +
                "                            <th>Matiere</th>\n" +
                "                        </tr>\n" +
                "                        </thead>+" +
                "                        <tbody class=\"table-border-bottom-0\">";


        answer+="<tr>";
        for (int i = 0; i < allname.length; i++)
        {
            answer+="<td>"+allname[i]+"</td>";
        }
        answer+="</tr>";
        for (int a = 0; a < zavatra.length; a++)
        {
            answer+="<tr>";
            for (int in = 0; in < allname.length; in++)
            {
                try
                {
                    Method fomba =zavatra[a].getClass().getMethod("get"+capitalizeFirstLetter(allname[in]));
                    answer+="<td>"+fomba.invoke(zavatra[a])+"</td>";
                } catch (Exception e)
                {
                    // TODO: handle exception
                }
            }
            answer+="</tr>";
        }
        answer+="</tbody>\n" +
                "                    </table></div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>";
        return answer;
    }

    public static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return Character.toUpperCase(input.charAt(0)) + input.substring(1);
    }

}
