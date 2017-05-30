package com.mtc.translate;

import com.mtc.dao.BaseDao;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by majun on 5/18/17.
 */
@SuppressWarnings("Duplicates")
public class TranslateOITM {

    private BaseDao baseDaoByHana;

    private Map<String,String> translateMap = new HashMap<>();

    private Map<String,String> replaceMap = new HashedMap();

    private String table_name = "OITM";

    public TranslateOITM(){
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/application-context.xml");
        //baseDaoBySqlServer = (BaseDao)context.getBean("baseDaoBySqlServer");
        baseDaoByHana = (BaseDao)context.getBean("baseDaoByHana");

        replaceMap.put("一","1");
        replaceMap.put("二","2");
        replaceMap.put("三","3");
        replaceMap.put("四","4");
        replaceMap.put("五","5");
        replaceMap.put("六","6");
        replaceMap.put("七","7");
        replaceMap.put("八","8");
        replaceMap.put("九","9");
        replaceMap.put("十","10");


        translateMap.put("鸡苗-细菌蛋","bacterial egg");
        translateMap.put("父母代母鸡苗","P.S. Pullet DOC");
        translateMap.put("父母代种母鸡","P.S. Hen");
        translateMap.put("自产种蛋","Own Hatching Egg");
        translateMap.put("外购商品代鸡苗","Outsourced Broiler DOC");
        translateMap.put("^毛鸡","Live Birds ");
        translateMap.put("自产商品代鸡苗","Own Broiler DOC");
        translateMap.put("劳保用品","labour protection appliance");
        translateMap.put("实验用品","Experimental supplies");
        translateMap.put("化学用品","chemicals");
        translateMap.put("父母代种公鸡","P.S. Male");
        translateMap.put("父母代公鸡苗","P.S. Male DOC");
        translateMap.put("外购毛鸡","Outsourced Live Birds");
        translateMap.put("垫料","Litte");

        //translateMap.put("种蛋-畸形蛋","Egg-Deformed");
        //translateMap.put("种蛋-破蛋","Egg-Broken");
        //translateMap.put("鸡苗-毛蛋","DOC-Dead in Shell");
        //translateMap.put("公鸡前期料","Male Starter (0-4wks)");
        //translateMap.put("肉鸡停药期料","Broiler Withdrawal (5wks-mkt)");
        //translateMap.put("种鸡育雏料2","Breeder Starter 2 (3-6wks)");
        //translateMap.put("种鸡产蛋停药期料","Breeder Phase Withdrawal ( 7days before mkt)");
        //translateMap.put("种鸡产蛋料1","Breeder Phase 1 ( 25-45 wks)");
        //translateMap.put("种鸡产蛋料2","Breeder Phase 2 (45 wks onwards)");
        //translateMap.put("公鸡中期料","Male Grower (4-25wks)");
        //translateMap.put("种公鸡料","Breeder Male ( 25 wks onwards)");
        //translateMap.put("种公鸡停药期料","Breeder Male Withdrawal ( 7 days before mkt)");
        //translateMap.put("可销售废料","Salable waste ingredient");
        //translateMap.put("肉鸡后期料","Broiler Finisher (4-5 wks)");
        //translateMap.put("种鸡育成料","Breeder Grower (6wks-104d)");
        //translateMap.put("种鸡产前料","Breeder Pre-Breeder 105d-5%egg (25wks)");
        //translateMap.put("种鸡育雏料1","Breeder Starter 1 (0-3wks)");
        //translateMap.put("鸡维生素","Broiler Vitamin Premix");
        //translateMap.put("外购种蛋","Outsourced egg");
        //translateMap.put("鸡苗-损失","DOC-Loss");
        //translateMap.put("淘汰雏","Culled DOC");
        //translateMap.put("种蛋-小蛋","Egg-sall");
        //translateMap.put("种蛋-双黄蛋","Egg-Double yolked");
        //translateMap.put("种蛋-损失","Egg-Loss");
        //translateMap.put("种鸡-公鸡-死鸡","Breeder-Male-Dead");
        //translateMap.put("种鸡-公鸡-淘鸡","Breeder-Male-Culled");
        //translateMap.put("种鸡-公鸡-鉴别错误鸡","Breeder-Male-sexing false");
        //translateMap.put("种鸡-母鸡-死鸡","Breeder-Female-Dead");
        //translateMap.put("种鸡-母鸡-淘鸡","Breeder-Female-Culled");
        //translateMap.put("种鸡-母鸡-鉴别错误鸡","Breeder-Female-sexing false");
        //translateMap.put("肉鸡中期料","Broiler Grower (2-4wks)");
        //translateMap.put("鸡苗-无精蛋","DOC-Infertile egg");
        //translateMap.put("肉鸡育雏料","Broiler Starter (0-2wks)");
        //translateMap.put("可销售废料","Salable waste ingredient");

        /*translateMap.put("大麦","M&V 1");
        translateMap.put("先锋肠乐(.*)","M&V 2");
        translateMap.put("克斯特-硫氰酸红霉素-康地恩","M&V 3");
        translateMap.put("拜安15L－癸甲溴铵－拜耳","M&V 4");
        translateMap.put("康奇强10ml/支－林可壮观－康地恩","5");
        translateMap.put("安普霉素500g(.*)","M&V 6");
        translateMap.put("维可多－液体维生素－齐鲁动保","M&V 7");
        translateMap.put("全清-杜邦","M&V 8");
        translateMap.put("阿莫西林1000g－齐鲁","M&V 9");
        translateMap.put("惠克宁－硫酸头孢喹肟注射液－齐鲁动保","M&V 10");
        translateMap.put("派乐新－80%阿莫西林可溶性粉－默沙东","M&V 11");
        translateMap.put("盐酸林可－大观霉素可溶性粉（500g）－青岛康地恩","M&V 12");
        translateMap.put("新城疫活疫苗-南京天邦","M&V 13");
        translateMap.put("新支流三联苗-南京天邦","M&V 14");
        translateMap.put("新支二联活疫苗-南京天邦","M&V 15");
        translateMap.put("立克法(.*)","M&V 16");
        translateMap.put("新流二联-(.*)易邦","M&V 17");
        translateMap.put("新城疫－ND（LaSota株）-青岛易邦","M&V 18");
        translateMap.put("新支二联-(.*)易邦","M&V 19");
        translateMap.put("新威灵-ND（VG/GA）－梅里亚","M&V 20");
        translateMap.put("种鸡球虫疫苗（8价） 1000羽份－默沙东","M&V 21");
        translateMap.put("稀释液30ml-青岛易邦","M&V 22");
        translateMap.put("ND\\+IB\\(Ma5\\+Clone30\\)－2500羽－默沙东","M&V 23");
        translateMap.put("瑞特杀5000ml－瑞先农","M&V 24");
        translateMap.put("98%片碱","M&V 25");
        translateMap.put("瑞特杀5000ml－瑞先农","M&V 26");
        translateMap.put("98%片碱","M&V 27");
        translateMap.put("赛可新25kg－赛尔可","M&V 28");
        translateMap.put("农福5L－杜邦","M&V 29");
        translateMap.put("百球清100ml－托曲珠利－拜耳","M&V 30");
        translateMap.put("噻呋健1g－头孢噻呋钠－海正","M&V 31");
        translateMap.put("杆力能100g－氧氟沙星－赫福莱","M&V 32");
        translateMap.put("扶本康500g－氟苯尼考－诺华","M&V 33");
        translateMap.put("枝原净1000g－泰妙菌素－诺华","M&V 34");
        translateMap.put("囊胚宝－IBD 抗原抗体复合苗5000dos－诗华","M&V 35");
        translateMap.put("拜固舒1000g－复合预混剂－拜耳","M&V 36");
        translateMap.put("利好500g－磺胺间甲－拜耳","M&V 37");
        translateMap.put("百球清1000ml－托曲珠利－拜耳","M&V 38");
        translateMap.put("阿莫西林100g－齐鲁","M&V 39");
        translateMap.put("泰勇－替米考星-礼来","M&V 40");
        translateMap.put("拜诺欣1000ml－恩诺沙星－拜耳","M&V 41");
        translateMap.put("优力峰－氨苄西林－瑞普","M&V 42");
        translateMap.put("安普强100g－安普霉素－瑞普","M&V 43");
        translateMap.put("福欣100g－多烯环素－瑞普","M&V 44");
        translateMap.put("普瑞芬100ml－氟苯尼考－齐鲁","M&V 45");
        translateMap.put("卫可1kg－杜邦","M&V 46");
        translateMap.put("利高速壮100g－林可壮观－康地恩","M&V 47");
        translateMap.put("维克赞500g－OSR-维克","M&V 48");
        translateMap.put("益支通－替米考星－绿叶","M&V 49");
        translateMap.put("多烯环素100g－齐鲁","M&V 50");
        translateMap.put("禽必利－Reo（Killed）－默沙东","M&V 51");
        translateMap.put("挑战依可胖－阿苯达唑\\+伊维菌素－天津挑战","M&V 52");
        translateMap.put("多烯环素100g－诺华","M&V 53");
        translateMap.put("球苗助悬剂－正典","M&V 54");
        translateMap.put("乳亿如意-乳杆菌、粪球菌－潍坊康地恩","M&V 55");
        translateMap.put("替米考星100ml－冀中药业","M&V 56");
        translateMap.put("安劲亚红10kg-硫氰酸红霉素－瑞先农","M&V 57");
        translateMap.put("利高150g－大观林可霉素－辉瑞","M&V 58");
        translateMap.put("速服宁－复方磺胺嘧啶混悬液－维克","M&V 59");
        translateMap.put("爱胺补500ml－阿卡","M&V 60");
        translateMap.put("乐宜安－替米考星－齐鲁动保","M&V 61");
        translateMap.put("疫苗稀释液－康牧","M&V 62");
        translateMap.put("康欣乐－硫酸新霉素－青岛康地恩","M&V 63");
        translateMap.put("欣之林－阿莫西林－河北科星","M&V 64");
        translateMap.put("促纤维素溶解酶－河北科星","M&V 65");
        translateMap.put("硫酸新霉素溶液－天津生机","M&V 66");
        translateMap.put("5%氟苯咪唑预混剂（500g）-倍康","M&V 67");
        translateMap.put("新油宝－ND\\(Lasota 100ml killed\\)－诗华","M&V 68");
        translateMap.put("禽流感H5（Re－6\\＋Re－4 killed）－青岛易邦","M&V 69");
        translateMap.put("新支灵－ND\\+IB（Lasota\\＋B48）－梅里亚中国","M&V 70");
        translateMap.put("新倍灵－ND\\(Lasota\\)－梅里亚中国","M&V 71");
        translateMap.put("禽必利－Reo（S1133）－默沙东","M&V 72");
        translateMap.put("鸡毒支原体活疫苗（MG6/85株）－默沙东","M&V 73");
        translateMap.put("新支宝-ND\\+IB\\(Lasota\\+H120\\+w93\\+N79\\)-宝依特","M&V 74");
        translateMap.put("新流－H9 F株－梅里亚中国","M&V 75");
        translateMap.put("威支灵－ND\\+IB\\(VG/GA\\+H120\\)-梅里亚中国","M&V 76");
        translateMap.put("鸡传喉－CHP50株－海博莱","M&V 77");
        translateMap.put("百威保-ND\\(VG/GA\\)-梅里亚进口","M&V 78");
        translateMap.put("甘保安－IBD\\(LIBDV\\)-诗华","M&V 79");
        translateMap.put("脑痘保－AE\\+FP－梅里亚进口","M&V 80");
        translateMap.put("肾型传支 Ma5－默沙东","M&V 81");
        translateMap.put("禽流感H5（Re－6\\＋Re－4 killed）－大华农","M&V 82");
        translateMap.put("鸡传鼻—A\\+C－北里","M&V 83");
        translateMap.put("新油宝－ND\\(Lasota 500ml killed\\)－诗华","M&V 84");
        translateMap.put("免疫宝－免疫调节剂－诗华","M&V 85");
        translateMap.put("新必妥－Lasota\\+V4s—瑞普保定","M&V 86");
        translateMap.put("新支妥－Lasota\\+H120－瑞普保定","M&V 87");
        translateMap.put("球虫活疫苗\\( 4 strain\\)－正典","M&V 88");
        translateMap.put("爱乐啼－鸡传喉组织活疫苗－默沙东","M&V 89");
        translateMap.put("新流二联－ND\\+H9 killed －华都诗华","M&V 90");
        translateMap.put("新支减－ND\\+IB\\+EDS－诗华","M&V 91");
        translateMap.put("新支法呼－ND\\+IB\\+G\\+REO－默沙东","M&V 92");
        translateMap.put("新支保－ND\\+IB\\(Lasota\\+Mass\\/Conn\\)-梅里亚进口 2000羽\\/瓶","M&V 93");
        translateMap.put("百威保-ND\\(VG/GA\\)1000dos-梅里亚进口","M&V 94");
        translateMap.put("新支保-ND\\+IB（LaSota\\+Mass/Conn）1000羽/瓶－梅里亚进口","M&V 95");
        translateMap.put("畜必生（200ml）－新拜特","M&V 96");
        translateMap.put("龙消安（5KG）－龙沙","M&V 97");
        translateMap.put("瑞特杀500ml－瑞先农","M&V 98");
        translateMap.put("爱吉特－噻虫嗪-法国诗华","M&V 99");
        translateMap.put("安力500ml-瑞先农","M&V 100");
        translateMap.put("拜洁1L－苯扎溴铵－拜耳","M&V 101");
        translateMap.put("拜安5L－癸甲溴铵－拜耳","M&V 102");
        translateMap.put("速洁4L-天津挑战","M&V 103");
        translateMap.put("安灭杀5L-默沙东","M&V 104");
        translateMap.put("卫可5kg-杜邦","M&V 105");
        translateMap.put("烟熏菌毒净－华尔康","M&V 106");
        translateMap.put("农富-复合酚-青岛康地恩","M&V 107");
        translateMap.put("立克命","M&V 108");
        translateMap.put("生命源5L－复合预混饲料－康地恩","M&V 109");
        translateMap.put("百优保清－MG S6株－梅里亚进口","M&V 110");
        translateMap.put("派健－枯草芽孢杆菌等－维克","M&V 111");
        translateMap.put("囊胚宝－IBD 抗原抗体复合苗2000dos－诗华","M&V 112");
        translateMap.put("新流二联（H9 killed）－宝依特","M&V 113");
        translateMap.put("液体拜固舒-复合维生素-拜耳","M&V 114");*/
    }

    public void translate(){
        List<Map<String,Object>> list = baseDaoByHana.findResultHana(table_name,"SBO_AG_FARM");
        for(String key : translateMap.keySet()){
            String express = translateMap.get(key);
            translate(list,key,express);
        }
    }

    private void translate(List<Map<String,Object>> list,String key,String express){
        list.stream().forEach(map->{
            String beforeFarmName = map.get("ItemName") == null ? "":map.get("ItemName").toString();
            if(contains(beforeFarmName,key)){
                String afterFarmName = replace(beforeFarmName,express);
                System.out.println("before:"+beforeFarmName + "  after:"+afterFarmName);
                Map<String,Object> setMap = new HashMap<>();
                setMap.put("ItemName",afterFarmName);

                Map<String,Object> whereMap = new HashMap<>();
                whereMap.put("ItemCode",map.get("ItemCode"));
                //System.out.println(setMap);
                //System.out.println(whereMap);
                baseDaoByHana.update("SBO_AG_FARM",table_name,setMap,whereMap);
            }
        });
    }

    private String replace(String before,String express){
        String regex = "[{0-9}一二三四五六七八九十]+";
        Pattern p = Pattern. compile(regex);
        Matcher matcher = p.matcher(before);
        List<String> numbers = new ArrayList<>();
        while (matcher.find()) {
            if(StringUtils.isNumeric(matcher.group(0))){
                numbers.add(matcher.group(0));
            }else{
                numbers.add(replaceMap.get(matcher.group(0)));
            }
        }
        String afterFarmName = null;
        switch (numbers.size()){
            case 0:
                afterFarmName = express;
                break;
            case 1:
                afterFarmName = MessageFormat.format(express,numbers.get(0));
                break;
            case 2:
                afterFarmName = MessageFormat.format(express,numbers.get(0),numbers.get(1));
                break;
            default:
                afterFarmName = MessageFormat.format(express,numbers.get(0),numbers.get(1));
        }
        return afterFarmName;
    }

    private boolean contains(String value,String express){
        Pattern p = Pattern.compile(express);
        Matcher matcher = p.matcher(value);
        return matcher.find();
    }

    public static void main(String[] args) {
        TranslateOITM oprc = new TranslateOITM();
        oprc.translate();
        //Pullet青年鸡场1号第1栋
        //Pullet 3# House 1#
    }

}
