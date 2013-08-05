package us.codecraft.webmagic.pipeline;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

import java.io.File;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-8-5 <br>
 * Time: 下午2:11 <br>
 */
public class LucenePipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        try {

        } catch (Exception e) {

        }
    }

    public static void main(String[] args) throws Exception {
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);
//        Directory directory = new RAMDirectory();
        // To store an index on disk, use this instead:
        Directory directory = FSDirectory.open(new File("/data/webmagic/www.guoxue123.cn/"));
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_44, analyzer);
        IndexWriter iwriter = new IndexWriter(directory, config);
        Document doc = new Document();
//        String text = "This is the text to be indexed.";
//        doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
//        iwriter.addDocument(doc);
        iwriter.close();

        // Now search the index:
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser(Version.LUCENE_44, "fieldname", analyzer);
        Query query = parser.parse("经典");
        ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
        // Iterate through the results:
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = isearcher.doc(hits[i].doc);
            System.out.println(hitDoc);
        }
        ireader.close();
        directory.close();
    }
}
