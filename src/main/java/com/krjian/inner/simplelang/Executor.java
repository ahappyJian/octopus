package com.krjian.inner.simplelang;

import com.krjian.inner.data.AIType;
import com.krjian.inner.data.AIint;
import com.krjian.inner.data.AIstring;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhujian on 10/3/14.
 */
public class Executor {
    private List<String> cmds;
    Iterator iter = null;
    HashMap<String, AIType> varTable = new HashMap<String, AIType>();

    public static LinkedList<String> loadCmd(File file) throws IOException{
        FileReader fr = null;
        BufferedReader br = null;
        LinkedList<String> cmdList = new LinkedList<String>();
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line = null;
            while((line = br.readLine()) != null){
                line = line.trim();
                if(line.length() == 0){ continue; }
                String[] statements = line.split(";");
                for(String str: statements){
                    cmdList.add(str);
                }
            }
        }finally {
            if(fr != null ) { fr.close(); }
            if(br != null) { br.close(); }
        }
        return cmdList;
    }

    public Executor(List<String> cmds){
        this.cmds = cmds;
        iter = cmds.iterator();
    }
    public HashMap<String, AIType> getContext(){
        return varTable;
    }

    public void resetExec(){
        iter = cmds.iterator();
    }
    public boolean exec() throws Exception{
        if(iter.hasNext() == false){
            return false;
        }
        String cmd = (String)iter.next();
        cmd = cmd.trim();
        if(cmd.length() != 0){
            parseCmd(cmd);
        }
        return true;
    }

    private void parseCmd(String cmd) throws Exception {
        if (cmd.matches("^print\\s+(\\S+)$")) {
            //print A;
            printVar(cmd);
        }else if(cmd.matches("^(\\w+)\\s*:=(.*)$")){
            //A:=string
            parseValue(cmd);
        } else if (cmd.matches("^(\\w+)\\s*=\\s*\\((int|long|float|double|string)\\)\\s*([-+\\w.]+)$")) {
            //A=(type)var
            parseTypeCasting(cmd);
        } else if (cmd.matches("^(\\w+)\\s*=\\s*(\\w+)\\s*([-+*/.])\\s*(\\w+)$")) {
            //A=B op C;
            parseOperation(cmd);
        } else {
            throw new Exception("Bad statment:" + cmd);
        }
    }
    private void printVar(String cmd) throws Exception{
        String var = cmd.replaceAll("^print\\s+", "");
        AIType value = varTable.get(var);
        if(value == null){
            System.out.println("No value for:" + var);
        }else{
            System.out.println(value.toAIString().toString());
        }
    }
    private void parseValue(String cmd) throws Exception{
        //A:=string
        Pattern pat = Pattern.compile("^(\\w+)\\s*:=(.*)$");
        Matcher m = pat.matcher(cmd);
        if(! m.find()) {
            throw new Exception("Bad statment:" + cmd);
        }
        String varA = m.group(1);
        String str = m.group(2);
        varTable.put(varA, new AIstring(str));
    }
    private void parseTypeCasting(String cmd) throws Exception{
        //A=(type)string
        Pattern pat = Pattern.compile("^(\\w+)\\s*=\\s*\\((int|long|float|double|string)\\)\\s*([-+\\w.]+)$");
        Matcher m = pat.matcher(cmd);
        if(! m.find()) {
            throw new Exception("Bad statment:" + cmd);
        }
        String varA = m.group(1);
        String type = m.group(2);
        String value = m.group(3);
        AIType aiType = varTable.get(value);
        if(aiType == null){
            aiType = new AIstring(value);
        }
        if("int".equals(type)){
            varTable.put(varA, aiType.toInt());
        }else if("long".equals(type)){
            varTable.put(varA, aiType.toLong());
        }else if("float".equals(type)){
            varTable.put(varA, aiType.toFloat());
        }else if("double".equals(type)){
            varTable.put(varA, aiType.toDouble());
        }else{
            varTable.put(varA, aiType);
        }
    }
    private void parseOperation(String cmd) throws Exception{
        //A=B op C
        Pattern pat = Pattern.compile("^(\\w+)\\s*=\\s*(\\w+)\\s*([-+*/.])\\s*(\\w+)$");
        Matcher m = pat.matcher(cmd);
        if(! m.find()) {
            throw new Exception("Bad statment:" + cmd);
        }
        String A = m.group(1);
        String B = m.group(2);
        String op = m.group(3);
        String C = m.group(4);
        AIType bb = varTable.get(B);
        bb = bb == null ? new AIstring(B) : bb;
        AIType cc = varTable.get(C);
        cc = cc == null ? new AIstring(C) : cc;
        AIType res = null;
        if("+".equals(op)){
            res = bb.plus(cc, false);
        }else if("-".equals(op)){
            res = bb.minus(cc, false);
        }else if("*".equals(op)){
            res = bb.multiple(cc, false);
        }else if("/".equals(op)){
            res = bb.divide(cc, false);
        }else {
            res = bb.concat(cc, false);
        }
        varTable.put(A, res);
    }

}
