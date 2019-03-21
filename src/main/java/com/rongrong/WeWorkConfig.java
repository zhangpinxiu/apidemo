package com.rongrong;

/**
 * @author rongrong
 * @Title: com.rongrong.WeWorkConfig
 * @ProjectName apidemo
 * @Description: TODO
 * @date 2019/3/17 10:33
 * @email 1163565402@qq.com
 */
public class WeWorkConfig {
    public String agentId="1000005";

    //我自己的 d0Re-DvQTKIfqveaSqQspgB7Chm6DLQFPfMfDSMZAq4 testhome的 1JPyY9GvPLZfpvxEDjok-Xt_9v7HIBYJhZUoO6EgNGY
    public String secret="d0Re-DvQTKIfqveaSqQspgB7Chm6DLQFPfMfDSMZAq4";

    //我自己的 wwef1746775ffcb07b testhome的 wwd6da61649bd66fea
    public String corpid = "wwef1746775ffcb07b";

    //我自己的 nLybG44Z1sAKLTiIb_FbcNT5X4dsOVHE_drMMjBL0z0 testhome的 C7uGOrNyxWWzwBsUyWEbLQdOqoWPz4hNvxj9RIFv-4U
    public String contactSecret="nLybG44Z1sAKLTiIb_FbcOYy4ft12TKI2efuKbXFnOw";

    private static WeWorkConfig weworkConfig;
    public static WeWorkConfig getInstance(){
        if(weworkConfig==null){
            weworkConfig=new WeWorkConfig();
        }
        return weworkConfig;

    }

    public static void load(String path){

        //todo: read from yaml or json

    }
}
