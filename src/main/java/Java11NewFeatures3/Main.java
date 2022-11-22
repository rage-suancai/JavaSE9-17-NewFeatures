package Java11NewFeatures3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * 全新的HttpClient使用
 * 在Java9的时候其实就已经引入了全新的HttpClientAPI 用于取代之前比较老旧的HttpURLConnection类 新的API支持最新的HTTP2和WebSocket协议
 *                  HttpClient client = HttpClient.newHttpClient(); // 直接创建一个新的HttpClient
 *                  try {
 *                      // 现在我们只需要构造一个Http请求实体 就可以让客户端帮助我们发送出去了(实际上就跟浏览器访问类似)
 *                      HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://www.bilibili.com")).build();
 *                      // 现在我们就可以把请求头发送出去了 注意send方法后面还需要一个响应体处理器(内置了很多) 这里我们选择ofString直接响应实体转换为String字符串
 *                      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
 *                      // 来看看响应实体是什么吧
 *                      System.out.println(response.body());
 *                  } catch (IOException | InterruptedException | URISyntaxException e) {
 *                      e.printStackTrace();
 *                  }
 *
 * 利用全新的客户端 我们甚至可以轻松地做一个爬虫(仅供学习使用 别去做违法的事情 爬虫玩得好 牢房吃到饱) 比如现在我们想去批量下载某个网站的壁纸:
 *
 * 网站地址: https://pic.netbian.com/4kmeinv/ 我们随便点击一张壁纸 查看网站的URL格式
 *
 * 并且不同的壁纸似乎都是这样: https//pic.netbian.com/tupian/数字.html 好了差不多可以开始整活了:
 *                   HttpClient client = HttpClient.newHttpClient();
 *                   for (int i = 0; i < 5; i++) { // 先不要一次性获取太多 先来5个
 *                       try {
 *                           HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://pic.netbian.com/tupian/" + (30532 + i) + ".html")).build();
 *                           HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
 *                           System.out.println(response.body()); // 这里打印一下看看网页
 *                       } catch (IOException | InterruptedException | URISyntaxException e) {
 *                           e.printStackTrace();
 *                       }
 *                   }
 *
 * 可以看到 最后控制台成功获取到这些图片的网站页面了:
 *                  <!doctype html>
 *                  <html>
 *                  <head>
 *                  <meta charset="gbk" />
 *                  <title>�Ӽ� ɳ�� �崿 ��Ů ���� ��ɫ���� 4k ���� ��ֽ_�˰�ͼ��pic.netbian.com</title>
 *                  <meta name="keywords" content="4k,4k��ֽ,4k�����ֽ,4k�����ֽ,5k��ֽ,8k��ֽ">
 *                  <meta name="description" content="�˰�ͼ�����������Ӽ� ɳ�� �崿 ��Ů ���� ��ɫ���� 4k ���� ��ֽ,��������ҵ���Ҫ�ı�ֽ��">
 *                  <link href="/static/css/style.css" rel="stylesheet" type="text/css" />
 *                  <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
 *                  <script type="text/javascript" src="/static/js/jquery.min.js"></script>
 *                  <script type="text/javascript" src="/static/js/common.js"></script>
 *
 * 接着我们需要来观察一下网站的HTML具体怎么写的 把图片的地址提取出来:
 *                  HttpClient client = HttpClient.newHttpClient();
 *                  for (int i = 0; i < 5; i++) {
 *                      try {
 *                          HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://pic.netbian.com/tupian/" + (30532 + i) + ".html")).build();
 *                          HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
 *                          String html = response.body();
 *                          String prefix = "<a href=\"\" id=\"img\"><img src=\""; // 先找好我们要截取的前面一段 作为前缀去匹配位置
 *                          String suffix = "\" data-pic="; // 再找好我们要截取的屁股后面紧接着的位置 作为后缀去匹配位置
 *                          // 直接定位 然后前后截取 得到最后终的图片地址
 *                          html = html.substring(html.indexOf(prefix) + prefix.length());
 *                          html = html.substring(0, html.indexOf(suffix));
 *                          System.out.println(html); // 最后终的图片地址就有了
 *                      } catch (IOException | InterruptedException | URISyntaxException e) {
 *                          e.printStackTrace();
 *                      }
 *                  }
 *
 * 好了 现在图片地址也可以批量拿到了 直接获取这些图片然后保存到本地吧:
 *                  HttpClient client = HttpClient.newHttpClient();
 *                  for (int i = 0; i < 5; i++) {
 *                      try {
 *                          // 创建请求 把图片取到
 *                          HttpRequest imageRequest = HttpRequest.newBuilder().uri(new URI("https://pic.netbian.com/tupian/" + (30532 + i) + ".html")).build();
 *                          // 这里以输入流的方式获取 不过貌似可以直接下载文件 各位小伙伴可以单独试试看
 *                          HttpResponse<InputStream> imageResponse = client.send(imageRequest, HttpResponse.BodyHandlers.ofInputStream());
 *                          // 拿到输入流和文件输出流
 *                          InputStream imageInput = imageResponse.body();
 *                          FileOutputStream stream = new FileOutputStream("/" + i + ".jpg"); // 一会要保存的格式
 *                          try (stream; imageInput) { // 直接把要close的变量放进来就行 简洁一些了
 *                              int size; // 下面具体保存过程的不用多说了吧
 *                              byte[] data = new byte[1024];
 *                              while ((size = imageInput.read(data)) > 0) {
 *                                  stream.write(data, 0, size);
 *                              }
 *                          }
 *                      } catch (IOException | InterruptedException | URISyntaxException e) {
 *                          e.printStackTrace();
 *                      }
 *                  }
 *
 * 我们现在来看看效果吧 图片成功保存到本地了
 *
 * 当然 这仅仅是比较简单的爬虫 不过我们的最终目的还是希望各位能够学会使用新的HttpClientAPI
 */
public class Main {

    static void test1() {

        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://www.bilibili.com")).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

    static void test2() {

        HttpClient client = HttpClient.newHttpClient();
        for (int i = 0; i < 5; i++) {
            try {
                HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://pic.netbian.com/tupian/" + (30532 + i) + ".html")).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println(response.body());
            } catch (IOException | InterruptedException | URISyntaxException e) {
                e.printStackTrace();
            }
        }

    }

    static void test3() {

        HttpClient client = HttpClient.newHttpClient();
        for (int i = 0; i < 5; i++) {
            try {
                HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://pic.netbian.com/tupian/" + (30532 + i) + ".html")).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String html = response.body();
                String prefix = "<a href=\"\" id=\"img\"><img src=\"";
                String suffix = "\" data-pic=";
                html = html.substring(html.indexOf(prefix) + prefix.length());
                html = html.substring(0, html.indexOf(suffix));
                System.out.println(html);
            } catch (IOException | InterruptedException | URISyntaxException e) {
                e.printStackTrace();
            }
        }

    }

    static void test4() throws IOException, InterruptedException, URISyntaxException {

        HttpClient client = HttpClient.newHttpClient();
        for (int i = 0; i < 5; i++) {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://pic.netbian.com/tupian/" + (30532 + i) + ".html")).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String html = response.body();
            String prefix = "ca href=\"\" id=\"img\"><img src=\"";
            String suffix = "\" data-pic=";
            html = html.substring(html.indexOf(prefix) + prefix.length());
            html = html.substring(0, html.indexOf(suffix));
            //System.out.println("下载: " + html);

            HttpRequest imageRequest = HttpRequest.newBuilder().uri(new URI("https://pic.netbian.com/tupian/" + (30532 + i) + ".html")).build();
            HttpResponse<InputStream> imageResponse = client.send(imageRequest, HttpResponse.BodyHandlers.ofInputStream());
            InputStream imageInput = imageResponse.body();
            FileOutputStream stream = new FileOutputStream("D:/back-end learning/java-exercise/JavaSE/javaSE-yxs/javaSE9-17-NewFeatures/img/" + i + ".jpg");
            try (stream; imageInput) {
                int size;
                byte[] data = new byte[1024];
                while ((size = imageInput.read(data)) > 0) {
                    stream.write(data, 0, size);
                }
            }
        }

    }

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        //test1();
        //test2();
        //test3();
        test4();
    }

}
