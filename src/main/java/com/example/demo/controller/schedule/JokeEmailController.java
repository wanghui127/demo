package com.example.demo.controller.schedule;

import com.example.demo.entity.joke.Joke;
import com.example.demo.service.joke.JokeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 每天定时向我的邮箱发送笑话（调用聚合数据的接口，随机的笑话）
 * 并将最新获取到的笑话，插入到数据库中
 */
@Component
public class JokeEmailController {

    @Autowired
    JokeService jokeService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    //配置您申请的KEY
    public static final String APPKEY = "d0c8c5450ab8804ba18d450ab2a13585";

    /**
     * 每天早上6点40执行
     */
    @Scheduled(cron = "0 40 7 ? * *")
    public  void sendJokeEmail() {
        String from  = "1360907366@qq.com";
        //本人
        String to = "1360907366@qq.com";
        this.sendJokeEmail(from,to);

        //wh
        String towh = "653003583@qq.com";
        //this.sendJokeEmail(from,towh);

        //wlp
        String towlp = "1341241436@qq.com";
        this.sendJokeEmail(from,towlp);
    }

    /**
     * 需要执行的
     */
    public  void sendJokeEmail(String from,String to) {

        //首先聚合数据接口取到最新笑话
        String result = null;
        //最新笑话
        //String url = "http://v.juhe.cn/joke/content/text.php";//请求接口地址
        //随机笑话
        String url = "http://v.juhe.cn/joke/randJoke.php";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("page", "");//当前页数,默认1
        params.put("pagesize", "10");//每次返回条数,默认1,最大20
        params.put("key", APPKEY);//您申请的key
        try {
            result = net(url, params, "GET");
            //System.err.println(result);//{"reason":"Success","result":{"data":[{...}]},"error_code":0}
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                //为最新笑话时
                //JSONArray jokes = JSONObject.fromObject(object.get("result")).getJSONArray("data");
                //随机取笑话时
                JSONArray jokes = object.getJSONArray("result");
                if (jokes.size() > 0) {
                    //初始化
                    Joke joke = null;
                    List<String> jokeList = new ArrayList<>();
                    for (int i = 0; i < jokes.size(); i++) {
                        JSONObject job = jokes.getJSONObject(i);
                        // 得到 每个对象中的属性值
                        joke = new Joke();
                        joke.setContent(job.getString("content"));

                        //将数组变成集合
                        jokeList.add(job.getString("content"));
                        //将笑话同步到数据库中
                        //jokeService.insertJoke(joke);
                    }
                    //返回结果到模板中，发送邮件
                    MimeMessage mimeMessage = mailSender.createMimeMessage();
                    //邮件正文内容
                    Context context = new Context();
                    //头部内容
                    context.setVariable("headContent", headContent());
                    //中间笑话内容
                    context.setVariable("content", jokeList);
                    //尾部内容
                    context.setVariable("endContent", endContent());
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                    helper.setFrom(from);
                    //本人
                    helper.setTo(to);
                    //subject标题内容
                    helper.setSubject(subjectContent());

                    //创建邮件正文
                    String emailContent = templateEngine.process("email/emailTemplate", context);
                    helper.setText(emailContent, true);
                    //随机取图片（从指定文件夹下）
                    FileSystemResource file = new FileSystemResource(new File(srcContent()));
                    //附件
                    helper.addAttachment("附件.jpg", file);
                    mailSender.send(mimeMessage);
                }
            } else {
                throw new RuntimeException("请求笑话接口异常，返回状态吗错误！！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("请求笑话接口异常，请求结果result错误！！！");
        }

    }


    /**
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return 网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params, String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET")) {
                strUrl = strUrl + "?" + urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.equals("GET")) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    //将map型转为请求参数型
    public static String urlencode(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    //subject标题
    public static String subjectContent() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(new Date()) + "的快乐，请接收";
    }


    //头部内容
    public static String headContent() {
        Random random = new Random();
        String[] headString = {
                "愿你年少足风流，一万星辰掬在手，三千清诗唱不休。愿你霜尘梦不朽，也有白月牵衣袖，也有春秋抚眉头。"
                , "I love three things in this world.Sun,moon and you. Sun for morning, moon for night, and you forever!"
                , "宝髻松松挽就，铅华淡淡妆成。红姻翠雾罩轻盈，飞絮游丝无定。相见争如不见，有情还似无情。笙歌散后酒微醒。深院月斜人静。"
                , "东风夜放花千树。更吹落、星如雨。宝马雕车香满路。凤箫声动，玉壶光转，一夜鱼龙舞。蛾儿雪柳黄金缕。笑语盈盈暗香去。众里寻他千百度。蓦然回首，那人却在，灯火阑珊处。"
                , "少年听雨歌楼上。红烛昏罗帐。壮年听雨客舟中。江阔云低、断雁叫西风。而今听雨僧庐下。鬓已星星也。悲欢离合总无情。一任阶前、点滴到天明。"
                , "长街长，烟花繁，你挑灯回看。短亭短，红尘辗，我把萧再叹。"
                , "山有木兮木有枝，心悦君兮君不知。"
                , "红笺小字，说尽平生意。 鸿雁在云鱼在水，惆怅此情难寄。斜阳独倚西楼，遥山恰对帘钩。人面不知何处，绿波依旧东流。"
                , "人生自是有情痴，此恨不关风与月。"
                , "这次我离开你，是风，是雨，是夜晚；你笑了笑，我摆一摆手，一条寂寞的路便展向两头了。"
                , "入我相思门，知我相思苦，长相思兮长相忆，短相思兮无穷极。"
        };
        return headString[random.nextInt(headString.length)];
    }

    //底部内容
    public static String endContent() {
        Random random = new Random();
        String[] endString = {
                "锦瑟无端五十弦，一弦一柱思华年。庄生晓梦迷蝴蝶，望帝春心托杜鹃。沧海月明珠有泪，蓝田日暖玉生烟。此情可待成追忆？只是当时已惘然。"
                , "我住长江头，君住长江尾。 日日思君不见君，共饮长江水。此水几时休，此恨何时已。只愿君心似我心，定不负相思意。"
                , "纤云弄巧，飞星传恨，银汉迢迢暗度。金风玉露一相逢，便胜却人间无数。柔情似水，佳期如梦，忍顾鹊桥归路。两情若是久长时，又岂在朝朝暮暮。"
                , "花自飘零水自流。一种相思，两处闲愁。 此情无计可消除，才下眉头，却上心头。"
                , "十年生死两茫茫。不思量，自难忘。千里孤坟、无处话凄凉。纵使相逢应不识、尘满面，鬓如霜。夜来幽梦忽还乡。小轩窗，正梳妆。相顾无言，惟有泪千行，料得年年断肠处，明月夜，短松冈。"
                , "人间所事堪惆怅，莫向横塘问旧游。"
                , "一生一代一双人，争教两处销魂。相思相望不相亲，天为谁春。"
                , "其形也，翩若惊鸿，婉若游龙。荣曜秋菊，华茂春松。髣髴兮若轻云之蔽月，飘飖兮若流风之回雪。"
                , "人生若只如初见，何事秋风悲画扇。等闲变却故人心，却道故人心易变。骊山语罢清宵半，泪雨霖铃终不怨。何如薄幸锦衣郎，比翼连枝当日愿。"
        };
        return endString[random.nextInt(endString.length)];
    }

    /**
     * 获取根目录下所有图片文件，随机获取一张
     *
     * @param
     */
    public static String srcContent() {
        Random random = new Random();

        //获取项目路径
        String path = getSrcPath();
        //获取该文件夹下所有文件
        String[] files = getFile(path);
        return files[random.nextInt(files.length)];
    }

    private static String[] getFile(String path) {
        // get file list where the path has
        File file = new File(path);
        // get the folder list
        File[] array = file.listFiles();

        String[] files = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            if (array[i].isFile()) {
                // 只是名字
                //System.out.println("^^^^^" + array[i].getName());
                //名字加路径
                //System.out.println("#####" + array[i]);
                //名字加路径
                //System.out.println("*****" + array[i].getPath());
                files[i] = array[i].getPath();
            } else if (array[i].isDirectory()) {
                getFile(array[i].getPath());
            }
        }
        return  files;
    }

    /**
     * 获取项目路径
     *
     * @return
     */
    public static String getSrcPath() {
        String path = JokeEmailController.class.getResource("").toString();
        int m = path.indexOf("target");// file:/<----点位到file:后面的反斜杠
        path = path.substring(0, m).substring(6) + "src/main/resources/static/images/Ghostknife";
        return path;
    }

    public static void main(String[] args) {
        //System.err.println(subjectContent());
        //System.err.println(headContent());
        //System.err.println(endContent());
        System.err.println(srcContent());

    }
}
