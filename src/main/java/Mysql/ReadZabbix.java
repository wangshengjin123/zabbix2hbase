package Mysql;

import utils.SelectIo;

public class ReadZabbix {
    public static void main(String[] args) {
        String  ip= "172.16.2.224";

/*        SelectIo io =new SelectIo(ip);
        Selectmem.Selectmem(ip);
        Selectcpu.Selectcpu(ip);*/


        SelectIo.SelectSingleIo(ip);


    }
}
