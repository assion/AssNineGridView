package com.assionhonty.assninegridview;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author assionhonty
 * Created on 2018/9/19 12:02.
 * Email：assionhonty@126.com
 * Function:
 */
public class UrlData {

    /**
     * 图片假数据
     */
    private static final String IMGURL1 = "http://img.hb.aicdn.com/05927bdaec8213d858a0c3ec201ea0f405ad40e845d02-qJDlLb_fw658";
    private static final String IMGURL2 = "http://img.hb.aicdn.com/98e2007c524387a1d6444f9b80a15cf253d408b2244ed-owRaCM_fw658";
    private static final String IMGURL3 = "http://img.hb.aicdn.com/f69f6ea969f2231be1f9fe6ffd0e73965774a6336986f-mEVEjy_fw658";
    private static final String IMGURL4 = "http://img.hb.aicdn.com/c7f89bec028ecdc8348e80b0911baf10666f932b40396-u7wY7L_fw658";
    private static final String IMGURL5 = "http://img.hb.aicdn.com/d99978ec4bd8ac4013ac1b36b00a8c13098a5827540dd-LkJX6p_fw658";
    private static final String IMGURL6 = "http://img.hb.aicdn.com/e411e58dbd56ad3227724bbbbd7eb07416e4b43a46a41-JGzo9p_fw658";
    private static final String IMGURL7 = "http://img.hb.aicdn.com/75225644fec9d08dd4fdde72def94de0998cb38528a77-dOl6eM_fw658";
    private static final String IMGURL8 = "http://img.hb.aicdn.com/25e4071ba9d56aec8997857d916811e2cb020256504be-897MSv_fw658";
    private static final String IMGURL9 = "http://img.hb.aicdn.com/9b31060eaa4185bbb660af61d8c72206746657782631e-IviKwU_fw658";
    private static final String IMGURL10 = "http://img.hb.aicdn.com/dad95cc911f2e9b9bd614aacab76fc004c9d8d7e205bd-Awr1Pc_fw658";
    private static final String IMGURL11 = "http://img.hb.aicdn.com/9eececc00249510fcae7fa8253d00629969b8b52131e3-F5DykE_fw658";
    private static final String IMGURL12 = "http://img.hb.aicdn.com/79df58c579c840c2fe8f4a10eff2ceb34737c9df3915c-ZuiCYr_fw658";
    private static final String IMGURL13 = "http://img.hb.aicdn.com/e656366714f79488096591ab01f14e7d6d963782292b4-iRFQVL_fw658";
    private static final String IMGURL14 = "http://img.hb.aicdn.com/a7ab84658399a8a9299e25c73c4c32e365d743b4344d7-X6J071_fw658";
    private static final String IMGURL15 = "http://img.hb.aicdn.com/3c5bdda5f73ed9f0dfe7c1f4ade4959ad82721ee11964-lfaGLD_fw658";

    public static List<String> getImageLists() {
        String[] photos = {IMGURL1, IMGURL2, IMGURL3, IMGURL4, IMGURL5, IMGURL6, IMGURL7, IMGURL8, IMGURL9, IMGURL10, IMGURL11, IMGURL12, IMGURL13, IMGURL14, IMGURL15};
        List<String> photoLists = Arrays.asList(photos);
        Collections.shuffle(photoLists);
        return photoLists;
    }

}
