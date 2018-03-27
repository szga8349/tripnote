package com.lenovo.spider.interfaces;

import com.lenovo.spider.common.Config;
import com.lenovo.spider.util.LogUtil;
import com.lenovo.spider.util.UrlUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Hbase上传接口
 *
 * @author：涂有
 * @date 2017年12月8日 下午5:15:04
 */
public class HBaseInterface {

    public static Configuration conf;
    public static Connection connection;
    private static Logger logger = LogUtil.getLogger("time");


    static {
        conf = HBaseConfiguration.create();
        String hbaseMaster = Config.get("hbaseMaster");
        if (hbaseMaster != null) {
            conf.set("hbase.master", hbaseMaster);
            String[] ipAndPort = hbaseMaster.split(":");
            conf.set("hbase.zookeeper.quorum", ipAndPort[0]);
            conf.set("hbase.zookeeper.property.clientPort", "2181");
            try {
                connection = ConnectionFactory.createConnection(conf);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 上传原始网页
     *
     * @param pageText
     * @throws IOException
     * @author：涂有
     * @date 2017年12月8日 下午5:25:11
     */
    public static void uploadRawPage(String url, String pageText) throws IOException {
        long start = System.currentTimeMillis();
        Table table = connection.getTable(TableName.valueOf(Config.get("hbaseRawTextTableName")));
        Put put = new Put(DigestUtils.md5(url.getBytes()));
        put.addColumn(Bytes.toBytes("default"), Bytes.toBytes("text"), Bytes.toBytes(pageText));
        table.put(put);
        table.close();
        long end = System.currentTimeMillis();
        logger.info("上传原始网页所花时间：{}", end - start);
    }

    /**
     * 上传原始网页，根据ｓｉｔｅＭｄ５做区分
     *
     * @param pageText
     * @throws IOException
     * @author：刘涵
     * @date 2017年12月8日 下午5:25:11
     */
    public static void uploadRawPage(String site, String url, String pageText) throws IOException {
        long start = System.currentTimeMillis();
        TableName tableName = TableName.valueOf(Config.get("hbaseRawTextTableName") + "_" + site);
        Table table = getOrCreateTable(tableName);
        Put put = new Put(DigestUtils.md5(url.getBytes()));
        put.addColumn(Bytes.toBytes("default"), Bytes.toBytes("text"), Bytes.toBytes(pageText));
        table.put(put);
        table.close();
        long end = System.currentTimeMillis();
        logger.info("上传原始网页所花时间：{}", end - start);
    }


    /**
     * 上传网页解析出来的数据
     *
     * @param data
     * @throws IOException
     * @author：涂有
     * @date 2017年12月8日 下午5:25:11
     */
    public static void uploadAnalyzeData(String url, String data) throws IOException {
        long start = System.currentTimeMillis();
        Table table = connection.getTable(TableName.valueOf(Config.get("hbaseAnalyzeJsonTableName")));
        Put put = new Put(DigestUtils.md5(url.getBytes()));
        put.addColumn(Bytes.toBytes("default"), Bytes.toBytes("json"), Bytes.toBytes(data));
        table.put(put);
        table.close();
        long end = System.currentTimeMillis();
        logger.info("上传解析数据所花时间：{}", end - start);
    }

    /**
     * 上传原始网页
     *
     * @param data
     * @throws IOException
     * @author：涂有
     * @date 2017年12月8日 下午5:25:11
     */
    public static void uploadAnalyzeData(String site, String url, String data) throws IOException {
        long start = System.currentTimeMillis();
        TableName tableName = TableName.valueOf(Config.get("hbaseAnalyzeJsonTableName") + "_" + site);
        Table table = getOrCreateTable(tableName);
        Put put = new Put(DigestUtils.md5(url.getBytes()));
        put.addColumn(Bytes.toBytes("default"), Bytes.toBytes("json"), Bytes.toBytes(data));
        table.put(put);
        table.close();
        long end = System.currentTimeMillis();
        logger.info("上传解析数据所花时间：{}", end - start);
    }

    @SuppressWarnings("deprecation")
    public static void scanPrint(String tableName, String family, String col) {
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            Scan scan = new Scan();
            scan.addColumn(Bytes.toBytes(family), Bytes.toBytes(col));
            scan.setBatch(100);
            scan.setMaxResultSize(Integer.MAX_VALUE);
            ResultScanner scanner = table.getScanner(scan);
            Result result = null;
            while ((result = scanner.next()) != null) {
                List<Cell> columnCells = result.getColumnCells(Bytes.toBytes(family), Bytes.toBytes(col));
                for (Cell c : columnCells) {
                    String value = new String(c.getValue());
                    System.out.println(value);
                }
            }
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据配置的前缀及url获取hbase表名
     *
     * @param url
     * @param prefix
     * @return
     */
    public static TableName getTableName(String url, String prefix) {
        String prefixStr = Config.get(prefix);
        String domain = UrlUtil.getMd5Domain(url);
        return Objects.isNull(domain) ? TableName.valueOf(prefixStr) : TableName.valueOf(prefix + "_" + domain);
    }

    public static void close() {
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Table getOrCreateTable(TableName tableName) throws IOException {
        Admin admin = connection.getAdmin();
        if (!admin.tableExists(tableName)) {
            HTableDescriptor htd = new HTableDescriptor(tableName);
            htd.addFamily(new HColumnDescriptor("default"));
            admin.createTable(htd);
        }
        admin.close();
        return connection.getTable(tableName);
    }

    public static void main(String[] args) throws IOException {
        uploadAnalyzeData(UrlUtil.getDomainSubject("http://xueqiu.com/"), "http://xueqiu.com/xxx", "{\"name\": \"a\"}");
    }
}
