package me.fengorz.leetcode.concurrency.web_crawler_multithreaded;
//给你一个初始地址 startUrl 和一个 HTML 解析器接口 HtmlParser，请你实现一个 多线程的网页爬虫，用于获取与 startUrl 有 相
//同主机名 的所有链接。 
//
// 以 任意 顺序返回爬虫获取的路径。 
//
// 爬虫应该遵循： 
//
// 
// 从 startUrl 开始 
// 调用 HtmlParser.getUrls(url) 从指定网页路径获得的所有路径。 
// 不要抓取相同的链接两次。 
// 仅浏览与 startUrl 相同主机名 的链接。 
// 
//
// 
//
// 如上图所示，主机名是 example.org 。简单起见，你可以假设所有链接都采用 http 协议，并且没有指定 端口号。举个例子，链接 http://l
//eetcode.com/problems 和链接 http://leetcode.com/contest 属于同一个 主机名， 而 http://example
//.org/test 与 http://example.com/abc 并不属于同一个 主机名。 
//
// HtmlParser 的接口定义如下： 
//
// 
//interface HtmlParser {
//  // Return a list of all urls from a webpage of given url.
//  // This is a blocking call, that means it will do HTTP request and return wh
//en this request is finished.
//  public List<String> getUrls(String url);
//} 
//
// 注意一点，getUrls(String url) 模拟执行一个HTTP的请求。 你可以将它当做一个阻塞式的方法，直到请求结束。 getUrls(Strin
//g url) 保证会在 15ms 内返回所有的路径。 单线程的方案会超过时间限制，你能用多线程方案做的更好吗？ 
//
// 对于问题所需的功能，下面提供了两个例子。为了方便自定义测试，你可以声明三个变量 urls，edges 和 startUrl。但要注意你只能在代码中访问 s
//tartUrl，并不能直接访问 urls 和 edges。 
//
// 
//
// 拓展问题： 
//
// 
// 假设我们要要抓取 10000 个节点和 10 亿个路径。并且在每个节点部署相同的的软件。软件可以发现所有的节点。我们必须尽可能减少机器之间的通讯，并确保每
//个节点负载均衡。你将如何设计这个网页爬虫？ 
// 如果有一个节点发生故障不工作该怎么办？ 
// 如何确认爬虫任务已经完成？ 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：
//urls = [
//  "http://news.yahoo.com",
//  "http://news.yahoo.com/news",
//  "http://news.yahoo.com/news/topics/",
//  "http://news.google.com",
//  "http://news.yahoo.com/us"
//]
//edges = [[2,0],[2,1],[3,2],[3,1],[0,4]]
//startUrl = "http://news.yahoo.com/news/topics/"
//输出：[
//  "http://news.yahoo.com",
//  "http://news.yahoo.com/news",
//  "http://news.yahoo.com/news/topics/",
//  "http://news.yahoo.com/us"
//]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：
//urls = [
//  "http://news.yahoo.com",
//  "http://news.yahoo.com/news",
//  "http://news.yahoo.com/news/topics/",
//  "http://news.google.com"
//]
//edges = [[0,2],[2,1],[3,2],[3,1],[3,0]]
//startUrl = "http://news.google.com"
//输出：["http://news.google.com"]
//解释：startUrl 链接与其他页面不共享一个主机名。 
//
// 
//
// 提示： 
//
// 
// 1 <= urls.length <= 1000 
// 1 <= urls[i].length <= 300 
// startUrl 是 urls 中的一个。 
// 主机名的长度必须为 1 到 63 个字符（包括点 . 在内），只能包含从 “a” 到 “z” 的 ASCII 字母和 “0” 到 “9” 的数字，以及中划
//线 “-”。 
// 主机名开头和结尾不能是中划线 “-”。 
// 参考资料：https://en.wikipedia.org/wiki/Hostname#Restrictions_on_valid_hostnames 
// 你可以假设路径都是不重复的。 
// 
// Related Topics 深度优先搜索 广度优先搜索 
// 👍 14 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface HtmlParser {
 * public List<String> getUrls(String url) {}
 * }
 */
public class Solution {

    private Set<String> syncSet;
    private Queue<String> blockingQueue;
    private ExecutorService service;
    // 计数器，当递减为0时，代表任务完成
    private AtomicInteger counter;
    private HtmlParser parser;
    private String host;

    public Solution() {
        this.syncSet = Collections.synchronizedSet(new HashSet<>());
        this.blockingQueue = new LinkedBlockingQueue<>();
        this.service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 40);
        // this.service = Executors.newCachedThreadPool();
        // 计数器，当递减为0时，代表任务完成
        this.counter = new AtomicInteger(1);
    }

    /**
     * 要解决的问题有：
     * ---- 问题A：每次拿到url怎么确保线程安全的情况下，尽可能快判断是否连接已经被爬虫了?
     * -------- 维护一个阻塞队列，每次判断syncSet里面还没有的url就扔进阻塞队列
     * ---- 问题B：怎么尽可能通过多线程安全得去竞争调用getUrls？
     * -------- 通过线程池去竞争，线程池大小由机器的器性能决定
     * ---- 问题C：怎么确定所有url都被爬虫过？
     * -------- 用LockSupport.parkNanos(1L);和AtomicInteger来解决
     * <p>
     * 官方判题貌似用了ExecutorService 或者 HashSet会超时。。。
     *
     * @param startUrl
     * @param htmlParser
     * @return
     */
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        parser = htmlParser;
        syncSet.add(startUrl);
        blockingQueue.add(startUrl);

        host = startUrl.substring(7);
        final String HTTP_HEAD = "http://";
        int i = host.indexOf("/");
        if (i > -1) {
            host = HTTP_HEAD + host.substring(0, i);
        } else {
            host = HTTP_HEAD + host;
        }

        while (0 < counter.get()) {
            service.submit(task);
            LockSupport.parkNanos(1L);
        }

        return new ArrayList<>(syncSet);
    }

    private Runnable task = () -> {
        List<String> urls = parser.getUrls(blockingQueue.poll());
        if (!urls.isEmpty()) {
            urls.forEach(url -> {
                // 解决问题A
                if (url.startsWith(host) && !syncSet.contains(url)) {
                    blockingQueue.add(url);
                    syncSet.add(url);
                    counter.incrementAndGet();
                }
            });
        }
        // 解决问题C，阻塞队列每弹出一次，计数器就减1
        counter.decrementAndGet();
    };

    // TODO ZSF 拓展问题1
    // TODO ZSF 拓展问题2
    // TODO ZSF 拓展问题3

    private interface HtmlParser {
        List<String> getUrls(String url);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
